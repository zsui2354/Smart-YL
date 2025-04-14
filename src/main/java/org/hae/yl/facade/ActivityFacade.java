package org.hae.yl.facade;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.hae.yl.entity.Activity;
import org.hae.yl.entity.Announcement;
import org.hae.yl.service.ActivityService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Component
public class ActivityFacade {

    @Resource
    private ActivityService activityService;

    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<Activity> queryByPage(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize) {
        PageHelper.startPage(pageNum, pageSize);  // 启动分页
        List<Activity> list = activityService.SelectAll();  // 原始查询
        return new PageInfo<>(list);  // 包装分页对象返回
    }

    /**
     * 查询所有
     */
    public List<Activity> SelectAll(){
        return activityService.SelectAll();
    }

    /**
     * 根据Id查询
     */
    public Activity SelectById(int id){
        return activityService.SelectById(id);
    }

    /**
     * 模糊查询
     */
    public List<Activity> SelectByLike(String like){
        return activityService.SelectByLike(like);
    }

    /**
     *  根据 Id 修改
     */
    public void Update(int id, Activity activity){
        activityService.Update(id, activity);
    }

    /**
     *  根据 Id 删除
     *  根据 Id 批量删除
     */
    public void DeleteById(int id){
        activityService.DeleteById(id);
    }

   public void DeleteByIdbatch(List<Integer> ids){
        activityService.DeleteByIdbatch(ids);
    }

    /**
     *  增加
     */
   public void Insert(Activity activity){
        activityService.Insert(activity);
    }
}
