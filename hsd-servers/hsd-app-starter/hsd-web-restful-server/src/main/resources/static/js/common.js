var basePath = "http://localhost:6060" //网站根目录
var site = {
    user: {
        login: basePath + "/api/m/user/login" //登录
        , refreshToken: basePath + "/api/m/user/refreshToken" //刷新token
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
        "Authorization": sessionStorage.getItem("vr_authorizationToken")
    }
    , xhrFields: {
        withCredentials: true
    }
    , crossDomain: true
})
$(document).ajaxComplete(function (event, xhr, settings) {
    if (xhr && xhr.responseText) {
        var result = JSON.parse(xhr.responseText);
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
        $.get(site.user.login, userJson,
            function (result) {
                if (result.code == 0) {
                    if (result.data) {
                        sessionStorage.setItem('vr_user', JSON.stringify(result.data.user));
                        if (result.data.authorizationToken) {
                            sessionStorage.setItem('vr_tokenExpMillis', result.data.tokenExpMillis);
                            sessionStorage.setItem("vr_authorizationToken", result.data.authorizationToken);
                            sessionStorage.setItem("vr_authorizationInfoPerms", result.data.authorizationInfoPerms);
                            sessionStorage.setItem("vr_authorizationInfoRoles", result.data.authorizationInfoRoles);
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
                            sessionStorage.setItem('vr_tokenExpMillis', result.data.tokenExpMillis);
                            sessionStorage.setItem("vr_authorizationToken", result.data.authorizationToken)
                        }
                    }
                    callback && callback();
                } else {
                    alert(result.message);
                }
            }, 'json');
    },
    logout: function (callback) {
        sessionStorage.removeItem('vr_tokenExpMillis');
        sessionStorage.removeItem('vr_user');
        sessionStorage.removeItem("vr_authorizationToken");
        sessionStorage.removeItem("vr_authorizationInfoPerms");
        sessionStorage.removeItem("vr_authorizationInfoRoles");

        location.href = '/login.html';
    },
    info: function (callback) {
        var userJson = sessionStorage.getItem('vr_user');
        if (userJson) {
            var userInfo = JSON.parse(userJson);
            $data.userInfo = userInfo;
        }
        callback && callback();
    },
    init: function (callback) {
        try {
            var expMillis = 0;
            if (sessionStorage.getItem('vr_tokenExpMillis')) {
                expMillis = sessionStorage.getItem('vr_tokenExpMillis') - (new Date().getTime());
            }
            if (expMillis > 0 && expMillis < (10 * 60 * 1000)) {//还有10分钟过期
                this.refreshToken();
            } else {
                if (sessionStorage.getItem('vr_user') && expMillis > 0) {
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
        var vr_authorizationInfo = sessionStorage.getItem("vr_authorizationInfoPerms");
        if (vr_authorizationInfo) {
            var permsArr = vr_authorizationInfo.split(",");
            if (permsArr && permsArr.indexOf(str) != -1) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
    , hasRole: function (str) {
        var vr_authorizationInfo = sessionStorage.getItem("vr_authorizationInfoRoles");
        if (vr_authorizationInfo) {
            var permsArr = vr_authorizationInfo.split(",");
            if (permsArr && permsArr.indexOf(str) != -1) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}
//
// /**demo示例*/
// var demo = {
//     info: function (id) {
//         $.get(site.demo.info + id, {},
//             function (result) {
//                 //console.info("获取数据.." + JSON.stringify(result));
//                 if (result.code == 0) {
//                     $data.infoSuccess = true;
//                     $data.item = result.data;
//                 } else {
//                     $data.infoError = true;
//                     $data.message = result.message;
//                 }
//                 $data.$apply();
//             }, 'json');
//     },
//     page: function (pageNum) {
//         isPageReady = false;
//         // console.info("访问路径.." + site.demo.page + pageNum);
//         $.post(site.demo.page + pageNum, {pageSize: pageSize},
//             function (result) {
//                 page = result.data;
//                 //console.info("获取数据.." + JSON.stringify(result));
//                 if (result.code != 0 || page.size == 0) {
//                     $data.infoError = true;
//                     // console.info("size="+page.size)
//                     if (page && page.size == 0) {
//                         $data.message = "暂无数据";
//                     } else {
//                         $data.message = result.message;
//                     }
//                 } else {
//                     $data.infoSuccess = true;
//                     if ($data.list) {
//                         $data.list = $data.list.concat(page.list);
//                     } else {
//                         $data.list = page.list;
//                     }
//                     if (!page.hasNextPage) {
//                         $data.loaded = true;
//                     }
//                 }
//                 $data.$apply();
//                 isPageReady = true;
//             }, 'json');
//     }
// }
