package com.hsd.account.staff.dto.sys;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hsd.framework.dto.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("应用系统表 DTO")
public class SysAppDto extends BaseDto {
    @ApiModelProperty("appId")
    @Size(max = 22, message = "id最大22字符")
    private String id;
    @ApiModelProperty("系统名称")
    @NotNull(message = "name不能为空")
    @Size(max = 50, message = "name最大50字符")
    private String name;
    @ApiModelProperty("登录链接")
    @NotNull(message = "login_url不能为空")
    @Size(max = 255, message = "login_url最大255字符")
    private String loginUrl;
    @ApiModelProperty("主页登链")
    @Size(max = 255, message = "main_url最大255字符")
    private String mainUrl;
    @ApiModelProperty("备注")
    @Size(max = 255, message = "memo最大255字符")
    private String memo;
    @ApiModelProperty("是否删除")
    private Integer delFlag;
    @Size(max = 255, message = "memo最大255字符")
    private String keyword;
    @ApiModelProperty("建立者id")
    private Long createId;
    @ApiModelProperty("创建时间")
    private Date dateCreated;
    @ApiModelProperty("更新时间")
    private Date dateUpdated;
    /**
     * 已存在的禁止删除(0否1是)
     */
    private Integer noDelFlag;

}