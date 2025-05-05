package org.hae.yl.controller.API.user;

import com.github.pagehelper.PageInfo;
import org.hae.yl.entity.Activity;
import org.hae.yl.entity.Activity_enroll;
import org.hae.yl.entity.Announcement;
import org.hae.yl.entity.News;
import org.hae.yl.facade.NewsFacade;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/user/news")
public class ApiUser_NewsController {

    @Resource
    private NewsFacade newsFacade;

    /**
     * 分页查询 获取新闻列表
     * @return
     */
    @GetMapping("/list")
    public PageInfo<News> queryByPage(@RequestParam(defaultValue = "1") int pageNum,
                                              @RequestParam(defaultValue = "10") int pageSize) {
        return newsFacade.queryByPage(pageNum, pageSize);
    }

    /**
     * 获取新闻详情
     */
    @GetMapping("/getNewsDetail")
    public News getNewsDetail(int id){
        return newsFacade.getNewsDetail(id);
    }

    /**
     * 分页查询 获取活动列表
     * @return
     */
    @GetMapping("/Activityquerypage")
    public PageInfo<Activity> ActivityqueryByPage(@RequestParam(defaultValue = "1") int pageNum,
                                      @RequestParam(defaultValue = "10") int pageSize) {
        return newsFacade.ActivityqueryByPage(pageNum, pageSize);
    }

    /**
     * 获取活动详情
     */
    @GetMapping("/getActivityinfo")
    public Activity ActivitySelectById(int id){
        return newsFacade.ActivitySelectById(id);
    }

    /**
     * 分页查询 活动报名
     * @return
     */
    @GetMapping("/activity_enrollquerypage")
    public PageInfo<Activity_enroll> Activity_enrollqueryByPage(@RequestParam(defaultValue = "1") int pageNum,
                                                  @RequestParam(defaultValue = "10") int pageSize) {
        return newsFacade.Activity_enrollqueryByPage(pageNum, pageSize);
    }

    /**
     * 用户报名参加活动
     */
    @PostMapping("/activity_enrollInsert")
    public void activityEnrollInsert(@RequestBody Activity_enroll activity_enroll){
        newsFacade.activityEnrollInsert(activity_enroll);
    }

    /**
     * 用户取消活动报名
     */
    @PostMapping("/activity_enrollDeleteById")
    public void activityEnrollDeleteById(int id){
        newsFacade.activityEnrollDeleteById(id);
    }
}
