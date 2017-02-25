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
    private Long projId;
    /**
     * 场景id
     */
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
     * 建立者ID
     */
    private Long createId;
    /**
     * 创建时间
     */
    private Date dateCreated;
}
