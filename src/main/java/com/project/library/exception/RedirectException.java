package com.project.library.exception;

public class RedirectException extends RuntimeException {
    private final String redirectUrl;

    public RedirectException(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String getRedirectUrl() {
        return this.redirectUrl;
    }
}
