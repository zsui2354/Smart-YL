package org.hae.yl;

import io.jsonwebtoken.*;
import org.hae.yl.service.UserService;
import org.hae.yl.entity.User;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;


public class Test {

    @Resource
    UserService userService;


    /**
     * 基本使用 JWT
         * 1 . 生成token
         * 2 . 解密token
         * 3 . 测试JWT功能
     */

    @Test
    public void test(){
        System.out.println("test");
    }
    private long time = 1000 * 60 * 60 * 24;
    private String signature = "admin";         //key 密钥

    @org.junit.jupiter.api.Test
    public void jwt() {
        /**
         * 1 使用 Jwts类 中 JwtBuilder 构建器 构建 JWT
         * 2
         */
        JwtBuilder jwtBuilder = Jwts.builder();
        String jwttoken = jwtBuilder
            //Header 部分
                .setHeaderParam("type", "JWT")                              //类型
                .setHeaderParam("algo", "hs256")                            //加密算法
            //Payload 载荷部分
                .claim("username", "tom")
                .claim("role", "admin")
                .setSubject("admin-test")                                         //主题
                .setExpiration(new Date(System.currentTimeMillis() + time))
                .setId(UUID.randomUUID().toString())                              //为生成的 JWT 设置一个随机的，唯一的 UUID 字符串（标识符）
            //Signature 签名
                .signWith(SignatureAlgorithm.HS256, signature)                    //为生成的 JWT 设置 签名算法 和 签名密钥
                .compact();                                                       //生成并返回最终的 JWT 字符串

        System.out.println(jwttoken);                                             //打印 token
    }

    @org.junit.jupiter.api.Test
    public void parse(){
        String token = "eyJ0eXBlIjoiSldUIiwiYWxnbyI6ImhzMjU2IiwiYWxnIjoiSFMyNTYifQ.eyJ1c2VybmFtZSI6InRvbSIsInJvbGUiOiJhZG1pbiIsInN1YiI6ImFkbWluLXRlc3QiLCJleHAiOjE3NDQyMDQwNTcsImp0aSI6IjI5OTVjNDgzLWY3ZjgtNDJjMy05ZmEwLWUzOGIxODUzMDZiZiJ9.ahrOS7JWXlDEyvRzugzGEsaPojfdD7DdZBB9k6lzt2I";

        JwtParser parser = Jwts.parser();                                                       //获取 parser 对象
        parser.setSigningKey(signature).parseClaimsJws(token);                                  //通过 Key 解密成 Claim 格式的信息 返回一个 List
        Jws<Claims> claimsJws = parser.setSigningKey(signature).parseClaimsJws(token);          //通过调用 Key 和 token 解密 获取到 JWT 中的载荷 ， 用 Jws包装类 封装已解析的 JWT 内容

        Claims claims = claimsJws.getBody();
        System.out.println(claims.get("username"));                     //输出解密后  用户名
        System.out.println(claims.get("role"));                         //输出解密后  角色
        System.out.println(claims.getId());                             //输出解密后  JWT id
        System.out.println(claims.getSubject());                        //输出解密后  主题
        System.out.println(claims.getExpiration());                     //输出解密后  有效期
    }





}
