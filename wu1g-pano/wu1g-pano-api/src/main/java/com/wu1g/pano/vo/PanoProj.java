/*	
 * 全景_项目 BEAN类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ---------------------------	
 * 1.00     2016.10.02      easycode         程序.发布		
 * -------- ----------- --------------- ---------------------------	
 * Copyright 2016 baseos System. - All Rights Reserved.
 *	
 */

package com.wu1g.pano.vo;


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
 *
 * @author easycode
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
    @Size(max = 32, message = "id最大32字符")
    private String id;
    /**
     * 类目id
     */
    @NotNull(message = "categoryId不能为空")
    @Size(max = 22, message = "categoryId最大22字符")
    private String categoryId;
    /**
     * 项目类型0图片1视频
     */
    @NotNull(message = "type不能为空")
    @Size(max = 1, message = "type最大1字符")
    private String type;
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
    @Size(max = 1, message = "isComments最大1字符")
    private String isComments;
    /**
     * 小行星开场 0否1是
     */
    @Size(max = 1, message = "isPlanetoid最大1字符")
    private String isPlanetoid;
    /**
     * 是否补地 0否1是
     */
    @Size(max = 1, message = "isMending最大1字符")
    private String isMending;
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
    @Size(max = 1, message = "isSeccuss最大1字符")
    private String isSeccuss;
    /**
     * 点赞数量
     */
    private Integer thumbsUNum;
    /**
     * 浏览量
     */
    private Integer pvNum;
    /**
     * XMlDATA
     */
    @Size(max = 65535, message = "xmlData最大65535字符")
    private String xmlData;
    /**
     * logo图片
     */
    @Size(max = 255, message = "logoPic最大255字符")
    private String logoPic;
    /**
     * logo链接
     */
    @Size(max = 255, message = "logoUrl最大255字符")
    private String logoUrl;
    /**
     * 下雪类型
     */
    @Size(max = 50, message = "snowType最大50字符")
    private String snowType;
    /**
     * 显示fps0否1是
     */
    @Size(max = 1, message = "isFps最大1字符")
    private String isFps;
    /**
     * 版本号
     */
    private Integer version;
    /**
     * 状态
     */
    @Size(max = 1, message = "state最大1字符")
    private String state;
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
     * 是否删除(0否1是)
     */
    @NotNull(message = "delFlag不能为空")
    @Size(max = 1, message = "delFlag最大1字符")
    private Byte delFlag;
    /**
     * 创建时间
     */
    private Date dateCreated;
    /**
     * 建立者ID
     */
    @Size(max = 64, message = "createId最大64字符")
    private String createId;
    /**
     * 建立者IP
     */
    @Size(max = 64, message = "createIp最大64字符")
    private String createIp;
    /**
     * 修改时间
     */
    private Date dateUpdate;
    /**
     * 修改者ID
     */
    @Size(max = 64, message = "updateId最大64字符")
    private String updateId;
    /**
     * 修改者IP
     */
    @Size(max = 64, message = "updateIp最大64字符")
    private String updateIp;

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

   private Integer thumbsUpNum;
}