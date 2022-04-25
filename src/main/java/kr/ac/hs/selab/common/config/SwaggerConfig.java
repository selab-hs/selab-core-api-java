package kr.ac.hs.selab.common.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebSession;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Configuration
@EnableSwagger2
@RequiredArgsConstructor
public class SwaggerConfig {

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(List.of(new SecurityReference("SELAB REST API WITH JWT", new AuthorizationScope[]{new AuthorizationScope("global", "accessEverything")})))
                .build();
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(true)
                .useDefaultResponseMessages(false)
                .ignoredParameterTypes(
                        WebSession.class,
                        ServerHttpRequest.class,
                        ServerHttpResponse.class,
                        ServerWebExchange.class
                )
                .apiInfo(new ApiInfo(
                        "SELAB API",
                        "Management REST API SERVICE",
                        "1.0",
                        "urn:tos",
                        new Contact("SELAB", "https://github.com/selab-hs/selab", "wrjssmjdhappy@gmail.com"),
                        "Apache 2.0",
                        "http://www.apache.org/licenses/LICENSE-2.0",
                        new ArrayList<>()
                ))
                .produces(new HashSet<>(Arrays.asList("application/json", "application/xml")))
                .consumes(new HashSet<>(Arrays.asList("application/json", "application/xml")))
                .securityContexts(List.of(securityContext()))
                .securitySchemes(List.of(new ApiKey("SELAB REST API WITH JWT", "Authorization", "header")))
                .select()
                .paths(PathSelectors.regex("/api/.*"))
                .build();
    }

    public String[] whiteListInSwagger() {
        return "/v2/api-docs,/swagger-resources,/swagger-resources/**,/configuration/ui,/configuration/security,/swagger-ui.html,/webjars/**,/v3/api-docs/**,/swagger-ui/**,/swagger".split(",");
    }
}