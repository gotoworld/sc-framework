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
public class AccountLog extends BaseEntity {
     /**主键*/
     private Long id;
     /**交易编号*/
     private String tradeCode;
     /**交易记录id*/
     private Long accountTradeLogId;
     /**交易金额*/
     private BigDecimal amount;
     /**货币类型*/
     private Integer currency;
     /**域用户id*/
     private Long appUserId;
     /**支付账户id*/
     private Long accountId;
     /**子账户类型*/
     private Long accountSubType;
     /**子账户id*/
     private Long accountSubId;
     /**账户余额*/
     private BigDecimal accountBalance;
     /**账户类型*/
     private Long accountType;
     /**对方账户类型*/
     private Long otherAccountType;
     /**对方账户id*/
     private Long otherAccountId;
     /**对方子账户类型*/
     private Long otherAccountSubType;
     /**对方子账户id*/
     private Long otherAccountSubId;
     /**外部订单号*/
     private String outOrderNo;
     /**外部交易时间*/
     private String outTradeDate;
     /**交易机构*/
     private Long tradeAgency;
     /**交易说明*/
     private String tradeDesc;
     /**交易数据json*/
     private String data;
     /**冲正标记*/
     private Integer isCorrect;
     /**流水方向0存入1支出*/
     private Integer type;
     /**红字标记*/
     private Integer isRed;
     /**删除标记*/
     private Integer delFlag;
     /**凭证id*/
     private String finVoucherId;
     /**交易操作人id*/
     private Long createId;
     /**IP*/
     private String createIp;
     /**交易时间*/
     private Date dateCreated;
     /**最后修改时间*/
     private Date dateUpdated;
     /**备注*/
     private String memo;
     /**BI时间戳*/
     private Date biUpdateTs;
}