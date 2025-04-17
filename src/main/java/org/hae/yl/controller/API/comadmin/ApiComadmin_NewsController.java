package org.hae.yl.controller.API.comadmin;

import com.github.pagehelper.PageInfo;
import org.hae.yl.entity.Activity;
import org.hae.yl.entity.News;
import org.hae.yl.entity.Nursing_home;
import org.hae.yl.facade.NewsFacade;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/comadmin/")
public class ApiComadmin_NewsController {

    @Resource
    private NewsFacade newsFacade;

    /**
     * 分页查询接口 获取新闻列表
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
     * 分页查询 获取活动列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/list")
    public PageInfo<News> huodongqueryByPage(@RequestParam(defaultValue = "1") int pageNum,
                                              @RequestParam(defaultValue = "10") int pageSize) {
        return newsFacade.queryByPage(pageNum, pageSize);
    }

    /**
     * 获取新闻详情
     */
    public String NewsgetNewsDetail(int id){
        return newsFacade.getNewsDetail(id);
    }

    /**
     *  发布新闻（管理员）
     */
    public void NewsInsert(News news){
        newsFacade.Insert(news);
    }
}
