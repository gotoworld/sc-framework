package com.hsd.account.channel.dto.channel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hsd.framework.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChannelInfoDto extends BaseDto {
     /**渠道商ID*/
     private Long id;
     /**渠道商登录账户*/
	 @NotNull(message="account不能为空")@Size(max=255,message="account最大255字符")
     private String account;
     /**渠道商登录密码*/
     private String pwd;
     /**渠道商名称*/
	 @NotNull(message="channel_name不能为空")@Size(max=50,message="channel_name最大50字符")
     private String channelName;
     /**渠道商类型*/
	 @NotNull(message="channeltype不能为空")
     private int channeltype;
     /**性别(0:男，1：女，2：保密）*/
	 @NotNull(message="gender不能为空")
     private int gender;
     /**电话1*/
	 @NotNull(message="phone1不能为空")@Size(max=50,message="phone1最大50字符")
     private String phone1;
     /**电话2*/
	 @Size(max=50,message="email最大50字符")
     private String email;
     /**传真*/
	 @Size(max=50,message="fax最大50字符")
     private String fax;
     /**生日*/
	 @DateTimeFormat(pattern="yyyy-MM-dd")
     private Date birthday;
     /**婚姻状态(0：已婚，1,：未婚）*/
     private Integer maritalStatus;
     /**身份证号*/
     private String idNo;
     /**收入*/
     private BigDecimal income;
     /**收入是否验证（0：是，1：否）*/
     private Integer incVerifiable;
     /**其他收入*/
     private BigDecimal otherIncome;
     /**其他收入来源*/
	 @Size(max=50,message="other_income_src最大50字符")
     private String otherIncomeSrc;
     /**财务状况评分*/
     private Integer selfFico;
     /**累计工作年限*/
     private Integer cumulativeJobTenure;
     /**教育程度*/
	 @Size(max=50,message="education_level最大50字符")
     private String educationLevel;
     /**税收*/
     private BigDecimal tax;
     /**车辆数*/
     private Integer ownCarNum;
     /**房产数*/
     private Integer ownHouseNum;
     /**是否有小孩（0：是，1：否）*/
     private Integer childrenStatus;
     /**孩子数量*/
     private Integer childrenNum;
     /**其他财产备注*/
	 @Size(max=100,message="asset_other最大100字符")
     private String assetOther;
     /**贷款状态(0:已贷，1：未贷）*/
     private Integer debtStatus;
     /**微信*/
	 @Size(max=50,message="wechat最大50字符")
     private String wechat;
     /**QQ*/
	 @Size(max=50,message="qq最大50字符")
     private String qq;
     /**备注信息*/
	 @Size(max=500,message="remark最大500字符")
     private String remark;
     /**开始工作时间*/
	 @DateTimeFormat(pattern="yyyy-MM-dd")
     private Date workStartDate;
     /**户籍与居住地是否一致*/
     private Integer domicileLiveDiff;
     /**创建日期*/
     private Date dateCreated;
     /**是否公司员工（0：是，1：否）*/
	 @NotNull(message="relation不能为空")
     private Integer relation;
     /**员工工号*/
	 @Size(max=50,message="staff_no最大50字符")
     private String staffNo;
     /**地址*/
     private String address;
     /**BI时间戳*/
     private Date biUpdateTs;
     /**渠道商状态（0:停用，1：启用）*/
     private Integer staus;
     /**是否删除*/
     private Integer delFlag;

}