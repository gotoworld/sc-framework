package com.hsd.staff.entity.org;

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
public class OrgInfo extends BaseEntity {
     /**ID*/
     private Long id;
     /**编码*/
     private String code;
     /**类型0企业1部门2组*/
     private Integer type;
     /**名称*/
     private String name;
     /**父级ID*/
     private Long parentId;
     /**状态0启动*/
     private Integer state;
     /**备注*/
     private String memo;
     /**版本号*/
     private Integer version;
     /**排序*/
     private Integer orderNo;
     /**关键字*/
     private String keyword;
     /**删除标记(0正常1删除)*/
     private Integer delFlag;
     /**建立者ID*/
     private Long createId;
     /**创建时间*/
     private Date dateCreated;
     /**更新时间*/
     private Date dateUpdated;
}