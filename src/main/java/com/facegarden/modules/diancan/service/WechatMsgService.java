package com.facegarden.modules.diancan.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.facegarden.common.service.CrudService;
import com.facegarden.modules.diancan.dao.WechatMsgDao;
import com.facegarden.modules.diancan.entity.WechatMsg;

@Service
@Transactional(readOnly = true)
public class WechatMsgService extends CrudService<WechatMsgDao, WechatMsg> {
	
}