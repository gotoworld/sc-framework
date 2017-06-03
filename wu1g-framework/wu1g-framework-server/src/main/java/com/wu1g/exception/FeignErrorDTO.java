package com.wu1g.exception;

import lombok.Data;

/**
 * Created by Administrator on 17-6-1.
 */
@Data
public class FeignErrorDTO {
    private Long timestamp;
    private int status ;
    private String error ;
    private String exception ;
    private String message;
    private String path;
}
