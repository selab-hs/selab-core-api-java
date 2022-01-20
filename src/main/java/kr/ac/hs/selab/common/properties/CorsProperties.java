package kr.ac.hs.selab.common.properties;

import java.util.Arrays;
import java.util.List;
import javax.validation.constraints.Max;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@NoArgsConstructor
@ConfigurationProperties(prefix = "cors")
public class CorsProperties {

    private static final String COMMA = ",";

    // TODO : 기본적으로 제공하는 방어로직이 있는 건 어떨까?
    private String allowedOrigins;
    private String allowedMethods;
    private String allowedHeaders;
    @Getter
    @Max(3600)
    private Long maxAge;
    @Getter
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

    private List<String> asListWithSplitRegex(String str) {
        return Arrays.asList(str.split(COMMA));
    }
}