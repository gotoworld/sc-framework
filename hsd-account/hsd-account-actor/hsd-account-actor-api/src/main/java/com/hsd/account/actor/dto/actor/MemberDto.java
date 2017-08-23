package com.hsd.account.actor.dto.actor;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.hsd.framework.dto.BaseDto;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberDto extends BaseDto {
     /**用户ID*/
	 @NotNull(message="user_id不能为空")
     private Long userId;
     /**传真*/
	 @Size(max=50,message="fax最大50字符")
     private String fax;
     /**生日*/
     private Date birthday;
     /**婚姻状态0否1是*/
     private Integer maritalStatus;
     /**省份证号*/
	 @Size(max=50,message="credential_number最大50字符")
     private String credentialNumber;
     /**收入*/
     private BigDecimal income;
     /**收入是否验证0否1是*/
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
     /**是否有小孩0否1是*/
     private Integer childrenStatus;
     /**孩子数量*/
     private Integer childrenNum;
     /**其他财产备注*/
	 @Size(max=100,message="asset_other最大100字符")
     private String assetOther;
     /**贷款状态0否1是*/
     private Integer debtStatus;
     /**微信*/
	 @Size(max=50,message="wechat最大50字符")
     private String wechat;
     /**QQ*/
	 @Size(max=50,message="qq最大50字符")
     private String qq;
     /**开始工作时间*/
     private Date workStartDate;
     /**户籍与居住地是否一致0否1是*/
     private Integer domicileLiveDiff;
     /**是否公司员工0否1是*/
     private Integer relation;
     /**员工工号*/
	 @Size(max=50,message="staff_no最大50字符")
     private String staffNo;
     /**地址*/
	 @Size(max=200,message="address最大200字符")
     private String address;
     /**发展员工*/
	 @Size(max=50,message="mantance_staff_no最大50字符")
     private String mantanceStaffNo;
     /**用户子类型*/
	 @Size(max=50,message="user_sub_type最大50字符")
     private String userSubType;
     /**代理人等级*/
     private Integer proxyLevel;
     /**上级代理人*/
     private Integer proxyUserId;
     /**代理人逐级关系(1|2|3)*/
	 @Size(max=50,message="proxy_chain最大50字符")
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
	 @Size(max=255,message="memo最大255字符")
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