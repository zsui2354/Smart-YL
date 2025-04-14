package org.hae.yl.facade;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.hae.yl.entity.Service_item;
import org.hae.yl.entity.User;
import org.hae.yl.service.Service_itemService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Component
public class Service_itemFacade {

    @Resource
    private Service_itemService service_itemService;

    /**
     * 分页查询
     * @return
     */
    public PageInfo<Service_item> queryByPage(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize) {
        PageHelper.startPage(pageNum, pageSize);  // 启动分页
        List<Service_item> list = service_itemService.SelectAll();  // 原始查询
        return new PageInfo<>(list);  // 包装分页对象返回
    }

    //查询全部
    public List<Service_item> SelectAll(){
        return service_itemService.SelectAll();
    }

    //根据 ID 查询
    public Service_item SelectById(Integer id){
        return service_itemService.SelectById(id);
    }

    //模糊查询
    public List<Service_item> SelectByLike(Service_item service_item){
        return service_itemService.SelectByLike(service_item);
    }

    //根据 ID 修改
    public void UpdateById(Service_item service_item){
        service_itemService.UpdateById(service_item);
    }

    //根据 ID 删除
    public void DeleteById(Integer id){
        service_itemService.DeleteById(id);
    }

    //根据 ID 批量删除
    public void DeleteBybatch(List<Integer> ids){
        service_itemService.DeleteBybatch(ids);
    }

    //添加
    public void Insert(Service_item service_item){
        service_itemService.Insert(service_item);
    }
}
