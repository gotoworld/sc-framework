package com.hsd.account.actor.entity.template;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hsd.framework.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TemplateAttribute extends BaseEntity {
     /**id*/
     private Long id;
     /**所属模板*/
     private Long templateId;
     /**属性编码*/
     private String attributeCode;
     /**属性名称*/
     private String attributeName;
     /**输入类型*/
     private String inputType;
     /**是否必填*/
     private Integer required;
     /**字段可选值*/
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