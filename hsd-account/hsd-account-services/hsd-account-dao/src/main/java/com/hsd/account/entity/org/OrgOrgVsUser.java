package com.hsd.account.entity.org;

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
public class OrgOrgVsUser extends BaseEntity {
     /**用户id*/
     private Long userId;
     /**组织id*/
     private Long orgId;
     /**建立者ID*/
     private Long createId;
     /**创建时间*/
     private Date dateCreated;
}