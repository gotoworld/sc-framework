var uploadPath = "http://192.168.108.100:28888/";
var basePath = "http://localhost:8080/";
var apiPath = {
    account: {
        channel: "http://192.168.108.100:28890/hsd-account-channel-web-portal",
        actor: "http://192.168.108.100:28890/hsd-account-actor-web-portal"
    }
    ,msg:{
        sms: "http://192.168.108.100:28890/hsd-util-sms-web-portal"
    }
};
var site = {
    portalHome:"/html/account/actor/user/home.html"
    ,channel: { //渠道商
        login: apiPath.account.channel + "/api/account/channel/sign/login" //登录
        , logout: apiPath.account.channel + "/api/account/channel/sign/logout" //登出
        , refreshToken: apiPath.account.channel + "/api/account/channel/sign/refreshToken" //刷新token
        , modifyPwd: apiPath.account.channel +"/api/account/channel/sign/modifyPwd"
        , edit: apiPath.account.channel + "/api/account/channel/channelInfo/edit"
        , info: apiPath.account.channel + "/api/account/channel/channelInfo/info/"
        , list: apiPath.account.channel + "/api/account/channel/channelInfo/list"
    }
    ,msg:{
        sms:{
            imgCaptcha:{
                json: apiPath.msg.sms+"/api/util/sms/imgCaptcha/json"
                ,html: apiPath.msg.sms+"/api/util/sms/imgCaptcha/html"
            }
        }
    }
    ,findpwd: {
        view: basePath + "/html/account/channel/channel/findpwd"
        , verify: basePath + "/html/account/channel/channel/findpwd_verify"
        , getAccount: apiPath.account.channel + "/api/account/channel/findpwd/getAccount"
        , sendSms:apiPath.account.channel+"/api/account/channel/findpwd/send/captcha/sms"
        , sendMmail:apiPath.account.channel+"/api/account/channel/findpwd/send/captcha/email"
        , verifyCaptchaSms: apiPath.account.channel + "/api/account/channel/findpwd/verifyCaptchaSms"
        , verifyCaptchaEmail: apiPath.account.channel + "/api/account/channel/findpwd/verifyCaptchaEmail"
        , verifystate: apiPath.account.channel + "/api/account/channel/findpwd/verifystate"
        , update: apiPath.account.channel + "/api/account/channel/findpwd/restPwd"
    }
};
var $data, $ngHttp, $ngCompile, $ngSce;
var page;

function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}


/**渠道商登录信息验证头*/
$.ajaxSetup({
    headers: {
        "Authorization": sessionStorage.getItem("hsd_channel_authorizationToken")
    }
});
$(document).ajaxComplete(function (event, xhr, settings) {
    if (xhr && xhr.responseText) {
        var result = JSON.parse(xhr.responseText);
        //console.info("result=="+JSON.stringify(result))
        if (result.code == 403) {//授权验证失败!
            // console.info('授权验证失败!需跳转到登陆界面');
            alert('授权验证失败,请重新登陆!');
            location.href = '/login.html';
        }
    }
});
/**渠道商信息*/
var channel = {
    login: function (channelJson, callback) {
        $.post(site.channel.login, channelJson, function (result) {
            if (result.code == 0) {
                if (result.data) {
                    sessionStorage.setItem('hsd_channel_channel', JSON.stringify(result.data.channel));
                    if (result.data.authorizationToken) {
                        sessionStorage.setItem('hsd_channel_tokenExpMillis', result.data.tokenExpMillis);
                        sessionStorage.setItem("hsd_channel_authorizationToken", result.data.authorizationToken);
                    }
                }
                callback && callback();
            } else {
                $data.result = result;
                // alert(result.message);
                if (!$data.$$phase) $data.$apply();
            }
        }, 'json');
    },
    refreshToken: function (callback) {
        $.get(site.channel.refreshToken, {}, function (result) {
            if (result.code == 0) {
                if (result.data) {
                    if (result.data.authorizationToken) {
                        sessionStorage.setItem('hsd_channel_tokenExpMillis', result.data.tokenExpMillis);
                        sessionStorage.setItem("hsd_channel_authorizationToken", result.data.authorizationToken)
                    }
                }
                callback && callback();
            } else {
                alert(result.message);
            }
        }, 'json');
    },
    logout: function (callback) {
        $.get(site.channel.logout, {}, function (result) {
            sessionStorage.removeItem('hsd_channel_tokenExpMillis');
            sessionStorage.removeItem('hsd_channel_channel');
            sessionStorage.removeItem("hsd_channel_authorizationToken");
            callback && callback();
            top.location.href = '/';
        }, 'json');
    },
    info: function (callback) {
        try {
            console.info("channel.info.............");
            var channelJson = sessionStorage.getItem('hsd_channel_channel');
            if (channelJson) {
                var channelInfoUser = JSON.parse(channelJson);
                $data.channelInfoUser = channelInfoUser;
            }
            console.info("111111111");
            callback && callback();
        } catch (e) {
        } finally {
            if ($data) {
                if (!$data.$$phase) $data.$apply();
            }
        }
    },
    init: function (callback) {
        try {
            var expMillis = 0;
            if (sessionStorage.getItem('hsd_channel_tokenExpMillis')) {
                expMillis = sessionStorage.getItem('hsd_channel_tokenExpMillis') - (new Date().getTime());
            }
            if (expMillis > 0 && expMillis < (10 * 60 * 1000)) {//还有10分钟过期
                channel.refreshToken(callback);
            } else {
                if (sessionStorage.getItem('hsd_channel_channel') && expMillis > 0) {
                    channel.info(callback);
                } else {
                    alert('未登录或登陆过期,请重新登陆!');
                    top.location.href = '/login.html';
                }
            }
        } catch (e) {
        } finally {
            if ($data) {
                //定义全局函数
                $data.channel = channel;
                $data.util = util;
            }
        }
    }
};
/**
 * 判断是否为空
 */

//工具类
var util = {
    notEmpty: function (p) {
        console.info('notEmpty...........');
        if (p == undefined || p == null ||  p == '' || p == 'null'  || p == 'undefined') {
            return false;
        } else {
            return true;
        }
    },
    dateTimeFormat: function (value) {
        if (value) {
            var date = new Date(value); //value为时间戳
            var Y = date.getFullYear() + '-'; //年
            var M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-'; //月
            var D = date.getDate() < 10 ? '0' + date.getDate() : date.getDate(); //日 + ' '; //日
            var h = date.getHours() + ':'; //时
            var m = date.getMinutes() + ':'; //分
            var s = date.getSeconds(); //秒
            return Y + M + D + " " + h + m + s; // 2016-12-7 16:0:12
        }
        return "";
    }
    , guid: function () {
        function S4() {
            return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
        }

        return (S4() + S4() + "-" + S4() + "-" + S4() + "-" + S4() + "-" + S4() + S4() + S4());
    }
    , parseInt: function (num) {
        return Math.round(num);
    }
    , parseJson: function (json) {
        return JSON.parse(json);
    }
    , split: function (str,splitStr) {
        if(str && splitStr) return str.split(splitStr);
        return [];
    }
    , removeArrItem: function (_arr, _obj) {
        var length = _arr.length;
        for (var i = 0; i < length; i++) {
            if (_arr[i] == _obj) {
                if (i == 0) {
                    _arr.shift(); //删除并返回数组的第一个元素
                    return;
                } else if (i == length - 1) {
                    _arr.pop();  //删除并返回数组的最后一个元素
                    return;
                } else {
                    _arr.splice(i, 1); //删除下标为i的元素
                    return;
                }
            }
        }
    }
};
