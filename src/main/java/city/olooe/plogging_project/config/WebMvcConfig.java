package city.olooe.plogging_project.config;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: 천은경
 * @date: 2023.06.04
 * @brief: frontend와 backend 통합 작업
 */

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    private final long MAX_AGE_SECS = 3600;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 모든 경로에 대해
        registry.addMapping("/**")
                // Origin이 http://localhost:3000에 대해./
//                .allowedOrigins("http://192.168.20.38:3000")
//                .allowedOrigins("http://13.209.48.33:8080")
//                .allowedOrigins("https://jubging.sionms.co.kr")
                .allowedOriginPatterns(CorsConfiguration.ALL)
                // "GET", "POST", "PUT", "DELETE", "OPTIONS" 메서드를 허용
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(MAX_AGE_SECS);
    }

    private static final String defaultFormat = "yyyy-MM-dd";

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer customizer() {
        return b -> b.simpleDateFormat(defaultFormat);
    }
}
