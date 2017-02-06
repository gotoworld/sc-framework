/*	
 * 全景_评论 BEAN类	
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
 * <p>全景_评论  BEAN类。
 *
 * @author easycode
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PanoComments extends BaseVO {

    private static final long serialVersionUID = -590742611946168992L;
    /**
     * id
     */
    @Size(max = 32, message = "id最大32字符")
    private String id;
    /**
     * 场景id
     */
    @NotNull(message = "sceneId不能为空")
    @Size(max = 32, message = "sceneId最大32字符")
    private String sceneId;
    /**
     * sname
     */
    @Size(max = 255, message = "sname最大255字符")
    private String sname;
    /**
     * 内容
     */
    @Size(max = 1000, message = "content最大1000字符")
    private String content;
    /**
     * 头像
     */
    @Size(max = 255, message = "img最大255字符")
    private String img;
    /**
     * 水平坐标
     */
    @Size(max = 20, message = "ath最大20字符")
    private String ath;
    /**
     * 垂直坐标
     */
    @Size(max = 20, message = "atv最大20字符")
    private String atv;
    /**
     * 昵称
     */
    @Size(max = 255, message = "nickname最大255字符")
    private String nickname;
    /**
     * 性别
     */
    @Size(max = 1, message = "sex最大1字符")
    private String sex;
    /**
     * 省份
     */
    @Size(max = 255, message = "province最大255字符")
    private String province;
    /**
     * 市区
     */
    @Size(max = 255, message = "city最大255字符")
    private String city;
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
    private String delFlag;
    /**
     * 数据过期时间0:永不过期
     */
    private Long invalidTime;
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
     * 项目标题
     */
    private String projTitle;
    /**
     * 项目Id
     */
    private String projId;
    /** 场景标题*/
    private String sceneTitle;
}