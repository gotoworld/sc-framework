package com.hsd.account.staff.entity.sys;

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
public class SysMenu extends BaseEntity {
     /**id*/
     private Long id;
     /**名称*/
     private String name;
     /**所属系统域*/
     private String domainCode;
     /**父节点*/
     private Long parentId;
     /**页面链接*/
     private String url;
     /**排序*/
     private Integer orderNo;
     /**版本号*/
     private Integer version;
     /**是否删除*/
     private Integer delFlag;
     /**创建时间*/
     private Date dateCreated;
     /**更新时间*/
     private Date dateUpdated;
     /**BI时间戳*/
     private Date biUpdateTs;
}