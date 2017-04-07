package com.wu1g.vo.sys;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wu1g.framework.vo.BaseVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * <p>新闻资讯  BEAN类。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SysNews extends BaseVO {

    private static final long serialVersionUID = -687161275861309523L;
    /**
     * ID
     */
    private Long id;
    /**
     * 标题
     */
    @NotNull(message = "title不能为空")
    @Size(max = 55, message = "title最大55字符")
    private String title;
    /**
     * 副标题
     */
    @Size(max = 255, message = "secondTitle最大255字符")
    private String secondTitle;
    /**
     * 标题图
     */
    @Size(max = 255, message = "coverPic最大255字符")
    private String coverPic;
    /**
     * 简介
     */
    @Size(max = 255, message = "briefInfo最大255字符")
    private String briefInfo;
    /**
     * 详情
     */
    private String detailInfo;
    /**
     * 置顶
     */
    private Integer isOntop;
    /**
     * 点击量
     */
    private Integer clickCount;
    /**
     * 状态
     */
    private Integer state;
    /**
     * 备注
     */
    @Size(max = 255, message = "memo最大255字符")
    private String memo;
    /**
     * 版本号
     */
    private Integer version;
    /**
     * 排序
     */
    private Integer orderNo;
    /**
     * 关键字
     */
    @Size(max = 255, message = "keyword最大255字符")
    private String keyword;
    /**
     * 删除标记(0正常1删除)
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
    /**
     * 栏目id
     */
    private Long categoryId;

    /**栏目id*/
    private List<Long> categorys;
}