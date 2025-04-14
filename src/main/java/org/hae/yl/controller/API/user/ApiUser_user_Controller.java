package org.hae.yl.controller.API.user;

import org.hae.yl.entity.User;
import org.hae.yl.facade.UserFacade;
import org.hae.yl.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/user/user")
public class ApiUser_user_Controller {

    @Resource
    private UserFacade facade;

//    @GetMapping("/getuserinfo")
//    public User getUserInfo(@RequestHeader ){
//
//    }
}
