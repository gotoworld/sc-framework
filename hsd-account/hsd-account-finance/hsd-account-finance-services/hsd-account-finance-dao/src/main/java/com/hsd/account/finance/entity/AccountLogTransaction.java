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
public class AccountLogTransaction extends BaseEntity {
     /**主键*/
     private Long id;
     /**支付账户id*/
     private Long accountId;
     /**支付流水号*/
     private String serialNumber;
     /**外部交易时间*/
     private String outTradeDate;
     /**交易编号*/
     private String tradeCode;
     /**交易金额*/
     private BigDecimal tradeAmount;
     /**货币类型*/
     private Integer currency;
     /**冻结状态*/
     private Integer isFreeze;
     /**转出账户类型*/
     private Long outAccountType;
     /**转出账户id*/
     private Long outAccountId;
     /**转出子账户类型*/
     private Long outAccountSubType;
     /**转出子账户id*/
     private Long outAccountSubId;
     /**转入账户类型*/
     private Long inAccountType;
     /**转入账户id*/
     private Long inAccountId;
     /**转入子账户类型*/
     private Long inAccountSubType;
     /**转入子账户id*/
     private Long inAccountSubId;
     /**操作人id*/
     private Long actionAppUserId;
     /**交易状态*/
     private Integer state;
     /**交易渠道*/
     private String tradeChannel;
     /**交易机构*/
     private Long tradeAgency;
     /**外部订单号*/
     private String outOrderNo;
     /**外部订单名称*/
     private String outOrderName;
     /**交易说明*/
     private String tradeDesc;
     /**交易数据json*/
     private String data;
     /**业务类型:1投资扣费,2收款,3充值,4提现,6奖励,7转账,8投资奖励,9融资,10扣费,11充值手续费,12提现撤销,13提现手续费,14借款管理费,15投资冻结资金,16投资撤回解冻资金,17投资奖励扣除,19还款,20内部调账,21结息,22原交易退款,23原交易撤销*/
     private Integer bizType;
     /**业务类型名称*/
     private String bizTypeName;
     /**业务ID*/
     private String bizId;
     /**业务描述*/
     private String bizName;
     /**相关交易id*/
     private String refTradeId;
     /**会计科目代码*/
     private String finSubjectCode;
     /**凭证id*/
     private String finVoucherId;
     /**交易时间*/
     private Date dateCreated;
     /**最后修改时间*/
     private Date dateUpdated;
     /**备注*/
     private String memo;
     /**BI时间戳*/
     private Date biUpdateTs;
}