package org.hae.yl.controller.API.user;

import com.github.pagehelper.PageInfo;
import org.hae.yl.entity.Activity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/user/activity")
public class ApiUser_activity_Controller {

    @Resource
    private ActivityFacade facade;

    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/list")
    public PageInfo<Activity> list(@RequestParam(defaultValue = "1") int pageNum,
                                   @RequestParam(defaultValue = "10") int pageSize) {
        return facade.queryByPage(pageNum, pageSize);
    }
}
