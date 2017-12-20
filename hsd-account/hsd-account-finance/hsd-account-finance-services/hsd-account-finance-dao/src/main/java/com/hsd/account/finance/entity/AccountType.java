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
public class AccountType extends BaseEntity {
     /**主键*/
     private Long id;
     /**名称*/
     private String name;
     /**账户性质0个人1企业*/
     private Integer isEnterprise;
     /**允许充值*/
     private Integer isRecharge;
     /**允许提现*/
     private Integer isWithdraw;
     /**允许透支*/
     private Integer isOverdraft;
     /**允许支付*/
     private Integer isPay;
     /**允许转入*/
     private Integer isShiftIn;
     /**允许转出*/
     private Integer isShiftOut;
     /**安全保障等级*/
     private Integer securityLevel;
     /**简介*/
     private String briefInfo;
     /**排序*/
     private Integer orderNo;
     /**删除标记(0正常1删除)*/
     private Integer delFlag;
     /**建立者id*/
     private Long createId;
     /**创建时间*/
     private Date dateCreated;
     /**更新时间*/
     private Date dateUpdated;
     /**BI时间戳*/
     private Date biUpdateTs;
}