package com.hsd.framework;

import lombok.Data;

@Data
public class FeignErrorDTO {
    private Long timestamp;
    private int status ;
    private String error ;
    private String exception ;
    private String message;
    private String path;
}
