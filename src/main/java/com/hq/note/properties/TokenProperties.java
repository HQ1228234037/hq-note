package com.hq.note.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * token 属性配置
 *
 * @author HQ
 **/
@Data
@ConfigurationProperties(prefix = "token")
public class TokenProperties {

    /**
     * 密钥
     **/
    private String key;

}
