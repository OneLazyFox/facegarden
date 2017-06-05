package com.facegarden.modules.diancan.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.facegarden.common.service.CrudService;
import com.facegarden.common.utils.CacheUtils;
import com.facegarden.modules.diancan.dao.MealListDao;
import com.facegarden.modules.diancan.entity.MealList;
import com.facegarden.modules.sys.utils.DictUtils;

@Service
@Transactional(readOnly = false)
public class MealListService extends CrudService<MealListDao,MealList> {
	
	@Transactional(readOnly = false)
	public void delete(MealList mealList) {
		super.delete(mealList);
		CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
	}
	
	public String split(String context) {
		StringBuffer sb = new StringBuffer();
		int index = 0;
		int number=0;
		int len = context.length();
		if (context.indexOf(':') > 0) {
			index = context.indexOf(':');

		} else if (context.indexOf('：') > 0) {
			index = context.indexOf('：');

		}
		if (context.indexOf('。') > 0) {
			len = len - 1;
		}
		context = context.substring(index + 1, len);
		String[] strs = context.split("、");
		String str="";
		for (int i = 0; i < strs.length - 1; i++) {
			for (int j = i + 1; j < strs.length - 1; j++) {
				number++;
				sb.append(strs[i] + "、");
				sb.append(strs[j] + "、" + strs[strs.length - 1]);
				str += number+" "+sb.toString()+"<br />";    
				MealList mealList=new MealList();
				mealList.setMealNo(number);
				mealList.setListName(sb.toString());
				this.save(mealList);
				sb.delete(0, sb.length());
				}
			}
		return  str.toString();
	}
	
}