package org.hae.yl;
import org.junit.jupiter.api.Test;
import org.hae.yl.entity.User;
import org.hae.yl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest  // 启动 Spring 测试上下文
public class TestItem {
    @Autowired
    private UserService userService;  // 确保 userService 被注入

//    @Test
//    public void testselectSysadmin() {
//        User user = userService.selectSysadmin();
//        if (user != null) {
//            System.out.println(user);
//            System.out.println(user.toString());
//        } else {
//            System.out.println("User is null");
//        }
//    }
}
