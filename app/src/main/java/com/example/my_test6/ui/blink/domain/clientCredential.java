package com.example.my_test6.ui.blink.domain;

public class clientCredential {
    String client_id;
    String client_secret;
    String grant_type;

    public clientCredential(String client_id, String client_secret, String grant_type) {
        this.client_id = client_id;
        this.client_secret = client_secret;
        this.grant_type = grant_type;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    public String getGrant_type() {
        return grant_type;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }
}
