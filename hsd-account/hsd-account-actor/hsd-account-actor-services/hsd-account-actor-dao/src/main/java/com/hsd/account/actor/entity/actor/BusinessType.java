package com.hsd.account.actor.entity.actor;

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
public class BusinessType extends BaseEntity {
     /**id*/
     private String id;
     /**编码*/
     private String code;
     /**名称*/
     private String name;
     /**备注*/
     private String memo;
     /**版本号*/
     private Integer version;
     /**是否删除*/
     private Integer delFlag;
     /**建立者ID*/
     private Long createId;
     /**创建时间*/
     private Date dateCreated;
     /**更新时间*/
     private Date dateUpdated;
}