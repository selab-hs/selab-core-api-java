package kr.ac.hs.selab.common.properties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;

@Setter
@NoArgsConstructor
@ConfigurationProperties(prefix = "swagger")
public class SwaggerProperties {

    private String projectName;
    private String githubUrl;
    private String developerEmail;
    private String apiTitle;
    private String apiDescription;
    private String apiVersion;
    private String temrsOfServiceUrl;
    private String license;
    private String licenseUrl;
    private String typeJson;
    private String typeXml;
    private String apiName;
    private String apiKeyName;
    private String apiPassAs;
    private String authorizationScope;
    private String authorizationDescription;

    public Set<String> toProducesAndConsumes() {
        return new HashSet<>(Arrays.asList(typeJson, typeXml));
    }

    public ApiInfo toApiInfo() {
        return new ApiInfo(apiTitle, apiDescription, apiVersion, temrsOfServiceUrl, toContact(),
            license, licenseUrl, new ArrayList<>());
    }

    private Contact toContact() {
        return new Contact(projectName, githubUrl, developerEmail);
    }

    public ApiKey toApiKey() {
        return new ApiKey(apiName, apiKeyName, apiPassAs);
    }

    public List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = toAuthorizationScope();
        return List.of(toReference(authorizationScope));
    }

    private SecurityReference toReference(AuthorizationScope authorizationScope) {
        return new SecurityReference(apiName, new AuthorizationScope[]{authorizationScope});
    }

    private AuthorizationScope toAuthorizationScope() {
        return new AuthorizationScope(authorizationScope, authorizationDescription);
    }
}