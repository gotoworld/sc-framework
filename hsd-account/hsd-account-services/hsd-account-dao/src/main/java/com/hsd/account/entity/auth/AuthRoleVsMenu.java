package com.hsd.account.entity.auth;

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
public class AuthRoleVsMenu extends BaseEntity {
     /**角色id*/
     private Long roleId;
     /**菜单id*/
     private Long menuId;
     /**建立者ID*/
     private Long createId;
     /**创建时间*/
     private Date dateCreated;
}