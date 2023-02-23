package com.bms.globalcustomvalidations.service.request;

import lombok.Data;

@Data
public abstract class BaseUserRequest {
    private String username;
    private String email;
    private String password;
}
