package com.wu1g.vo.pano;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wu1g.framework.vo.BaseVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * <p>全景_场景  BEAN类。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PanoScene extends BaseVO {

    private static final long serialVersionUID = -586813007296179232L;
    /**
     * id
     */
    private String id;
    /**
     * 项目id
     */
    private Long projId;
    /**
     * 项目编码
     */
    private String projCode;
    /**
     * 场景名称
     */
    @NotNull(message = "sceneTitle不能为空")
    @Size(max = 50, message = "sceneTitle最大50字符")
    private String sceneTitle;
    /**封面*/
    private String logoUrl;
    /**
     * 场景图/全景视频
     */
    @NotNull(message = "sceneSrc不能为空")
    @Size(max = 255, message = "sceneSrc最大255字符")
    private String sceneSrc;
    /**
     * 水平视角
     */
    @Size(max = 20, message = "hlookat最大20字符")
    private String hlookat;
    /**
     * 垂直视角
     */
    @Size(max = 20, message = "vlookat最大20字符")
    private String vlookat;
    /**
     * 扩展配置json
     */
    @Size(max = 4000, message = "extCfgJson最大4000字符")
    private String extCfgJson;
    /**
     * 版本号
     */
    private Integer version;
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
     * 全景分解图地址
     */
    private String breakdownImg;
    /**
     * 场景热点
     */
    private List<PanoSpots> hotSpots;
    /**
     * 场景热点-装饰图/单图片展示
     */
    private List<PanoSpots> imgSpots;
    /**
     * 场景热点-外部链接
     */
    private List<PanoSpots> linkSpots;
    /**
     * 场景热点-弹窗/图文介绍
     */
    private List<PanoSpots> richSpots;
    /**
     * 场景热点-语音/讲解
     */
    private List<PanoSpots> soundSpots;
    /**
     * 场景热点-视频/讲解
     */
    private List<PanoSpots> videoSpots;
}