package org.hae.yl.controller.API.sysadmin;

import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.hae.yl.entity.Nursing_home;
import org.hae.yl.facade.Nursing_HomeFacade;
import org.hae.yl.model.Nursinghomeparameter;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/sysadmin/nursinghome")
public class ApiSysadmin_nursing_homeController {

    @Resource
    private Nursing_HomeFacade nursinghomefacade;



    /**
     * 获取单个机构详细信息
     * @param id
     * @return
     */
    @GetMapping("/SelectById")
    public Nursing_home SelectById(int id){
        return nursinghomefacade.SelectById(id);
    }

    /**
     * 获取某机构下所有服务项目
     */
    @GetMapping("/getOrgServiceList")
    public List<Nursing_home> getOrgServiceList(int home_id){
        return nursinghomefacade.getOrgServiceList(home_id);
    }

    /**
     * 地图搜索机构，根据关键词模糊查询
     * @param name
     * @return
     */
    @PostMapping("/mapSearchInstitutions")
    public List<Nursing_home> mapSearchInstitutions(String name){
        return nursinghomefacade.mapSearchInstitutions(name);
    }

    /**
     * 查询所有
     * @return
     */
    @GetMapping("/query")
    public List<Nursing_home> queryAll() {
        return nursinghomefacade.queryAll();
    }

    /**
     * 模糊查询
     * @param like
     * @return
     */
//    @GetMapping("/querylike")
//    public List<Nursing_home> queryLike(String like) {
//        return nursinghomefacade.queryByLike(like);
//    }

    /**
     * 分页查询接口
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/list")
    public PageInfo<Nursing_home> list(@RequestParam(defaultValue = "1") int pageNum,
                                       @RequestParam(defaultValue = "10") int pageSize) {
        return nursinghomefacade.queryByPage(pageNum, pageSize);
    }

    /**
     * 根据 Id 修改
     * @param nursing_home
     */
    @PostMapping("updateById")
    public void UpdateById(@RequestBody Nursinghomeparameter nursing_home) {
        nursinghomefacade.Update(nursing_home.getId(), nursing_home);
    }

    /**
     * 插入数据接口
     * @param nursingHome
     */
    @PostMapping("/add")
    public void Insert(@RequestBody Nursinghomeparameter nursingHome) {
        nursinghomefacade.Insert(nursingHome);
    }


    /**
     * 根据 Id 删除
     * @param nursing_home
     */
    @PostMapping("/deleteById")
    public void DeleteById(@RequestBody Nursinghomeparameter nursing_home) {
        nursinghomefacade.DeleteById(nursing_home.getId());
    }

    /**
     * 根据 Id 批量删除
     * @param ids
     */
    @PostMapping("/deleteBybatch")
    public void DeleteByBatch(@RequestBody List<Integer> ids) {
        nursinghomefacade.DeleteBybatch(ids);
    }
}
