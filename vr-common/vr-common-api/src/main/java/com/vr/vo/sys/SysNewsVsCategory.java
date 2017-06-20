package com.vr.vo.sys;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.vr.framework.vo.BaseVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * <p>新闻资讯与类目  BEAN类。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SysNewsVsCategory extends BaseVO {

    private static final long serialVersionUID = -687161275861309523L;
    /**
     * 资讯id
     */
    private Long newsId;
    /**
     * 类目id
     */
    private Long categoryId;
    /**
     * 创建时间
     */
    private Date dateCreated;
}