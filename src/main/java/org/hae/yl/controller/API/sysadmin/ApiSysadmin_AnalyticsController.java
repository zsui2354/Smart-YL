package org.hae.yl.controller.API.sysadmin;

import org.hae.yl.entity.Discussion;
import org.hae.yl.entity.Service_appointment;
import org.hae.yl.facade.AnalyticsFacade;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/sysadmin/analytics")
public class ApiSysadmin_AnalyticsController {

    @Resource
    private AnalyticsFacade analyticsFacade;

    /**
     * CPU 占用
     */
    @GetMapping("/Cpuinfo")
    public double Cpuinfo() throws InterruptedException{
        return analyticsFacade.Cpuinfo();
    }

    /**
     * RAM 占用
     */
    @GetMapping("/Raminfo")
    public double Raminfo() throws InterruptedException{
        return analyticsFacade.Raminfo();
    }

    /**
     * 用户 total
     * SELECT COUNT(*) FROM user;
     */
    @GetMapping("/User_total")
    public int User_total(){
        return analyticsFacade.User_total();
    }

    /**
     * 管理员 total
     * SELECT COUNT(*) FROM user where role_id = 2 or role_id  = 3;
     */
    @GetMapping("/Admin_total")
    public int Admin_total(){
        return analyticsFacade.Admin_total();
    }

    /**
     * 获取最受欢迎的服务排行
     */
    @GetMapping("/GetpH")
    public List<Service_appointment> GetpH(){
        return analyticsFacade.GetpH();
    }

    /**
     * Issues total
     */
    @GetMapping("/IssuesTotal")
    public int IssuesTotal(){
        return analyticsFacade.IssuesTotal();
    }

    /**
     * 获取当前用户的所有 Issues 记录
     */
    @GetMapping("/CurrentUserIssuesTotal")
    public List<Discussion> CurrentUserIssuesTotal(int id){
        return analyticsFacade.CurrentUserIssuesTotal(id);
    }
}
