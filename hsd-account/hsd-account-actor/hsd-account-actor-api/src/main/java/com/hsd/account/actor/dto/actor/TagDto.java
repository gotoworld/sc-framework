package com.hsd.account.actor.dto.actor;

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
public class TagDto extends BaseDto {
     /**id*/
     private Long id;
     /**名称*/
	 @NotNull(message="name不能为空")@Size(max=50,message="name最大50字符")
     private String name;
     /**类型0会员1供应商*/
	 @NotNull(message="type不能为空")
     private Integer type;
     /**备注*/
	 @Size(max=255,message="memo最大255字符")
     private String memo;
     /**排序*/
     private Integer orderNo;
     /**建立者ID*/
     private Long createId;
     /**创建时间*/
     private Date dateCreated;
     /**更新时间*/
     private Date dateUpdated;

}