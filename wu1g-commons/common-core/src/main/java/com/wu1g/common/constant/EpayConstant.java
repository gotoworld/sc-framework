package com.wu1g.common.constant;

/**
 * Created by zhudingkun on 2016/10/14.
 */
public class EpayConstant {
    //日志的过程status 0:发布,1出账成功,,2:入账成功,3:第三方处理成功,4:退款成功,5:成功,6:失败 7:第3方处理失败
    public static final Byte LOG_STATUS_DO = 0;
    public static final Byte LOG_STATUS_AMOUNT_OUT_SUCCESS = 1;
    public static final Byte LOG_STATUS_AMOUNT_IN_SUCCESS = 2;
    public static final Byte LOG_STATUS_THIRDPARTY_SUCCESS = 3;
    public static final Byte LOG_STATUS_REFUND_SUCCESS = 4;
    public static final Byte LOG_STATUS_SUCCESS = 5;
    public static final Byte LOG_STATUS_FAILED = 6;
    public static final Byte LOG_STATUS_THIRDPARTY_FAILED = 7;
    //出入账类型 0：出账，1：入账
    public static final Byte OUTINTYPE_OUT = 0;
    public static final Byte OUTINTYPE_IN = 1;
    // 订单的order_status 0处理中1成功2失败
    public static final Byte ORDER_STATUS_DO = 0;
    public static final Byte ORDER_STATUS_SUCCESS = 1;
    public static final Byte ORDER_STATUS_FAILED = 2;
    public static final Byte ORDER_STATUS_TUIKUAN_SUCCESS = 3;
    // 操作类型
    // 1.服务订单 2.转账订单 3.红包订单 4.充值订单 5.提现订单
    // 6.服务订单收入 7.服务订单退款 8.转账收入 9.转账退款 10.红包收入
    // 11.红包退款 12.充值入账 13.充值退款 14.提现退款 15.提现到银行卡
    // 16.提现到支付宝 17.转账拒收退款 18.转账到期退款
    public static final Byte FUWU_OUT = 1;
    public static final Byte ZHUANZANG_OUT = 2;
    public static final Byte HONGBAO_OUT = 3;
    public static final Byte CONGZHI_OUT = 4;
    public static final Byte TIXIAN_OUT = 5;

    public static final Byte FUWU_IN = 6;
    public static final Byte FUWU_TUIKUAN_IN = 7;
    public static final Byte ZHUANZANG_IN = 8;
    public static final Byte ZHUANZANG_TUIKUAN_IN = 9;
    public static final Byte HONGBAO_IN = 10;
    public static final Byte HONGBAO_TUIKUAN_IN = 11;
    public static final Byte CONGZHI_IN = 12;
    public static final Byte CONGZHI_TUIKUAN_IN = 13;
    public static final Byte TIXIAN_TUIKUAN_IN = 14;
    public static final Byte TIXIAN_BANKCARD_IN = 15;
    public static final Byte TIXIAN_ALIPAY_IN = 16;
    public static final Byte TRANSFER_REFUSE_REFUND = 17; // 转账拒收退款
    public static final Byte TRANSFER_EXPIRED_REFUND = 18; // 转账到期退款
    //1 余额 2 银行卡 3 支付宝 4 微信
    public static final Byte WALLET = 1;
    public static final Byte BANKCARD = 2;
    public static final Byte ALIPAY = 3;
    public static final Byte WECHATPAY = 4;

    //支付密码状态 0:未冻结, 1:已冻结
    public static final short WEI_DONG_JIE = 0;
    public static final short YI_DONG_JIE = 1;

    //交易记录状态常量
    public static final class PayDealFlowConstants {
        //处理中
        public static final byte PAYDEALFLOW_PROCESSING = 0;
        //成功
        public static final byte PAYDEALFLOW_SUCCESS = 1;
        //失败
        public static final byte PAYDEALFLOW_FAIL = 2;
        //交易关闭
        public static final byte PAYDEALFLOW_CLOSE = 3;
    }

    //交易记录状态常量
    public static final class FinTransactionRecordConstants {
        //待处理
        public static final byte FINTRANSACTION_PROCESSING = 1;
        //成功
        public static final byte FINTRANSACTION_SUCCESS = 2;
        //失败
        public static final byte FINTRANSACTION_FAIL = 3;
        //关闭
        public static final byte FINTRANSACTION_CLOSE = 4;

        //支付处理中
        public static final byte FINTRANSACTION_PAYING = 9;
    }
}
