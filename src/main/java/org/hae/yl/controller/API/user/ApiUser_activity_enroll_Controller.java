package org.hae.yl.controller.API.user;

import com.github.pagehelper.PageInfo;
import org.hae.yl.entity.Activity_enroll;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/user/activity_enroll")
public class ApiUser_activity_enroll_Controller {

    @Resource
    private Activity_enrollFacade facade;

    /**
     * 分页查询报名
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/list")
    public PageInfo<Activity_enroll> list(@RequestParam(defaultValue = "1") int pageNum,
                                          @RequestParam(defaultValue = "10") int pageSize) {
        return facade.queryByPage(pageNum, pageSize);
    }

    /**
     *  添加报名活动
     */
    public void Insert(Activity_enroll activity_enroll) {
        facade.Insert(activity_enroll);
    }
}
