package com.hy.blog.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(value=IllegalArgumentException.class)
    public String handleArgumentException(IllegalArgumentException e) {
        return "<h1>"+e.getMessage()+"</h1>";
    }
//    public ResponseDto<String> handleArgumentException(Exception e) {
//        return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()); // 500
//    }
}
