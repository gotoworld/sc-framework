package com.hsd.msg.dto.msg;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hsd.framework.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MsgSmsDto extends BaseDto {
     /**id*/
     private Long id;
     /**收信人*/
	 @NotNull(message="cellphone不能为空")@Size(max=22,message="cellphone最大22字符")
     private String cellphone;
     /**短信内容*/
	 @NotNull(message="content不能为空")@Size(max=255,message="content最大255字符")
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