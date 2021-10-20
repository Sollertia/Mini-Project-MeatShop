package com.hanghae.miniprojectmeatshop.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {

    @Value("${image.upload.directory}")
    private String uploadDir;

    @Value("${image.upload.url}")
    private String uploadUrl;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(uploadUrl+"*")
                .addResourceLocations("file:"+uploadDir);
    }


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // url 및 method 접근 풀기
        registry.addMapping("/**")
                //.allowedOrigins("S3 호스팅 주소") // 배포 전
                .allowedOrigins("http://localhost:3000") // react 서버 허용
                .allowedMethods(
                        HttpMethod.GET.name(),
                        HttpMethod.POST.name(),
                        HttpMethod.PUT.name(),
                        HttpMethod.DELETE.name(),
                        HttpMethod.HEAD.name())
                .allowCredentials(true); // 인증관련
    }
}
