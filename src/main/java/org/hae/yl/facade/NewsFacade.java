package org.hae.yl.facade;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.hae.yl.entity.News;
import org.hae.yl.entity.Nursing_home;
import org.hae.yl.service.NewsService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Component
public class NewsFacade {

    @Resource
    private NewsService newsService;

    /**
     * 分页查询
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
     * 查询所有
     */
    public List<News> SelectAll(){
        return newsService.SelectAll();
    }

    /**
     * 根据Id查询
     */
    public News SelectById(int id){
        return newsService.SelectById(id);
    }

    /**
     * 模糊查询
     */
    public List<News> SelectByLike(String like){
        return newsService.SelectByLike(like);
    }

    /**
     *  根据 Id 修改
     */
    public void Update(int id, News nursing_home){
        newsService.Update(id, nursing_home);
    }

    /**
     *  根据 Id 删除
     *  根据 Id 批量删除
     */
    public void DeleteById(int id){
        newsService.DeleteById(id);
    }

    public void DeleteByIdbatch(List<Integer> ids){
        newsService.DeleteByIdbatch(ids);
    }

    /**
     *  增加
     */
    public void Insert(News news){
        newsService.Insert(news);
    }
}
