package org.hae.yl.controller.API.user;

import com.github.pagehelper.PageInfo;
import org.hae.yl.entity.Nursing_home;
import org.hae.yl.facade.Nursing_HomeFacade;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/user/nursinghome")
public class ApiUser_NursingHomeController {

    @Resource
    private Nursing_HomeFacade nursing_homeFacade;

    /**
     * 分页查询 机构列表
     * @return
     */
    @GetMapping("/query")
    public PageInfo<Nursing_home> queryByPage(){
        return nursing_homeFacade.queryByPage(1,10);
    }

    /**
     * 获取单个机构详细信息
     * @param id
     * @return
     */
    @GetMapping("/nursinghomeinfo")
    public Nursing_home SelectById(int id){
        return nursing_homeFacade.SelectById(id);
    }

    /**
     * 获取某机构下所有服务项目
     */
    @GetMapping("/nursinghome_serviceitem")
    public List<Nursing_home> getOrgServiceList(int home_id){
        return nursing_homeFacade.getOrgServiceList(home_id);
    }

    /**
     * 地图搜索机构，根据关键词或位置信息查询
     * @param name
     * @return
     */
    @GetMapping("/getmappostion")
    public Nursing_home mapSearchInstitutions(String name){
        return nursing_homeFacade.mapSearchInstitutions(name);
    }
}
