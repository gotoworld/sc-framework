package com.hsd.account.actor.dto.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hsd.framework.dto.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel("APP用户表 DTO")
public class UserAppDto extends BaseDto {
     @ApiModelProperty("app用户id")
     private Long id;
     @ApiModelProperty("用户id")
     private Long userId;
     @ApiModelProperty("应用id")
	 @Size(max=32,message="app_id最大32字符")
     private String appId;
     @ApiModelProperty("注册时间")
     private Date dateCreated;
     @ApiModelProperty("BI时间戳")
     private Date biUpdateTs;

}