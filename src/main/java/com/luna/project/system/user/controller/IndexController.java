package com.luna.project.system.user.controller;

import java.util.List;

import com.luna.common.exception.LoginException;
import com.luna.common.utils.StringUtils;
import com.luna.common.utils.security.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import com.luna.framework.config.LunaConfig;
import com.luna.framework.web.controller.BaseController;
import com.luna.project.system.config.service.IConfigService;
import com.luna.project.system.menu.domain.Menu;
import com.luna.project.system.menu.service.IMenuService;
import com.luna.project.system.user.domain.User;

/**
 * 首页 业务处理
 * 
 * @author luna
 */
@Controller
public class IndexController extends BaseController
{
    @Autowired
    private IMenuService menuService;

    @Autowired
    private IConfigService configService;

    @Autowired
    private LunaConfig lunaConfig;

	/**
	 * 系统首页
	 * @param mmap
	 * @return
	 */
	@GetMapping("/index")
    public String index(ModelMap mmap)
    {
        // 取身份信息
        User user = getSysUser();
	    if (StringUtils.isNull(user))
	    {
		    throw new LoginException("用户未登录，无法访问请求。");
	    }
	    mmap.put("user", user);

        // 根据用户id取出菜单
        List<Menu> menus = menuService.selectMenusByUser(user);
        mmap.put("menus", menus);
        mmap.put("user", user);
        mmap.put("sideTheme", configService.selectConfigByKey("sys.index.sideTheme"));
        mmap.put("skinName", configService.selectConfigByKey("sys.index.skinName"));
        mmap.put("copyrightYear", lunaConfig.getCopyrightYear());
        mmap.put("demoEnabled", lunaConfig.isDemoEnabled());
        return "index";
    }

    // 切换主题
    @GetMapping("/system/switchSkin")
    public String switchSkin(ModelMap mmap)
    {
        return "skin";
    }

    // 系统介绍
    @GetMapping("/system/main")
    public String main(ModelMap mmap)
    {
        mmap.put("version", lunaConfig.getVersion());
        return "main";
    }
}
