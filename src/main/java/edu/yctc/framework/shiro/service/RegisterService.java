package edu.yctc.framework.shiro.service;

import edu.yctc.framework.manager.AsyncManager;
import edu.yctc.framework.manager.factory.AsyncFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import edu.yctc.common.constant.Constants;
import edu.yctc.common.constant.ShiroConstants;
import edu.yctc.common.constant.UserConstants;
import edu.yctc.common.utils.MessageUtils;
import edu.yctc.common.utils.ServletUtils;
import edu.yctc.project.system.user.domain.User;
import edu.yctc.project.system.user.service.IUserService;

/**
 * 注册校验方法
 * 
 * @author luna
 */
@Component
public class RegisterService
{
    @Autowired
    private IUserService userService;

    /**
     * 注册
     */
    public String register(User user)
    {
        String msg = "", username = user.getLoginName(), password = user.getPassword();

        if (!StringUtils.isEmpty(ServletUtils.getRequest().getAttribute(ShiroConstants.CURRENT_CAPTCHA)))
        {
            msg = "验证码错误";
        }
        else if (StringUtils.isEmpty(username))
        {
            msg = "用户名不能为空";
        }
        else if (StringUtils.isEmpty(password))
        {
            msg = "用户密码不能为空";
        }
        else if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH)
        {
            msg = "密码长度必须在5到20个字符之间";
        }
        else if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                || username.length() > UserConstants.USERNAME_MAX_LENGTH)
        {
            msg = "账户长度必须在2到20个字符之间";
        }
        else if (UserConstants.USER_NAME_NOT_UNIQUE.equals(userService.checkLoginNameUnique(username)))
        {
            msg = "保存用户'" + username + "'失败，注册账号已存在";
        }
        else
        {
            boolean regFlag = userService.registerUser(user);
            if (!regFlag)
            {
                msg = "注册失败,请联系系统管理人员";
            }
            else
            {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.REGISTER, MessageUtils.message("user.register.success")));
            }
        }
        return msg;
    }
}
