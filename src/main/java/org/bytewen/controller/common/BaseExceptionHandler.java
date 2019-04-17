package org.bytewen.controller.common;

import org.bytewen.po.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler
    public Result errorHandle(Throwable e){
        System.out.println("记录日志，发生异常");
        return new Result(false,e.getMessage());
    }
}
