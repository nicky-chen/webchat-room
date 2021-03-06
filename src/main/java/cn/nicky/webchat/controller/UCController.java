package cn.nicky.webchat.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.nicky.webchat.config.ApolloConfig;
import cn.nicky.webchat.pojo.User;
import cn.nicky.webchat.service.ILogService;
import cn.nicky.webchat.service.IUserService;
import cn.nicky.webchat.utils.CommonDate;
import cn.nicky.webchat.utils.ErrorMessageConstant;
import cn.nicky.webchat.utils.LogUtil;
import cn.nicky.webchat.utils.NetUtil;

import static cn.nicky.webchat.utils.ErrorMessageConstant.LOGIN_PASSWORD_ERROR;
import static cn.nicky.webchat.utils.ErrorMessageConstant.LOGIN_SUCCESS;
import static cn.nicky.webchat.utils.ErrorMessageConstant.LOGIN_USERID_DISABLED;
import static cn.nicky.webchat.utils.ErrorMessageConstant.LOGOUT_SUCCESS;
import static cn.nicky.webchat.utils.ErrorMessageConstant.LOG_DETAIL_USER_LOGIN;
import static cn.nicky.webchat.utils.ErrorMessageConstant.LOG_TYPE_LOGIN;

/**
 * @author nicky_chin
 * @description:
 * @date: 2020/3/12 上午9:58
 * @since JDK 1.8
 */
@Controller
@RequestMapping(value = "/user")
public class UCController {

    @Resource
    private IUserService userService;

    @Resource
    private ILogService logService;

    @Autowired
    private ApolloConfig apolloConfig;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String userId, String password, HttpSession session, RedirectAttributes attributes,
        CommonDate date, LogUtil logUtil, HttpServletRequest request) {
        User user = userService.selectUserByUserid(userId);
        if (user == null) {
            user = new User();
            user.setPassword(password);
            user.setUserid(userId);
            user.setSex(0);
            user.setAge(18);
            user.setProfilehead("");
            user.setProfile("初来驾到");
            user.setFirsttime(date.getTime24());
            user.setLasttime(user.getFirsttime());
            user.setNickname("user_" + String.valueOf(System.currentTimeMillis()).substring(5));
            user.setStatus(1);
            userService.insert(user);
            loginSuccess(userId, session, attributes, date, logUtil, request, user);
            return "redirect:/chat";
        }

        if (!user.getPassword().equals(password)) {
            attributes.addFlashAttribute("error", LOGIN_PASSWORD_ERROR);
            return "redirect:/user/login";
        }
        if (user.getStatus() != 1) {
            attributes.addFlashAttribute("error", LOGIN_USERID_DISABLED);
            return "redirect:/user/login";
        }
        loginSuccess(userId, session, attributes, date, logUtil, request, user);
        return "redirect:/chat";

    }

    @RequestMapping(value = "/logout")
    public String logout(HttpSession session, RedirectAttributes attributes, ErrorMessageConstant defined) {
        session.removeAttribute("userid");
        session.removeAttribute("login_status");
        attributes.addFlashAttribute("message", LOGOUT_SUCCESS);
        return "redirect:/user/login";
    }

    /**
     * 登录成功
     * @param userId
     * @param session
     * @param attributes
     * @param date
     * @param logUtil
     * @param request
     * @param user
     */
    private void loginSuccess(String userId, HttpSession session, RedirectAttributes attributes, CommonDate date,
        LogUtil logUtil, HttpServletRequest request, User user) {
        logService.insert(logUtil
            .setLog(userId, date.getTime24(), LOG_TYPE_LOGIN, LOG_DETAIL_USER_LOGIN, NetUtil.getIpAddress(request)));
        session.setAttribute("userid", userId);
        session.setAttribute("login_status", true);
        user.setLasttime(date.getTime24());
        userService.update(user);
        attributes.addFlashAttribute("message", LOGIN_SUCCESS);
    }
}
