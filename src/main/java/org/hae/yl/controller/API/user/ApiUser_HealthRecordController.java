package org.hae.yl.controller.API.user;

import com.github.pagehelper.PageInfo;
import org.hae.yl.entity.Health_record;
import org.hae.yl.entity.Service_appointment;
import org.hae.yl.facade.Health_recordFacade;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/user/healthrecord")
public class ApiUser_HealthRecordController {

    @Resource
    private Health_recordFacade health_recordFacade;

    /**
     * 分页查询
     * @return
     */
    @GetMapping("/healthrecordqueryByPage")
    public PageInfo<Health_record> queryByPage(){
        return health_recordFacade.queryByPage(1,10);
    }

    /**
     * 用户每日健康打卡记录
     */
    @PostMapping("/healthrecordInsert")
    public void Insert(Health_record health_record){
        health_recordFacade.Insert(health_record);
    }

    /**
     * 查询用户完整健康档案
     */
    @GetMapping("/healthrecordInfo")
    public Health_record SelectById(int id){
        return health_recordFacade.SelectById(id);
    }

    /**
     * 获取用户最近一次健康打卡状态
     * @param id
     * @return
     */
    @GetMapping("/healthrecordEndSelection")
    public Health_record SelectByIdEndSelection(int id){
        return health_recordFacade.SelectByIdEndSelection(id);
    }

    /**
     * 获取ID用户的折线图体征数据（体温、血压等）
     * @param id
     * @return
     */
    @GetMapping("/healthrecordSignsData")
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
    @GetMapping("/HealthAbnormalityWarning_work")
    public void HealthAbnormalityWarning_work(int id, Service_appointment service_appointment){
        health_recordFacade.HealthAbnormalityWarning_work(id, service_appointment);
    }
}
