package org.hae.yl.entity;


/**
 * 用于封装 token 得到 AuthResponse 对象 返回给前端
 */
public class AuthResponse {

    private String token;

    // 构造函数
    public AuthResponse(String token) {
        this.token = token;
    }

    // Getter 和 Setter 方法
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}