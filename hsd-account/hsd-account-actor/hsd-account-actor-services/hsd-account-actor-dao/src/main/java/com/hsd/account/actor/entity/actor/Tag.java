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
public class Tag extends BaseEntity {
     /**id*/
     private Long id;
     /**名称*/
     private String name;
     /**类型1会员2供应商*/
     private Integer type;
     /**备注*/
     private String memo;
     /**排序*/
     private Integer orderNo;
     /**建立者ID*/
     private Long createId;
     /**创建时间*/
     private Date dateCreated;
     /**更新时间*/
     private Date dateUpdated;
}