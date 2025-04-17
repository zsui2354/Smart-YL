package org.hae.yl.controller.API.comadmin;

import com.github.pagehelper.PageInfo;
import org.hae.yl.entity.Health_record;
import org.hae.yl.entity.Nursing_home;
import org.hae.yl.entity.Service_appointment;
import org.hae.yl.facade.Health_recordFacade;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/comadmin/")
public class ApiComadmin_HealthRecirdController {

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
     * 用户每日健康打卡记录
     */
    public void Insert(Health_record health_record){
        health_recordFacade.Insert(health_record);
    }

    /**
     * 查询用户完整健康档案
     */
    public Health_record SelectById(int id){
        return health_recordFacade.SelectById(id);
    }

    /**
     * 获取用户最近一次健康打卡状态
     * @param id
     * @return
     */
    public Health_record SelectByIdEndSelection(int id){
        return health_recordFacade.SelectByIdEndSelection(id);
    }

    /**
     * 获取ID用户的折线图体征数据（体温、血压等）
     * @param id
     * @return
     */
    public List<Health_record> SelectAllSignsData(int id){
        return health_recordFacade.SelectAllSignsData(id);
    }

    /**
     * 健康异常预警检测（如高血压、发热等
     * @param UserId
     * @return 风险等级（0=正常，1=轻度异常，2=中度异常，3=严重异常，4=危急异常）
     */
    public int HealthAbnormalityWarning(int UserId){
        return health_recordFacade.HealthAbnormalityWarning(UserId);
    }

    //健康异常处理  判定等级自动发送处理工单
    public void HealthAbnormalityWarning_work(int id, Service_appointment service_appointment){
        health_recordFacade.HealthAbnormalityWarning_work(id, service_appointment);
    }

    /**
     * 查询所有
     */
    public List<Health_record> SelectAll(){
        return health_recordFacade.SelectAll();
    }

    /**
     * 根据 Id 修改
     */
    public void Update(int id, Health_record health_record){
        health_recordFacade.Update(id, health_record);
    }

    /**
     * 根据 Id 删除
     */
    public void DeleteById(int id){
        health_recordFacade.DeleteById(id);
    }

    /**
     * 根据 Id 批量删除
     */
    public void DeleteByIdbatch(List<Integer> ids){
        health_recordFacade.DeleteByIdbatch(ids);
    }
}
