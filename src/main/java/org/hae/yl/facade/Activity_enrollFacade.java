package org.hae.yl.facade;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.hae.yl.entity.Activity;
import org.hae.yl.entity.Activity_enroll;
import org.hae.yl.service.ActivityService;
import org.hae.yl.service.Activity_enrollService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Component
public class Activity_enrollFacade {

    @Resource
    private Activity_enrollService activity_enrollService;

    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<Activity_enroll> queryByPage(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize) {
        PageHelper.startPage(pageNum, pageSize);  // 启动分页
        List<Activity_enroll> list = activity_enrollService.SelectAll();  // 原始查询
        return new PageInfo<>(list);  // 包装分页对象返回
    }

    /**
     * 查询所有
     */
    public List<Activity_enroll> SelectAll(){
        return activity_enrollService.SelectAll();
    }

    /**
     * 根据Id查询
     */
    public Activity_enroll SelectById(int id){
        return activity_enrollService.SelectById(id);
    }

    /**
     * 根据 Id 修改
     */
    public void Update(int id, Activity_enroll activity_enroll){
        activity_enrollService.Update(id, activity_enroll);
    }

    /**
     * 根据 Id 删除
     * 根据 Id 批量删除
     */
    public void DeleteById(int id){
        activity_enrollService.DeleteById(id);
    }

    public void DeleteByIdbatch(List<Integer> ids){
        activity_enrollService.DeleteByIdbatch(ids);
    }

    /**
     * 增加
     */
    public void Insert(Activity_enroll activity_enroll){
        activity_enrollService.Insert(activity_enroll);
    }
}
