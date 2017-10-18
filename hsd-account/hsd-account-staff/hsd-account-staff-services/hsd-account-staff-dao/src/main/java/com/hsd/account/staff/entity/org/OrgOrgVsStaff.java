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
public class OrgOrgVsStaff extends BaseEntity {
     /**员工id*/
     private Long staffId;
     /**组织id*/
     private Long orgId;
     /**建立者ID*/
     private Long createId;
     /**创建时间*/
     private Date dateCreated;

     /**员工职级*/
     private String level;
     /**组织编码*/
     private String code;
}