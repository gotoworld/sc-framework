package com.wu1g.framework.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseDto extends com.wu1g.framework.vo.BaseDto {

    String token;

    /** 开始时间 */
    String dateBegin;
    /** 结束时间 */
    String dateEnd;
    /** 页码 */
    Integer pageIndex;
    /** 每页显示条数 */
    Integer pageSize;
}
