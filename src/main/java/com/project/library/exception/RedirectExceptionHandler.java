package com.project.library.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.view.RedirectView;

@ControllerAdvice
public class RedirectExceptionHandler {
    
    @ExceptionHandler(RedirectException.class)
    public RedirectView handleRedirectException(RedirectException ex) {
        return new RedirectView(ex.getRedirectUrl());
    }
}
