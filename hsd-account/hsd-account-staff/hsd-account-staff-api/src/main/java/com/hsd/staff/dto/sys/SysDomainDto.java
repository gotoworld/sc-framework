package com.hsd.staff.dto.sys;

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
public class SysDomainDto extends BaseDto {
     /**CODE*/
	@NotNull(message="code不能为空")@Size(max=50,message="code最大50字符")
     private String code;
     /**系统名称*/
	@NotNull(message="name不能为空")@Size(max=50,message="name最大50字符")
     private String name;
     /**DNS*/
	@NotNull(message="dns不能为空")@Size(max=50,message="dns最大50字符")
     private String dns;
     /**是否展示0否1是*/
     private Integer isShow;
     /**备注*/
	@Size(max=255,message="memo最大255字符")
     private String memo;
     /**排序*/
     private Integer orderNo;
     /**版本号*/
     private Integer version;
     /**关键字*/
	@Size(max=255,message="keyword最大255字符")
     private String keyword;
     /**是否删除*/
     private Integer delFlag;
     /**建立者ID*/
     private Long createId;
     /**创建时间*/
     private Date dateCreated;
     /**更新时间*/
     private Date dateUpdated;
     /**BI时间戳*/
     private Date biUpdateTs;

}