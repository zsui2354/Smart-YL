package org.hae.yl.controller.API.sysadmin;

import com.github.pagehelper.PageInfo;
import org.hae.yl.entity.Announcement;
import org.hae.yl.facade.AnnouncementFacade;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/sysadmin/announcement")
public class ApiSysadmin_AnnouncementController {


    @Resource
    private AnnouncementFacade announcementFacade; ;

    /**
     * 分页查询接口 公告列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/list")
    public PageInfo<Announcement> queryByPage(@RequestParam(defaultValue = "1") int pageNum,
                                              @RequestParam(defaultValue = "10") int pageSize) {
        return announcementFacade.queryByPage(pageNum, pageSize);
    }

    /**
     * 发布公告（系统、机构、社区）（管理员权限）
     */
    @PostMapping("/publishNotice")
    public void publishNotice(@RequestBody Announcement announcement){
        announcementFacade.publishNotice(announcement);
    }

    /**
     * 根据 Id删除公告
     */
    @PostMapping("/DeleteById")
    public void DeleteById(int id){
        announcementFacade.DeleteById(id);
    }

    /**
     * 根据 Id批量删除公告
     * @param ids
     */
    @PostMapping("/DeleteByIdbatch")
    public void DeleteByIdbatch(List<Integer> ids){
        announcementFacade.DeleteByIdbatch(ids);
    }

    /**
     * 查询所有
     */
    @PostMapping("/SelectAll")
    public List<Announcement> SelectAll(){
        return announcementFacade.SelectAll();
    }

    /**
     * 根据Id查询
     */
    @PostMapping("/SelectById")
    public Announcement SelectById(int id){
        return announcementFacade.SelectById(id);
    }

    /**
     * 根据 Id 修改
     */
    @PostMapping("/Update")
    public void Update(int id, Announcement announcement){
        announcementFacade.Update(id, announcement);
    }
}
