package kr.ac.hs.selab.common.properties;

import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@NoArgsConstructor
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    private String header;
    private String secret;
    private String tokenValidityWithSeconds;
}
