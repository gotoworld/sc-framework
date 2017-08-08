var basePath="http://localhost";
var apiPath={
    account:"http://localhost"
}
var site = {
    user: {
         login: apiPath.account + "/boss/account/sign/login" //登录
        ,refreshToken: apiPath.account + "/boss/account/sign/refreshToken" //刷新token
    }
    ,sysDomain: {
         view: basePath + "/html/account/sys/sys_domain"
        ,page: apiPath.account + "/boss/account/sys/sysDomain/page/"
        ,save: apiPath.account + "/boss/account/sys/sysDomain/save"
        ,info: apiPath.account + "/boss/account/sys/sysDomain/info/"
        ,del: apiPath.account + "/boss/account/sys/sysDomain/del/"
    }
}
var $data;
var pageSize = 15;
var page;
var isPageReady = false;//标记防止重复加载

function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}
/**用户登录信息验证头*/
$.ajaxSetup({
    headers: {
        "Authorization": sessionStorage.getItem("hsd_authorizationToken")
    }
    , xhrFields: {
        withCredentials: true
    }
    , crossDomain: true
})
$(document).ajaxComplete(function (event, xhr, settings) {
    if (xhr && xhr.responseText) {
        var result = JSON.parse(xhr.responseText);
        console.info("result=="+JSON.stringify(result))
        if (result.code == 403) {//授权验证失败!
            // console.info('授权验证失败!需跳转到登陆界面');
            alert('授权验证失败,请重新登陆!');
            location.href = '/login.html';
        }
    }
});

/**用户信息*/
var user = {
    login: function (userJson, callback) {
        $.post(site.user.login, userJson,
            function (result) {
                if (result.code == 0) {
                    if (result.data) {
                        sessionStorage.setItem('hsd_user', JSON.stringify(result.data.user));
                        if (result.data.authorizationToken) {
                            sessionStorage.setItem('hsd_tokenExpMillis', result.data.tokenExpMillis);
                            sessionStorage.setItem("hsd_authorizationToken", result.data.authorizationToken);
                            sessionStorage.setItem("hsd_authorizationInfoPerms", result.data.authorizationInfoPerms);
                            sessionStorage.setItem("hsd_authorizationInfoRoles", result.data.authorizationInfoRoles);
                            document.cookie = "sid=" + result.data.sid + ";expires=Session;";
                        }
                    }
                    callback && callback();
                } else {
                    $data.result = result;
                    // alert(result.message);
                    $data.$apply();
                }
            }, 'json');
    },
    refreshToken: function (callback) {
        $.get(site.user.refreshToken, {},
            function (result) {
                if (result.code == 0) {
                    if (result.data) {
                        if (result.data.authorizationToken) {
                            sessionStorage.setItem('hsd_tokenExpMillis', result.data.tokenExpMillis);
                            sessionStorage.setItem("hsd_authorizationToken", result.data.authorizationToken)
                        }
                    }
                    callback && callback();
                } else {
                    alert(result.message);
                }
            }, 'json');
    },
    logout: function (callback) {
        sessionStorage.removeItem('hsd_tokenExpMillis');
        sessionStorage.removeItem('hsd_user');
        sessionStorage.removeItem("hsd_authorizationToken");
        sessionStorage.removeItem("hsd_authorizationInfoPerms");
        sessionStorage.removeItem("hsd_authorizationInfoRoles");

        location.href = '/login.html';
    },
    info: function (callback) {
        var userJson = sessionStorage.getItem('hsd_user');
        if (userJson) {
            var userInfo = JSON.parse(userJson);
            $data.userInfo = userInfo;
        }
        callback && callback();
    },
    init: function (callback) {
        try {
            var expMillis = 0;
            if (sessionStorage.getItem('hsd_tokenExpMillis')) {
                expMillis = sessionStorage.getItem('hsd_tokenExpMillis') - (new Date().getTime());
            }
            if (expMillis > 0 && expMillis < (10 * 60 * 1000)) {//还有10分钟过期
                this.refreshToken();
            } else {
                if (sessionStorage.getItem('hsd_user') && expMillis > 0) {
                    user.info(callback);
                } else {
                    // this.login(function () {
                    //     user.info(callback);
                    // })
                    alert('登陆过期,请重新登陆!');
                    location.href = '/login.html';
                }
            }
        } catch (e) {
        }finally {
            if($data){
                //定义全局函数
                $data.openMyBoxLayer = function (mytitle, myurl) {
                    openMyBoxLayer(mytitle, myurl);
                }
                $data.closeMyBoxLayer = function () {
                    closeMyBoxLayer();
                }
                $data.loadUrlPage = function (pageNumA, formId, divId) {
                    loadUrlPage(pageNumA, formId, divId);
                }
                $data.user=user;
            }
        }
    }
    //-- shiro 权限
    , hasPermission: function (str) {
        return true;
        var hsd_authorizationInfo = sessionStorage.getItem("hsd_authorizationInfoPerms");
        if (hsd_authorizationInfo) {
            var permsArr = hsd_authorizationInfo.split(",");
            if (permsArr && permsArr.indexOf(str) != -1) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
    , hasRole: function (str) {
        var hsd_authorizationInfo = sessionStorage.getItem("hsd_authorizationInfoRoles");
        if (hsd_authorizationInfo) {
            var permsArr = hsd_authorizationInfo.split(",");
            if (permsArr && permsArr.indexOf(str) != -1) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}

/**
 * 判断是否为空
 */

//工具类
var util={
    notEmpty:function (p){
        if(p==null || p=='' || p== 'null' || p==undefined || p== 'undefined'){
            return true;
        }else{
            return false;
        }
    },
    dateTimeFormat:function(value) {
        if (!value) {
            return ""
        } else {
            var date = new Date(value); //value为时间戳
            var Y = date.getFullYear() + '-'; //年
            var M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-'; //月
            var D = date.getDate() < 10 ? '0' + date.getDate() : date.getDate(); //日 + ' '; //日
            var h = date.getHours() + ':'; //时
            var m = date.getMinutes() + ':'; //分
            var s = date.getSeconds(); //秒
            return Y + M + D + " " + h + m + s; // 2016-12-7 16:0:12
        }
    }
}
