package com.wu1g.panorama.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wu1g.framework.vo.BaseDto;
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
public class panoramaContactDto extends BaseDto {
    /**
     * id
     */
    private Long id;
    /**
     * 行业类型
     */
    @Size(max = 50, message = "panoramaType最大50字符")
    private String panoramaType;
    /**
     * 商户名称
     */
    @NotNull(message = "N不能为空")
    @Size(max = 35, message = "N最大35字符")
    private String panoramaName;
    /**
     * 联系人
     */
    @NotNull(message = "contactName不能为空")
    @Size(max = 35, message = "contactName最大35字符")
    private String contactName;
    /**
     * 电话号码
     */
    @NotNull(message = "telphone不能为空")
    @Size(max = 25, message = "telphone最大25字符")
    private String telphone;
    /**
     * 省
     */
    @Size(max = 55, message = "province最大55字符")
    private String province;
    /**
     * 市
     */
    @Size(max = 55, message = "city最大55字符")
    private String city;
    /**
     * 区县
     */
    @Size(max = 55, message = "county最大55字符")
    private String county;
    /**
     * 地址
     */
    @Size(max = 55, message = "adderss最大55字符")
    private String adderss;
    /**
     * 简介
     */
    @Size(max = 255, message = "briefInfo最大255字符")
    private String briefInfo;
    /**
     * 活动详情
     */
    @Size(max = 255, message = "detailInfo最大255字符")
    private String detailInfo;
    /**
     * 状态0待处理1未通过2成功
     */
    private Integer state;
    /**
     * VERSION
     */
    private Integer version;
    /**
     * 序号
     */
    private Long sortNum;
    /**
     * 创建人id
     */
    private Long createdId;
    /**
     * 创建人名称
     */
    private String createdName;
    /**
     * 创建时间
     */
    private Date createdAt;
    /**
     * 修改时间
     */
    private Date updatedAt;
    /**
     * 备注
     */
    @Size(max = 255, message = "remarks最大255字符")
    private String remarks;
    /**
     * 删除标识：0正常  1删除
     */
    private Byte delFlag;

    /**图片地址集合*/
    List<String> pics;
    /**地区名称*/
    private String areaName;
    /**状态名称*/
    private String stateName;
    /**状态标记 0 未通过 1已通过*/
    private String stateFlag;

}
