package com.hsd.account.staff.dto.org;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hsd.framework.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrgLogOperationDto extends BaseDto {
     /**ID*/
     private Long id;
     /**操作类型*/
     private String type;
     /**所属系统域*/
     private String appId;
     /**描述*/
     private String memo;
     /**对象信息*/
     private String detailInfo;
     /**操作时间*/
     private Date dateCreated;
     /**操作人ID*/
     private Long createId;
     /**操作人姓名*/
     private String createName;
     /**操作人IP*/
     private String createIp;

}