package com.kynnn.demo.advice;

import com.kynnn.demo.exception.PortalException;
import com.kynnn.demo.vo.CommonResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Zhudiwei
 * @date 2020/11/03
 **/
@RestControllerAdvice
public class GlobalExceptionAdvice {

    /**
     * 对 PortalException 进行统一处理
     *
     * @param request
     * @param exception
     * @return
     */
    @ExceptionHandler(value = PortalException.class)
    public CommonResponse<String> handlerCouponException(HttpServletRequest request,
                                                         PortalException exception) {
        CommonResponse<String> response = new CommonResponse<>(-1, exception.getMessage());
        response.setData(null);
        return response;
    }
}
