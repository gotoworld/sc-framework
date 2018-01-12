var uploadPath = "http://192.168.108.100:28888/";
var basePath = "http://localhost/";
// var basePath = "http://192.168.108.100:8080/";
var apiPath = {
    account: {
        staff: "http://192.168.108.100:28890/hsd-account-staff-web-boss",//-wxg
        channel: "http://192.168.108.100:28890/hsd-account-channel-web-boss",//-wxg
        actor: "http://192.168.108.100:28890/hsd-account-actor-web-boss",//
        finance: "http://192.168.108.100:28890/hsd-account-finance-web-boss"//-wxg
    }
};
var site = {
    staff: {
        login: apiPath.account.staff + "/boss/account/staff/sign/login" //登录
        , logout: apiPath.account.staff + "/boss/account/staff/sign/logout" //登出
        , refreshToken: apiPath.account.staff + "/boss/account/staff/sign/refreshToken" //刷新token
        , updatePwd: apiPath.account.staff + "/boss/account/staff/org/orgStaff/update/pwd" //密码更新
    }
    , sysApp: {//应用系统表
        view: basePath + "/html/account/staff/sys/sys_app"
        ,page: apiPath.account.staff + "/boss/account/staff/sys/sysApp/page/"       //分页
        ,recycle: apiPath.account.staff + "/boss/account/staff/sys/sysApp/recycle/"       //回收站
        ,list: apiPath.account.staff + "/boss/account/staff/sys/sysApp/list"       //列表
        ,save: apiPath.account.staff + "/boss/account/staff/sys/sysApp/save"        //新增or保存
        ,info: apiPath.account.staff + "/boss/account/staff/sys/sysApp/info/"       //详情
        ,phydel: apiPath.account.staff + "/boss/account/staff/sys/sysApp/phydel/"   //物理删除
        ,del: apiPath.account.staff + "/boss/account/staff/sys/sysApp/del/"   //删除
        ,recovery: apiPath.account.staff + "/boss/account/staff/sys/sysApp/recovery/"   //恢复
    }
    , orgInfo: {
        view: basePath + "/html/account/staff/org/org_info"
        , page: apiPath.account.staff + "/boss/account/staff/org/orgInfo/page/"
        , briefPage: apiPath.account.staff + "/boss/account/staff/org/orgInfo/briefPage/"
        , recyclePage: apiPath.account.staff + "/boss/account/staff/org/orgInfo/recyclePage/"
        , tree: apiPath.account.staff + "/boss/account/staff/org/orgInfo/tree"
        , save: apiPath.account.staff + "/boss/account/staff/org/orgInfo/save"
        , info: apiPath.account.staff + "/boss/account/staff/org/orgInfo/info/"
        , del: apiPath.account.staff + "/boss/account/staff/org/orgInfo/del/"
        , getStaff: apiPath.account.staff + "/boss/account/staff/org/orgInfo/get/staff/"
        , addStaff: apiPath.account.staff + "/boss/account/staff/org/orgInfo/add/staff"
        , delStaff: apiPath.account.staff + "/boss/account/staff/org/orgInfo/del/staff"
        , getRole: apiPath.account.staff + "/boss/account/staff/org/orgInfo/get/role/"
        , addRole: apiPath.account.staff + "/boss/account/staff/org/orgInfo/add/role"
        , delRole: apiPath.account.staff + "/boss/account/staff/org/orgInfo/del/role"
        , setManager: apiPath.account.staff + "/boss/account/staff/org/orgInfo/set/manager"
        , getManager: apiPath.account.staff + "/boss/account/staff/org/orgInfo/get/manager/"
        , recovery: apiPath.account.staff + "/boss/account/staff/org/orgInfo/recovery/"
    }
    , orgStaff: {
        view: basePath + "/html/account/staff/org/org_staff"
        , page: apiPath.account.staff + "/boss/account/staff/org/orgStaff/page/"
        , recyclePage: apiPath.account.staff + "/boss/account/staff/org/orgStaff/recyclePage/"
        , briefPage: apiPath.account.staff + "/boss/account/staff/org/orgStaff/briefPage/"
        , recovery: apiPath.account.staff + "/boss/account/staff/org/orgStaff/recovery/"
        , info: apiPath.account.staff + "/boss/account/staff/org/orgStaff/info/"
        , del: apiPath.account.staff + "/boss/account/staff/org/orgStaff/del/"
        , save: apiPath.account.staff + "/boss/account/staff/org/orgStaff/save"
        , isAccountYN: apiPath.account.staff + "/boss/account/staff/org/orgStaff/isAccountYN/"
        , resetpwd: apiPath.account.staff + "/boss/account/staff/org/orgStaff/reset/pwd/"
        , addBatch: apiPath.account.staff + "/boss/account/staff/org/orgStaff/add/batch"
        , getOrg: apiPath.account.staff + "/boss/account/staff/org/orgStaff/get/org/"
        , addOrg: apiPath.account.staff + "/boss/account/staff/org/orgStaff/add/org"
        , delOrg: apiPath.account.staff + "/boss/account/staff/org/orgStaff/del/org"
        , getStaffRole: apiPath.account.staff + "/boss/account/staff/org/orgStaff/get/role/staff/"
        , getOrgRole: apiPath.account.staff + "/boss/account/staff/org/orgStaff/get/role/org/"
        , addRole: apiPath.account.staff + "/boss/account/staff/org/orgStaff/add/role"
        , delRole: apiPath.account.staff + "/boss/account/staff/org/orgStaff/del/role"
        , setLeadership: apiPath.account.staff + "/boss/account/staff/org/orgStaff/set/leadership"
        , getLeadership: apiPath.account.staff + "/boss/account/staff/org/orgStaff/get/leadership/"
        , getMaxJobNo: apiPath.account.staff + "/boss/account/staff/org/orgStaff/maxJobNo"
        ,offline :apiPath.account.staff + "/boss/account/staff/org/orgStaff/offline/"
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
        , app: apiPath.account.staff + "/boss/account/staff/auth/authRole/app"
        , info: apiPath.account.staff + "/boss/account/staff/auth/authRole/info/"
        , del: apiPath.account.staff + "/boss/account/staff/auth/authRole/del/"
    }
    , sysVariable: {
        view: basePath + "/html/account/staff/sys/sys_variable"
        , page: apiPath.account.staff + "/boss/account/staff/sys/sysVariable/page/"
        , list: apiPath.account.staff + "/boss/account/staff/sys/sysVariable/list/"
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
        , addBatch: apiPath.account.channel + "/boss/account/channel/channel/channelInfo/add/batch"

    }
    , channelType: {//渠道商类型
        view: basePath + "/html/account/channel/channel/channel_type"
        , page: apiPath.account.channel + "/boss/account/channel/channel/channelType/page/"
        , list: apiPath.account.channel + "/boss/account/channel/channel/channelType/list"
        , save: apiPath.account.channel + "/boss/account/channel/channel/channelType/save"
        , info: apiPath.account.channel + "/boss/account/channel/channel/channelType/info/"
        , del: apiPath.account.channel + "/boss/account/channel/channel/channelType/del/"
    }
    ,user: {//用户表
        view: basePath + "/html/account/actor/user/user"
        ,page: apiPath.account.actor + "/boss/account/actor/user/user/page/"
        ,save: apiPath.account.actor + "/boss/account/actor/user/user/save"
        ,setTags: apiPath.account.actor + "/boss/account/actor/user/user/setTags"
        ,info: apiPath.account.actor + "/boss/account/actor/user/user/info/"
        ,setBlacklist: apiPath.account.actor + "/boss/account/actor/user/user/set/blacklist/"
        ,delBlacklist: apiPath.account.actor + "/boss/account/actor/user/user/del/blacklist/"
    }
    , businessType: {//业务类型
        view: basePath + "/html/account/actor/actor/business_type"
        ,page: apiPath.account.actor + "/boss/account/actor/actor/businessType/page/"
        ,save: apiPath.account.actor + "/boss/account/actor/actor/businessType/save"
        ,info: apiPath.account.actor + "/boss/account/actor/actor/businessType/info/"
        ,del: apiPath.account.actor + "/boss/account/actor/actor/businessType/del/"
    }
    ,member: {//会员信息表
        view: basePath + "/html/account/actor/actor/member"
        ,page: apiPath.account.actor + "/boss/account/actor/actor/member/page/"
        ,save: apiPath.account.actor + "/boss/account/actor/actor/member/save"
        ,info: apiPath.account.actor + "/boss/account/actor/actor/member/info/"
        ,del: apiPath.account.actor + "/boss/account/actor/actor/member/del/"
    }
    , tag: {//标签
        view: basePath + "/html/account/actor/actor/tag"
        ,page: apiPath.account.actor + "/boss/account/actor/actor/tag/page/"       //分页
        ,save: apiPath.account.actor + "/boss/account/actor/actor/tag/save"        //新增or保存
        ,list: apiPath.account.actor + "/boss/account/actor/actor/tag/list"        //列表
        ,info: apiPath.account.actor + "/boss/account/actor/actor/tag/info/"       //详情
        ,phydel: apiPath.account.actor + "/boss/account/actor/actor/tag/phydel/"   //物理删除
    }
    ,identity: {//实名认证表
        view: basePath + "/html/account/actor/identity/identity"
        ,page: apiPath.account.actor + "/boss/account/actor/identity/identity/page/"
        ,save: apiPath.account.actor + "/boss/account/actor/identity/identity/save"
        ,info: apiPath.account.actor + "/boss/account/actor/identity/identity/info/"
    }
    , identityLog: {//用户实名认证日志
        view: basePath + "/html/account/actor/identity/identity_log"
        ,page: apiPath.account.actor + "/boss/account/actor/identity/identityLog/page/"       //分页
        ,info: apiPath.account.actor + "/boss/account/actor/identity/identityLog/info/"       //详情
    }
    ,template: {//档案模板
        view: basePath + "/html/account/actor/template/template"
        ,page: apiPath.account.actor + "/boss/account/actor/template/template/page/"
        ,list: apiPath.account.actor + "/boss/account/actor/template/template/list"
        ,save: apiPath.account.actor + "/boss/account/actor/template/template/save"
        ,info: apiPath.account.actor + "/boss/account/actor/template/template/info/"
        ,del: apiPath.account.actor + "/boss/account/actor/template/template/del/"
    }
    , templateAttribute: {//模板属性
        view: basePath + "/html/account/actor/template/template_attribute"
        ,list: apiPath.account.actor + "/boss/account/actor/template/templateAttribute/list"        //列表
    }
    ,userExtInfo: {//用户扩展数据
        view: basePath + "/html/account/actor/user/user_ext_info"
        ,info: apiPath.account.actor + "/boss/account/actor/user/userExtInfo/info/"
    }
    ,userSignContract: {//用户网签协议记录
        view: basePath + "/html/account/actor/user/user_sign_contract"
        ,page: apiPath.account.actor + "/boss/account/actor/user/userSignContract/page/"
        ,save: apiPath.account.actor + "/boss/account/actor/user/userSignContract/save"
        ,info: apiPath.account.actor + "/boss/account/actor/user/userSignContract/info/"
    }
    ,userSnapshot: {//用户照表
        view: basePath + "/html/account/actor/user/user_snapshot"
        ,info: apiPath.account.actor + "/boss/account/actor/user/userSnapshot/info/"
    }
    ,userLoginLog: {//用户登录日志
        view: basePath + "/html/account/actor/user/user_login_log"
        ,page: apiPath.account.actor + "/boss/account/actor/user/userLoginLog/page/"
        ,info: apiPath.account.actor + "/boss/account/actor/user/userLoginLog/info/"
    }
    //------------------------金融账户------------------------
    , accountCapital: {//支付账户
        view: basePath + "/html/account/finance/account"
        ,page: apiPath.account.finance + "/boss/account/finance/account/page/"       //分页
        ,info: apiPath.account.finance + "/boss/account/finance/account/info/"       //详情
        ,freeze: apiPath.account.finance + "/boss/account/finance/account/op/freeze"   //冻结
        ,unfreeze: apiPath.account.finance + "/boss/account/finance/account/op/unfreeze"   //解冻
        ,state: apiPath.account.finance + "/boss/account/finance/account/op/state"   //状态变更
        ,reverse: apiPath.account.finance + "/boss/account/finance/account/op/reverse"   //冲正/抵扣
    }
    , accountSubGold: {//子账户-实物贵金属
        view: basePath + "/html/account/finance/account_sub_gold"
        ,page: apiPath.account.finance + "/boss/account/finance/accountSubGold/page/"       //分页
        ,info: apiPath.account.finance + "/boss/account/finance/accountSubGold/info/"       //详情
        ,freeze: apiPath.account.finance + "/boss/account/finance/accountSubGold/op/freeze"   //冻结
        ,unfreeze: apiPath.account.finance + "/boss/account/finance/accountSubGold/op/unfreeze"   //解冻
        ,state: apiPath.account.finance + "/boss/account/finance/accountSubGold/op/state"   //状态变更
        ,reverse: apiPath.account.finance + "/boss/account/finance/accountSubGold/op/reverse"   //冲正/抵扣
    }
    , accountSubLoan: {//子账户-P2P网贷
        view: basePath + "/html/account/finance/account_sub_loan"
        ,page: apiPath.account.finance + "/boss/account/finance/accountSubLoan/page/"       //分页
        ,info: apiPath.account.finance + "/boss/account/finance/accountSubLoan/info/"       //详情
        ,freeze: apiPath.account.finance + "/boss/account/finance/accountSubLoan/op/freeze"   //冻结
        ,unfreeze: apiPath.account.finance + "/boss/account/finance/accountSubLoan/op/unfreeze"   //解冻
        ,state: apiPath.account.finance + "/boss/account/finance/accountSubLoan/op/state"   //状态变更
        ,reverse: apiPath.account.finance + "/boss/account/finance/accountSubLoan/op/reverse"   //冲正/抵扣
    }
    , accountBindThirdparty: {//支付账户与三方账户绑定
        view: basePath + "/html/account/finance/account_bind_thirdparty"
        ,page: apiPath.account.finance + "/boss/account/finance/accountBindThirdparty/page/"       //分页
        ,info: apiPath.account.finance + "/boss/account/finance/accountBindThirdparty/info/"       //详情
    }
    , accountTemplate: {//账户模板
        view: basePath + "/html/account/finance/account_template"
        ,page: apiPath.account.finance + "/boss/account/finance/accountTemplate/page/"       //分页
        ,save: apiPath.account.finance + "/boss/account/finance/accountTemplate/save"        //新增or保存
        ,list: apiPath.account.finance + "/boss/account/finance/accountTemplate/list"        //列表
        ,recycle: apiPath.account.finance + "/boss/account/finance/accountTemplate/recycle/" //回收站
        ,info: apiPath.account.finance + "/boss/account/finance/accountTemplate/info/"       //详情
        ,del: apiPath.account.finance + "/boss/account/finance/accountTemplate/del/"         //逻辑删除
        ,phydel: apiPath.account.finance + "/boss/account/finance/accountTemplate/phydel/"   //物理删除
        ,recovery: apiPath.account.finance + "/boss/account/finance/accountTemplate/recovery/"  //恢复删除
    }
    , accountType: {//账户类型
        view: basePath + "/html/account/finance/account_type"
        ,page: apiPath.account.finance + "/boss/account/finance/accountType/page/"       //分页
        ,list: apiPath.account.finance + "/boss/account/finance/accountType/list"        //列表
        ,recycle: apiPath.account.finance + "/boss/account/finance/accountType/recycle/" //回收站
        ,save: apiPath.account.finance + "/boss/account/finance/accountType/save"        //新增or保存
        ,info: apiPath.account.finance + "/boss/account/finance/accountType/info/"       //详情
        ,del: apiPath.account.finance + "/boss/account/finance/accountType/del/"         //逻辑删除
        ,phydel: apiPath.account.finance + "/boss/account/finance/accountType/phydel/"   //物理删除
        ,recovery: apiPath.account.finance + "/boss/account/finance/accountType/recovery/"  //恢复删除
    }
    , accountTemplateAttribute: {//账户模板属性
        list: apiPath.account.finance + "/boss/account/finance/accountTemplateAttribute/list"        //列表
    }
    , accountLog: {//账户-日志-交易记录
        view: basePath + "/html/account/finance/account_log"
        ,page: apiPath.account.finance + "/boss/account/finance/accountLog/page/"       //分页
        ,info: apiPath.account.finance + "/boss/account/finance/accountLog/info/"       //详情
    }
    , accountLogFreeze: {//账户-日志-资金冻结记录
        view: basePath + "/html/account/finance/account_log_freeze"
        ,page: apiPath.account.finance + "/boss/account/finance/accountLogFreeze/page/"       //分页
        ,info: apiPath.account.finance + "/boss/account/finance/accountLogFreeze/info/"       //详情
    }
    , accountLogOperational: {//用户账户-操作日志
        view: basePath + "/html/account/finance/account_log_operational"
        ,page: apiPath.account.finance + "/boss/account/finance/accountLogOperational/page/"       //分页
        ,info: apiPath.account.finance + "/boss/account/finance/accountLogOperational/info/"       //详情
    }
    , accountLogRecharge: {//账户-日志-充值记录
        view: basePath + "/html/account/finance/account_log_recharge"
        ,page: apiPath.account.finance + "/boss/account/finance/accountLogRecharge/page/"       //分页
        ,info: apiPath.account.finance + "/boss/account/finance/accountLogRecharge/info/"       //详情
    }
    , accountLogWithdrawal: {//账户-日志-提现流水
        view: basePath + "/html/account/finance/account_log_withdrawal"
        ,page: apiPath.account.finance + "/boss/account/finance/accountLogWithdrawal/page/"       //分页
        ,info: apiPath.account.finance + "/boss/account/finance/accountLogWithdrawal/info/"       //详情
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

/**员工登录信息验证头*/
$.ajaxSetup({
    headers: {
        "Authorization": sessionStorage.getItem("hsd_staff_authorizationToken"),
        "X-Cache": sessionStorage.getItem("XCache")
    }
});
$(document).ajaxComplete(function (event, xhr, settings) {
    if (xhr && xhr.responseText) {
        var result ={};
        try {
            result = JSON.parse(xhr.responseText);
        } catch (e) {
        }
        //console.info("result=="+JSON.stringify(result))
        if (result.code == 403||result.code == 110) {//授权验证失败!
            //alert('授权验证失败,请重新登陆!');
            sessionStorage.removeItem('hsd_staff_tokenExpMillis');
            sessionStorage.removeItem('hsd_staff_staff');
            sessionStorage.removeItem("hsd_staff_authorizationToken");
            sessionStorage.removeItem("hsd_staff_authorizationInfoPerms");
            sessionStorage.removeItem("hsd_staff_authorizationInfoRoles");
            sessionStorage.removeItem("hsd_staff_app");
            if (result.code == 110) {
                alert(result.message);
            }
            top.location.href = '/login.html';
        }
    }
});

/**员工信息*/
var staff = {
    login: function (staffJson, callback) {
        $.post(site.staff.login, staffJson,
            function (result) {
                if (result.code == 0) {
                    if (result.data) {
                        sessionStorage.setItem('hsd_staff_staff', JSON.stringify(result.data.staff));
                        if (result.data.authorizationToken) {
                            sessionStorage.setItem('hsd_staff_tokenExpMillis', result.data.tokenExpMillis);
                            sessionStorage.setItem("hsd_staff_authorizationToken", result.data.authorizationToken);
                            sessionStorage.setItem("hsd_staff_authorizationInfoPerms", result.data.staff.authorizationInfoPerms);
                            sessionStorage.setItem("hsd_staff_authorizationInfoRoles", result.data.staff.authorizationInfoRoles);
                            sessionStorage.setItem("hsd_staff_app", JSON.stringify(result.data.app));
                            sessionStorage.setItem('XCache', result.data.XCache);
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
        $.get(site.staff.refreshToken, {}, function (result) {
            if (result.code == 0) {
                if (result.data) {
                    if (result.data.authorizationToken) {
                        sessionStorage.setItem('hsd_staff_tokenExpMillis', result.data.tokenExpMillis);
                        sessionStorage.setItem("hsd_staff_authorizationToken", result.data.authorizationToken);
                        sessionStorage.setItem('XCache', result.data.XCache);
                    }
                }
                callback && callback();
            } else {
                alert(result.message);
            }
        }, 'json');
    },
    logout: function (callback) {
        $.get(site.staff.logout, {}, function (result) {
            sessionStorage.removeItem('hsd_staff_tokenExpMillis');
            sessionStorage.removeItem('hsd_staff_staff');
            sessionStorage.removeItem("hsd_staff_authorizationToken");
            sessionStorage.removeItem("hsd_staff_authorizationInfoPerms");
            sessionStorage.removeItem("hsd_staff_authorizationInfoRoles");
            sessionStorage.removeItem("hsd_staff_app");
            callback && callback();
            top.location.href = '/login.html';
        }, 'json');
    },
    info: function (callback) {
        var staffJson = sessionStorage.getItem('hsd_staff_staff');
        if (staffJson) {
            var staffInfo = JSON.parse(staffJson);
            $data.staffInfo = staffInfo;
            var staffAppJson = sessionStorage.getItem('hsd_staff_app');
            if (staffAppJson) {
                $data.staffApps = JSON.parse(staffAppJson);
            }
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
                staff.refreshToken(callback);
            } else {
                if (sessionStorage.getItem('hsd_staff_staff') && expMillis > 0) {
                    staff.info(callback);
                } else {
                    // staff.login(function () {
                    //     staff.info(callback);
                    // })
                    //alert('未登录或登陆过期,请重新登陆!');
                    top.location.href = '/login.html';
                }
            }
        } catch (e) {
        } finally {
            if ($data) {
                //定义全局函数
                $data.openMyBoxLayer = function (mytitle, myurl) {
                    openMyBoxLayer(mytitle, myurl);
                };
                $data.closeMyBoxLayer = function () {
                    closeMyBoxLayer();
                };
                $data.kfReady=function(key,val){
                    if(KindEditor) KindEditor.html('#'+key,val);
                    return val;
                };
                $data.selected=function(val1,val2){
                    if(val1==val2) return true;
                    return false;
                };
                $data.staff = staff;
                $data.util = util;
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
};

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
        if(!_arr || _arr.length ==0) return;
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
