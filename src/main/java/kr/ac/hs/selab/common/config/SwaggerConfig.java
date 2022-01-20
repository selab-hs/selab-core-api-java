package kr.ac.hs.selab.common.config;

import java.util.List;
import kr.ac.hs.selab.common.properties.SwaggerProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableConfigurationProperties(SwaggerProperties.class)
@RequiredArgsConstructor
public class SwaggerConfig {

    private final SwaggerProperties swaggerProperties;

    private SecurityContext securityContext() {
        return SecurityContext.builder()
            .securityReferences(swaggerProperties.defaultAuth()).build();
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(swaggerProperties.toApiInfo())
            .produces(swaggerProperties.toProducesAndConsumes())
            .consumes(swaggerProperties.toProducesAndConsumes())
            .securityContexts(List.of(securityContext()))
            .securitySchemes(List.of(swaggerProperties.toApiKey()));
    }

    public String[] whiteListInSwagger() {
        return swaggerProperties.getWhiteList();
    }
}