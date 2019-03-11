package com.sst.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class GlobalCatchException {
    /**
     * 拦截Exception类的异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Map<String, Object> exceptionHandler(Exception e) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("respCode", "9999");
        result.put("respMsg", e.getMessage());
        //正常响应实体，如CommonResp
        return result;
    }
}
