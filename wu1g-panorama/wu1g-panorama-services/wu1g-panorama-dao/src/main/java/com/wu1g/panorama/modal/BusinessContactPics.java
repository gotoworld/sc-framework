package com.wu1g.panorama.modal;


import lombok.Data;

import java.util.Date;

@Data
public class panoramaContactPics extends BaseEntity {
    /**
     * 录入商家id
     */
    private Long panoramaContactId;
    /**
     * 图片地址
     */
    private String picUrl;
    /**
     * 创建时间
     */
    private Date createdAt;

}