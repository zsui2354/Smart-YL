package org.hae.yl.controller.API.staff;

import com.github.pagehelper.PageInfo;
import org.hae.yl.entity.Discussion;
import org.hae.yl.entity.Health_record;
import org.hae.yl.entity.Service_appointment;
import org.hae.yl.facade.Health_recordFacade;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/staff/healthrecord")
public class ApiStaff_HealthRecirdController {

    @Resource
    private Health_recordFacade health_recordFacade;

    /**
     * 分页查询接口 健康档案
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/list")
    public PageInfo<Health_record> queryByPage(@RequestParam(defaultValue = "1") int pageNum,
                                               @RequestParam(defaultValue = "10") int pageSize) {
        return health_recordFacade.queryByPage(pageNum, pageSize);
    }

    /**
     * 查询用户完整健康档案
     */
    @GetMapping("/SelectById")
    public Health_record SelectById(int id){
        return health_recordFacade.SelectById(id);
    }

    /**
     * 获取用户最近一次健康打卡状态
     * @param id
     * @return
     */
    @GetMapping("/SelectByIdEndSelection")
    public Health_record SelectByIdEndSelection(int id){
        return health_recordFacade.SelectByIdEndSelection(id);
    }

    /**
     * 获取ID用户的折线图体征数据（体温、血压等）
     * @param id
     * @return
     */
    @GetMapping("/SelectAllSignsData")
    public List<Health_record> SelectAllSignsData(int id){
        return health_recordFacade.SelectAllSignsData(id);
    }

    /**
     * 健康异常预警检测（如高血压、发热等
     * @param UserId
     * @return 风险等级（0=正常，1=轻度异常，2=中度异常，3=严重异常，4=危急异常）
     */
    @GetMapping("/HealthAbnormalityWarning")
    public int HealthAbnormalityWarning(int UserId){
        return health_recordFacade.HealthAbnormalityWarning(UserId);
    }

    //健康异常处理  判定等级自动发送处理工单
    @PostMapping("/HealthAbnormalityWarning_work")
    public void HealthAbnormalityWarning_work(int id, Service_appointment service_appointment){
        health_recordFacade.HealthAbnormalityWarning_work(id, service_appointment);
    }
}
