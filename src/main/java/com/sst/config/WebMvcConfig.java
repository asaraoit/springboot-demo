package com.sst.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName WebMvcConfig
 * @Description 配置类
 * @Author Asarao
 * @Date 2018/11/9 17:56
 * @Version 1.0
 */
@EnableTransactionManagement
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /*
     * @Author Asarao
     * @Description 跨域请求处理配置
     * @Date 2018/11/20
     * @Param [registry]
     * @return void
     **/
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                .maxAge(3600)
                .allowCredentials(true);
    }

    /*
     * @Author Asarao
     * @Description 分页插件
     * @Date 2018/11/20
     * @Param []
     * @return com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor
     **/
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
