package com.hq.note.config;

import com.hq.note.properties.TokenProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义属性配置
 *
 * @author HQ
 **/
@Configuration
@EnableConfigurationProperties(TokenProperties.class)
public class PropertiesConfiguration {
}
