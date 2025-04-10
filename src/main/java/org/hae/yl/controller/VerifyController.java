package org.hae.yl.controller;

import org.hae.yl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static org.hae.yl.Util.JwtUtil.validateToken;

@RestController
@CrossOrigin
@RequestMapping("/verify")
public class VerifyController {

    @Autowired
    private UserService userService;

    @PostMapping("/routerverify")
    public boolean routerverify(@RequestHeader("Authorization") String token) {
        System.out.println("token 验证 ："+
                token
        );
        return validateToken(token);
    }



//    public Map<String, Object> routerverify(@RequestHeader("Authorization") String token) {
//        String userRole = validateTokenAndGetRole(token); // 根据 token 获取角色
//        Map<String, Object> response = new HashMap<>();
//        response.put("role", userRole);  // 角色信息
//        return response;
//    }
//
//
//
//
//
//
}
