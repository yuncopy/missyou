package com.lin.missyou.core;

import com.lin.missyou.core.config.ExceptionCodeConfiguration;
import com.lin.missyou.exception.http.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionAdvice {

    @Autowired
    ExceptionCodeConfiguration exceptionCodeConfiguration ;

    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public UnifyResponse handleException(HttpServletRequest req,Exception e){

        String method = req.getMethod();
        String requestUrl = req.getRequestURI();
        System.out.println(e); //打印异常信息 记录日志
        UnifyResponse unifyResponse = new UnifyResponse(9999,"服务器错误",method+" "+requestUrl);
        return unifyResponse;
    }

    @ExceptionHandler(HttpException.class)
    public ResponseEntity<UnifyResponse> handleHttpException(HttpServletRequest req, HttpException e){
        String method = req.getMethod();
        String requestUrl = req.getRequestURI();
        System.out.println(e);//打印异常信息 记录日志

        //ResponseEntity 可以自定义 : HTTP状态码 、 HTTP头信息 、 返回的内容

        int code = e.getCode();
        String message = exceptionCodeConfiguration.getMessage(code);
        String request = method+" "+requestUrl;
        UnifyResponse unifyResponse = new UnifyResponse(code,message,request); // 返回的内容

        HttpHeaders httpHeaders = new HttpHeaders(); //HTTP头信息
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpStatus httpStatus = HttpStatus.resolve(e.getHttpStatusCode()); //HTTP状态码

        ResponseEntity<UnifyResponse> responseEntity = new ResponseEntity(unifyResponse,httpHeaders,httpStatus);
        return responseEntity;
    }

    //参数校验
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public UnifyResponse handleBeanValidation(HttpServletRequest req, MethodArgumentNotValidException e){
        String method = req.getMethod();
        String requestUrl = req.getRequestURI();

        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        String message = formatAllErrorMessages(errors);

        return new UnifyResponse(10001,message,method+" "+requestUrl);
    }

    private String formatAllErrorMessages(List<ObjectError> errors){
        StringBuffer errorMsg = new StringBuffer();
        errors.forEach(error ->
                errorMsg.append(error.getDefaultMessage()).append(";")
        );
        return errorMsg.toString();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public UnifyResponse handleConstrainException(HttpServletRequest req, ConstraintViolationException e){
        String method = req.getMethod();
        String requestUrl = req.getRequestURI();
        String message = e.getMessage();
        return new UnifyResponse(10001,message,method+" "+requestUrl);
//        for(ConstraintViolation error : e.getConstraintViolations()){
//            ConstraintViolation a = error;
//        }
//        return null;
    }


}
