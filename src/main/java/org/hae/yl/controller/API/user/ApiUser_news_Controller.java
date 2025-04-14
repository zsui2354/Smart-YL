package org.hae.yl.controller.API.user;

import com.github.pagehelper.PageInfo;
import org.hae.yl.entity.News;
import org.hae.yl.facade.NewsFacade;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/user/news")
public class ApiUser_news_Controller {

    @Resource
    private NewsFacade facade;

    /**
     * 分页查询新闻
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/list")
    public PageInfo<News> list(@RequestParam(defaultValue = "1") int pageNum,
                               @RequestParam(defaultValue = "10") int pageSize) {
        return facade.queryByPage(pageNum, pageSize);
    }


    /**
     * 添加新闻
     */
    @PostMapping("/add")
    public void Insert(@RequestBody News news) {
        facade.Insert(news);
    }
}
