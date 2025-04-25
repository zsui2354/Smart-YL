package org.hae.yl.common.config;

import cn.hutool.core.util.ObjectUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.hae.yl.common.Constants;
import org.hae.yl.common.Enums.ResultCodeEnum;
import org.hae.yl.entity.User;
import org.hae.yl.entity.Role;
import org.hae.yl.exception.CustomException;
import org.hae.yl.facade.RoleFacade;
import org.hae.yl.facade.UserFacade;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.hae.yl.Util.JwtUtil.validateToken;

@Configuration
public class JwtInterceptor implements HandlerInterceptor {

    //private static final Logger log = LoggerFactory.getLogger(JwtInterceptor.class);        //创建一个 日志记录器

    @Resource
    private RoleFacade roleFacade;

    @Resource
    private UserFacade userfacade;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截器触发 ~~~~~~~~~~");

        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
            System.out.println("检测到 OPTION请求，放行");
            return true;
        }

        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
            System.out.println("OPTIONS请求，放行");
            return true;
        }

        String requestURI = request.getRequestURI();       // 正确获取 URI
        System.out.println("Request URI: " + requestURI);  // 打印请求路径，用于调试


        String token ="";
        if (request.getMethod().equals("OPTIONS")){
            return true;
        } else{
            token = request.getHeader(Constants.TOKEN); //从请求头获取 token
        }

       // String token = request.getHeader(Constants.TOKEN); //从请求头获取 token
        System.out.println("拦截器获取token: " + token);

        //验证 token 是否有效
        if(!validateToken(token)){
            throw new CustomException(ResultCodeEnum.PERMISSION_DENIED); // 没有权限
        }

        User user = userfacade.getUserInfo(request,token);   // 获取用户信息
        try{

            String role = "";
            switch (user.getRole_id()) {
                case 1: role = "user";
                    break;
                case 2: role = "comadmin";
                    break;
                case 3: role = "sysadmin";
                    break;
                case 4: role = "staff";
                    break;
            }
            if (user != null){// 如果用户存在，查询用户的角色
                Role roleEntity  = roleFacade.SelectById(user.getRole_id()); // 根据 user 表的 role_id 查询 Role 表 进行配对
                if (roleEntity  != null && !roleEntity.getName().equals(role)){
                    throw new CustomException(ResultCodeEnum.TOKEN_CHECK_ERROR);
                }
                switch (Integer.parseInt(roleEntity.getId())){
                    case 1: // 普通用户（老人、家属）
                        if (!request.getRequestURI().startsWith("/api/user")) {
                            throw new CustomException(ResultCodeEnum.PERMISSION_DENIED); // 没有权限
                        }
                        break;
                    case 2: // 社区管理员(常务管理，客服)
                        if (!request.getRequestURI().startsWith("/api/comadmin") && !request.getRequestURI().startsWith("/manager")) {
                            throw new CustomException(ResultCodeEnum.PERMISSION_DENIED); // 没有权限
                        }
                        break;
                    case 3: // 系统管理员
                        if (!request.getRequestURI().startsWith("/api/sysadmin")){
                            throw new CustomException(ResultCodeEnum.PERMISSION_DENIED); // 没有权限
                        }
                        break;
                    case 4: // 职工（护工，医生）
                        if (!request.getRequestURI().startsWith("/api/sstaff") && !request.getRequestURI().startsWith("/manager")) {
                            throw new CustomException(ResultCodeEnum.PERMISSION_DENIED); // 没有权限
                        }
                        break;
                    default:
                        throw new CustomException(ResultCodeEnum.PERMISSION_DENIED); // 没有权限
                }
            }
        }catch (Exception e){
            throw new CustomException(ResultCodeEnum.TOKEN_CHECK_ERROR);//抛出 token 检查错误
        }

        // 如果用户不存在
        if (ObjectUtil.isNull(user)) {
            throw new CustomException(ResultCodeEnum.USER_NOT_EXIST_ERROR);//抛出 用户不存在错误
        }
        try{
            // 用户密码加签验证 token
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
            jwtVerifier.verify(token); // 验证token
        }catch (JWTVerificationException e){
            throw new CustomException(ResultCodeEnum.TOKEN_CHECK_ERROR);//抛出 token 检查错误
        }
        return true;
    }
}
