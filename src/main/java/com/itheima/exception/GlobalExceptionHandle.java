package com.itheima.exception;

import com.itheima.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 全局异常处理器
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandle {

    @ExceptionHandler
    public Result handleException(Exception e){
        log.error("异常信息：{}", e);
        return Result.error("操作失败");
    }

    @ExceptionHandler
    public Result handleDuplicateKeyException(DuplicateKeyException e) {
        log.error("异常信息：", e);

        String message = e.getMessage();
        Matcher matcher = Pattern
                .compile("Duplicate entry '([^']*)'")
                .matcher(message);

        if (matcher.find()) {
            String phone = matcher.group(1);
            return Result.error("手机号 " + phone + " 已存在");
        }

        return Result.error("数据已存在");
    }
}
