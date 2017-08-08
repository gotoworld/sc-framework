package com.hsd.account.staff.entity.auth;

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
public class AuthRoleVsPerm extends BaseEntity {
     /**角色id*/
     private Long roleId;
     /**权限id*/
     private String permId;
     /**建立者ID*/
     private Long createId;
     /**创建时间*/
     private Date dateCreated;
}