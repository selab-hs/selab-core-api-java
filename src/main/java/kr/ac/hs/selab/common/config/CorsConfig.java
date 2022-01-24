package kr.ac.hs.selab.common.config;

import javax.validation.constraints.NotNull;
import kr.ac.hs.selab.common.properties.CorsProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableConfigurationProperties(CorsProperties.class)
@RequiredArgsConstructor
public class CorsConfig {

    @NotNull
    private final CorsProperties corsProperties;

    @Bean
    public UrlBasedCorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfig = makeCorsConfiguration();
        return makeUrlBasedCorsConfigurationSource(corsConfig);
    }

    private CorsConfiguration makeCorsConfiguration() {
        return registerCorsConfiguration(new CorsConfiguration());
    }

    private CorsConfiguration registerCorsConfiguration(
        @NotNull final CorsConfiguration corsConfig) {
        corsConfig.setAllowedHeaders(corsProperties.getAllowedHeaders());
        corsConfig.setAllowedMethods(corsProperties.getAllowedMethods());
        corsConfig.setAllowedOrigins(corsProperties.getAllowedOrigins());
        corsConfig.setAllowCredentials(true);
        corsConfig.setMaxAge(corsConfig.getMaxAge());
        return corsConfig;
    }

    private UrlBasedCorsConfigurationSource makeUrlBasedCorsConfigurationSource(
        @NotNull final CorsConfiguration corsConfig) {
        return addCorsConfiguration(corsConfig, new UrlBasedCorsConfigurationSource());
    }

    private UrlBasedCorsConfigurationSource addCorsConfiguration(
        @NotNull final CorsConfiguration corsConfig,
        @NotNull final UrlBasedCorsConfigurationSource corsConfigSource) {
        corsConfigSource.registerCorsConfiguration(corsProperties.getApplyUrlRange(), corsConfig);
        return corsConfigSource;
    }
}