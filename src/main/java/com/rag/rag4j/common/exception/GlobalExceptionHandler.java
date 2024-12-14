package com.rag.rag4j.common.exception;

import com.rag.rag4j.common.adapter.in.dto.response.CommonBody;
import com.rag.rag4j.common.adapter.in.dto.response.ICommonBody;
import com.rag.rag4j.common.enums.code.ICommonCustomCode;
import com.rag.rag4j.common.exception.response.ICommonResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 서버 공통 정의 예외를 handling 하는 Method
     *
     * @param : 서버 공통으로 정의된 Exception Class
     * @return : client response
     * @author : PLO
     */
    @ExceptionHandler(GlobalException.class)
    protected ResponseEntity<ICommonBody<Void>> handleAbstractBaseException(GlobalException e) {
        ICommonResponseCode responseCode = e.getCode();
        HttpStatus httpStatus = responseCode.getHttpStatus();
        String message = responseCode.getMessage();

        long code = responseCode.getCustomCode();

        ICommonBody<Void> body = CommonBody.of(code, message, null);
        this.writeLog(e, message);
        return ResponseEntity
            .status(httpStatus)
            .body(body);
    }

    /**
     * Spring Framework 에서 Request 데이터 파싱중 발생한 예외를 handling 하는 Method
     *
     * @param : Request 관련 예외
     * @return : client response
     * @author : PLO
     */
    @ExceptionHandler(
        {
            BindException.class,
            HttpMessageNotReadableException.class,
            MethodArgumentNotValidException.class,
            MissingServletRequestParameterException.class
        }
    )
    protected ResponseEntity<ICommonBody<Void>> handleSpringRequestException(Exception e) {
        String message = e.getMessage();
        ICommonBody<Void> body = CommonBody.of(99999L, message, null);
        this.writeLog(e, message);
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(body);
    }

    /**
     * 예외 발생시 server측 log 작성 method
     *
     * @param : 예외 및 예외 발생시 loggingg할 메시지
     * @return : client response
     * @author : PLO
     */
    private void writeLog(Exception e, String message) {
        StackTraceElement[] stackTrace = e.getStackTrace();

        // class name, func name 초기화
        String defaultLog = "UNKNOWN";
        String className = defaultLog;
        String funcName = defaultLog;

        if (stackTrace.length >= 2) {
            className = stackTrace[2].getClassName();
            funcName = stackTrace[2].getMethodName();
        }

        log.error(String.format("[%s][%s] %s", className, funcName, message), e);
    }

}