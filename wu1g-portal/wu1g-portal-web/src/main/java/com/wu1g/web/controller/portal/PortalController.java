package com.wu1g.web.controller.portal;

import com.wu1g.api.sys.ISysCategoryService;
import com.wu1g.api.sys.ISysNewsService;
import com.wu1g.web.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * <p>门户首页。
 */
@Controller
@Slf4j
public class PortalController extends BaseController {
	@Autowired
	protected ISysCategoryService sysCategoryService;
	@Autowired
	protected ISysNewsService sysNewsService;

	private static final String acPrefix="/";
	private static final String index = "client/index";
	private static final String channel = "client/channel";
	private static final String news = "client/news";
	private static final String tour = "client/tour";
	private static final String video = "client/video";
	/**
	 * <p> 首页。
	 */
	@RequestMapping(method={RequestMethod.GET},value="/")
	public String index() {
		log.info("SysNewsController index.........");
		request.setAttribute("navKey","index");
		return index;
	}
	/**
	 * <p> 栏目。
	 */
	@RequestMapping(method={RequestMethod.GET},value=acPrefix+"channel")
	public String channel() {
		log.info("SysNewsController channel.........");
		request.setAttribute("navKey","news");
		return channel;
	}
	/**
	 * <p> 资讯。
	 */
	@RequestMapping(method={RequestMethod.GET},value=acPrefix+"news")
	public String news() {
		log.info("SysNewsController news.........");
		request.setAttribute("navKey","news");
		return news;
	}
	/**
	 * <p> 全景图。
	 */
	@RequestMapping(method={RequestMethod.GET},value=acPrefix+"tour")
	public String tour() {
		log.info("SysNewsController tour.........");
		request.setAttribute("navKey","tour");
		return tour;
	}
	/**
	 * <p> 全景视频。
	 */
	@RequestMapping(method={RequestMethod.GET},value=acPrefix+"video")
	public String video() {
		log.info("SysNewsController video.........");
		request.setAttribute("navKey","video");
		return video;
	}
}