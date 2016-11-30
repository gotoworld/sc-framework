/*	
 * 全景_热点 BEAN类	
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

/**
 * <p>全景_热点  BEAN类。
 *
 * @author easycode
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
    @Size(max = 32, message = "id最大32字符")
    private String id;
    /**
     * 项目id
     */
    @NotNull(message = "projId不能为空")
    @Size(max = 32, message = "projId最大32字符")
    private String projId;
    /**
     * 场景id
     */
    @NotNull(message = "sceneId不能为空")
    @Size(max = 64, message = "sceneId最大64字符")
    private String sceneId;
    /**
     * 类型0热点1图片
     */
    @NotNull(message = "htype不能为空")
    @Size(max = 1, message = "htype最大1字符")
    private String htype;
    /**
     * 标题
     */
    @Size(max = 50, message = "title最大50字符")
    private String title;
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
     * 可否点击0否1是
     */
    @Size(max = 1, message = "isOnclick最大1字符")
    private String isOnclick;
    /**
     * 版本号
     */
    private Integer version;
    /**
     * 创建时间
     */
    private Date dateCreated;
    /**
     * 修改时间
     */
    private Date dateUpdate;

}