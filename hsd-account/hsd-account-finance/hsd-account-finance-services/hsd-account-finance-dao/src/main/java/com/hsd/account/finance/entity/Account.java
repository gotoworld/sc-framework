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
public class Account extends BaseEntity {
     /**主键*/
     private Long id;
     /**域用户id,安全控制值*/
     private Long appUserId;
     /**账户类型,安全控制值*/
     private Long accountType;
     /**账户名称*/
     private String aliasName;
     /**账户币种,安全控制值*/
     private Integer currency;
     /**账户总额,安全控制值*/
     private BigDecimal totalMoney;
     /**可用余额,安全控制值*/
     private BigDecimal availableMoney;
     /**冻结资金,安全控制值*/
     private BigDecimal freezeMoney;
     /**冻结资金,安全控制值*/
     private BigDecimal inFloatMoney;
     /**账户状态0生效1冻结2注销*/
     private Integer state;
     /**账户模板id*/
     private Long accountTemplateId;
     /**模板值（json形式保存）*/
     private String expandJson;
     /**开户时间*/
     private Date dateCreated;
     /**更新时间*/
     private Date dateUpdated;
     /**校验码md5,安全控制值编码*/
     private String checkValue;
     /**充值次数*/
     private Integer rechargeCount;
     /**充值总额*/
     private BigDecimal rechargeTotalAmount;
     /**首次充值时间*/
     private Date dateRechargeFirst;
     /**提现次数*/
     private Integer withdrawCount;
     /**提现总额*/
     private BigDecimal withdrawTotalAmount;
     /**客户注册时间*/
     private Date dateUserRegister;
     /**会计科目代码*/
     private String finSubjectCode;
     /**BI时间戳*/
     private Date biUpdateTs;
}