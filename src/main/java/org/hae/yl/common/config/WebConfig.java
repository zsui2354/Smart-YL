package org.hae.yl.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Resource
    private JwtInterceptor jwtInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        System.out.println("注册拦截器...。。。。。。。。。");
        // 添加拦截器，并指定拦截路径
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/api/**")
                .excludePathPatterns(
                        "/api/**",              //这个是经过 JWT 拦截器拦截并判断 身份权限的 Controller 接口
                        "/publicapi/**",        //这个是公开Controller接口不需要拦截，登录授权 和 token 验证在这里
                        "/files/**",            //文件访问路径 在这里
                        "/error",
                        "/favicon.ico"
                );
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")                               // 允许跨域的路径
                .allowedOrigins("http://localhost:8080")                   // 允许的来源
                .allowedMethods("GET", "POST", "PUT", "DELETE","OPTIONS")  // 允许的请求方法
                .allowedHeaders("*")                                       // 允许的请求头
                .allowCredentials(true);                                   // 是否允许发送Cookie信息
    }
}
