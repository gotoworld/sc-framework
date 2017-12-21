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
public class AccountLogWithdrawal extends BaseEntity {
     /**主键*/
     private Long id;
     /**域用户id*/
     private Long appUserId;
     /**支付账户id*/
     private Long accountId;
     /**流水号*/
     private String serialNumber;
     /**真实姓名*/
     private String realName;
     /**提现银行*/
     private String bankName;
     /**提现支行*/
     private String bankBranch;
     /**提现账户*/
     private String bankAccount;
     /**提现金额*/
     private BigDecimal money;
     /**提现手续费*/
     private BigDecimal fee;
     /**到账金额*/
     private BigDecimal arriveMoney;
     /**提现机构交易码,成功后回写,用于对账*/
     private String bankTradeNo;
     /**提现回馈*/
     private String bankResult;
     /**提现时间*/
     private Date dateCreated;
     /**审核时间*/
     private Date dateAudited;
     /**状态1待审核,2提现成功,3提现失败,4撤销提现*/
     private Integer state;
     /**审核人*/
     private Long auditorId;
     /**BI时间戳*/
     private Date biUpdateTs;
}