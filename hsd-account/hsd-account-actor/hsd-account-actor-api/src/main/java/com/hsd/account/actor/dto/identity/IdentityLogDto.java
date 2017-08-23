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
public class IdentityLogDto extends BaseDto {
     /**id*/
     private Long id;
     /**用户ID*/
	 @NotNull(message="user_id不能为空")
     private Long userId;
     /**证件类型:0身份证*/
	 @NotNull(message="credential_type不能为空")
     private Integer credentialType;
     /**证件编号*/
	 @Size(max=50,message="credential_number最大50字符")
     private String credentialNumber;
     /**证件A面*/
	 @Size(max=255,message="credential_photo_a最大255字符")
     private String credentialPhotoA;
     /**证件B面*/
	 @Size(max=255,message="credential_photo_b最大255字符")
     private String credentialPhotoB;
     /**真实姓名*/
	 @Size(max=55,message="real_name最大55字符")
     private String realName;
     /**认证状态0待处理1成功2失败*/
     private Integer state;
     /**BI时间戳*/
     private Date biUpdateTs;

}