package com.example.my_test6.ui.blink.domain;

public class clientInfo {
    private int clientImage;
    String clientName;
    String clientBlink;

    public clientInfo(int clientImage, String clientName, String clientBlink) {
        this.clientImage = clientImage;
        this.clientName = clientName;
        this.clientBlink = clientBlink;
    }

    public String getClientName() {
        return clientName;
    }

    public String getClientBlink() {
        return clientBlink;
    }

    public int getClientImage() {
        return clientImage;
    }

    public void setClientImage(int clientImage) {
        this.clientImage = clientImage;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setClientBlink(String clientBlink) {
        this.clientBlink = clientBlink;
    }

}
