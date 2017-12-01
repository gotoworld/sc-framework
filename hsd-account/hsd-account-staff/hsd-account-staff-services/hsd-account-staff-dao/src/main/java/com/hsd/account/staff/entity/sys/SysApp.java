package com.hsd.account.staff.entity.sys;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.hsd.framework.entity.BaseEntity;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SysApp extends BaseEntity {
     /**AppId*/
     private String id;
     /**系统名称*/
     private String name;
     /**主页登链*/
     private String loginUrl;
     /**登录链接*/
     private String mainUrl;
     /**备注*/
     private String memo;
     /**建立者id*/
     private Long createId;
     /**创建时间*/
     private Date dateCreated;
     /**更新时间*/
     private Date dateUpdated;
}