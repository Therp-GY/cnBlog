package com.example.my_test6.ui.blink.domain;

public class accessToken {
    String access_token;
    String expires_in;
    String token_type;

    public accessToken(String token, String expires_in, String token_type) {
        this.access_token = token;
        this.expires_in = expires_in;
        this.token_type = token_type;
    }

    public String getToken() {
        return access_token;
    }

    public void setToken(String token) {
        this.access_token = token;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }
}
