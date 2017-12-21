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
     /**支付账户id*/
     private Long accountId;
     /**支付流水号*/
     private String serialNumber;
     /**交易金额*/
     private BigDecimal amount;
     /**货币类型0人民币*/
     private Integer currency;
     /**操作者域用户id*/
     private Long opAppUserId;
     /**操作者账户类型*/
     private Long opAccountType;
     /**操作者账户id*/
     private Long opAccountId;
     /**操作者子账户类型*/
     private Long opAccountSubType;
     /**操作者子账户id*/
     private Long opAccountSubId;
     /**对方域用户id*/
     private Long otherAppUserId;
     /**对方账户类型*/
     private Long otherAccountType;
     /**对方账户id*/
     private Long otherAccountId;
     /**对方子账户类型*/
     private Long otherAccountSubType;
     /**对方子账户id*/
     private Long otherAccountSubId;
     /**外部交易时间*/
     private String outTradeDate;
     /**外部订单号*/
     private String outOrderNo;
     /**外部订单名称*/
     private String outOrderName;
     /**交易状态*/
     private Integer state;
     /**业务类型:1投资扣费,2收款,3充值,4提现,6奖励,7转账,8投资奖励,9融资,10扣费,11充值手续费,12提现撤销,13提现手续费,14借款管理费,15投资冻结资金,16投资撤回解冻资金,17投资奖励扣除,19还款,20内部调账,21结息,22原交易退款,23原交易撤销*/
     private Integer tradeType;
     /**业务类型名称*/
     private String tradeTypeName;
     /**交易渠道*/
     private String tradeChannel;
     /**交易机构*/
     private Long tradeAgency;
     /**交易说明*/
     private String tradeDesc;
     /**支付方式*/
     private Integer payType;
     /**支付机构交易码,充值成功后回写,用于对账*/
     private String payTradeNo;
     /**支付回馈*/
     private String payResult;
     /**流水方向0存入1支出*/
     private Integer isDirection;
     /**冲正标记0否1是*/
     private Integer isCorrect;
     /**红字标记0否1是*/
     private Integer isRed;
     /**会计科目代码*/
     private String finSubjectCode;
     /**凭证id*/
     private String finVoucherId;
     /**交易数据json*/
     private String data;
     /**交易操作人id*/
     private Long createId;
     /**交易操作人IP*/
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