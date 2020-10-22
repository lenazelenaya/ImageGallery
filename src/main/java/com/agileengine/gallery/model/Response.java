package com.agileengine.gallery.model;

import lombok.Data;

@Data
public class Response {
    private boolean auth;
    private String token;
    public boolean isAuthorized() {
        return token != null;
    }
}
