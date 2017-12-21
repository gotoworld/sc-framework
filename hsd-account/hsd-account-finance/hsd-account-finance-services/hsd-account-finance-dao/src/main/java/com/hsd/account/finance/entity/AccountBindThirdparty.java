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
public class AccountBindThirdparty extends BaseEntity {
     /**主键*/
     private Long id;
     /**域用户id*/
     private Long appUserId;
     /**支付账户id*/
     private Long accountId;
     /**真实姓名*/
     private String realName;
     /**身份证号码*/
     private String cardNo;
     /**预留手机号码*/
     private String cellphone;
     /**三方账户币种*/
     private Integer thirdpartyCurrency;
     /**三方账户类型0银行卡*/
     private Integer thirdpartyType;
     /**三方账户组织名称*/
     private String thirdpartyName;
     /**三方账户开户机构*/
     private String thirdpartyBranch;
     /**三方账户账号*/
     private String thirdpartyAccount;
     /**三方账号失效日期*/
     private Date thirdpartyDateExpiry;
     /**状态*/
     private Integer state;
     /**绑定时间*/
     private Date dateBind;
     /**成功时间*/
     private Date dateSuccess;
     /**解绑时间*/
     private Date dateUnbind;
     /**备注*/
     private String memo;
     /**BI时间戳*/
     private Date biUpdateTs;
}