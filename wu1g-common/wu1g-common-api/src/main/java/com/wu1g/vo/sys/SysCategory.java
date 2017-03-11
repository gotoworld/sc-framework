package com.wu1g.vo.sys;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wu1g.framework.vo.BaseVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * <p>系统类目  BEAN类。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SysCategory extends BaseVO {

    private static final long serialVersionUID = -687161275861309523L;
    /**
     * ID
     */
    private Long id;
    /**
     * 名称
     */
    @NotNull(message = "name不能为空")
    @Size(max = 55, message = "name最大55字符")
    private String name;
    /**
     * 编码
     */
    @Size(max = 32, message = "code最大32字符")
    private String code;
    /**
     * 父级ID
     */
    private Long parentId;
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
     * 版本号
     */
    private Integer version;
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
}