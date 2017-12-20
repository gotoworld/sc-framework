package com.hsd.account.finance.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hsd.framework.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountLogOperational extends BaseEntity {
     /**主键*/
     private Long id;
     /**域用户id*/
     private Long appUserId;
     /**用户名称*/
     private String userName;
     /**支付账户id*/
     private Long accountId;
     /**子账户类型*/
     private Long accountSubType;
     /**子账户id*/
     private Long accountSubId;
     /**流水类型*/
     private Integer type;
     /**交易时间*/
     private Date dateCreated;
     /**交易数据json*/
     private String data;
     /**交易说明*/
     private String memo;
     /**IP地址*/
     private String ip;
     /**BI时间戳*/
     private Date biUpdateTs;
}