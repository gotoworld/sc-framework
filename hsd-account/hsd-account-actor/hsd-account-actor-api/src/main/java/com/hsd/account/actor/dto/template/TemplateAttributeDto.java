package com.hsd.account.actor.dto.template;

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
public class TemplateAttributeDto extends BaseDto {
     /**id*/
     private Long id;
     /**所属模板*/
     private Long templateId;
     /**属性编码*/
	 @Size(max=50,message="attribute_code最大50字符")
     private String attributeCode;
     /**属性名称*/
	 @Size(max=50,message="attribute_name最大50字符")
     private String attributeName;
     /**输入类型*/
	 @Size(max=20,message="input_type最大20字符")
     private String inputType;
     /**是否必填*/
     private Integer required;
     /**字段可选值*/
	 @Size(max=1000,message="option_values最大1000字符")
     private String optionValues;
     /**建立者ID*/
     private Long createId;
     /**创建时间*/
     private Date dateCreated;
     /**更新时间*/
     private Date dateUpdated;
     /**BI时间戳*/
     private Date biUpdateTs;

}