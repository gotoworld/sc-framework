/*
 * 行政区划信息管理 service 接口实现类
 *
 * VERSION  DATE        BY              REASON
 * -------- ----------- --------------- ------------------------------------------
 * 1.00     20154.01.13  wuxiaogang     程序・发布
 * -------- ----------- --------------- ------------------------------------------
 * Copyright 2015 baseos System. - All Rights Reserved.
 *
 */
package com.wu1g.adi.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.baseos.dao.daointer.adi.IAreaDao;
import cn.com.baseos.service.BaseService;
import cn.com.baseos.service.adi.IAreaService;
/**
 * <p> 行政区划管理   service 接口类。</p>
 */
@Service
public class AreaService extends BaseService implements IAreaService {

}