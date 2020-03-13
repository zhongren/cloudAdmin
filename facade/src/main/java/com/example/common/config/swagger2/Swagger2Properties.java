
package com.example.common.config.swagger2;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
@Data
@Component
@ConfigurationProperties(prefix = "swagger", ignoreUnknownFields = true, ignoreInvalidFields = true)
public class Swagger2Properties {

    private String title;
    private String description;
    private String version;
    private String contact;
    private String serviceUrl;


}
