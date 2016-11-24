package com.wu1g.panorama.modal;


import lombok.Data;

import java.util.Date;
@Data
public class panoramaContact extends BaseEntity {
    /**
     * id
     */
    private Long id;
    /**
     * 行业类型
     */
    private String panoramaType;
    /**
     * 商户名称
     */
    private String panoramaName;
    /**
     * 联系人
     */
    private String contactName;
    /**
     * 电话号码
     */
    private String telphone;
    /**
     * 省
     */
    private String province;
    /**
     * 市
     */
    private String city;
    /**
     * 区县
     */
    private String county;
    /**
     * 地址
     */
    private String adderss;
    /**
     * 简介
     */
    private String briefInfo;
    /**
     * 活动详情
     */
    private String detailInfo;
    /**
     * 状态0待处理1未通过2成功
     */
    private Integer state;
    /**
     * VERSION
     */
    private Integer version;
    /**
     * 序号
     */
    private Long sortNum;
    /**
     * 创建人id
     */
    private Long createdId;
    /**
     * 创建人名称
     */
    private String createdName;
    /**
     * 创建时间
     */
    private Date createdAt;
    /**
     * 修改时间
     */
    private Date updatedAt;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 删除标识：0正常  1删除
     */
    private Byte delFlag;

    /**地区名称*/
    private String areaName;
    /**状态名称*/
    private String stateName;
    /**状态标记 0 未通过 1已通过*/
    private String stateFlag;

}