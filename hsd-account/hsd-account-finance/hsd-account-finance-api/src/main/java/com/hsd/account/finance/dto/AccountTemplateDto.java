package com.hsd.account.finance.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hsd.framework.dto.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("账户模板 DTO")
public class AccountTemplateDto extends BaseDto {
     @ApiModelProperty("主键")
     private Long id;
     @ApiModelProperty("账户类型")
	 @NotNull(message="type不能为空")
     private Long type;
     @ApiModelProperty("模板名称")
	 @NotNull(message="name不能为空")@Size(max=55,message="name最大55字符")
     private String name;
     @ApiModelProperty("排序")
     private Integer orderNo;
     @ApiModelProperty("删除标记(0正常1删除)")
     private Integer delFlag;
     @ApiModelProperty("建立者id")
     private Long createId;
     @ApiModelProperty("创建时间")
     private Date dateCreated;
     @ApiModelProperty("备注")
     @Size(max=255,message="memo最大255字符")
     private String memo;
     /**属性name集合*/
     private List<String> attrName;
     private List<AccountTemplateAttributeDto> attributes;

     private String typeName;
}