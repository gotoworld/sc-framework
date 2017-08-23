package com.hsd.account.actor.entity.actor;

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
public class Member extends BaseEntity {
     /**用户ID*/
     private Long userId;
     /**传真*/
     private String fax;
     /**生日*/
     private Date birthday;
     /**婚姻状态0否1是*/
     private Integer maritalStatus;
     /**省份证号*/
     private String credentialNumber;
     /**收入*/
     private BigDecimal income;
     /**收入是否验证0否1是*/
     private Integer incVerifiable;
     /**其他收入*/
     private BigDecimal otherIncome;
     /**其他收入来源*/
     private String otherIncomeSrc;
     /**财务状况评分*/
     private Integer selfFico;
     /**累计工作年限*/
     private Integer cumulativeJobTenure;
     /**教育程度*/
     private String educationLevel;
     /**税收*/
     private BigDecimal tax;
     /**车辆数*/
     private Integer ownCarNum;
     /**房产数*/
     private Integer ownHouseNum;
     /**是否有小孩0否1是*/
     private Integer childrenStatus;
     /**孩子数量*/
     private Integer childrenNum;
     /**其他财产备注*/
     private String assetOther;
     /**贷款状态0否1是*/
     private Integer debtStatus;
     /**微信*/
     private String wechat;
     /**QQ*/
     private String qq;
     /**开始工作时间*/
     private Date workStartDate;
     /**户籍与居住地是否一致0否1是*/
     private Integer domicileLiveDiff;
     /**是否公司员工0否1是*/
     private Integer relation;
     /**员工工号*/
     private String staffNo;
     /**地址*/
     private String address;
     /**发展员工*/
     private String mantanceStaffNo;
     /**用户子类型*/
     private String userSubType;
     /**代理人等级*/
     private Integer proxyLevel;
     /**上级代理人*/
     private Integer proxyUserId;
     /**代理人逐级关系(1|2|3)*/
     private String proxyChain;
     /**投资状态0否1是*/
     private Integer lenderState;
     /**借款状态0否1是*/
     private Integer borrowerState;
     /**投资状态变更时间*/
     private Date dateLenderState;
     /**借款状态变更时间*/
     private Date dateBorrowerState;
     /**备注*/
     private String memo;
     /**是否删除0否1是*/
     private Integer delFlag;
     /**创建时间*/
     private Date dateCreated;
     /**更新时间*/
     private Date dateUpdated;
     /**BI时间戳*/
     private Date biUpdateTs;
}