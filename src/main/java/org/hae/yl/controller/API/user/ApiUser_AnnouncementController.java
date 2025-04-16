package org.hae.yl.controller.API.user;

import com.github.pagehelper.PageInfo;
import org.hae.yl.entity.Announcement;
import org.hae.yl.facade.AnnouncementFacade;
import org.hae.yl.service.AnnouncementService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/user/announcement")
public class ApiUser_AnnouncementController {

    @Resource
    private AnnouncementFacade announcementFacade;

    /**
     * 分页查询 公告
     * @return
     */
    @GetMapping("/announcementqueryByPage")
    public PageInfo<Announcement> queryByPage(){
        return announcementFacade.queryByPage(1,10);
    }
}
