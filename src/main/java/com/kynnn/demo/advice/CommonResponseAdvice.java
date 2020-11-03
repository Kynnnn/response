package com.kynnn.demo.advice;

import com.kynnn.demo.annotation.IgnoreResponseAdvice;
import com.kynnn.demo.vo.CommonResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * <h1>统一响应</h1>
 *
 * @author Zhudiwei
 * @date 2020/11/03
 **/
@RestControllerAdvice
public class CommonResponseAdvice implements ResponseBodyAdvice<Object> {
    /**
     * 判断是否要对响应进行处理
     *
     * @param methodParameter
     * @param aClass
     * @return
     */
    @Override
    @SuppressWarnings("all")
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        //如果当前的 类 标识了 @IgnoreResponseAdvice 注解，不需要处理
        if (methodParameter.getDeclaringClass().isAnnotationPresent(IgnoreResponseAdvice.class)) {
            return false;
        }
        //如果当前的 方法 标识了 @IgnoreResponseAdvice 注解，不需要处理
        if (methodParameter.getMethod().isAnnotationPresent(IgnoreResponseAdvice.class)) {
            return false;
        }
        //对响应进行处理，执行 beforeBodyWrite 方法 ↓
        return true;
    }

    @Override
    @SuppressWarnings("all")
    public Object beforeBodyWrite(Object o,
                                  MethodParameter methodParameter,
                                  MediaType mediaType, Class aClass,
                                  ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {

        //定义最终的返回对象
        CommonResponse<Object> response = new CommonResponse<>(1, "成功");
        // 如果o是null，则不需要设置data
        if (null == o) {
            return response;
        // 如果 o 已经是 CommonResponse，则不需要处理
        }else if (o instanceof CommonResponse){
            response=(CommonResponse<Object>) o;
        }else{
            //否则，把 o 作为 CommonResponse 的 data，set进去
            response.setData(o);
        }
        return response;
    }
}
