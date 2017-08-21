package com.hsd.account.staff.dto.org;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.hsd.framework.dto.BaseDto;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrgLogLoginDto extends BaseDto {
     /**id*/
     private Long id;
     /**员工ID*/
     private Long staffId;
     /**员工名称*/
     private String staffName;
     /**类型0登录1登出*/
     private Integer type;
     /**IP地址*/
     private String ipAddr;
     /**MAC地址*/
     private String deviceMac;
     /**创建时间*/
     private Date dateCreated;

}