package com.hsd.account.staff.entity.sys;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.hsd.framework.entity.BaseEntity;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserApp extends BaseEntity {
     /**app用户id*/
     private Long id;
     /**用户id*/
     private Long userId;
     /**应用id*/
     private String appId;
     /**注册时间*/
     private Date dateCreated;
     /**BI时间戳*/
     private Date biUpdateTs;
}