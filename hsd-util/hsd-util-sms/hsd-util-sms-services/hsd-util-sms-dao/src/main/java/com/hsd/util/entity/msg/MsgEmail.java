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
public class MsgEmail extends BaseEntity {
     /**id*/
     private Long id;
     /**发送人*/
     private String sender;
     /**收件人*/
     private String recipient;
     /**主题*/
     private String subject;
     /**短内容*/
     private String scontent;
     /**长内容*/
     private String lcontent;
     /**附件*/
     private String files;
     /**预定发送时间*/
     private Date dateSend;
     /**实际发送时间*/
     private Date dateActual;
     /**实际发送次数*/
     private Integer sendCount;
     /**发送状态0：未发送 1：发送中 2：已发送*/
     private Integer state;
}