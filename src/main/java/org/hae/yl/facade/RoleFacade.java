package org.hae.yl.facade;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.hae.yl.entity.Nursing_home;
import org.hae.yl.entity.Role;
import org.hae.yl.service.RoleService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Component
public class RoleFacade {

    @Resource
    private RoleService roleService;

    /**
     * 查询全部
     * @return
     */
    public List<Role> SelectAll(){
        return roleService.SelectAll();
    }

    /**
     * 根据Id 查询
     * @param id
     * @return
     */
    public Role       SelectById(int id){
        return roleService.SelectById(id);
    }

    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<Role> queryByPage(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize) {
        PageHelper.startPage(pageNum, pageSize);  // 启动分页
        List<Role> list = roleService.SelectAll();  // 原始查询
        return new PageInfo<>(list);  // 包装分页对象返回
    }
}
