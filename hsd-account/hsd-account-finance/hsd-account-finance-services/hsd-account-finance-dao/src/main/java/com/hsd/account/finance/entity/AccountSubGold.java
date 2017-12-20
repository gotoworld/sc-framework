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
public class AccountSubGold extends BaseEntity {
     /**贵金属账户id*/
     private Long id;
     /**域用户id*/
     private Long appUserId;
     /**子账户类型*/
     private Long type;
     /**子账户名称*/
     private String aliasName;
     /**支付账户id*/
     private Long accountId;
     /**金总克重*/
     private BigDecimal totalGold;
     /**购买次数*/
     private Integer buyCount;
     /**购买总额*/
     private BigDecimal totalAmount;
     /**累计收益*/
     private BigDecimal cumulativeIncome;
     /**首次购买时间*/
     private Date dateBuyFirst;
     /**是否购买过新手金*/
     private Integer isBuyNovice;
     /**账户状态*/
     private Integer state;
     /**校验码md5,安全控制值编码*/
     private String checkValue;
     /**开户日期*/
     private Date dateOpen;
     /**销户日期*/
     private Date dateClose;
     /**账户模板id*/
     private Long accountTemplateId;
     /**模板值（json形式保存）*/
     private String expandJson;
     /**BI时间戳*/
     private Date biUpdateTs;
}