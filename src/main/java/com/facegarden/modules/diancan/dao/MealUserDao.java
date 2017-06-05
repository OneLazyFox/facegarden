package com.facegarden.modules.diancan.dao;

import com.facegarden.common.persistence.CrudDao;
import com.facegarden.common.persistence.annotation.MyBatisDao;
import com.facegarden.modules.diancan.entity.MealUser;

/**
 * 定义Dao查询类型
 */
@MyBatisDao
public interface MealUserDao extends CrudDao<MealUser>{
	public MealUser findAccount(MealUser mealUser);

}
