package com.facegarden.modules.diancan.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.facegarden.common.service.CrudService;
import com.facegarden.modules.diancan.dao.MealUserDao;
import com.facegarden.modules.diancan.entity.MealUser;

@Service
@Transactional(readOnly = true)
public class MealUserService extends CrudService<MealUserDao,MealUser> {
	public MealUser findAccount(MealUser mealUser){
		return dao.findAccount(mealUser);
	}
	
}
