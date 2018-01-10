package com.hsd.account.finance.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hsd.framework.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountLogFreeze extends BaseEntity {
     /**主键*/
     private Long id;
     /**域用户id*/
     private Long appUserId;
     /**支付账户id*/
     private Long accountId;
     /**操作类型[0冻结1解冻]*/
     private Integer actionType;
     /**金额*/
     private BigDecimal amount;
     /**冻结类型*/
     private Integer freezeType;
     /**冻结总额*/
     private BigDecimal freezeTotalAmount;
     /**创建时间*/
     private Date dateCreated;
     /**操作人id*/
     private Long createId;
     /**子账户类型*/
     private Long accountSubType;
     /**子账户id*/
     private Long accountSubId;
     /**外部业务id*/
     private String outRefId;
     /**BI时间戳*/
     private Date biUpdateTs;
}