package com.hsd.account.channel.dto.channel;

import com.fasterxml.jackson.annotation.JsonInclude;
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
public class ChannelTypeDto extends BaseDto {
     /**类型ID*/
     private Long id;
     /**类型名称*/
	 @NotNull(message="type不能为空")@Size(max=100,message="type最大100字符")
     private String type;
     /**创建时间*/
     private Date dateCreated;
     /**创建人*/
	 @Size(max=50,message="created_by最大50字符")
     private String createdBy;
     /**BI时间戳*/
     private Date biUpdateTs;
     /**是否删除*/
     private Integer delFlag;

}