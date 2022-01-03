package kr.ac.hs.selab.properties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;

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
}