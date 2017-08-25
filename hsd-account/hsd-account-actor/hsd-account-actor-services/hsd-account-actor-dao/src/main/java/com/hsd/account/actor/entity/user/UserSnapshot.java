package com.hsd.account.actor.entity.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hsd.framework.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserSnapshot extends BaseEntity {
     /**客户id*/
     private Long userId;
     /**可用余额*/
     private BigDecimal balance;
     /**可用积分*/
     private Integer credit;
     /**可用合时币*/
     private Integer coin;
     /**客户等级*/
     private Integer vipLevel;
     /**VIP分数*/
     private Integer vipScore;
     /**BI时间戳*/
     private Date biUpdateTs;
}