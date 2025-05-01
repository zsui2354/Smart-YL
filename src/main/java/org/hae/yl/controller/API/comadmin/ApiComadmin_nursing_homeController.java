package org.hae.yl.controller.API.comadmin;

import com.github.pagehelper.PageInfo;
import org.hae.yl.entity.Nursing_home;
import org.hae.yl.facade.Nursing_HomeFacade;
import org.hae.yl.model.Nursinghomeparameter;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/comadmin/nursing_home")
public class ApiComadmin_nursing_homeController {

    @Resource
    private Nursing_HomeFacade facade;

    /**
     * 查询所有
     * @return
     */
    @GetMapping("/query")
    public List<Nursing_home> queryAll() {
        return facade.queryAll();
    }

    /**
     * 模糊查询
     * @param like
     * @return
     */
    @GetMapping("/querylike")
    public List<Nursing_home> queryLike(String like) {
        return facade.queryByLike(like);
    }

    /**
     * 分页查询接口
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/list")
    public PageInfo<Nursing_home> list(@RequestParam(defaultValue = "1") int pageNum,
                                       @RequestParam(defaultValue = "10") int pageSize) {
        return facade.queryByPage(pageNum, pageSize);
    }

    /**
     * 根据 Id 修改
     * @param nursing_home
     */
    @PostMapping("updateById")
    public void UpdateById(@RequestBody Nursinghomeparameter nursing_home) {
        facade.Update(nursing_home.getId() , nursing_home);
    }

    /**
     * 插入数据接口
     * @param nursingHome
     */
    @PostMapping("/add")
    public void Insert(@RequestBody Nursinghomeparameter nursingHome) {
        facade.Insert(nursingHome);
    }


    /**
     * 根据 Id 删除
     * @param nursing_home
     */
    @PostMapping("/deleteById")
    public void DeleteById(@RequestBody Nursing_home nursing_home) {
        facade.DeleteById(nursing_home.getId());
    }

    /**
     * 根据 Id 批量删除
     * @param ids
     */
    @PostMapping("/deleteBybatch")
    public void DeleteByBatch(@RequestBody List<Integer> ids) {
        facade.DeleteBybatch(ids);
    }

}
