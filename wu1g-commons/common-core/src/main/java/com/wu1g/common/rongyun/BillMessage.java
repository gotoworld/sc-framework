package com.wu1g.common.rongyun;

import com.wu1g.framework.util.Converter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 账单相关消息推送模板
 * fromUserId = 5 账单发送方
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillMessage extends Message{
    /**
     * 消息类型
     */
    private String type="CDD:PaymentMsg";
    /**
     * 发送方
     */
    private String fromAccid = "5";
    /**
     * 明细id
     */
    private Long paymentId;

    /**
     * 类型 (0:余额提现申请  1:余额提现到账  2:余额提现退款  3:红包退款)
     */
    private int paymentType;

    /**
     * 金额
     */
    private double amount;

    /**
     * 内容1
     * 退款方式、提现银行
     */
    private String content1;

    /**
     * 内容2
     * 到账时间、提现时间
     */
    private String content2;

    /**
     * 内容3
     * 退款原因 、 到账时间 、 预计到账时间
     */
    private String content3;

    private String  content4;

    private String  content5;

    private Integer serviceType;

    public String toString(){
        return Converter.parseObject(this);
    }

}
