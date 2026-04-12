package com.crimson.projectred.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "ordering")
@Configuration
@Data
@RefreshScope
public class ApplicationProperties {
    private String notificationTopic;
    private String notificationStatusTopic;
}
