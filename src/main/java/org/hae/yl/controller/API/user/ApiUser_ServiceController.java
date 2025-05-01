package org.hae.yl.controller.API.user;

import com.github.pagehelper.PageInfo;
import org.hae.yl.entity.News;
import org.hae.yl.entity.Service_appointment;
import org.hae.yl.entity.Service_item;
import org.hae.yl.facade.SericeFacade;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/user/service")
public class ApiUser_ServiceController {

    @Resource
    private SericeFacade sericeFacade;

    /**
     * 分页查询 可预约服务列表
     */
    @GetMapping("/querypage")
    public PageInfo<Service_item> getAllAvailableServices(@RequestParam(defaultValue = "1") int pageNum,
                                      @RequestParam(defaultValue = "10") int pageSize) {
        return sericeFacade.getAllAvailableServices(pageNum, pageSize);
    }

    /**
     * 获取某个服务详情信息
     */
    @GetMapping("serviceinfoId")
    public Service_item getServiceDetail(Integer id){
        return sericeFacade.getServiceDetail(id);
    }

    /**
     * 查询 对应用户的预约记录(status 传值（0待处理，1已确认，2已完成，3已取消）)
     */
    @GetMapping("/appointmentrecord")
    public List<Service_appointment> AppointmentRecord(int userId, int ServicesStatus){
        return sericeFacade.AppointmentRecord(userId,ServicesStatus);
    }

    /**
     *  用户提交服务预约申请
     */
    @PostMapping("/submitAppointment")
    public void submitAppointment(@RequestBody Service_appointment appointment){

        Service_appointment St = new Service_appointment();
        St.setUser_id(appointment.getUser_id());
        St.setService_id(appointment.getService_id());
        St.setStatus(appointment.getStatus());
        St.setNote(appointment.getNote());

        sericeFacade.submitAppointment(appointment);
    }

    /**
     * 用户根据 ID 删除预约记录
     * @param id 预约ID
     */
    @PostMapping("/cancelappointment")
    public void cancelAppointment(Integer id){
        sericeFacade.cancelAppointment(id);
    }


}
