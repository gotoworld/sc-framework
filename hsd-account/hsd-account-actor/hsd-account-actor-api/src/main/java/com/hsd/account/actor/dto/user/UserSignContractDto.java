package com.hsd.account.actor.dto.user;

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
public class UserSignContractDto extends BaseDto {
     /**id*/
     private Long id;
     /**用户id*/
	 @NotNull(message="user_id不能为空")
     private Long userId;
     /**协议编码*/
	 @NotNull(message="contract_id不能为空")@Size(max=30,message="contract_id最大30字符")
     private String contractId;
     /**协议版本名称*/
	 @Size(max=30,message="contract_name最大30字符")
     private String contractName;
     /**签约时间*/
     private Date signDate;
     /**备注*/
	 @Size(max=255,message="memo最大255字符")
     private String memo;
     /**建立者ID*/
     private Long createId;
     /**创建时间*/
     private Date dateCreated;

}