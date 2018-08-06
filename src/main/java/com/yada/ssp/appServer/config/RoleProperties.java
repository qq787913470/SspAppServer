package com.yada.ssp.appServer.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

@Component
@ConfigurationProperties
public class RoleProperties {

    private Map<String, LinkedList<String>> role = new LinkedHashMap<>();

    public Map<String, LinkedList<String>> getRole() {
        return role;
    }

    public void setRole(Map<String, LinkedList<String>> role) {
        this.role = role;
    }
}
