package org.hae.yl.controller.API.comadmin;

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
@RequestMapping("/api/comadmin/user")
public class ApiComadmin_UserController {

    @Resource
    private UserFacade userFacade;

    /**
     * 获取当前用户信息
     * @param request
     * @return
     */
    public User getUserInfo(HttpServletRequest request){
        return userFacade.getUserInfo(request);
    }

    /**
     * 更新用户个人资料
     * @return
     */
    public void Modify_UserInformation(HttpServletRequest request ,@RequestBody Userparameter requestBody){
        userFacade.Modify_UserInformation(request,requestBody);
    }

    /**
     * 查询当前用户登录日志（登录时间、IP等）
     * @param request
     */
    public List<Login_log> getLoginLogs(HttpServletRequest request){
        return userFacade.getLoginLogs(request);
    }

    /**
     * 分页查询接口 当前用户登录日志（登录时间、IP等）
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/list")
    public PageInfo<User> queryByPage(@RequestParam(defaultValue = "1") int pageNum,
                                      @RequestParam(defaultValue = "10") int pageSize) {
        return userFacade.queryByPage(pageNum, pageSize);
    }

    /**
     * 角色列表分页查询
     * @return
     */
    public PageInfo<Role> RolequeryByPage(){
        return userFacade.RolequeryByPage(1,10);
    }

    /**
     * 根据 Id 查询 角色对象
     * @param id
     * @return
     */
    public Role SelectRoleById(int id){
        return userFacade.SelectRoleById(id);
    }
}
