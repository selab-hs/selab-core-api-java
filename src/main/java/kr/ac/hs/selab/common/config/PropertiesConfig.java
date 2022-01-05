package kr.ac.hs.selab.common.config;

import kr.ac.hs.selab.common.properties.CorsProperties;
import kr.ac.hs.selab.common.properties.JwtProperties;
import kr.ac.hs.selab.common.properties.SwaggerProperties;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({
    SwaggerProperties.class, JpaProperties.class, JwtProperties.class
})
public class PropertiesConfig {

}