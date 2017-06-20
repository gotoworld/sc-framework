package com.vr.framework.vo;

import com.vr.framework.IEntity;
import com.vr.framework.IVO;
import com.vr.framework.util.IdUtil;
import lombok.Data;

@Data
public class BaseVO implements IVO,IEntity {
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
    /** 用户id */
    String accid;
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
