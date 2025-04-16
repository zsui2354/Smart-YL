package org.hae.yl.controller.API.user;

import com.github.pagehelper.PageInfo;
import org.hae.yl.entity.Login_log;
import org.hae.yl.entity.Role;
import org.hae.yl.entity.User;
import org.hae.yl.facade.UserFacade;
import org.hae.yl.model.Userparameter;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/user/user")
public class ApiUser_UserController {

    @Resource
    private UserFacade userFacade;

    /**
     * User 分页查询
     * @return
     */
    public PageInfo<User> queryByPage(){
        return userFacade.queryByPage(1 , 10);
    }

    /**
     * 获取当前用户信息
     * @param request
     * @return
     */
    @GetMapping("/getuserinfo")
    public User getUserInfo(HttpServletRequest request){
        return userFacade.getUserInfo(request);
    }

    /**
     * 更新用户个人资料
     */
    @PostMapping("/setuserinfo")
    public void setUserInfo(HttpServletRequest request,@RequestBody Userparameter requestBody){
        userFacade.Modify_UserInformation( request , requestBody);
    }

    /**
     * 查询当前用户登录日志（登录时间、IP等）
     */
    @GetMapping("/queryloginlog")
    public List<Login_log> getLoginLogs(HttpServletRequest request){
        return userFacade.getLoginLogs(request);
    }

}
