package com.wu1g.common.rongyun;

import com.wu1g.framework.util.Converter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by CLOUD on 2016/11/17.
 * 消息模板外层
 * 发送所有消息的参数都是该实体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublicMessage<T> {
    /**
     * 接收方
     */
    private String toAccid;
    /**
     * 发送方
     */
    private String fromAccid;
    /**
     * 具体消息类型，根据具体消息类型选择消息模板
     * TaskMessage 订单
     * ImgMessage 图片
     * BillMessage 账单相关
     */
    private T data;
    /**
     * 消息模板类型
     */
    private String type;
    /**
     * 消息类型
     * 0普通消息 1群消息
     */
    private int messageType = 0;

    private String name;

    public String toString() {
        return Converter.parseObject(this);
    }

    public String dataToString() {
        return Converter.parseObject(this.data);
    }
}
