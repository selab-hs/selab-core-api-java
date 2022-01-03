package kr.ac.hs.selab.common.config;

import kr.ac.hs.selab.common.properties.SwaggerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(SwaggerProperties.class)
public class PropertiesConfig {

}