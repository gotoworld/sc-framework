package com.hsd.framework.dto;

import com.hsd.framework.IDto;
import com.hsd.framework.util.IdUtil;
import lombok.Data;

@Data
public class BaseDto implements IDto {
    String token;
    /**新增标记0否1是*/
    Integer newFlag=0;
    /** 开始时间 */
    String dateBegin;
    /** 结束时间 */
    String dateEnd;
    /** 页码 */
    Integer pageNum;
    /** 每页显示条数 */
    Integer pageSize;
    /** 关键字 */
    String keyword;
    /** 用户名 */
    String account;
    /** 用户id */
    Long accid;
    /**  建立者ID */
    private Long createId;
    /** 会员 */
    boolean isMember;

    String str;
    public String getToken(){
        if(token==null){
            token= IdUtil.createUUID(32);
        }
        return token;
    }
}
