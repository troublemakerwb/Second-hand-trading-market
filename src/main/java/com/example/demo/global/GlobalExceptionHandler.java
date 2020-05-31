package com.example.demo.global;

import com.example.demo.domain.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResultData> handleException(Exception e){
        log.info("exception:{}",e.getMessage());

        ResultData resultData = new ResultData();
        resultData.setCode(0);
        resultData.setData("This is an exception test");
        resultData.setMessage(e.getMessage());

        return ResponseEntity.ok(resultData);

    }

}
