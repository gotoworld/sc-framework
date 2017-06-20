package com.vr.vo.pano;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.vr.framework.vo.BaseVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.util.Date;

/**
 * <p>全景_评论  BEAN类。
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
    private Long id;
    /**
     * 场景id
     */
    private String sceneId;
    /**
     * 内容
     */
    @Size(max = 55, message = "content最大55字符")
    private String content;
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
     * 头像
     */
    @Size(max = 255, message = "avatar最大255字符")
    private String avatar;
    /**
     * 昵称
     */
    @Size(max = 55, message = "nickname最大55字符")
    private String nickname;
    /**
     * 性别
     */
    private Integer sex;
    /**
     * 省份
     */
    @Size(max = 24, message = "province最大24字符")
    private String province;
    /**
     * 市区
     */
    @Size(max = 24, message = "city最大24字符")
    private String city;
    /**
     * 备注
     */
    @Size(max = 255, message = "memo最大255字符")
    private String memo;
    /**
     * 删除标记(0正常1删除)
     */
    private Integer delFlag;
    /**
     * 创建时间
     */
    private Date dateCreated;
    /**
     * 更新时间
     */
    private Date dateUpdated;
    /**
     * 项目标题
     */
    private String projTitle;
    /**
     * 项目Id
     */
    private String projId;
    /**
     * 场景标题
     */
    private String sceneTitle;

    /**
     * 水平视角
     */
    private String hlookat;
    /**
     * 垂直视角
     */
    private String vlookat;
    /**
     * 排序
     */
    private Integer orderNo;
}