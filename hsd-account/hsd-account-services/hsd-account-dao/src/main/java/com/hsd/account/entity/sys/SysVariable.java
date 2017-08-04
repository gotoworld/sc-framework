package com.hsd.account.entity.sys;

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
public class SysVariable extends BaseEntity {
     /**ID*/
     private Long id;
     /**编码*/
     private String code;
     /**名称*/
     private String name;
     /**父级ID*/
     private Long parentId;
     /**备注*/
     private String memo;
     /**版本号*/
     private Integer version;
     /**排序*/
     private Integer orderNo;
     /**删除标记(0正常1删除)*/
     private Integer delFlag;
     /**建立者ID*/
     private Long createId;
     /**创建时间*/
     private Date dateCreated;
     /**更新时间*/
     private Date dateUpdated;
}