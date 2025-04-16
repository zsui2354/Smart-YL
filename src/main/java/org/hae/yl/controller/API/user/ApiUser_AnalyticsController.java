package org.hae.yl.controller.API.user;

import org.hae.yl.entity.Discussion;
import org.hae.yl.entity.Service_appointment;
import org.hae.yl.facade.AnalyticsFacade;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/user/analytics")
public class ApiUser_AnalyticsController {

    @Resource
    private AnalyticsFacade analyticsFacade;

    
    /**
     * 获取最受欢迎的服务排行
     */
    @GetMapping("/GetpH")
    public List<Service_appointment> GetpH(){
        return analyticsFacade.GetpH();
    }

    /**
     * 获取当前用户的所有 Issues 记录
     */
    @GetMapping("/CurrentUserIssuesTotal")
    public List<Discussion> CurrentUserIssuesTotal(int id){
        return analyticsFacade.CurrentUserIssuesTotal(id);
    }

}
