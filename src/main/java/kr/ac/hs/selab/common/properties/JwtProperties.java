package kr.ac.hs.selab.common.properties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Setter
@NoArgsConstructor
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    private String header;
    @Getter
    private String issuer;
    @Getter
    private String secret;
    @Getter
    private Long tokenValidityInSeconds;
}
