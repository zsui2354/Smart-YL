package org.hae.yl.controller.API.sysadmin;

import com.github.pagehelper.PageInfo;
import org.hae.yl.entity.Role;
import org.hae.yl.facade.RoleFacade;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/sysadmin/role")
public class ApiSysadmin_RoleController {

    @Resource
    private RoleFacade roleFacade;

    /**
     * 角色列表分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/queryByPage")
    public PageInfo<Role> queryByPage(int pageNum, int pageSize){
        return roleFacade.queryByPage(pageNum,pageSize);
    }
}
