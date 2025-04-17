package org.hae.yl.controller.API.comadmin;

import com.github.pagehelper.PageInfo;
import org.hae.yl.entity.Announcement;
import org.hae.yl.entity.Nursing_home;
import org.hae.yl.facade.AnnouncementFacade;
import org.hae.yl.service.AnnouncementService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/comadmin/")
public class ApiComadmin_AnnouncementController {


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
    public void publishNotice(Announcement announcement){
        announcementFacade.publishNotice(announcement);
    }

    /**
     * 根据 Id删除公告
     */
    public void DeleteById(int id){
        announcementFacade.DeleteById(id);
    }

    /**
     * 根据 Id批量删除公告
     * @param ids
     */
    public void DeleteByIdbatch(List<Integer> ids){
        announcementFacade.DeleteByIdbatch(ids);
    }

    /**
     * 查询所有
     */
    public List<Announcement> SelectAll(){
        return announcementFacade.SelectAll();
    }

    /**
     * 根据Id查询
     */
    public Announcement SelectById(int id){
        return announcementFacade.SelectById(id);
    }

    /**
     * 根据 Id 修改
     */
    public void Update(int id, Announcement announcement){
        announcementFacade.Update(id, announcement);
    }
}
