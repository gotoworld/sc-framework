package com.hsd.account.finance.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("账户模板属性 DTO")
public class AccountTemplateAttributeDto extends BaseDto {
     @ApiModelProperty("id")
     private Long id;
     @ApiModelProperty("账户类型")
	 @NotNull(message="type不能为空")
     private Long type;
     @ApiModelProperty("所属模板")
     private Long templateId;
     @ApiModelProperty("属性编码")
	 @Size(max=50,message="attribute_code最大50字符")
     private String attributeCode;
     @ApiModelProperty("属性名称")
	 @Size(max=50,message="attribute_name最大50字符")
     private String attributeName;
     @ApiModelProperty("输入类型")
	 @Size(max=20,message="input_type最大20字符")
     private String inputType;
     @ApiModelProperty("是否必填")
     private Integer required;
     @ApiModelProperty("字段可选值")
	 @Size(max=1000,message="option_values最大1000字符")
     private String optionValues;
     @ApiModelProperty("排序")
     private Integer orderNo;
     @ApiModelProperty("建立者id")
     private Long createId;
     @ApiModelProperty("创建时间")
     private Date dateCreated;
     @ApiModelProperty("更新时间")
     private Date dateUpdated;
     @ApiModelProperty("BI时间戳")
     private Date biUpdateTs;

}