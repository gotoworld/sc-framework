package com.wu1g.common.rongyun;

import com.wu1g.framework.util.Converter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 订单消息推送模板
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskMessage extends Message {
    /**
     * 发送方
     */
    private String fromAccid = "2";
    /**
     * 消息类型
     */
    private String type = "CDD:TaskMsg";
    public String toString(){
        return Converter.parseObject(this);
    }
}
