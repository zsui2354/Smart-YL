package org.hae.yl.controller.API.staff;

import com.github.pagehelper.PageInfo;
import org.hae.yl.entity.Announcement;
import org.hae.yl.entity.Discussion;
import org.hae.yl.facade.AnnouncementFacade;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/staff/announcement")
public class ApiStaff_AnnouncementController {

    @Resource
    private AnnouncementFacade announcementFacade;

    /**
     * 分页查询接口 公告
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
     * 根据Id查询
     */
    @GetMapping("/SelectById")
    public Announcement SelectById(int id){
        return announcementFacade.SelectById(id);
    }


}
