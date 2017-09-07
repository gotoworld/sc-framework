package com.hsd.account.actor.dto.identity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hsd.framework.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IdentityDto extends BaseDto {
     /**客户ID*/
	 @NotNull(message="user_id不能为空")
     private Long userId;
     /**真实姓名*/
	 @Size(max=55,message="real_name最大55字符")
     private String realName;
     /**证件类型:0身份证*/
	 @NotNull(message="credential_type不能为空")
     private Integer credentialType;
     /**证件编号*/
	 @Size(min = 7,max=50,message="证件编号[7-50]个字符")
     private String credentialNumber;
     /**证件A面*/
	 @Size(max=255,message="credential_photo_a最大255字符")
     private String credentialPhotoA;
     /**证件B面*/
	 @Size(max=255,message="credential_photo_b最大255字符")
     private String credentialPhotoB;
     /**认证状态0待处理1成功2失败*/
     private Integer state;
     /**备注*/
	 @Size(max=255,message="memo最大255字符")
     private String memo;
     /**版本号*/
     private Integer version;
     /**关键字*/
	 @Size(max=255,message="keyword最大255字符")
     private String keyword;
     /**建立者ID*/
     private Long createId;
     /**创建时间*/
     private Date dateCreated;
     /**更新时间*/
     private Date dateUpdated;

    /** 图片验证码 */
    private String imgCaptcha;
}