package org.hae.yl.controller.API.comadmin;

import com.github.pagehelper.PageInfo;
import org.hae.yl.entity.Discussion;
import org.hae.yl.entity.Login_log;
import org.hae.yl.entity.User;
import org.hae.yl.facade.AdminStatsFacade;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/api/staff/adminStats")
public class ApiComadmin_AdminStatsController {

    @Resource
    private AdminStatsFacade adminStatsFacade;

    /**
     * 获取用户列表（带筛选条件）
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @param username 用户名（可选）
     * @param roleId 角色ID（可选）
     * @param status 状态（可选）
     * @return 分页用户列表
     */
    public PageInfo<User> getUserList(int pageNum, int pageSize, String username, Integer roleId, Integer status){
        return adminStatsFacade.getUserList(pageNum, pageSize, username, roleId, status);
    }

    /**
     * 修改用户账号状态
     * @param userId 用户ID
     * @param status 新状态（1-启用，0-禁用）
     * @return 是否成功
     */
    public boolean updateUserStatus(Integer userId, Integer status){
        return adminStatsFacade.updateUserStatus(userId, status);
    }

    /**
     * 获取服务统计信息
     * @return 统计信息Map
     */
    public Map<String, Object> getServiceStatistics(){
        return adminStatsFacade.getServiceStatistics();
    }

    /**
     * 获取预约数据统计
     * @return 统计信息Map
     */
    public Map<String, Object> getAppointmentStatistics(){
        return adminStatsFacade.getAppointmentStatistics();
    }

    /**
     * 分页查询接口 登录日志
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/list")
    public PageInfo<Login_log> queryByPage(@RequestParam(defaultValue = "1") int pageNum,
                                           @RequestParam(defaultValue = "10") int pageSize) {
        return adminStatsFacade.queryByPage(pageNum, pageSize);
    }

}
