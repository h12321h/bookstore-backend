//package org.example.mainservice.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class CorsConfig implements WebMvcConfigurer {
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**") // 应用于所有的路径
//                .allowedOriginPatterns("http://localhost:3000")
//                .allowedMethods("OPTIONS,GET,POST,DELETE,PUT") // 允许的HTTP方法
//                .allowedHeaders("Content-Type, Authorization, X-Requested-With, Accept") // 允许所有头部
//                .allowCredentials(true) // 允许发送 Cookie
//                .maxAge(3600); // 预检请求的有效期，单位为秒
//    }
//}
//
