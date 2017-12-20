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
public class AccountSubLoan extends BaseEntity {
     /**网贷子账户id*/
     private Long id;
     /**域用户id*/
     private Long appUserId;
     /**账户类型*/
     private Long accountType;
     /**0投资/1贷款*/
     private Integer balanceDirection;
     /**子账户名称*/
     private String aliasName;
     /**支付账户id*/
     private Long accountId;
     /**币种*/
     private Integer currency;
     /**账户总额*/
     private BigDecimal totalMoney;
     /**可用余额*/
     private BigDecimal availableMoney;
     /**冻结资金*/
     private BigDecimal freezeMoney;
     /**待收资金*/
     private BigDecimal dueIn;
     /**待还资金*/
     private BigDecimal stayStill;
     /**待收利息*/
     private BigDecimal stayInterest;
     /**已赚利息*/
     private BigDecimal makeInterest;
     /**已赚奖励*/
     private BigDecimal makeReward;
     /**逾期金额*/
     private BigDecimal overdue;
     /**账户状态*/
     private Integer state;
     /**账户模板id*/
     private Long accountTemplateId;
     /**模板值（json形式保存）*/
     private String expandJson;
     /**校验码md5,安全控制值编码*/
     private String checkValue;
     /**开户日期*/
     private Date dateOpen;
     /**销户日期*/
     private Date dateClose;
     /**信用金额*/
     private BigDecimal creditAmount;
     /**抵押冻结金*/
     private BigDecimal depositAmount;
     /**风控冻结金*/
     private BigDecimal ctuAmount;
     /**系统冻结金*/
     private BigDecimal sysAmount;
     /**BI时间戳*/
     private Date biUpdateTs;
}