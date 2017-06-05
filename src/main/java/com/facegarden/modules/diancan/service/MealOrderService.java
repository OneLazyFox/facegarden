package com.facegarden.modules.diancan.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.facegarden.common.service.CrudService;
import com.facegarden.modules.diancan.dao.MealOrderDao;
import com.facegarden.modules.diancan.entity.MealOrder;

@Service
@Transactional(readOnly = true)
public class MealOrderService extends CrudService<MealOrderDao, MealOrder> {
	
}