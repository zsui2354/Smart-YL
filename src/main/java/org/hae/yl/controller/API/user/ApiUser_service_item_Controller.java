package org.hae.yl.controller.API.user;

import com.github.pagehelper.PageInfo;
import org.hae.yl.entity.Service_item;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/user/service_item")
public class ApiUser_service_item_Controller {

    @Resource
    private Service_itemFacade facade;


    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/list")
    public PageInfo<Service_item> list(@RequestParam(defaultValue = "1") int pageNum,
                                       @RequestParam(defaultValue = "10") int pageSize) {
        return facade.queryByPage(pageNum, pageSize);
    }

}
