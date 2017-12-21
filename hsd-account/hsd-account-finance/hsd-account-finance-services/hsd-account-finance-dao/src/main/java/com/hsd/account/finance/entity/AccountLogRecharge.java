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
public class AccountLogRecharge extends BaseEntity {
     /**充值记录id*/
     private Long id;
     /**域用户id*/
     private Long appUserId;
     /**支付账户id*/
     private Long accountId;
     /**子账户类型*/
     private Long accountSubType;
     /**子账户id*/
     private Long accountSubId;
     /**交易订单号*/
     private String tradeNo;
     /**用户名称*/
     private String userName;
     /**真实姓名*/
     private String realName;
     /**充值金额*/
     private BigDecimal money;
     /**手续费*/
     private BigDecimal fee;
     /**到帐金额*/
     private BigDecimal accountMoney;
     /**充值状态1申请2成功3失败4撤销*/
     private Integer state;
     /**充值数据json*/
     private String data;
     /**支付方式*/
     private Integer payType;
     /**支付机构交易码,充值成功后回写,用于对账*/
     private String payTradeNo;
     /**支付回馈*/
     private String payResult;
     /**备注*/
     private String memo;
     /**用户IP*/
     private String createIp;
     /**申请时间*/
     private Date dateCreated;
     /**更新时间*/
     private Date dateUpdated;
     /**成功时间*/
     private Date dateSuccess;
     /**BI时间戳*/
     private Date biUpdateTs;
}