package org.hae.yl.controller.API.staff;

import com.github.pagehelper.PageInfo;
import org.hae.yl.entity.Activity;
import org.hae.yl.entity.Activity_enroll;
import org.hae.yl.entity.Discussion;
import org.hae.yl.entity.News;
import org.hae.yl.facade.NewsFacade;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/staff/news")
public class ApiStaff_NewsController {

    @Resource
    private NewsFacade newsFacade;

    /**
     * 分页查询 获取新闻列表
     *
     * @param pageNum
     * @param pageSize
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
    public String getNewsDetail(int id){
        return newsFacade.getNewsDetail(id);
    }

    /**
     * 分页查询 获取活动列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/Activitylist")
    public PageInfo<Activity> ActivityqueryByPage(@RequestParam(defaultValue = "1") int pageNum,
                                                  @RequestParam(defaultValue = "10") int pageSize) {
        return newsFacade.ActivityqueryByPage(pageNum, pageSize);
    }

    /**
     * 获取活动详情
     */
    @GetMapping("/ActivitySelectById")
    public String ActivitySelectById(int id){
        return newsFacade.ActivitySelectById(id);
    }

    /**
     * 分页查询 活动报名
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/Activity_enrollqueryByPage")
    public PageInfo<Activity_enroll> Activity_enrollqueryByPage (@RequestParam(defaultValue = "1") int pageNum,
                                                                 @RequestParam(defaultValue = "10") int pageSize) {
        return newsFacade.Activity_enrollqueryByPage(pageNum, pageSize);
    }

}
