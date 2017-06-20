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
 * <p>系统消息  BEAN类。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SysMessage extends BaseVO {

    private static final long serialVersionUID = -687161275861309523L;
    /**
     * ID
     */
    private Long id;
    /**
     * 发件人id[0代表管理员]
     */
    private Long sendId;
    /**
     * 收件人id[0代表所有人]
     */
    private Long acceptId;
    /**
     * 消息标题
     */
    @Size(max = 255, message = "title最大255字符")
    private String title;
    /**
     * 消息内容
     */
    @Size(max = 1024, message = "content最大1024字符")
    private String content;
    /**
     * 是否已读0未读1已读
     */
    private Integer state;
    /**
     * 创建时间
     */
    private Date dateCreated;
    /**
     * 更新时间
     */
    private Date dateUpdated;
}