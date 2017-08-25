package com.hsd.account.actor.entity.identity;

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
public class Identity extends BaseEntity {
     /**客户ID*/
     private Long userId;
     /**真实姓名*/
     private String realName;
     /**证件类型:0身份证*/
     private Integer credentialType;
     /**证件编号*/
     private String credentialNumber;
     /**证件A面*/
     private String credentialPhotoA;
     /**证件B面*/
     private String credentialPhotoB;
     /**认证状态0待处理1成功2失败*/
     private Integer state;
     /**备注*/
     private String memo;
     /**版本号*/
     private Integer version;
     /**关键字*/
     private String keyword;
     /**建立者ID*/
     private Long createId;
     /**创建时间*/
     private Date dateCreated;
     /**更新时间*/
     private Date dateUpdated;
}