package com.hsd.account.staff.dto.sys;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hsd.framework.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SysMenuDto extends BaseDto {
     /**id*/
     private Long id;
     /**名称*/
	@NotNull(message="name不能为空")@Size(max=50,message="name最大50字符")
     private String name;
     /**所属系统域*/
	@NotNull(message="app_id不能为空")@Size(max=50,message="app_id最大50字符")
     private String appId;
     /**父节点*/
     private Long parentId;
     /**页面链接*/
	@NotNull(message="url不能为空")@Size(max=255,message="url最大255字符")
     private String url;
     /**排序*/
     private Integer orderNo;
     /**版本号*/
     private Integer version;
     /**是否删除*/
     private Integer delFlag;
     /**创建时间*/
     private Date dateCreated;
     /**更新时间*/
     private Date dateUpdated;
     /**BI时间戳*/
     private Date biUpdateTs;

    /** 子对象集合 */
    private List<SysMenuDto> nodes;
    private String text;
    private Integer[] tags;
    public Integer[] getTags(){
        if(text!=null) return new Integer[]{nodes!=null?nodes.size():0};else return  null;
    }
}