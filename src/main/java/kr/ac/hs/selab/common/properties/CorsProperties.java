package kr.ac.hs.selab.common.properties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

@Setter
@NoArgsConstructor
@ConfigurationProperties(prefix = "cors")
public class CorsProperties {

    private static final String COMMA = ",";
    @NotBlank
    private String allowedOrigins;
    @NotBlank
    private String allowedMethods;
    @NotBlank
    private String allowedHeaders;
    @Getter
    @NotNull
    @Max(3600)
    private Long maxAge;
    @Getter
    @NotBlank
    private String applyUrlRange;

    public List<String> getAllowedOrigins() {
        return asListWithSplitRegex(allowedOrigins);
    }

    public List<String> getAllowedMethods() {
        return asListWithSplitRegex(allowedMethods);
    }

    public List<String> getAllowedHeaders() {
        return asListWithSplitRegex(allowedHeaders);
    }

    private List<String> asListWithSplitRegex(final String str) {
        return Arrays.asList(str.split(COMMA));
    }
}