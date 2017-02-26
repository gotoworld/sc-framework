package com.wu1g.vo.pano;

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
 * <p>全景_项目  BEAN类。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PanoProj extends BaseVO {

    private static final long serialVersionUID = -964391982845378801L;
    /**
     * id
     */
    private Long id;
    /**
     * 编码
     */
    private String code;
    /**
     * 类目id
     */
    private Long categoryId;
    /**
     * 项目类型0图片1视频
     */
    private Integer type;
    /**
     * 名称
     */
    @NotNull(message = "name不能为空")
    @Size(max = 50, message = "name最大50字符")
    private String name;
    /**
     * 背景音乐
     */
    @Size(max = 255, message = "soundSrc最大255字符")
    private String soundSrc;
    /**
     * 开放评论 0否1是
     */
    private Integer isComments;
    /**
     * 小行星开场 0否1是
     */
    private Integer isPlanetoid;
    /**
     * 显示FPS 0否1是
     */
    private Integer isFps;
    /**
     * 雪景类型
     */
    private String snowType;
    /**
     * 导览图
     */
    @Size(max = 255, message = "mapSrc最大255字符")
    private String mapSrc;
    /**
     * 视频解说
     */
    @Size(max = 255, message = "videoSrc最大255字符")
    private String videoSrc;
    /**
     * 全景生成标记0否1是
     */
    private Integer isSeccuss;
    /**
     * 点赞数量
     */
    private Integer thumbsUpNum;
    /**
     * 浏览量
     */
    private Integer pvNum;
    /**
     * logo图片
     */
    @Size(max = 255, message = "logoPicUrl最大255字符")
    private String logoPicUrl;
    /**
     * logo链接
     */
    @Size(max = 255, message = "logoWebUrl最大255字符")
    private String logoWebUrl;
    /**
     * 访问密码
     */
    @Size(max = 6, message = "pwd最大6字符")
    private String pwd;
    /**
     * 漫游编辑json
     */
    @Size(max = 65535, message = "tourEditJson最大65535字符")
    private String tourEditJson;
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
     * 场景集合
     */
    private List<PanoScene> scenes;
    /**
     * 导览图中场景信息
     */
    private List<PanoMap> radars;
    /**
     * 生成全景文件执行标记
     */
    private boolean makePanoFlag;
}