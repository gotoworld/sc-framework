package com.hsd.account.actor.dto.actor;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hsd.framework.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BusinessTypeDto extends BaseDto {
     /**id*/
     private Long id;
     /**编码*/
	 @Size(max=32,message="id最大32字符")
     private String code;
     /**名称*/
	 @Size(max=64,message="name最大64字符")
     private String name;
     /**备注*/
	 @Size(max=255,message="memo最大255字符")
     private String memo;
     /**版本号*/
     private Integer version;
     /**是否删除*/
     private Integer delFlag;
     /**建立者ID*/
     private Long createId;
     /**创建时间*/
     private Date dateCreated;
     /**更新时间*/
     private Date dateUpdated;

}