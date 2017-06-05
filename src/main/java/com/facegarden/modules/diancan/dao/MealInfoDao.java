package com.facegarden.modules.diancan.dao;

import com.facegarden.common.persistence.CrudDao;
import com.facegarden.common.persistence.annotation.MyBatisDao;
import com.facegarden.modules.diancan.entity.MealInfo;

/**
 * 定义Dao查询类型
 */
@MyBatisDao
public interface MealInfoDao extends CrudDao<MealInfo> {
	
}
