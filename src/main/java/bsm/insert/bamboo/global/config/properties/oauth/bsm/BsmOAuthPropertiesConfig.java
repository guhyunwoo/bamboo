package bsm.insert.bamboo.global.config.properties.oauth.bsm;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(BsmOAuthProperties.class)
public class BsmOAuthPropertiesConfig {
    // Properties Bean 등록
}
