/**
 * 
 */
package com.facegarden.modules.wechat.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.facegarden.common.utils.SpringContextHolder;
import com.facegarden.modules.diancan.entity.MealList;
import com.facegarden.modules.diancan.entity.MealUser;
import com.facegarden.modules.diancan.service.MealListService;
import com.facegarden.modules.diancan.service.MealUserService;

import io.github.elkan1788.mpsdk4j.core.WechatHandler;
import io.github.elkan1788.mpsdk4j.vo.event.BasicEvent;
import io.github.elkan1788.mpsdk4j.vo.event.LocationEvent;
import io.github.elkan1788.mpsdk4j.vo.event.MenuEvent;
import io.github.elkan1788.mpsdk4j.vo.event.ScanCodeEvent;
import io.github.elkan1788.mpsdk4j.vo.event.ScanEvent;
import io.github.elkan1788.mpsdk4j.vo.event.SendLocationInfoEvent;
import io.github.elkan1788.mpsdk4j.vo.event.SendPhotosEvent;
import io.github.elkan1788.mpsdk4j.vo.message.BasicMsg;
import io.github.elkan1788.mpsdk4j.vo.message.ImageMsg;
import io.github.elkan1788.mpsdk4j.vo.message.LinkMsg;
import io.github.elkan1788.mpsdk4j.vo.message.LocationMsg;
import io.github.elkan1788.mpsdk4j.vo.message.TextMsg;
import io.github.elkan1788.mpsdk4j.vo.message.VideoMsg;
import io.github.elkan1788.mpsdk4j.vo.message.VoiceMsg;
import io.github.elkan1788.mpsdk4j.vo.push.SentAllJobEvent;
import io.github.elkan1788.mpsdk4j.vo.push.SentTmlJobEvent;

/**
 * @author shiwei
 *
 */
public class MyWechatHandler implements WechatHandler {

	@Resource
	private MealListService mealListService;

	@Resource
	private MealUserService mealUserService;

	@Override
	public BasicMsg defMsg(BasicMsg bm) {
		TextMsg tm = new TextMsg(bm);
		tm.setContent("欢迎...");
		return tm;
	}

	@Override
	public BasicMsg defEvent(BasicEvent be) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BasicMsg text(TextMsg tm) {

		if (null == mealListService)
			mealListService = SpringContextHolder.getBean("mealListService");
		if (null == mealUserService)
			mealUserService = SpringContextHolder.getBean("mealUserService");

		String account = tm.getFromUserName(); // 获取公众号用户账号
		String context = tm.getContent(); // 获取公众号信息

		MealUser mealUser = new MealUser();
		mealUser.setAccount(account);
		mealUser = mealUserService.findAccount(mealUser);

		// 判断账号是否存在,绑定新增订餐用户
		if (!context.startsWith("#") && mealUser == null) {
			tm.setContent("账号不存在，回复”#“号键将绑定你的账号，就能享受为你提供优质服务");
		}
		if (context.startsWith("#") && mealUser == null) {
			MealUser mealUser1 = new MealUser();
			mealUser1.setAccount(account);
			mealUser1.setType("0");
			mealUserService.save(mealUser1);
			tm.setContent("绑定成功，享受优质服务，请回复”点餐“");
		}

		// 账户
		if (mealUser != null) {
			if (mealUser.getType().equals("1") || context != null) {
				String str = mealListService.split(context);
				tm.setContent(str);
			}
			if (mealUser.getType().equals("0") || context.equals("点餐")) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String str = sdf.format(new Date());
				MealList mealList = new MealList();
				mealList.setShowDate(str);
				List<MealList> lists = mealListService.findList(mealList);
				StringBuffer sb = new StringBuffer();
				int size = lists.size();
				if (lists.isEmpty()) {
					tm.setContent("今天菜单没更新，请稍后再来点餐吧");

				} else {
					for (int i = 0; i < size; i++) {
						sb.append(lists.get(i).getMealNo() + " " + lists.get(i).getListName() + "<br />");
					}
					tm.setContent(sb.toString());

				}
			}
		}
		return tm;
	}

	@Override
	public BasicMsg image(ImageMsg im) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BasicMsg voice(VoiceMsg vom) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BasicMsg video(VideoMsg vim) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BasicMsg shortVideo(VideoMsg vim) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BasicMsg location(LocationMsg lm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BasicMsg link(LinkMsg lm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BasicMsg eClick(MenuEvent me) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eView(MenuEvent me) {
		// TODO Auto-generated method stub

	}

	@Override
	public BasicMsg eSub(BasicEvent be) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eUnSub(BasicEvent be) {
		// TODO Auto-generated method stub

	}

	@Override
	public BasicMsg eScan(ScanEvent se) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eLocation(LocationEvent le) {
		// TODO Auto-generated method stub

	}

	@Override
	public BasicMsg eScanCodePush(ScanCodeEvent sce) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BasicMsg eScanCodeWait(ScanCodeEvent sce) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BasicMsg ePicSysPhoto(SendPhotosEvent spe) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BasicMsg ePicPhotoOrAlbum(SendPhotosEvent spe) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BasicMsg ePicWeixin(SendPhotosEvent spe) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BasicMsg eLocationSelect(SendLocationInfoEvent slie) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eSentTmplJobFinish(SentTmlJobEvent stje) {
		// TODO Auto-generated method stub

	}

	@Override
	public void eSentAllJobFinish(SentAllJobEvent saje) {
		// TODO Auto-generated method stub

	}

}
