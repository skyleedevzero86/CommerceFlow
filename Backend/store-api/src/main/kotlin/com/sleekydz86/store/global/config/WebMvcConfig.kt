package com.sleekydz86.store.global.config

import com.sleekydz86.store.global.config.filter.CorsCustomFilter
import jakarta.servlet.Filter
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebMvcConfig : WebMvcConfigurer {

    @Bean
    fun corsFilter(): FilterRegistrationBean<Filter> {
        val filterRegistrationBean = FilterRegistrationBean<Filter>(CorsCustomFilter())
        filterRegistrationBean.setOrder(1)
        filterRegistrationBean.addUrlPatterns("/*")
        return filterRegistrationBean
    }
}
