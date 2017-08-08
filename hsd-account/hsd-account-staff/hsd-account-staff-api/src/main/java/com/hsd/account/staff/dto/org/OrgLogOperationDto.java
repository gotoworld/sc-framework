package com.hsd.account.staff.dto.org;

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
public class OrgLogOperationDto extends BaseDto {
     /**ID*/
     private Long id;
     /**操作类型*/
	@NotNull(message="type不能为空")@Size(max=10,message="type最大10字符")
     private String type;
     /**所属系统域*/
	@NotNull(message="domain_code不能为空")@Size(max=50,message="domain_code最大50字符")
     private String domainCode;
     /**描述*/
	@NotNull(message="memo不能为空")@Size(max=50,message="memo最大50字符")
     private String memo;
     /**对象信息*/
	@Size(max=65535,message="detail_info最大65535字符")
     private String detailInfo;
     /**操作时间*/
     private Date dateCreated;
     /**操作人ID*/
     private Long createId;
     /**操作人姓名*/
	@Size(max=64,message="create_name最大64字符")
     private String createName;
     /**操作人IP*/
	@Size(max=64,message="create_ip最大64字符")
     private String createIp;
}