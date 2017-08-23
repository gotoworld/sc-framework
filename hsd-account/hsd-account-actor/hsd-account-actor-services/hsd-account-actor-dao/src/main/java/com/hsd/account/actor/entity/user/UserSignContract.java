package com.hsd.account.actor.entity.user;

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
public class UserSignContract extends BaseEntity {
     /**id*/
     private Long id;
     /**用户id*/
     private Long userId;
     /**协议编码*/
     private String contractId;
     /**协议版本名称*/
     private String contractName;
     /**签约时间*/
     private Date signDate;
     /**备注*/
     private String memo;
     /**建立者ID*/
     private Long createId;
     /**创建时间*/
     private Date dateCreated;
}