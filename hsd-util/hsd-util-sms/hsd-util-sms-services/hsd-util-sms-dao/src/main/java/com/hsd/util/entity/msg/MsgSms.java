package com.hsd.util.entity.msg;

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
public class MsgSms extends BaseEntity {
     /**id*/
     private Long id;
     /**收信人*/
     private String cellphone;
     /**短信内容*/
     private String content;
     /**预定发送时间*/
     private Date dateSend;
     /**实际发送时间*/
     private Date dateActual;
     /**实际发送次数*/
     private Integer sendCount;
     /**发送状态0：未发送 1：发送中 2：已发送*/
     private Integer state;
}