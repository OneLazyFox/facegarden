package com.facegarden.modules.diancan.dao;

import com.facegarden.common.persistence.CrudDao;
import com.facegarden.common.persistence.annotation.MyBatisDao;
import com.facegarden.modules.diancan.entity.MealOrder;

@MyBatisDao
public interface MealOrderDao extends  CrudDao<MealOrder> {
	
}
