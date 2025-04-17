package org.hae.yl.controller.API.staff;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.hae.yl.entity.Discussion;
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
@RequestMapping("/api/staff/user")
public class ApiStaff_UserController {

    @Resource
    private UserFacade userFacade;

    /**
     * 获取当前用户信息
     * @param request
     * @return
     */
    @GetMapping("/getUserInfo")
    public User getUserInfo(HttpServletRequest request){
        return userFacade.getUserInfo(request);
    }

    /**
     * 更新用户个人资料
     * @return
     */
    @PostMapping("/Modify_UserInformation")
    public void Modify_UserInformation(HttpServletRequest request ,@RequestBody Userparameter requestBody){
        userFacade.Modify_UserInformation(request,requestBody);
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
     * 分页查询接口
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
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/RolequeryByPage")
    public PageInfo<Role> RolequeryByPage(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize){
        return userFacade.RolequeryByPage(pageNum, pageSize);
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

    //根据 Id 查询
    @PostMapping("/SelectById")
    public User SelectById(int id){
        return userFacade.SelectById(id);
    }

    //根据 Id 删除
    @PostMapping("/Delete")
    public void Delete(int id){
        userFacade.Delete(id);
    }

    //根据 Id 批量删除
    @PostMapping("/DeleteBybatch")
    public void DeleteBybatch(List<Integer> ids){
        userFacade.DeleteBybatch(ids);
    }
}
