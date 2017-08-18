var basePath = "http://localhost/";
var apiPath = {
    account: {
        staff: "http://192.168.103.236:6061",
        channel: "http://localhost:6061"
    }
}
var site = {
    user: {
        login: apiPath.account.staff + "/boss/account/staff/sign/login" //登录
        , logout: apiPath.account.staff + "/boss/account/staff/sign/logout" //登出
        , refreshToken: apiPath.account.staff + "/boss/account/staff/sign/refreshToken" //刷新token
        , updatePwd: apiPath.account.staff + "/boss/account/staff/org/orgUser/update/pwd" //密码更新
    }
    , sysDomain: {
        view: basePath + "/html/account/staff/sys/sys_domain"
        , page: apiPath.account.staff + "/boss/account/staff/sys/sysDomain/page/"
        , list: apiPath.account.staff + "/boss/account/staff/sys/sysDomain/list"
        , save: apiPath.account.staff + "/boss/account/staff/sys/sysDomain/save"
        , info: apiPath.account.staff + "/boss/account/staff/sys/sysDomain/info/"
        , del: apiPath.account.staff + "/boss/account/staff/sys/sysDomain/del/"
    }
    , orgInfo: {
        view: basePath + "/html/account/staff/org/org_info"
        , page: apiPath.account.staff + "/boss/account/staff/org/orgInfo/page/"
        , briefPage: apiPath.account.staff + "/boss/account/staff/org/orgInfo/briefPage/"
        , tree: apiPath.account.staff + "/boss/account/staff/org/orgInfo/tree"
        , save: apiPath.account.staff + "/boss/account/staff/org/orgInfo/save"
        , info: apiPath.account.staff + "/boss/account/staff/org/orgInfo/info/"
        , del: apiPath.account.staff + "/boss/account/staff/org/orgInfo/del/"
        , getUser: apiPath.account.staff + "/boss/account/staff/org/orgInfo/get/user/"
        , addUser: apiPath.account.staff + "/boss/account/staff/org/orgInfo/add/user"
        , delUser: apiPath.account.staff + "/boss/account/staff/org/orgInfo/del/user"
        , getRole: apiPath.account.staff + "/boss/account/staff/org/orgInfo/get/role/"
        , addRole: apiPath.account.staff + "/boss/account/staff/org/orgInfo/add/role"
        , delRole: apiPath.account.staff + "/boss/account/staff/org/orgInfo/del/role"
    }
    , orgUser: {
        view: basePath + "/html/account/staff/org/org_user"
        , page: apiPath.account.staff + "/boss/account/staff/org/orgUser/page/"
        , recyclePage: apiPath.account.staff + "/boss/account/staff/org/orgUser/recyclePage/"
        , briefPage: apiPath.account.staff + "/boss/account/staff/org/orgUser/briefPage/"
        , recovery: apiPath.account.staff + "/boss/account/staff/org/orgUser/recovery/"
        , info: apiPath.account.staff + "/boss/account/staff/org/orgUser/info/"
        , del: apiPath.account.staff + "/boss/account/staff/org/orgUser/del/"
        , save: apiPath.account.staff + "/boss/account/staff/org/orgUser/save"
        , isAccountYN: apiPath.account.staff + "/boss/account/staff/org/orgUser/isAccountYN/"
        , resetpwd: apiPath.account.staff + "/boss/account/staff/org/orgUser/reset/pwd/"
        , addBatch: apiPath.account.staff + "/boss/account/staff/org/orgUser/add/batch"
        , getOrg: apiPath.account.staff + "/boss/account/staff/org/orgUser/get/org/"
        , addOrg: apiPath.account.staff + "/boss/account/staff/org/orgUser/add/org"
        , delOrg: apiPath.account.staff + "/boss/account/staff/org/orgUser/del/org"
        , getUserRole: apiPath.account.staff + "/boss/account/staff/org/orgUser/get/role/user/"
        , getOrgRole: apiPath.account.staff + "/boss/account/staff/org/orgUser/get/role/org/"
        , addRole: apiPath.account.staff + "/boss/account/staff/org/orgUser/add/role"
        , delRole: apiPath.account.staff + "/boss/account/staff/org/orgUser/del/role"
    }
    , authPerm: {
        view: basePath + "/html/account/staff/auth/auth_perm"
        , page: apiPath.account.staff + "/boss/account/staff/auth/authPerm/page/"
        , tree: apiPath.account.staff + "/boss/account/staff/auth/authPerm/tree"
        , save: apiPath.account.staff + "/boss/account/staff/auth/authPerm/save"
        , info: apiPath.account.staff + "/boss/account/staff/auth/authPerm/info/"
        , del: apiPath.account.staff + "/boss/account/staff/auth/authPerm/del/"
    }
    , sysMenu: {
        view: basePath + "/html/account/staff/sys/sys_menu"
        , page: apiPath.account.staff + "/boss/account/staff/sys/sysMenu/page/"
        , tree: apiPath.account.staff + "/boss/account/staff/sys/sysMenu/tree"
        , save: apiPath.account.staff + "/boss/account/staff/sys/sysMenu/save"
        , info: apiPath.account.staff + "/boss/account/staff/sys/sysMenu/info/"
        , del: apiPath.account.staff + "/boss/account/staff/sys/sysMenu/del/"
    }
    , authRole: {
        view: basePath + "/html/account/staff/auth/auth_role"
        , page: apiPath.account.staff + "/boss/account/staff/auth/authRole/page/"
        , list: apiPath.account.staff + "/boss/account/staff/auth/authRole/list"
        , save: apiPath.account.staff + "/boss/account/staff/auth/authRole/save"
        , perm: apiPath.account.staff + "/boss/account/staff/auth/authRole/perm"
        , menu: apiPath.account.staff + "/boss/account/staff/auth/authRole/menu"
        , info: apiPath.account.staff + "/boss/account/staff/auth/authRole/info/"
        , del: apiPath.account.staff + "/boss/account/staff/auth/authRole/del/"
    }
    , sysVariable: {
        view: basePath + "/html/account/staff/sys/sys_variable"
        , page: apiPath.account.staff + "/boss/account/staff/sys/sysVariable/page/"
        , tree: apiPath.account.staff + "/boss/account/staff/sys/sysVariable/tree"
        , save: apiPath.account.staff + "/boss/account/staff/sys/sysVariable/save"
        , info: apiPath.account.staff + "/boss/account/staff/sys/sysVariable/info/"
        , del: apiPath.account.staff + "/boss/account/staff/sys/sysVariable/del/"
    }
    , orgLogLogin: {
        view: basePath + "/html/account/staff/org/org_log_login"
        , page: apiPath.account.staff + "/boss/account/staff/org/orgLogLogin/page/"
        , save: apiPath.account.staff + "/boss/account/staff/org/orgLogLogin/save"
        , info: apiPath.account.staff + "/boss/account/staff/org/orgLogLogin/info/"
        , del: apiPath.account.staff + "/boss/account/staff/org/orgLogLogin/del/"
    }
    , orgLogOperation: {
        view: basePath + "/html/account/staff/org/org_log_operation"
        , page: apiPath.account.staff + "/boss/account/staff/org/orgLogOperation/page/"
        , save: apiPath.account.staff + "/boss/account/staff/org/orgLogOperation/save"
        , info: apiPath.account.staff + "/boss/account/staff/org/orgLogOperation/info/"
        , del: apiPath.account.staff + "/boss/account/staff/org/orgLogOperation/del/"
    }
    , channelInfo: {//渠道商信息
        view: basePath + "/html/account/channel/channel/channel_info"
        , page: apiPath.account.channel + "/boss/account/channel/channel/channelInfo/page/"
        , save: apiPath.account.channel + "/boss/account/channel/channel/channelInfo/save"
        , info: apiPath.account.channel + "/boss/account/channel/channel/channelInfo/info/"
        , del: apiPath.account.channel + "/boss/account/channel/channel/channelInfo/del/"
        , recovery: apiPath.account.channel + "/boss/account/channel/channel/channelInfo/recovery/"
        , resetPwd: apiPath.account.channel + "/boss/account/channel/channel/channelInfo/resetPwd/"

    }
    , channelType: {//渠道商类型
        view: basePath + "/html/account/channel/channel/channel_type"
        , page: apiPath.account.channel + "/boss/account/channel/channel/channelType/page/"
        , list: apiPath.account.channel + "/boss/account/channel/channel/channelType/list"
        , save: apiPath.account.channel + "/boss/account/channel/channel/channelType/save"
        , info: apiPath.account.channel + "/boss/account/channel/channel/channelType/info/"
        , del: apiPath.account.channel + "/boss/account/channel/channel/channelType/del/"
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

/**用户登录信息验证头*/
$.ajaxSetup({
    headers: {
        "Authorization": sessionStorage.getItem("hsd_staff_authorizationToken")
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

/**用户信息*/
var user = {
    login: function (userJson, callback) {
        $.post(site.user.login, userJson,
            function (result) {
                if (result.code == 0) {
                    if (result.data) {
                        sessionStorage.setItem('hsd_staff_user', JSON.stringify(result.data.user));
                        if (result.data.authorizationToken) {
                            sessionStorage.setItem('hsd_staff_tokenExpMillis', result.data.tokenExpMillis);
                            sessionStorage.setItem("hsd_staff_authorizationToken", result.data.authorizationToken);
                            sessionStorage.setItem("hsd_staff_authorizationInfoPerms", result.data.authorizationInfoPerms);
                            sessionStorage.setItem("hsd_staff_authorizationInfoRoles", result.data.authorizationInfoRoles);
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
                        sessionStorage.setItem('hsd_staff_tokenExpMillis', result.data.tokenExpMillis);
                        sessionStorage.setItem("hsd_staff_authorizationToken", result.data.authorizationToken)
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
            sessionStorage.removeItem('hsd_staff_tokenExpMillis');
            sessionStorage.removeItem('hsd_staff_user');
            sessionStorage.removeItem("hsd_staff_authorizationToken");
            sessionStorage.removeItem("hsd_staff_authorizationInfoPerms");
            sessionStorage.removeItem("hsd_staff_authorizationInfoRoles");
            callback && callback();
            location.href = '/login.html';
        }, 'json');
    },
    info: function (callback) {
        var userJson = sessionStorage.getItem('hsd_staff_user');
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
            if (sessionStorage.getItem('hsd_staff_tokenExpMillis')) {
                expMillis = sessionStorage.getItem('hsd_staff_tokenExpMillis') - (new Date().getTime());
            }
            if (expMillis > 0 && expMillis < (10 * 60 * 1000)) {//还有10分钟过期
                this.refreshToken(callback);
            } else {
                if (sessionStorage.getItem('hsd_staff_user') && expMillis > 0) {
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
            }
        }
    }
    //-- shiro 权限
    , hasPermission: function (str) {
        //console.info("permsstr="+str)
        var hsd_staff_authorizationInfo = sessionStorage.getItem("hsd_staff_authorizationInfoPerms");
        //console.info("hsd_staff_authorizationInfo="+hsd_staff_authorizationInfo)
        if (hsd_staff_authorizationInfo) {
            var permsArr = hsd_staff_authorizationInfo.split(",");
            //console.info("permsArr="+permsArr)
            //console.info("permsstr="+str)
            if (permsArr && permsArr.indexOf(str) != -1) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
    , hasRole: function (str) {
        var hsd_staff_authorizationInfo = sessionStorage.getItem("hsd_staff_authorizationInfoRoles");
        if (hsd_staff_authorizationInfo) {
            var permsArr = hsd_staff_authorizationInfo.split(",");
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
var util = {
    notEmpty: function (p) {
        if (p == null || p == '' || p == 'null' || p == undefined || p == 'undefined') {
            return true;
        } else {
            return false;
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
