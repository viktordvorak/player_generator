package cz.dvorakv.config

import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.filter.CharacterEncodingFilter
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig: WebMvcConfigurer {

    @Bean
    fun characterEncodingFilter(): FilterRegistrationBean<CharacterEncodingFilter> {
        val filter = CharacterEncodingFilter()
        filter.encoding = "UTF-8"
        filter.setForceEncoding(true)
        val registrationBean = FilterRegistrationBean(filter)
        registrationBean.addUrlPatterns("/*")
        registrationBean.order = Integer.MIN_VALUE
        return registrationBean
    }

    /**
     *
     * @param registry
     * allowedOriginPatterns - jaké cizí URL jsou povoleny
     * allowCredentials -server mohl posílat "cizí" URL adrese cookies.
     */
    override fun addCorsMappings(registry: CorsRegistry) {
        super.addCorsMappings(registry)
        registry.addMapping("/**")
            .allowedMethods("HEAD", "GET", "POST", "PUT", "DELETE", "OPTIONS")
            .allowedOriginPatterns("**")
            .allowCredentials(true)
    }

}