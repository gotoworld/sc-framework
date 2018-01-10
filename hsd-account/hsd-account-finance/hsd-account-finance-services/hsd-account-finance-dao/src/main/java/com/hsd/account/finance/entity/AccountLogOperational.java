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
     /**操作类型 1冻结,2解冻,3状态变更*/
     private Integer type;
     /**交易数据json*/
     private String data;
     /**交易说明*/
     private String memo;
     /**操作人id*/
     private Long createId;
     /**IP地址*/
     private String createIp;
     /**交易时间*/
     private Date dateCreated;
     /**BI时间戳*/
     private Date biUpdateTs;
}