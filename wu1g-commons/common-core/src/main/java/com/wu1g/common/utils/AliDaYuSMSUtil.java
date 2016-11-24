package com.wu1g.common.utils;

import java.util.Random;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;


import org.apache.commons.lang.StringUtils;

/**
*短信API服务调用示例代码 － 阿里大于
*在线接口文档：http://www.juhe.cn/docs/54
**/
 
public class AliDaYuSMSUtil {
	
	//短信请求地址
	public static final String BASE_URL = "https://eco.taobao.com/router/rest";
	//分配给应用的AppKey
	public static final String APPKEY = "23461099";
	public static final String SECRET = "80be19e47dbe2b94cfde0f9a387a002c";
	//短信类型，传入值请填写normal
	public static final String SMS_TYPE = "normal";
	
	
	
 
    //找回密码号
    private static final String ZhaoHuiMiMa ="SMS_16970016";
    //注册模板号
    private static final String ZhuCe ="SMS_15015301";
    //冻结账号
    private static final String DONGJIE ="13404";
    //提现申请
    private static final String TIXIANSHENQING ="13842";
    //退款
    private static final String TUIKUAN ="SMS_18240089";
    //确认服务
    private static final String QUERENFUWU ="SMS_18240088";
    //提现退款失败
    private static final String TIXIANTUIKUAN ="SMS_18335039";
    //付款成功
    private static final String FUKUANCHENGGONG ="SMS_18190075";
    //开始服务
    private static final String KAISIFUWU ="SMS_18370021";
    //通用短信模板
    private static final String CURRENCY="SMS_16930087";
    //发布需求时通知代理处理需求
    private static final String MESAGENT="SMS_18240087";
    //异常退款，充值到余额
    private static final String EXCEPTION_REFUND_RECHARGE="SMS_18255055";


    //2.发送短信
    /**
     * 
     * @param num  订单号
     * @param amount 订单金额
     * @param mobile 电话号码
     * @param type  短信类型
     * @return
     */
    public static String[] getRequest(String num,String amount,String mobile, int type/*0：注册，1：找回密码*/,String intCount){
    	if (!StringUtils.isEmpty(amount))amount=Double.parseDouble(amount)+"";

        String []returnStr = {"",""};
        String templateId = "";

  		//签名模版
  		String smsFreeSignName = "车当当";
  		//短信内容
  		String smsParamString = "";
  		//短信模版
  		String smsTemplateCode = "";
  		
        switch (type) {
        case 0:
        	smsParamString = "{\"code\":\""+intCount+"\",\"product\":\"车当当\"}";
        	smsTemplateCode = ZhuCe;
			break;
		case 1:
			smsParamString = "{\"code\":\""+intCount+"\"}";
			smsTemplateCode = ZhaoHuiMiMa;
			break;
		case 2:
			smsParamString = "{\"orderNum\":\""+num+"\",\"amount\":\""+amount+"\"}";
			smsTemplateCode = TUIKUAN;
			break;
		case 3:
			smsParamString = "{\"orderNum\":\""+num+"\",\"amount\":\""+amount+"\"}";
			smsTemplateCode = QUERENFUWU;
			break;
		case 4:
			smsParamString = "{\"orderNum\":\""+num+"\",\"amount\":\""+amount+"\"}";
			smsTemplateCode = TIXIANTUIKUAN;
			break;
		case 5:
			smsParamString = "{\"orderNum\":\""+num+"\",\"amount\":\""+amount+"\"}";
			smsTemplateCode = FUKUANCHENGGONG;
			break;
		case 6:
			smsParamString = "{\"orderNum\":\""+num+"\"}";
			smsTemplateCode = KAISIFUWU;
			break;
		case 7:
			smsParamString = "{\"code\":\""+intCount+"\"}";
			smsTemplateCode = CURRENCY;
			break;
		case 8:
			smsParamString = "{\"code\":\""+intCount+"\"}";
			smsTemplateCode = MESAGENT;
			break;
		case 9:
			// 【车当当】您的订单#orderNum#出现异常，已办理退款至您的余额，给您造成的不便，我们很抱歉，如有问题请联系客服：4008333955。
			smsParamString = "{\"orderNum\":\""+num+"\"}";
			templateId = EXCEPTION_REFUND_RECHARGE;
			break;
		default:
			break;
		}
        
        try {
        	TaobaoClient client = new DefaultTaobaoClient(BASE_URL, APPKEY, SECRET);
			AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
			req.setExtend("123456");
			req.setSmsType(SMS_TYPE);
			req.setSmsFreeSignName(smsFreeSignName);
			req.setSmsParamString(smsParamString);
			req.setRecNum(mobile);
			req.setSmsTemplateCode(smsTemplateCode);
			AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
			//System.out.println(rsp.getBody());

			ObjectMapper mapper = new ObjectMapper();
			JsonNode rootNode = mapper.readTree(rsp.getBody()); // 读取Json
			JsonNode obj = rootNode.path("alibaba_aliqin_fc_sms_num_send_response");
			JsonNode result = obj.path("result");

			String errorCode = result.path("err_code").asText();



           /* JSONObject object = JSONObject.fromObject(rsp.getBody());
            JSONObject obj = JSONObject.fromObject(object.get("alibaba_aliqin_fc_sms_num_send_response"));
            JSONObject result = JSONObject.fromObject(obj.get("result"));
            String errorCode = result.get("err_code").toString();*/
            /*if(Integer.parseInt(errorCode)==0){
                System.out.println(object.get("result"));
            }else{
                System.out.println(object.get("error_code")+":"+object.get("reason"));
            }*/
            returnStr[0] = errorCode+"";
            returnStr[1] = intCount.toString();
            return returnStr;
        } catch (Exception e) {
            e.printStackTrace();
        }

        //发送失败
		return returnStr;
    }
 
    
    public static void main(String[] args) {
		//System.out.println(getRequest(null, null, "18280456288", 0));
		StringBuffer intCount = new StringBuffer();

		for (int i=0;i<4;i++){
			intCount.append(new Random().nextInt(10));
		}

		System.out.println(intCount.toString());
	}
 
 
}