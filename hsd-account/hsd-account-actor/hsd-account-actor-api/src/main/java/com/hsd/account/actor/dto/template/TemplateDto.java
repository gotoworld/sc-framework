package com.hsd.account.actor.dto.template;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hsd.framework.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TemplateDto extends BaseDto {
     /**ID*/
     private Long id;
     /**客户类型*/
	 @NotNull(message="user_type不能为空")
     private Integer userType;
     /**模板名称*/
	 @NotNull(message="template_name不能为空")@Size(max=50,message="template_name最大50字符")
     private String templateName;
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

     /**属性name集合*/
     private List<String> attrName;
     private List<TemplateAttributeDto> attributes;
}