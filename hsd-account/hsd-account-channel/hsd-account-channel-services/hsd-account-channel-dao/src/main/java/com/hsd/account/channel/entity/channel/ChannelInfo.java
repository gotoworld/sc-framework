package com.hsd.account.channel.entity.channel;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hsd.framework.entity.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChannelInfo extends BaseEntity {
     /**渠道商ID*/
     private Long id;
     /**渠道商名称*/
     private String channelName;
     /**渠道商类型*/
     private Long channeltype;
     /**性别*/
     private String gender;
     /**电话1*/
     private String phone1;
     /**电话2*/
     private String phone2;
     /**传真*/
     private String fax;
     /**生日*/
     private Date birthday;
     /**婚姻状态*/
     private Integer maritalStatus;
     /**身份证号*/
     private String idNo;
     /**收入*/
     private BigDecimal income;
     /**收入是否验证*/
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
     /**是否有小孩*/
     private Integer childrenStatus;
     /**孩子数量*/
     private Integer childrenNum;
     /**其他财产备注*/
     private String assetOther;
     /**贷款状态*/
     private Integer debtStatus;
     /**微信*/
     private String wechat;
     /**QQ*/
     private String qq;
     /**备注信息*/
     private String remark;
     /**开始工作时间*/
     private Date workStartDate;
     /**户籍与居住地是否一致*/
     private Integer domicileLiveDiff;
     /**创建日期*/
     private Date dateCreated;
     /**是否公司员工*/
     private Integer relation;
     /**员工工号*/
     private String staffNo;
     /**地址*/
     private String address;
     /**BI时间戳*/
     private Date biUpdateTs;
     /**是否删除*/
     private Integer delFlag;
}