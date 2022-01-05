package kr.ac.hs.selab.common.config;

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

    private final CorsProperties corsProperties;

    @Bean
    public UrlBasedCorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfig = makeCorsConfiguration();
        return makeUrlBasedCorsConfigurationSource(corsConfig);
    }

    private CorsConfiguration makeCorsConfiguration() {
        return registerCorsConfiguration(new CorsConfiguration());
    }

    private CorsConfiguration registerCorsConfiguration(CorsConfiguration corsConfig) {
        registerAllowedHeaders(corsConfig);
        registerAllowedMethods(corsConfig);
        registerAllowedOrigins(corsConfig);
        registerAllowedCredentials(corsConfig);
        registerMaxAge(corsConfig);
        return corsConfig;
    }

    private void registerAllowedHeaders(CorsConfiguration corsConfig) {
        corsConfig.setAllowedHeaders(corsProperties.getAllowedHeaders());
    }

    private void registerAllowedMethods(CorsConfiguration corsConfig) {
        corsConfig.setAllowedMethods(corsProperties.getAllowedMethods());
    }

    private void registerAllowedOrigins(CorsConfiguration corsConfig) {
        corsConfig.setAllowedOrigins(corsProperties.getAllowedOrigins());
    }

    private void registerAllowedCredentials(CorsConfiguration corsConfig) {
        corsConfig.setAllowCredentials(true);
    }

    private void registerMaxAge(CorsConfiguration corsConfig) {
        corsConfig.setMaxAge(corsConfig.getMaxAge());
    }

    private UrlBasedCorsConfigurationSource makeUrlBasedCorsConfigurationSource(
        CorsConfiguration corsConfig) {
        return addCorsConfiguration(corsConfig, new UrlBasedCorsConfigurationSource());
    }

    private UrlBasedCorsConfigurationSource addCorsConfiguration(CorsConfiguration corsConfig,
        UrlBasedCorsConfigurationSource corsConfigSource) {
        corsConfigSource.registerCorsConfiguration(corsProperties.getApplyUrlRange(), corsConfig);
        return corsConfigSource;
    }
}