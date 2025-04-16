package org.hae.yl.controller.API.user;

import com.github.pagehelper.PageInfo;
import org.hae.yl.entity.Activity;
import org.hae.yl.entity.Activity_enroll;
import org.hae.yl.entity.News;
import org.hae.yl.facade.NewsFacade;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("/newsquerypage")
    public PageInfo<News> NewsqueryByPage(){
        return newsFacade.NewsqueryByPage(1,10);
    }

    /**
     * 获取新闻详情
     */
    @GetMapping("/getNewsDetail")
    public String getNewsDetail(int id){
        return newsFacade.getNewsDetail(id);
    }

    /**
     * 分页查询 获取活动列表
     * @return
     */
    @GetMapping("/Activityquerypage")
    public PageInfo<Activity> ActivityqueryByPage(){
        return newsFacade.ActivityqueryByPage(1,10);
    }

    /**
     * 获取活动详情
     */
    @GetMapping("/getActivityinfo")
    public String ActivitySelectById(int id){
        return newsFacade.ActivitySelectById(id);
    }

    /**
     * 分页查询 活动报名
     * @return
     */
    @GetMapping("/activity_enrollquerypage")
    public PageInfo<Activity_enroll> Activity_enrollqueryByPage(){
        return newsFacade.Activity_enrollqueryByPage(1,10);
    }

    /**
     * 用户报名参加活动
     */
    @PostMapping("/activity_enrollInsert")
    public void activityEnrollInsert(Activity_enroll activity_enroll){
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
