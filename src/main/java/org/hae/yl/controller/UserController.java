package org.hae.yl.controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class UserController {

    @GetMapping("/")
    public String Hello(){
        System.out.println("UserController 触发~~~~~~~~");
        return "Hello World";
    }

//    @GetMapping("/login")
//    public user login(user user){
//        if (USERNAME.equals(user.getUsername()) && PASSWORD.equals(user.getPassword())){
//            /**
//             * 登录成功
//             */
//            user.setToken(JwtUtil.generateToken(USERNAME));
//            return user;
//        }
//        return null;
//    }

//    @GetMapping("/checkToken")
//    public boolean checkToken(HttpServletRequest request){
//        String token = request.getHeader("token");
//        return JwtUtil.validateToken(token);
//    }
}
