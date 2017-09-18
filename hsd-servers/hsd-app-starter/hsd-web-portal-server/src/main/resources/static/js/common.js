var basePath = "http://localhost:8080/";
var apiPath = {
    account: {
        channel: "http://192.168.101.100:28890/hsd-account-channel-web-portal",
        actor: "http://192.168.101.100:28890/hsd-account-actor-web-portal"
    }
}
var site = {
    portalHome:"/html/account/actor/user/home.html"
    ,user: {
        login: apiPath.account.actor + "/api/account/actor/sign/login" //登录
        , logout: apiPath.account.actor + "/api/account/actor/sign/logout" //登出
        , refreshToken: apiPath.account.actor + "/api/account/actor/sign/refreshToken" //刷新token
    }
    ,findpwd: {
        view: basePath + "/html/account/actor/user/findpwd"
        , verify: basePath + "/html/account/actor/user/findpwd_verify"
        , getAccount: apiPath.account.actor + "/api/account/actor/findpwd/getAccount"
        , sendSms:apiPath.account.actor+"/api/account/actor/findpwd/send/captcha/sms"
        , sendMmail:apiPath.account.actor+"/api/account/actor/findpwd/send/captcha/email"
        , verifyCaptchaSms: apiPath.account.actor + "/api/account/actor/findpwd/verifyCaptchaSms" //验证码校验并设置密码重置状态
        , verifyCaptchaEmail: apiPath.account.actor + "/api/account/actor/findpwd/verifyCaptchaEmail" //验证码校验并设置密码重置状态
        , verifystate: apiPath.account.actor + "/api/account/actor/findpwd/verifystate" //校验密码重置状态
        , update: apiPath.account.actor + "/api/account/actor/findpwd/update" //密码修改
    }
    ,register: {
        view: basePath + "/html/account/actor/user/register"
        , register: apiPath.account.actor + "/api/account/actor/register/reg"
    }
    ,userSetting: {
         view:"/html/account/actor/home_setting"
        ,info: apiPath.account.actor + "/api/account/actor/user/setting/info"
        ,phone:{
            view: basePath + "/html/account/actor/setting/phone"
            ,  bindSms: apiPath.account.actor + "/api/account/actor/user/setting/phone/bind/sms" //手机号绑定-短信验证码
            , updateSms: apiPath.account.actor + "/api/account/actor/user/setting/phone/update/sms" //手机号修改-短信验证码
            , verifySms: apiPath.account.actor + "/api/account/actor/user/setting/phone/verify/sms" //手机号验证-短信验证码
            , verify: apiPath.account.actor + "/api/account/actor/user/setting/phone/verify" //手机号验证
            , bind: apiPath.account.actor + "/api/account/actor/user/setting/phone/bind" //手机号绑定
            , update: apiPath.account.actor + "/api/account/actor/user/setting/phone/update" //手机号修改
        }
        ,loginpwd:{
            view: basePath + "/html/account/actor/setting/pwd_login"
            ,update: apiPath.account.actor + "/api/account/actor/user/setting/pwd/login/update"
        }
        ,tradepwd:{
            view: basePath + "/html/account/actor/setting/pwd_trade"
            ,setting: apiPath.account.actor + "/api/account/actor/user/setting/pwd/trade/setting"  //设置交易密码
            ,update: apiPath.account.actor + "/api/account/actor/user/setting/pwd/trade/update"    //修改交易密码
            ,resetVerifySms: apiPath.account.actor + "/api/account/actor/user/setting/pwd/trade/reset/verify/sms"  //找回交易密码-验证短信验证码-推送
            ,resetVerify: apiPath.account.actor + "/api/account/actor/user/setting/pwd/trade/reset/verify"  //找回交易密码-验证短信验证码-验证
            ,resetSetting: apiPath.account.actor + "/api/account/actor/user/setting/pwd/trade/reset/setting"  //找回交易密码-密码重置
        }
        ,email:{
            view: basePath + "/html/account/actor/setting/email"
            , bind: apiPath.account.actor + "/api/account/actor/user/setting/email/bind" //邮箱绑定
            , verify: apiPath.account.actor + "/api/account/actor/user/setting/email/verify" //邮箱验证
            , update: apiPath.account.actor + "/api/account/actor/user/setting/email/update" //邮箱修改
        }
        ,identity:{
            view: basePath + "/html/account/actor/setting/identity"
            , setting: apiPath.account.actor + "/api/account/actor/user/setting/identity" //实名认证
        }
        ,member:{
            view: basePath + "/html/account/actor/setting/member"
            , templateList: apiPath.account.actor + "/api/account/actor/template/template/list" //模板列表
            , templateAttributeList: apiPath.account.actor + "/api/account/actor/template/templateAttribute/list" //模板属性列表
            , info: apiPath.account.actor + "/api/account/actor/actor/member/info" //个人信息
            , setting: apiPath.account.actor + "/api/account/actor/actor/member/save" //个人信息保存
        }

    }
}
var $data, $ngHttp, $ngCompile, $ngSce;
var page;

