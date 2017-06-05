package com.facegarden.modules.diancan.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.facegarden.common.service.CrudService;
import com.facegarden.modules.diancan.dao.MealInfoDao;
import com.facegarden.modules.diancan.entity.MealInfo;

@Service
@Transactional(readOnly = true)
public class MealInfService extends CrudService<MealInfoDao,MealInfo> {
	
}
