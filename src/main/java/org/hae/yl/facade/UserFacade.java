package org.hae.yl.facade;

import cn.hutool.core.util.ObjectUtil;
import com.auth0.jwt.JWT;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.hae.yl.Util.JwtUtil;
import org.hae.yl.common.Constants;
import org.hae.yl.common.Enums.ResultCodeEnum;
import org.hae.yl.entity.*;
import org.hae.yl.exception.CustomException;
import org.hae.yl.model.LoginRequest;
import org.hae.yl.model.Userparameter;
import org.hae.yl.service.Login_logService;
import org.hae.yl.service.RoleService;
import org.hae.yl.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;


/**
 * 用户与权限管理模块 Facade 层
 * 负责聚合底层 Service 的业务逻辑
 */
@Component
public class UserFacade {

    private static final Logger log = LoggerFactory.getLogger(UserFacade.class);
    @Resource
    RoleService roleService;

    @Resource
    UserService userService;

    @Resource
    Login_logService login_logService;


    /**
     * 注册
     * @return
     */
    public ResponseEntity<?> register(@RequestBody Userparameter registerRequest ) {
        System.out.println("+ [业务层]  注册"+ registerRequest);
        //获取用户输入的注册信息
        String username  = registerRequest.getUsername();
        String password  = registerRequest.getPassword();
        String real_name = registerRequest.getReal_name();
        int role_id      = registerRequest.getRole_id();
        String phone     = registerRequest.getPhone();

        // 看看有没有 取名冲突
        User user_temp = userService.SelectByUsername(username);
        if (user_temp != null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
        User user_obj = new User();
        user_obj.setUsername(username);
        user_obj.setPassword(password);
        user_obj.setReal_name(real_name);
        user_obj.setRole_id(role_id);
        user_obj.setPhone(phone);


        System.out.println("user_obj   ："+user_obj.toString());

        userService.Insert(user_obj);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("SUCCESS");
    }

    /**
     * 找回密码
     * @return
     */
    // 这个功能 如果实现就需要 验证手机号，但是赛场没有网络没手机 ，没办法接收，所以这个问题暂时搁置


    /**
     * 获取客户端IP
     * @param request
     * @return
     */
    public static String getClientIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
            return ip.split(",")[0];  // 多个 IP，取第一个
        }

