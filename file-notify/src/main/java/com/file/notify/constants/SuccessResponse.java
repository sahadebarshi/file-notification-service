package com.file.notify.constants;

import lombok.Data;

@Data
public class SuccessResponse {

    private String message;
    private Integer code;
    public SuccessResponse(String msg, Integer code)
    {
        this.message = msg;
        this.code = code;
    }
}
