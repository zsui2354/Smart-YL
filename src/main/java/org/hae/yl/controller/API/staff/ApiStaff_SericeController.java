package org.hae.yl.controller.API.staff;

import com.github.pagehelper.PageInfo;
import org.hae.yl.entity.Discussion;
import org.hae.yl.entity.Service_appointment;
import org.hae.yl.entity.Service_item;
import org.hae.yl.facade.SericeFacade;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/staff/service")
public class ApiStaff_SericeController {

    @Resource
    private SericeFacade sericeFacade;

    /**
     * 分页查询接口
     * @param pageNum
     * @param pageSize
     * @return
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
    @GetMapping("/getServiceDetail")
    public Service_item getServiceDetail(Integer id){
        return sericeFacade.getServiceDetail(id);
    }

    /**
     * 获取某个预约单的当前状态
     * @param id 预约ID
     * @return 预约信息
     */
    @GetMapping("/getAppointmentStatus")
    public Service_appointment getAppointmentStatus(Integer id){
        return sericeFacade.getAppointmentStatus(id);
    }

    /**
     * 为预约任务分配工作人员(职工)
     * @param userId
     * @param serviceId
     * @param Datatime
     * @param status
     * @param note
     */
    @PostMapping("/assignStaffToBooking")
    public void assignStaffToBooking(int userId,int serviceId,String Datatime,int status ,String note){
        sericeFacade.assignStaffToBooking(userId,serviceId,Datatime,status,note);
    }
}
