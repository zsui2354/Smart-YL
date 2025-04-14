package org.hae.yl.facade;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.hae.yl.entity.Service_appointment;
import org.hae.yl.entity.Service_item;
import org.hae.yl.service.Service_appointmentService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Component
public class Service_appointmentFacade {

    @Resource
    private Service_appointmentService service_appointmentService;


    /**
     * 分页查询
     * @return
     */
     public PageInfo<Service_appointment> queryByPage(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize) {
        PageHelper.startPage(pageNum, pageSize);  // 启动分页
        List<Service_appointment> list = service_appointmentService.SelectAll();  // 原始查询
        return new PageInfo<>(list);  // 包装分页对象返回
    }

    /**
     * 查询所有
     */
    public List<Service_appointment> SelectAll(){
        return service_appointmentService.SelectAll();
    }

    /**
     * 根据Id查询
     */
    public Service_appointment SelectById(int id){
        return service_appointmentService.SelectById(id);
    }

    /**
     * 根据 Id 修改
     */
    public void Update(int id, Service_appointment service_appointment){
        service_appointmentService.Update(id, service_appointment);
    }

    /**
     * 根据 Id 删除
     * 根据 Id 批量删除
     */
    public void DeleteById(int id){
        service_appointmentService.DeleteById(id);
    }

    public void DeleteByIdbatch(List<Integer> ids){
        service_appointmentService.DeleteByIdbatch(ids);
    }

    /**
     * 增加
     */
    public void Insert(Service_appointment service_appointment){
        service_appointmentService.Insert(service_appointment);
    }
}
