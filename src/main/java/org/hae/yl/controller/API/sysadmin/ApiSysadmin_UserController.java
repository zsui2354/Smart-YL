package org.hae.yl.controller.API.sysadmin;

import com.github.pagehelper.PageInfo;
import org.hae.yl.entity.Login_log;
import org.hae.yl.entity.Nursing_home;
import org.hae.yl.entity.Role;
import org.hae.yl.entity.User;
import org.hae.yl.facade.UserFacade;
import org.hae.yl.model.Userparameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/sysadmin/user")
public class ApiSysadmin_UserController {

    @Resource
    private UserFacade userFacade;


    /**
     * 获取当前用户信息
     * @param
     * @param token
     * @return
     */
    @GetMapping("/getUserInfoToken")
    public User getUserInfo(HttpServletRequest request,@RequestParam String token){
        System.out.println("token 内容 ：" + token);
        return userFacade.getUserInfo(request,token);
    }

    /**
     * 获取当前用户信息
     * @param request
     * @return
     */
    @GetMapping("/getUser")
    public User getUserInfoJWT(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        System.out.println("Controller 层 输出测试 token 内容：：："+token);
        return userFacade.getUserInfo(request, token);
    }


//    @GetMapping("/getUserInfo")
//    public User getUserInfo(HttpServletRequest request){
//        return userFacade.getUserInfo(request);
//    }

    /**
     * 更新当前用户个人资料
     * @return
     */
    @PostMapping("/Modify_UserInformation")
    public void Modify_UserInformation(HttpServletRequest request ,@RequestBody Userparameter requestBody){
        userFacade.Modify_UserInformation(request,requestBody);
    }


    /**
     * 根据 ID 更新用户资料
     */
    @PostMapping("/Change_UserInformation")
    public void Change_UserInformation(@RequestBody User requestBody){
        userFacade.Change_UserInformation(requestBody);
    }


    /**
     * 查询当前用户登录日志（登录时间、IP等）
     * @param request
     */
    @GetMapping("/getLoginLogs")
    public List<Login_log> getLoginLogs(HttpServletRequest request){
        return userFacade.getLoginLogs(request);
    }






    /**
     * 分页查询 用户列表
     * @return
     */
    @GetMapping("/list")
    public PageInfo<User> list(@RequestParam(defaultValue = "1") int pageNum,
                               @RequestParam(defaultValue = "10") int pageSize) {
        System.out.println(pageNum +":"+ pageSize);
        return userFacade.queryByPage(pageNum, pageSize);
    }

    //查询所有用户
    @GetMapping("/SelectAll")
    public List<User> SelectAll(){
        return userFacade.SelectAll();
    }

    //根据 Id 查询
    @GetMapping("/SelectById")
    public User SelectById(int id){
        return userFacade.SelectById(id);
    }

    //根据用户名查询
    @GetMapping("/SelectByUsername")
    public User SelectByUsername(String username){
        return userFacade.SelectByUsername(username);
    }

    //根据 Id 修改
    @PostMapping("/Update")
    public void Update(int id ,User user){
        userFacade.Update(id,user);
    }

//    //添加
//    @PostMapping("/Insert")
//    public void Insert(User user){
//        userFacade.Insert(user);
//    }


    //####################################################################

    /**
     * 角色列表查询
     * @return
     */
    @GetMapping("/RoleSelectAll")
    public List<Role> RoleSelectAll(){
        return userFacade.RoleSelectAll();
    }

    /**
     * 角色列表分页查询
     * @return
     */
    @GetMapping("/RolequeryByPage")
    public PageInfo<Role> RolequeryByPage(){
        return userFacade.RolequeryByPage(1,10);
    }

    /**
     * 根据 Id 查询 角色对象
     * @param id
     * @return
     */
    @GetMapping("/SelectRoleById")
    public Role SelectRoleById(int id){
        return userFacade.SelectRoleById(id);
    }
}
