package kr.ac.hs.selab.common.properties;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

    @NotBlank
    private String header;
    @Getter
    @NotBlank
    private String issuer;
    @Getter
    @NotBlank
    private String secret;
    @Getter
    @NotNull
    private Long tokenValidityInSeconds;
}
