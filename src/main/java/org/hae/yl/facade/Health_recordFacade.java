package org.hae.yl.facade;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.hae.yl.entity.Health_record;
import org.hae.yl.entity.Login_log;
import org.hae.yl.service.Health_recordService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Component
public class Health_recordFacade {

    @Resource
    private Health_recordService health_recordService;

    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<Health_record> queryByPage(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize) {
        PageHelper.startPage(pageNum, pageSize);  // 启动分页
        List<Health_record> list = health_recordService.SelectAll();  // 原始查询
        return new PageInfo<>(list);  // 包装分页对象返回
    }

    /**
     * 查询所有
     */
    public List<Health_record> SelectAll(){
        return health_recordService.SelectAll();
    }

    /**
     * 根据Id查询
     */
    public Health_record SelectById(int id){
        return health_recordService.SelectById(id);
    }

    /**
     * 根据 Id 修改
     */
    public void Update(int id, Health_record health_record){
        health_recordService.Update(id, health_record);
    }

    /**
     * 根据 Id 删除
     * 根据 Id 批量删除
     */
    public void DeleteById(int id){
        health_recordService.DeleteById(id);
    }

    public void DeleteByIdbatch(List<Integer> ids){
        health_recordService.DeleteByIdbatch(ids);
    }

    /**
     * 增加
     */
    public void Insert(Health_record health_record){
        health_recordService.Insert(health_record);
    }
}
