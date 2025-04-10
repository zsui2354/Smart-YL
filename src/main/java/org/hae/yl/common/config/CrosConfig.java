package org.hae.yl.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CrosConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();             // 创建 UrlBasedCorsConfigurationSource 实例

        CorsConfiguration corsconfig = new CorsConfiguration();                                     // 创建一个 CORS 配置对象
        corsconfig.addAllowedOrigin("*");                                                           // 1 设置访问源地址
        corsconfig.addAllowedHeader("*");                                                           // 2 设置访问源请求头
        corsconfig.addAllowedMethod("*");                                                           // 3 设置访问源请求方法
        source.registerCorsConfiguration("/**", corsconfig);                                 // 4 对接口配置跨域设置
        return new CorsFilter(source);
    }
}


/**



@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*"); // 1 设置访问源地址
        corsConfiguration.addAllowedHeader("*"); // 2 设置访问源请求头
        corsConfiguration.addAllowedMethod("*"); // 3 设置访问源请求方法
        source.registerCorsConfiguration("/**", corsConfiguration); // 4 对接口配置跨域设置
        return new CorsFilter(source);
    }
}




 */
