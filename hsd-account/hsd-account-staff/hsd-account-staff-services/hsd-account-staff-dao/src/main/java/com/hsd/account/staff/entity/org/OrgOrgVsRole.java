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
public class OrgOrgVsRole extends BaseEntity {
     /**角色id*/
     private Long roleId;
     /**组织id*/
     private Long orgId;
     /**建立者ID*/
     private Long createId;
     /**创建时间*/
     private Date dateCreated;
}