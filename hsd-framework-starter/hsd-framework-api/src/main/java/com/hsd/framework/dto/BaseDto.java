package com.hsd.framework.dto;

import com.hsd.framework.IDto;
import lombok.Data;

@Data
public class BaseDto implements IDto {
    String token;
    /**新增标记0否1是*/
    Integer newFlag;
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
    /**  建立者ID */
    private Long createId;
    String str;
//    public String getToken(){
//        if(token==null){
//            token= IdUtil.createUUID(32);
//        }
//        return token;
//    }
}
