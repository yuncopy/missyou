package com.lin.missyou.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@PropertySource(value = "classpath:config/exception-code.properties")
@ConfigurationProperties(prefix = "lin")
@Component
public class ExceptionCodeConfiguration {

    private Map<Integer,String> codes = new HashMap<>(); //lin.codes[9999] = 服务器未知异常O(∩_∩)O哈哈~

    public Map<Integer, String> getCodes() {
        return codes;
    }

    public void setCodes(Map<Integer, String> codes) {
        this.codes = codes;
    }

    public String getMessage(int code){
        String message = codes.get(code);
        return message;
    }
}