        ip = request.getHeader("Proxy-Client-IP");
        if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }

        ip = request.getHeader("WL-Proxy-Client-IP");
        if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }

        return request.getRemoteAddr();
    }


    /**
     * 登录授权
     * @return
     */
    public ResponseEntity<?> Loginauth(HttpServletRequest request ,@RequestBody LoginRequest loginRequest){
        System.out.println("+ [业务层]  登录授权"+ loginRequest);

        // 1. 获取用户输入的用户名和密码
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        // 2. 根据用户名查找用户
        User user_obj = userService.SelectByUsername(username);

        if (user_obj == null || !user_obj.getPassword().equals(password)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
        if(user_obj.getStatus() == 0){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("The account is banned");   //检测账户被封禁
        }

        System.out.println("user_obj   ："+user_obj.toString());

        // 3. 生成JWT token
        String token = JwtUtil.generateToken(
                user_obj.getUsername(),
                user_obj.getPassword(),
                user_obj.getRole_id()
        );

        /**
         * 在这里写 登录日志的添加数据 ， user_id , ip_address , login_time , user_agent
         */
        Login_log Lg = new Login_log();
        Lg.setUser_id(user_obj.getId());
        Lg.setIp_address(getClientIpAddress(request));
        Lg.setUser_agent(request.getHeader("User-Agent"));
        login_logService.Insert(Lg);

        System.out.println("token: "+token);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    /**
     * 获取当前用户信息 (拦截器专用)
     * @param request
     * @param token
     * @return
     */
    public User getUserInfo(HttpServletRequest request,String token){
        System.out.println("Token from Header: " + token);      // 打印获取到的 token 值

        if (ObjectUtil.isEmpty(token)) {
            // 如果没拿到，从参数里再拿一次
            token = request.getParameter(Constants.TOKEN);
        }


        // 2. 开始执行认证
        if (ObjectUtil.isEmpty(token)) {
            System.out.println("[ERROR] Token is empty  "+  token);
            throw new CustomException(ResultCodeEnum.TOKEN_INVALID_ERROR);
        }
        System.out.println("token格式检查: "+token);

        String userInfo = JWT.decode(token).getClaim("userInfo").asString(); // 提取 userInfo
        if (userInfo == null) {
            System.out.println("userInfo claim is missing or null.");
        }

        String[] parts = userInfo.split("-");
        String userId = parts[0];  // userId
        String role = parts[1];     // role

//        String userRole = JWT.decode(token).getAudience().get(0);
//        String userId = userRole.split("-")[0];
//        String role = userRole.split("-")[1];

        System.out.println("解密后的 :" +userInfo);
        System.out.println("Id :"+userId);
        System.out.println("role :"+role);


        User user = null;
        // 通过 用户名 查找用户
        user = userService.SelectByUsername(userId);                        //根据用户名
        //user = (User) userService.SelectById(Integer.parseInt(userId));   //根据 IDD
        System.out.println("用户数据预览  :" + user.toString());
        return user;
    }

    /**
     * 获取当前用户信息
     * @param request
     * @return
     */
    public User getUserInfo(HttpServletRequest request){
        String token = request.getHeader(Constants.TOKEN); //从请求头获取 token
        System.out.println("Token from Header: " + token);      // 打印获取到的 token 值

        if (ObjectUtil.isEmpty(token)) {
            // 如果没拿到，从参数里再拿一次
            token = request.getParameter(Constants.TOKEN);

            if (ObjectUtil.isEmpty(token)) {// 开始执行认证
                throw new CustomException(ResultCodeEnum.TOKEN_INVALID_ERROR);
            }
        }

//        // 2. 开始执行认证
//        if (ObjectUtil.isEmpty(token)) {
//            throw new CustomException(ResultCodeEnum.TOKEN_INVALID_ERROR);
//        }
        //String userRole = JWT.decode(token).getAudience().get(0);

//        String userRole = JWT.decode(token).getClaim("userInfo").asString();
//        System.out.println("JWT 解析出来的 ："+userRole);
//
//
//        String userId = userRole.split("-")[0];
//        String role =  userRole.split("-")[1];
//
//        System.out.println("解密后的 :" +userRole);
//        System.out.println("Id :"+userId);
//        System.out.println("role :"+role);
//
//        User user = null;
//        // 通过 userId 查找用户
//        user = (User) userService.SelectById(Integer.parseInt(userId));
//        System.out.println("用户数据预览  :" + user.toString());
//        return user;



        String userRole = JWT.decode(token).getClaim("userInfo").asString();
        System.out.println("JWT 解析出来的 ：" + userRole);

        User user = null;

// 检查userRole格式
        if (userRole != null && userRole.contains("-")) {
            String[] parts = userRole.split("-");
            if (parts.length == 2) {
                String userId = parts[0];
                String role = parts[1];

                System.out.println("解密后的 :" + userRole);
                System.out.println("Id :" + userId);
                System.out.println("role :" + role);

                try {
                    user = userService.SelectByUsername(userId);
                    //System.out.println("user.toString : "+user.toString());

                    if (user != null) {
                        System.out.println("用户数据预览  :" + user.toString());
                        return user;
                    } else {
                        System.out.println("用户未找到");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("无效的 userId: " + userId);
                }
            } else {
                System.out.println("userRole 格式错误");
            }
        } else {
            System.out.println("无效的 userRole");
        }

        return null;
    }

    /**
     * 更新当前登录用户个人资料
     * @return
     */
    public void Modify_UserInformation(HttpServletRequest request ,@RequestBody Userparameter requestBody){

        String        username  = requestBody.getUsername();
        String        password  = requestBody.getPassword();
        String        real_name = requestBody.getReal_name();
        String        phone     = requestBody.getPhone();
        String        avatar    = requestBody.getAvatar();
        int           role_id   = requestBody.getRole_id();
        int           status    = requestBody.getStatus();


       // User Guserid = getUserInfo(request);    //得到 token 验证并解析获取 用户对象
        User Guserid = getUserInfo(request);
        if (Guserid == null) {
            throw new IllegalArgumentException("User object cannot be null");
        }

        System.out.println("查看一下 信息预览    "+Guserid.toString());

        User user = new User();
        user.setId(Guserid.getId());
        user.setUsername(username);
        user.setPassword(password);
        user.setReal_name(real_name);
        user.setRole_id(role_id);
        user.setPhone(phone);
        user.setAvatar(avatar);
        user.setStatus(status);

        userService.Update(Guserid.getId(),user);  //根据 id 更新覆盖信息
    }

    /**
     * 根据 ID 更新用户资料
     */
    public void Change_UserInformation(@RequestBody User requestBody){
        User Ur = new User();
        Ur.setId(requestBody.getId());
        Ur.setUsername(requestBody.getUsername());
        Ur.setPassword(requestBody.getPassword());
        Ur.setReal_name(requestBody.getReal_name());
        Ur.setPhone(requestBody.getPhone());
        Ur.setAvatar(requestBody.getAvatar());
        Ur.setRole_id(requestBody.getRole_id());
        Ur.setStatus(requestBody.getStatus());

        userService.Update(Ur.getId(),Ur);
    }

    /**
     * 查询当前用户登录日志（登录时间、IP等）
     * @param request
     */
    public List<Login_log> getLoginLogs(HttpServletRequest request){
        User userinfo = getUserInfo(request);
        return login_logService.SelectUserIdLog(userinfo.getId());
    }

    /**
     * 分页查询
     * @return
     */
    public PageInfo<User> queryByPage(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize) {
        PageHelper.startPage(pageNum, pageSize);  // 启动分页
        List<User> list = userService.SelectAll();  // 原始查询
        return new PageInfo<>(list);  // 包装分页对象返回
    }

    //查询所有
    public List<User> SelectAll(){
        return userService.SelectAll();
    }

    //根据 Id 查询
    public User SelectById(int id){
        return userService.SelectById(id);
    }

    //根据用户名查询
    public User SelectByUsername(String username){
        return userService.SelectByUsername(username);
    }

    //根据 Id 修改
    public void Update(int id ,User user){
        userService.Update(id,user);
    }

    //根据 Id 删除
    public void Delete(int id){
        userService.Delete(id);
    }

    //根据 Id 批量删除
    public void DeleteBybatch(@Param("ids") List<Integer> ids){
        userService.DeleteBybatch(ids);
    }


    //###################################################################

    /**
     * 角色列表查询
     * @return
     */
    public List<Role> RoleSelectAll(){
        return roleService.SelectAll();
    }

    /**
     * 角色列表分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<Role> RolequeryByPage(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize) {
        PageHelper.startPage(pageNum, pageSize);  // 启动分页
        List<Role> list = roleService.SelectAll();  // 原始查询
        return new PageInfo<>(list);  // 包装分页对象返回
    }

    /**
     * 根据 Id 查询 角色对象
     * @param id
     * @return
     */
    public Role SelectRoleById(int id){
        return roleService.SelectById(id);
    }
}
