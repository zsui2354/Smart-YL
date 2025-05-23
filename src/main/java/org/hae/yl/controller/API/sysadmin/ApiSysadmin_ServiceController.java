package org.hae.yl.controller.API.sysadmin;

import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.hae.yl.entity.Service_appointment;
import org.hae.yl.entity.Service_item;
import org.hae.yl.facade.SericeFacade;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/sysadmin/service")
public class ApiSysadmin_ServiceController {

    @Resource
    private SericeFacade serviceFacade;

    /**
     * 获取所有可预约服务列表
     * @param pageNum 页码
     * @param pageSize 每页数量
     * @return 分页后的服务列表
     */
    @GetMapping("/servicequeryBypage")
    public PageInfo<Service_item> getAllAvailableServices(int pageNum,int pageSize){
        return serviceFacade.getAllAvailableServices(pageNum,pageSize);
    }


    /**
     * 获取某个服务详情信息
     * @param id 服务ID
     * @return 服务详情
     */
    @GetMapping("/getServiceDetail")
    public Service_item getServiceDetail(Integer id){
        return serviceFacade.getServiceDetail(id);
    }

    /**
     * 查询 对应用户的预约记录(status 传值（0待处理，1已确认，2已完成，3已取消）)
     */
    @GetMapping("/AppointmentRecord")
    public List<Service_appointment> AppointmentRecord(@RequestParam("userId") int userId, @RequestParam("ServicesStatus") int ServicesStatus){
        return serviceFacade.AppointmentRecord(userId,ServicesStatus);
    }

    /**
     * 用户提交服务预约申请
     * @param appointment 预约信息
     */
    @PostMapping("/submitAppointment")
    public void submitAppointment(Service_appointment appointment){
        serviceFacade.submitAppointment(appointment);
    }

    /**
     * 删除预约记录
     * @param id 预约ID
     */
    @PostMapping("/cancelAppointment")
    public void cancelAppointment(Integer id){
        serviceFacade.cancelAppointment(id);
    }

    /**
     * 添加服务项目
     * @param service_item
     */
    @PostMapping("/insertServiceItem")
    public void insertServiceItem(@RequestBody Service_item service_item){
        serviceFacade.insertServiceItem(service_item);
    }

    /**
     * 修改更新服务项目信息
     * @param service_item
     */
    @PostMapping("/updateServiceItem")
    public void updateServiceItem(@RequestBody Service_item service_item){
        serviceFacade.updateServiceItem(service_item);
    }

    /**
     * 根据Id 删除服务项目
     * @param id
     */
    @PostMapping("/DeleteServiceItem")
    public void DeleteServiceItem(Integer id){
        serviceFacade.DeleteServiceItem(id);
    }
}
