package org.hae.yl.controller.API.comadmin;

import com.github.pagehelper.PageInfo;
import org.hae.yl.entity.Nursing_home;
import org.hae.yl.entity.Service_appointment;
import org.hae.yl.entity.Service_item;
import org.hae.yl.facade.SericeFacade;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/comadmin/")
public class ApiComadmin_SericeController {

    @Resource
    private SericeFacade sericeFacade;

    /**
     * 获取所有可预约服务列表
     * @param pageNum 页码
     * @param pageSize 每页数量
     * @return 分页后的服务列表
     */
    @GetMapping("/list")
    public PageInfo<Service_item> queryByPage(@RequestParam(defaultValue = "1") int pageNum,
                                              @RequestParam(defaultValue = "10") int pageSize) {
        return sericeFacade.getAllAvailableServices(pageNum, pageSize);
    }

    /**
     * 获取某个服务详情信息
     * @param id 服务ID
     * @return 服务详情
     */
    public Service_item getServiceDetail(Integer id){
        return sericeFacade.getServiceDetail(id);
    }

    /**
     * 查询 对应用户的预约记录(status 传值（0待处理，1已确认，2已完成，3已取消）)
     */
    public List<Service_appointment> AppointmentRecord(int userId, int ServicesStatus){
        return sericeFacade.AppointmentRecord(userId, ServicesStatus);
    }

    /**
     * 用户提交服务预约申请
     * @param appointment 预约信息
     */
    public void submitAppointment(Service_appointment appointment){
        sericeFacade.submitAppointment(appointment);
    }

    /**
     * 用户取消预约
     * @param id 预约ID
     */
    public void cancelAppointment(Integer id){
        sericeFacade.cancelAppointment(id);
    }

    /**
     * 获取某个预约单的当前状态
     * @param id 预约ID
     * @return 预约信息
     */
    public Service_appointment getAppointmentStatus(Integer id){
        return sericeFacade.getAppointmentStatus(id);
    }

    //    管理员为预约任务分配工作人员(职工)
    public void assignStaffToBooking(int userId,int serviceId,String Datatime,int status ,String note){
        sericeFacade.assignStaffToBooking(userId,serviceId,Datatime,status,note);
    }
}
