package kr.ac.hs.selab.common.properties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Configuration
@Setter
@NoArgsConstructor
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

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