function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}

/**客户登录信息验证头*/
$.ajaxSetup({
    headers: {
        "Authorization": sessionStorage.getItem("hsd_actor_authorizationToken"),
        "Cache-X": sessionStorage.getItem("cacheX")
    }
})
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

/**客户信息*/
var user = {
    login: function (userJson, callback) {
        $.post(site.user.login, userJson, function (result) {
            if (result.code == 0) {
                if (result.data) {
                    sessionStorage.setItem('hsd_actor_user', JSON.stringify(result.data.user));
                    if (result.data.authorizationToken) {
                        sessionStorage.setItem('hsd_actor_tokenExpMillis', result.data.tokenExpMillis);
                        sessionStorage.setItem("hsd_actor_authorizationToken", result.data.authorizationToken);
                        sessionStorage.setItem('cacheX', result.data.cacheX);
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
        $.get(site.user.refreshToken, {}, function (result) {
            if (result.code == 0) {
                if (result.data) {
                    if (result.data.authorizationToken) {
                        sessionStorage.setItem('hsd_actor_tokenExpMillis', result.data.tokenExpMillis);
                        sessionStorage.setItem("hsd_actor_authorizationToken", result.data.authorizationToken);
                        sessionStorage.setItem('cacheX', result.data.cacheX);
                    }
                }
                callback && callback();
            } else {
                alert(result.message);
            }
        }, 'json');
    },
    logout: function (callback) {
        $.get(site.user.logout, {}, function (result) {
            sessionStorage.removeItem('hsd_actor_tokenExpMillis');
            sessionStorage.removeItem('hsd_actor_user');
            sessionStorage.removeItem("hsd_actor_authorizationToken");
            callback && callback();
            top.location.href = '/login.html';
        }, 'json');
    },
    info: function (callback) {
        try {
            console.info("user.info.............")
            var userJson = sessionStorage.getItem('hsd_actor_user');
            if (userJson) {
                var userInfo = JSON.parse(userJson);
                $data.userInfo = userInfo;
            }
            callback && callback();
        } catch (e) {
        } finally {
            $data.uLogin = function () {
                user.login($('#_login_form_modal').serialize(), function () {
                    location.href = '/html/account/actor/home.html';
                });
            }
            if ($data) {
                if (!$data.$$phase) $data.$apply();
            }
        }
    },
    init: function (callback) {
        try {
            var expMillis = 0;
            if (sessionStorage.getItem('hsd_actor_tokenExpMillis')) {
                expMillis = sessionStorage.getItem('hsd_actor_tokenExpMillis') - (new Date().getTime());
            }
            if (expMillis > 0 && expMillis < (10 * 60 * 1000)) {//还有10分钟过期
                user.refreshToken(callback);
            } else {
                if (sessionStorage.getItem('hsd_actor_user') && expMillis > 0) {
                    user.info(callback);
                } else {
                    // user.login(function () {
                    //     user.info(callback);
                    // })
                    //alert('未登录或登陆过期,请重新登陆!');
                    top.location.href = '/login.html';
                }
            }
        } catch (e) {
        } finally {
            if ($data) {
                //定义全局函数
                $data.user = user;
                $data.util = util;
                $data.uLogin = function () {
                    user.login($('#_login_form').serialize(), function () {
                        location.href = '/html/account/actor/home.html';
                    });
                }
            }
        }
    }
}

/**
 * 判断是否为空
 */

//工具类
var util = {
    notEmpty: function (p) {
        console.info('notEmpty...........')
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
}
