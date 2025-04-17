package org.hae.yl.controller.API.staff;

import com.github.pagehelper.PageInfo;
import org.hae.yl.entity.Nursing_home;
import org.hae.yl.entity.Service_item;
import org.hae.yl.facade.Nursing_HomeFacade;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/staff/nursinghome")
public class ApiStaff_nursing_homeController {
    @Resource
    private Nursing_HomeFacade nursing_homeFacade;

    /**
     * 查询所有
     * @return
     */
    @GetMapping("/query")
    public List<Nursing_home> queryAll() {
        return nursing_homeFacade.queryAll();
    }

    /**
     * 模糊查询
     * @param like
     * @return
     */
    @GetMapping("/querylike")
    public List<Nursing_home> queryLike(String like) {
        return nursing_homeFacade.queryByLike(like);
    }

    /**
     * 分页查询接口 机构列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/list")
    public PageInfo<Nursing_home> queryByPage(@RequestParam(defaultValue = "1") int pageNum,
                                              @RequestParam(defaultValue = "10") int pageSize) {
        return nursing_homeFacade.queryByPage(pageNum, pageSize);
    }

    /**
     * 获取单个机构详细信息
     * @param id
     * @return
     */
    @GetMapping("/SelectById")
    public Nursing_home SelectById(int id){
        return nursing_homeFacade.SelectById(id);
    }

    /**
     * 获取某机构下所有服务项目
     */
    @GetMapping("/getOrgServiceList")
    public List<Nursing_home> getOrgServiceList(int home_id){
        return nursing_homeFacade.getOrgServiceList(home_id);
    }

    /**
     * 地图搜索机构，根据关键词或位置信息查询
     * @param name
     * @return
     */
    @GetMapping("/mapSearchInstitutions")
    public Nursing_home mapSearchInstitutions(String name){
        return nursing_homeFacade.mapSearchInstitutions(name);
    }
}
