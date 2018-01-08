package com.hsd.account.finance.api;

import com.hsd.framework.Response;
import com.hsd.framework.config.FeignConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * Created by hsd7 on 2018/1/5.
 */
@FeignClient(value = "${feign.name.account.finance}",configuration = FeignConfiguration.class)//, fallback = TestServiceHystrix.class)
public interface ITransactionService {
    String acPrefix = "/feign/account/ITransactionService";
    /**
     * <p>扣款。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/deduct")
    public Response deduct(@RequestParam("userId") Long userId, @RequestParam("accountId") Long accountId, @RequestParam("cardNo") String cardNo, @RequestParam("amount") BigDecimal amount) throws Exception;

    /**
     * <p>冻结。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/frozen")
    public Response frozen(@RequestParam("userId") Long userId, @RequestParam("accountId") Long accountId, @RequestParam("amount") BigDecimal amount) throws Exception;

    /**
     * <p>解冻。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/frozen")
    public Response unfreeze(@RequestParam("userId") Long userId, @RequestParam("accountId") Long accountId, @RequestParam("amount") BigDecimal amount) throws Exception;


}
