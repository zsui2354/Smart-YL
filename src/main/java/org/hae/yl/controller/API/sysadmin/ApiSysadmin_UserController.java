package org.hae.yl.controller.API.sysadmin;

import com.github.pagehelper.PageInfo;
import org.hae.yl.entity.Login_log;
import org.hae.yl.entity.Role;
import org.hae.yl.entity.User;
import org.hae.yl.facade.UserFacade;
import org.hae.yl.model.Userparameter;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * @param request
     * @param token
     * @return
     */
    public User getUserInfo(HttpServletRequest request, String token){
        return userFacade.getUserInfo(request,token);
    }
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
     * 分页查询 用户列表
     * @return
     */
    public PageInfo<User> queryByPage(){
        return userFacade.queryByPage(1,10);
    }

    //查询所有用户
    public List<User> SelectAll(){
        return userFacade.SelectAll();
    }

    //根据 Id 查询
    public User SelectById(int id){
        return userFacade.SelectById(id);
    }

    //根据用户名查询
    public User SelectByUsername(String username){
        return userFacade.SelectByUsername(username);
    }

    //根据 Id 修改
    public void Update(int id ,User user){
        userFacade.Update(id,user);
    }

    //根据 Id 删除
    public void Delete(int id){
        userFacade.Delete(id);
    }

    //根据 Id 批量删除
    public void DeleteBybatch(List<Integer> ids){
        userFacade.DeleteBybatch(ids);
    }

    //添加
    public void Insert(User user){
        userFacade.Insert(user);
    }


    //####################################################################

    /**
     * 角色列表查询
     * @return
     */
    public List<Role> RoleSelectAll(){
        return userFacade.RoleSelectAll();
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
