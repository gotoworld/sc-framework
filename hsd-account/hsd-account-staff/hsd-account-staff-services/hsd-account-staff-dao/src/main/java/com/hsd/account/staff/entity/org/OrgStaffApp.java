package com.hsd.account.staff.entity.org;

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
public class OrgStaffApp extends BaseEntity {
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