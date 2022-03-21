package com.hy.blog.model;

import lombok.Data;

@Data
public class OAuthToken {
    private String token_type;
    private String access_token;
    private int expires_in;
    private String refresh_token;
    private int refresh_token_expires_in;
    private String scope;
}
