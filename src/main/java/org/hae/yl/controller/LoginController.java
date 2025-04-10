package org.hae.yl.controller;

import org.hae.yl.Util.JwtUtil;
import org.hae.yl.entity.AuthResponse;
import org.hae.yl.entity.User;
import org.hae.yl.model.LoginRequest;
import org.hae.yl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.security.util.AuthResources;

@RestController
@CrossOrigin
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/auth")
    public ResponseEntity<?> authenticate(@RequestBody LoginRequest loginRequest) {
        System.out.println("请求到达登录接口~~~~");

        // 1. 获取用户输入的用户名和密码
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        // 2. 根据用户名查找用户
        User user_obj = userService.SelectByUsername(username);

        System.out.println("user_obj   ："+user_obj.toString());

        if (user_obj == null || !user_obj.getPassword().equals(password)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }

        // 3. 生成JWT token
        String token = JwtUtil.generateToken(
                user_obj.getUsername(),
                user_obj.getRole_id()
        );
        System.out.println("token: "+token);

        // 4. 返回token
        return ResponseEntity.ok(new AuthResponse(token));

    }
}
