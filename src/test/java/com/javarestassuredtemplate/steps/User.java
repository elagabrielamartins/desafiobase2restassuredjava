package com.javarestassuredtemplate.steps;

import lombok.Data;

@Data
public class User {
    private String username;
    private String password;
    private String realName;
    private String email;
    private String accessLevelName;
    private String enabled;
    private String isProtected;
}
