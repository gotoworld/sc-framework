package com.wu1g.common.rongyun;

import com.wu1g.framework.util.Converter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 抢红包消息推送
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CmdMessage extends Message {
    private String type="RC:CmdMsg";
    private String name = "redPacket";
    private String content;
    private Long operationId;     //抢红包者Id
    private String operationName;   //抢红包者名字
    private Long targetUserId;    //发红包者id
    private String targetUserName;  //发红包者名字
    private Long redPacketId;     //红包Id
    private int redType;//红包类型 0单个红包 1普通群红包 2随机群红包
    public String toString(){
        return Converter.parseObject(this);
    }
}
