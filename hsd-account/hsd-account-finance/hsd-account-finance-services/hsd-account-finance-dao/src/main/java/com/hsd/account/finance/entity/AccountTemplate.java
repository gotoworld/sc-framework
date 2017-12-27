package com.hsd.account.finance.entity;

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
public class AccountTemplate extends BaseEntity {
     /**主键*/
     private Long id;
     /**账户类型*/
     private Long type;
     /**模板名称*/
     private String name;
     /**排序*/
     private Integer orderNo;
     /**删除标记(0正常1删除)*/
     private Integer delFlag;
     /**建立者id*/
     private Long createId;
     /**创建时间*/
     private Date dateCreated;
     private String memo;

     private String typeName;
}