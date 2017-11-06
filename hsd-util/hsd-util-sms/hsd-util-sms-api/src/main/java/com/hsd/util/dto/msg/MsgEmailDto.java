package com.hsd.util.dto.msg;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.hsd.framework.dto.BaseDto;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MsgEmailDto extends BaseDto {
     /**id*/
     private Long id;
     /**发送人*/
	 @NotNull(message="sender不能为空")@Size(max=55,message="sender最大55字符")
     private String sender;
     /**收件人*/
	 @NotNull(message="recipient不能为空")@Size(max=55,message="recipient最大55字符")
     private String recipient;
     /**主题*/
	 @NotNull(message="subject不能为空")@Size(max=255,message="subject最大255字符")
     private String subject;
     /**短内容*/
	 @Size(max=255,message="scontent最大255字符")
     private String scontent;
     /**长内容*/
	 @Size(max=65535,message="lcontent最大65535字符")
     private String lcontent;
     /**附件*/
	 @Size(max=255,message="files最大255字符")
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