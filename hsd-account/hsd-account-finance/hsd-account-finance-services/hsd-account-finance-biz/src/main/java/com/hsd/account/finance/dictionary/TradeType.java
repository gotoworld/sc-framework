package com.hsd.account.finance.dictionary;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by hsd7 on 2018/1/11.
 */
public enum TradeType {
    InvestmentDeduct(1,"投资扣费"),
    Receivables(2,"收款"),
    Recharge(3,"充值"),
    Withdrawals(4,"提现"),
    Reward(6,"奖励"),
    Transfer(7,"转账"),
    InvestmentReward(8,"投资奖励"),
    Financing(9,"融资"),
    Fee(10,"扣费"),
    RechargePoundage(11,"充值手续费"),
    WithdrawalsRevocation(12,"提现撤销"),
    WithdrawalsPoundage(13,"提现手续费"),
    LoanExpense(14,"借款管理费"),
    InvestmentFreeze(15,"投资冻结资金"),
    InvestmentUnfreeze(16,"投资撤回解冻资金"),
    InvestmentRewardDeduct(17,"投资奖励扣除"),
    Repayment(19,"还款"),
    InternalTransfer(20,"内部调账"),
    InterestSettlement(21,"结息"),
    Refund(22,"原交易退款"),
    Revocation(23,"原交易撤销");

    private final int Code;
    private final String Name;

    TradeType(int Code, String Name){
        this.Code=Code;
        this.Name=Name;
    }
    public static TradeType getTradeType(int Code){
        for (TradeType tradeType:values()){
            if(tradeType.Code == Code){
                return  tradeType;
            }
        }
        return null;
    }
    public int getCode(){
        return Code;
    }

    public String getName(){ return Name; }
}
