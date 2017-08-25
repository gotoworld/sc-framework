package com.hsd.account.actor.dto.user;

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
public class UserrLoginLogDto extends BaseDto {
     /**id*/
     private Long id;
     /**客户ID*/
     private Long userId;
     /**员工名称*/
	 @NotNull(message="user_name不能为空")@Size(max=32,message="user_name最大32字符")
     private String userName;
     /**类型0登录1登出*/
	 @NotNull(message="type不能为空")
     private Integer type;
     /**IP地址*/
	 @NotNull(message="ip_addr不能为空")@Size(max=64,message="ip_addr最大64字符")
     private String ipAddr;
     /**MAC地址*/
	 @Size(max=32,message="device_mac最大32字符")
     private String deviceMac;
     /**创建时间*/
     private Date dateCreated;

}