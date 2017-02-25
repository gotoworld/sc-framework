package com.wu1g.vo.pano;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wu1g.framework.vo.BaseVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * <p>全景_热点  BEAN类。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PanoSpots extends BaseVO {

    private static final long serialVersionUID = -734160071374181202L;
    /**
     * id
     */
    private Long id;
    /**
     * 项目id
     */
    private Long projId;
    /**
     * 场景id
     */
    private String sceneId;
    /**
     * 类型0热点1图片2外部链接3图文介绍4语音热点
     */
    private Integer htype;
    /**
     * 标题
     */
    @Size(max = 50, message = "title最大50字符")
    private String title;
    /**
     * 图文详情
     */
    private String detailInfo;
    /**
     * 热点编号
     */
    @NotNull(message = "hname不能为空")
    @Size(max = 255, message = "hname最大255字符")
    private String hname;
    /**
     * 水平坐标
     */
    @NotNull(message = "ath不能为空")
    @Size(max = 32, message = "ath最大32字符")
    private String ath;
    /**
     * 垂直坐标
     */
    @NotNull(message = "atv不能为空")
    @Size(max = 32, message = "atv最大32字符")
    private String atv;
    /**
     * 关联场景
     */
    @Size(max = 32, message = "linkedscene最大32字符")
    private String linkedscene;
    /**
     * 缩放比
     */
    @Size(max = 32, message = "scale最大32字符")
    private String scale;
    /**
     * 纵深
     */
    @Size(max = 32, message = "depth最大32字符")
    private String depth;
    /**
     * 旋转角度
     */
    @Size(max = 32, message = "rotate最大32字符")
    private String rotate;
    /**
     * 图片地址
     */
    @Size(max = 255, message = "url最大255字符")
    private String url;
    /**
     * 可否点击
     */
    private Integer isOnclick;
    /**
     * 版本号
     */
    private Integer version;
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