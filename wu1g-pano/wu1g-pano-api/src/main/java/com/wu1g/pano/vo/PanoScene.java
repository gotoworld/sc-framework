/*	
 * 全景_场景 BEAN类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ---------------------------	
 * 1.00     2016.10.02      easycode         程序.发布		
 * -------- ----------- --------------- ---------------------------	
 * Copyright 2016 pano System. - All Rights Reserved.
 *	
 */

package com.wu1g.pano.vo;


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
 *
 * @author easycode
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
    @Size(max = 64, message = "id最大64字符")
    private String id;
    /**
     * 项目id
     */
    @NotNull(message = "projId不能为空")
    @Size(max = 32, message = "projId最大32字符")
    private String projId;
    /**
     * 场景名称
     */
    @NotNull(message = "sceneTitle不能为空")
    @Size(max = 50, message = "sceneTitle最大50字符")
    private String sceneTitle;
    /**
     * 全景图宽高比须为2:1，格式为jpg,全景视频为mp4
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
     * 场景音乐
     */
    @Size(max = 255, message = "soundSrc最大255字符")
    private String soundSrc;
    /**
     * 场景视频解说
     */
    @Size(max = 255, message = "videoSrc最大255字符")
    private String videoSrc;
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
     * 全景分解图地址
     */
    private String breakdownImg;
    /**
     * 场景跳转热点
     */
    private List<PanoSpots> hotSpots;
    /**
     * 场景装饰图热点
     */
    private List<PanoSpots> imgSpots;
}