package org.hae.yl.controller.API.sysadmin;

import com.github.pagehelper.PageInfo;
import org.hae.yl.entity.Activity;
import org.hae.yl.entity.Activity_enroll;
import org.hae.yl.entity.News;
import org.hae.yl.entity.Nursing_home;
import org.hae.yl.facade.NewsFacade;
import org.hae.yl.facade.SericeFacade;
import org.hae.yl.service.ActivityService;
import org.hae.yl.service.Activity_enrollService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("//api/sysadmin/news")
public class ApiSysadmin_NewsController {

    @Resource
    private NewsFacade newsFacade;


    /**
     * 分页查询接口 新闻列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/newslist")
    public PageInfo<News> newslist(@RequestParam(defaultValue = "1") int pageNum,
                               @RequestParam(defaultValue = "10") int pageSize) {
        return newsFacade.queryByPage(pageNum, pageSize);
    }

    /**
     * 获取新闻详情
     */
    public String getNewsDetail(int id){
        return newsFacade.getNewsDetail(id);
    }

    /**
     *  发布新闻（管理员）
     */
    public void Insert(News news){
        newsFacade.Insert(news);
    }

    /**
     *  根据 Id 删除新闻（管理员）
     */
    public void DeleteById(int id){
        newsFacade.DeleteById(id);
    }

    /**
     * 根据 Id 批量删除新闻（管理员）
     * @param ids
     */
    public void DeleteByIdbatch(List<Integer> ids){
        newsFacade.DeleteByIdbatch(ids);
    }

    /**
     * 发布活动信息（管理员）
     */
    public void ActivityInsert(Activity activity){
        newsFacade.ActivityInsert(activity);
    }

    /**
     * 分页查询接口 活动列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/list")
    public PageInfo<Activity>Activitylist(@RequestParam(defaultValue = "1") int pageNum,
                               @RequestParam(defaultValue = "10") int pageSize) {
        return newsFacade.ActivityqueryByPage(pageNum, pageSize);
    }

    /**
     * 获取活动详情
     */
    public String ActivitySelectById(int id){
        return newsFacade.ActivitySelectById(id);
    }

    /**
     * 分页查询接口 活动报名列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/list")
    public PageInfo<Activity_enroll> Activity_enrolllist(@RequestParam(defaultValue = "1") int pageNum,
                                                         @RequestParam(defaultValue = "10") int pageSize) {
        return newsFacade.Activity_enrollqueryByPage(pageNum, pageSize);
    }




}
