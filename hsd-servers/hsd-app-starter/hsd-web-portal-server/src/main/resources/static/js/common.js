var basePath = "http://localhost:8080/";
var apiPath = {
    account: {
        channel: "http://192.168.101.100:28890/hsd-account-channel-web-boss",
        actor: "http://192.168.101.100:28890/hsd-account-actor-web-boss-wxg"
    }
}
var site = {
    user: {
        login: apiPath.account.actor + "/boss/account/user/sign/login" //登录
        , logout: apiPath.account.actor + "/boss/account/user/sign/logout" //登出
        , refreshToken: apiPath.account.actor + "/boss/account/user/sign/refreshToken" //刷新token
        , updatePwd: apiPath.account.actor + "/boss/account/user/org/orguser/update/pwd" //密码更新
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

/**员工登录信息验证头*/
$.ajaxSetup({
    headers: {
        "Authorization": sessionStorage.getItem("hsd_user_authorizationToken")
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

/**员工信息*/
var user = {
    login: function (userJson, callback) {
        $.post(site.user.login, userJson,
            function (result) {
                if (result.code == 0) {
                    if (result.data) {
                        sessionStorage.setItem('hsd_user_user', JSON.stringify(result.data.user));
                        if (result.data.authorizationToken) {
                            sessionStorage.setItem('hsd_user_tokenExpMillis', result.data.tokenExpMillis);
                            sessionStorage.setItem("hsd_user_authorizationToken", result.data.authorizationToken);
                            sessionStorage.setItem("hsd_user_authorizationInfoPerms", result.data.authorizationInfoPerms);
                            sessionStorage.setItem("hsd_user_authorizationInfoRoles", result.data.authorizationInfoRoles);
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
                        sessionStorage.setItem('hsd_user_tokenExpMillis', result.data.tokenExpMillis);
                        sessionStorage.setItem("hsd_user_authorizationToken", result.data.authorizationToken)
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
            sessionStorage.removeItem('hsd_user_tokenExpMillis');
            sessionStorage.removeItem('hsd_user_user');
            sessionStorage.removeItem("hsd_user_authorizationToken");
            sessionStorage.removeItem("hsd_user_authorizationInfoPerms");
            sessionStorage.removeItem("hsd_user_authorizationInfoRoles");
            callback && callback();
            top.location.href = '/login.html';
        }, 'json');
    },
    info: function (callback) {
        var userJson = sessionStorage.getItem('hsd_user_user');
        if (userJson) {
            var userInfo = JSON.parse(userJson);
            $data.userInfo = userInfo;
        }
        callback && callback();
        try {
            if ($data) {
                if (!$data.$$phase) $data.$apply();
            }
        } catch (e) {
        }
    },
    init: function (callback) {
        try {
            var expMillis = 0;
            if (sessionStorage.getItem('hsd_user_tokenExpMillis')) {
                expMillis = sessionStorage.getItem('hsd_user_tokenExpMillis') - (new Date().getTime());
            }
            if (expMillis > 0 && expMillis < (10 * 60 * 1000)) {//还有10分钟过期
                user.refreshToken(callback);
            } else {
                if (sessionStorage.getItem('hsd_user_user') && expMillis > 0) {
                    user.info(callback);
                } else {
                    // user.login(function () {
                    //     user.info(callback);
                    // })
                    alert('未登录或登陆过期,请重新登陆!');
                    top.location.href = '/login.html';
                }
            }
        } catch (e) {
        } finally {
            if ($data) {
                //定义全局函数
                $data.openMyBoxLayer = function (mytitle, myurl) {
                    openMyBoxLayer(mytitle, myurl);
                }
                $data.closeMyBoxLayer = function () {
                    closeMyBoxLayer();
                }
                $data.kfReady=function(key,val){
                    if(KindEditor) KindEditor.html('#'+key,val);
                    return val;
                }
                $data.selected=function(val1,val2){
                    if(val1==val2) return true
                    return false;
                }
                $data.user = user;
                $data.util = util;
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
