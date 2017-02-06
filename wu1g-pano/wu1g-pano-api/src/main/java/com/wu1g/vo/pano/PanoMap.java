/*	
 * 全景_导览图 BEAN类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ---------------------------	
 * 1.00     2016.10.02      easycode         程序.发布		
 * -------- ----------- --------------- ---------------------------	
 * Copyright 2016 pano System. - All Rights Reserved.
 *	
 */

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
 * <p>全景_导览图  BEAN类。
 *
 * @author easycode
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PanoMap extends BaseVO {

    private static final long serialVersionUID = -499724720850030385L;
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
     * 雷达旋转角度
     */
    @Size(max = 20, message = "rotate最大20字符")
    private String rotate;
    /**
     * 导览图坐标x
     */
    @NotNull(message = "x不能为空")
    @Size(max = 20, message = "x最大20字符")
    private String x;
    /**
     * 导览图坐标y
     */
    @NotNull(message = "y不能为空")
    @Size(max = 20, message = "y最大20字符")
    private String y;
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
