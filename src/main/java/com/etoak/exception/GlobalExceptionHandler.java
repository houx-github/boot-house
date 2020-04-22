package com.etoak.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(ParamException.class)
    public ModelAndView handlerParamException(ParamException e){
        log.error(e.getMessage(),e);
        ModelAndView mv=new ModelAndView();
        mv.addObject("error",e.getMessage());
        mv.setViewName("error");
        return mv;
    }
}
