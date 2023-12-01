package com.epam.marathon.auto.core;

public enum BASE_URI {
    UI(""),
    API("");

    private String uri;
    BASE_URI(String uri) {
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
