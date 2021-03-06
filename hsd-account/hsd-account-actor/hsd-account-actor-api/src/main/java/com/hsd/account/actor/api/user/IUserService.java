package com.hsd.account.actor.api.user;

import com.github.pagehelper.PageInfo;
import com.hsd.account.actor.dto.identity.IdentityDto;
import com.hsd.account.actor.dto.user.UserDto;
import com.hsd.framework.Response;
import com.hsd.framework.config.FeignConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
/**
 * <p>客户表 业务处理接口类。
 */
@FeignClient(value = "${feign.name.account.actor}",configuration = FeignConfiguration.class)//, fallback = TestServiceHystrix.class)
public interface IUserService {
    String acPrefix = "/feign/account/actor/IUserService";
    /**
     * <p>信息列表 分页。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataIsPage")
    public PageInfo findDataIsPage(UserDto dto) throws Exception;
    /**
     * <p>信息列表。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataIsList")
    public List<UserDto> findDataIsList(UserDto dto) throws Exception;
    /**
     * <p>信息编辑。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/saveOrUpdateData")
    public Response saveOrUpdateData(UserDto dto) throws Exception;
    /**
     * <p>信息详情。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataById")
    public UserDto findDataById(UserDto dto) throws Exception;
    /**
     * <p>物理删除。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/deleteData")
    public String deleteData(UserDto dto) throws Exception;
    /**
     * <p>设置标签。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/setTags")
    public Response setTags(UserDto dto) throws Exception;
    /**
     * <p>设置黑名单。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/setBlacklist")
    public Response setBlacklist(UserDto dto) throws Exception;
    /**
     * <p>移除黑名单。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/delBlacklist")
    public Response delBlacklist(UserDto dto) throws Exception;

    /**
     * <p>用户信息。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findUserByAccount")
    public UserDto findUserByAccount(@RequestParam("account") String account, @RequestParam("userType")  Integer userType);

    /**
     * <p>更新客户登陆信息
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/lastLogin")
    public Integer lastLogin(UserDto dto);

    /**
     * <p>客户注册。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/register")
    public Response register(UserDto dto) throws Exception;
    /**
     * <p>获取账号信息。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/getAccount")
    public UserDto getAccount(UserDto dto) throws Exception;
    /**
     * <p>客户密码找回。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/restPwd")
    public Response restPwd(UserDto dto) throws Exception;
    /**
     * <p>绑定手机修改。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/phoneBind")
    public Response phoneBind(UserDto dto) throws Exception;
    /**
     * <p>绑定邮箱修改。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/emailBind")
    public Response emailBind(UserDto dto) throws Exception;
    /**
     * <p>客户密码修改。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/updatePwd")
    public Response updatePwd(UserDto dto) throws Exception;
    /**
     * <p>实名认证。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/identity")
    public Response identity(IdentityDto dto) throws Exception;

    /** <p>交易密码-设置 or 密码重置。 */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/pwd/trade/setting")
    public Response pwdTradeSetting(UserDto dto) throws Exception;
    /** <p>交易密码-修改。 */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/pwd/trade/update")
    public Response pwdTradeUpdate(UserDto dto) throws Exception;
    /** <p>找回交易密码-密码重置。 */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/pwd/trade/reset/setting")
    public Response pwdTradeResetSetting(UserDto dto) throws Exception;

}