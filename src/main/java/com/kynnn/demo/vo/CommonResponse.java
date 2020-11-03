package com.kynnn.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <h1>通用响应对象定义</h1>
 *
 * @author Zhudiwei
 * @date 2020/11/03
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse<T> implements Serializable {

    private Integer returnCode;
    private String returnMsg;
    private T data;

    public CommonResponse(Integer code, String message) {
        this.returnCode = code;
        this.returnMsg = message;
    }
}
