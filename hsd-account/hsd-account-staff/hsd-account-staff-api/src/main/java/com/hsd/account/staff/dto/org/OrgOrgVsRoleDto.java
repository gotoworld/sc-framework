package com.hsd.account.staff.dto.org;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hsd.framework.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrgOrgVsRoleDto extends BaseDto {
    /**角色id*/
    private Long roleId;
    /**组织id*/
    private Long orgId;
    /**建立者ID*/
    private Long createId;
    /**创建时间*/
    private Date dateCreated;
}