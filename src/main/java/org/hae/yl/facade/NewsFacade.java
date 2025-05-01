package org.hae.yl.facade;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.hae.yl.entity.Activity;
import org.hae.yl.entity.Activity_enroll;
import org.hae.yl.entity.News;
import org.hae.yl.entity.Nursing_home;
import org.hae.yl.service.ActivityService;
import org.hae.yl.service.Activity_enrollService;
import org.hae.yl.service.NewsService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

/**
 * 公益活动与新闻动态 Facade 层
 * 负责聚合底层 Service 的业务逻辑
 */
@Component
public class NewsFacade {

    @Resource
    private NewsService newsService;

    @Resource
    private ActivityService activityService;

    @Resource
    private Activity_enrollService activityEnrollService; ;

    /**
     * 分页查询 获取新闻列表
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<News> queryByPage(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize) {
        PageHelper.startPage(pageNum, pageSize);  // 启动分页
        List<News> list = newsService.SelectAll();  // 原始查询
        return new PageInfo<>(list);  // 包装分页对象返回
    }

    /**
     * 获取新闻详情
     */
    public String getNewsDetail(int id) {
        News news = newsService.SelectById(id);
        return "<p>" + news.getContent() + "</p>";
    }

    /**
     *  发布新闻（管理员）
     */
    public void Insert(News news){
        newsService.Insert(news);
    }

    /**
     *  根据 Id 删除新闻（管理员）
     */
    public void DeleteById(int id){
        newsService.DeleteById(id);
    }

    /**
     * 根据 Id 批量删除新闻（管理员）
     * @param ids
     */
    public void DeleteByIdbatch(List<Integer> ids){
        newsService.DeleteByIdbatch(ids);
    }

    /**
     * 发布活动信息（管理员）
     */
    public void ActivityInsert(Activity activity){

        Activity Ay = new Activity();
        Ay.setTitle(activity.getTitle());
        Ay.setContent(activity.getContent());
        Ay.setPlace(activity.getPlace());
        Ay.setStart_time(activity.getStart_time());
        Ay.setEnd_time(activity.getEnd_time());
        Ay.setMax_participants(activity.getMax_participants());

        /**
         *
         *
         * {
         *     "title": "五一联欢",
         *     "content": "五一联欢，请来社区老人来一起",
         *     "place": "老头乐活动中心",
         *     "start_time": "2025-04-30 08:00:00",
         *     "end_time": "2025-05-10 00:00:00",
         *     "max_participants": 50
         * }
         *
         *
         *
         */
        activityService.Insert(Ay);
    }

    /**
     * 分页查询 获取活动列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<Activity> ActivityqueryByPage(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize) {
        PageHelper.startPage(pageNum, pageSize);  // 启动分页
        List<Activity> list = activityService.SelectAll();  // 原始查询
        return new PageInfo<>(list);  // 包装分页对象返回
    }

    /**
     * 获取活动详情
     */
    public Activity ActivitySelectById(int id){
        Activity activity = activityService.SelectById(id);
        return activity;
    }

    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<Activity_enroll> Activity_enrollqueryByPage(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize) {
        PageHelper.startPage(pageNum, pageSize);  // 启动分页
        List<Activity_enroll> list = activityEnrollService.SelectAll();  // 原始查询
        return new PageInfo<>(list);  // 包装分页对象返回
    }

    /**
     * 用户报名参加活动
     */
    public void activityEnrollInsert(@RequestBody Activity_enroll activity_enroll){

        Activity_enroll Al = new Activity_enroll();
        Al.setUser_id(activity_enroll.getUser_id());
        Al.setActivity_id(activity_enroll.getActivity_id());
        Al.setEnrolled_at(activity_enroll.getEnrolled_at());

        activityEnrollService.Insert(Al);
    }

    /**
     * 用户取消活动报名
     */
    public void activityEnrollDeleteById(int id){
        activityEnrollService.DeleteById(id);
    }

}
