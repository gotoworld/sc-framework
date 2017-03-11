package com.wu1g.vo.sys;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wu1g.framework.vo.BaseVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * <p>广告  BEAN类。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SysAdvertise extends BaseVO {

    private static final long serialVersionUID = -687161275861309523L;
    /**
     * ID
     */
    private Long id;
    /**
     * 广告名称
     */
    @NotNull(message = "name不能为空")
    @Size(max = 255, message = "name最大255字符")
    private String name;
    /**
     * 图片地址
     */
    @NotNull(message = "imgUrl不能为空")
    @Size(max = 255, message = "imgUrl最大255字符")
    private String imgUrl;
    /**
     * 广告链接地址
     */
    @NotNull(message = "linkUrl不能为空")
    @Size(max = 255, message = "linkUrl最大255字符")
    private String linkUrl;
    /**
     * 状态0下架1上架
     */
    private Integer state;
    /**
     * 广告位置
     */
    @NotNull(message = "position不能为空")
    @Size(max = 32, message = "position最大32字符")
    private String position;
    /**
     * 备注
     */
    @Size(max = 255, message = "memo最大255字符")
    private String memo;
    /**
     * 删除标记
     */
    private Integer delFlag;
    /**
     * 建立者ID
     */
    private Long createId;
    /**
     * 创建时间
     */
    private Date dateCreated;
    /**
     * 更新时间
     */
    private Date dateUpdated;
}