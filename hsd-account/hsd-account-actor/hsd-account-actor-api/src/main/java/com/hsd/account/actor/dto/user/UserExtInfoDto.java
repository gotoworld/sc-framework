package com.hsd.account.actor.dto.user;

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
public class UserExtInfoDto extends BaseDto {
     /**id*/
     private Long id;
     /**用户ID*/
	 @NotNull(message="user_id不能为空")
     private Long userId;
     /**所属模板id*/
	 @NotNull(message="template_id不能为空")
     private Long templateId;
     /**模板值（json形式保存）*/
	 @NotNull(message="attribute_json不能为空")@Size(max=4000,message="attribute_json最大4000字符")
     private String attributeJson;
     /**备注*/
	 @Size(max=255,message="memo最大255字符")
     private String memo;
     /**排序*/
     private Integer orderNo;
     /**版本号*/
     private Integer version;
     /**建立者ID*/
     private Long createId;
     /**创建时间*/
     private Date dateCreated;
     /**更新时间*/
     private Date dateUpdated;

}