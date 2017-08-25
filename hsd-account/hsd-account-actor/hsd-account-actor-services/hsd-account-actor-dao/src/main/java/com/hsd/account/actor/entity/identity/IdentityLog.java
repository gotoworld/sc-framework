package com.hsd.account.actor.entity.identity;

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
public class IdentityLog extends BaseEntity {
     /**id*/
     private Long id;
     /**客户ID*/
     private Long userId;
     /**证件类型:0身份证*/
     private Integer credentialType;
     /**证件编号*/
     private String credentialNumber;
     /**证件A面*/
     private String credentialPhotoA;
     /**证件B面*/
     private String credentialPhotoB;
     /**真实姓名*/
     private String realName;
     /**认证状态0待处理1成功2失败*/
     private Integer state;
     /**BI时间戳*/
     private Date biUpdateTs;
}