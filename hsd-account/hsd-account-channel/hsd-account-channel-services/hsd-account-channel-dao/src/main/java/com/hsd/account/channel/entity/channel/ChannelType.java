package com.hsd.account.channel.entity.channel;

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
public class ChannelType extends BaseEntity {
     /**类型ID*/
     private Long id;
     /**类型名称*/
     private String type;
     /**创建时间*/
     private Date dateCreated;
     /**创建人*/
     private String createdBy;
     /**BI时间戳*/
     private Date biUpdateTs;
     /**是否删除*/
     private Integer delFlag;
}