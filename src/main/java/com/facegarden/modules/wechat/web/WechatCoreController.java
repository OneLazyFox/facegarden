/**
 * 
 */
package com.facegarden.modules.wechat.web;

import io.github.elkan1788.mpsdk4j.mvc.WechatWebSupport;
import io.github.elkan1788.mpsdk4j.vo.MPAccount;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.facegarden.modules.wechat.service.MyWechatHandler;

/**
 * SpringMVC环境接入
 * 
 * @since 2.0
 */
@Controller
public class WechatCoreController extends WechatWebSupport {

    private static final Logger log = LoggerFactory.getLogger(WechatCoreController.class);

    @Value("${token}")
	private String token;
    
    @Value("${appSecret}")
	private String appSecret;
    
    @Value("${appId}")
	private String appId;
    
    @Value("${mpId}")
   	private String mpId;
    
    @Override
    public void init() {
        log.info("====== SpringMVC环境 =======");
        MPAccount mpact = new MPAccount();
        // 修改为实际的公众号信息,可以在开发者栏目中查看
        mpact.setAppId(appId);
        mpact.setAppSecret(appSecret);
        mpact.setToken(token);
        mpact.setMpId(mpId);
        mpact.setMpType("D");
        mpact.setNickName("点餐管家");
        _wk.setMpAct(mpact);
        _wk.setWechatHandler(new MyWechatHandler());
    }

    @RequestMapping("/wechat")
    public void wechatCore(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        this.interact(req, resp);
    }

}
