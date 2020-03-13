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
import cn.nicky.webchat.utils.LogUtil;
import cn.nicky.webchat.utils.NetUtil;
import cn.nicky.webchat.utils.WordDefined;

/**
 * @author nicky_chin
 * @description:
 * @date: 2020/3/12 上午9:58
 * @since JDK 1.8
 */
@Controller
@RequestMapping(value = "/user")
public class LoginController {

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
    public String login(String userid, String password, HttpSession session, RedirectAttributes attributes,
        WordDefined defined, CommonDate date, LogUtil logUtil, NetUtil netUtil, HttpServletRequest request) {
        User user = userService.selectUserByUserid(userid);
        if (user == null) {
            attributes.addFlashAttribute("error", defined.LOGIN_USERID_ERROR);
            return "redirect:/user/login";
        } else {
            if (!user.getPassword().equals(password)) {
                attributes.addFlashAttribute("error", defined.LOGIN_PASSWORD_ERROR);
                return "redirect:/user/login";
            } else {
                if (user.getStatus() != 1) {
                    attributes.addFlashAttribute("error", defined.LOGIN_USERID_DISABLED);
                    return "redirect:/user/login";
                } else {
                    logService.insert(logUtil
                        .setLog(userid, date.getTime24(), defined.LOG_TYPE_LOGIN, defined.LOG_DETAIL_USER_LOGIN,
                            netUtil.getIpAddress(request)));
                    session.setAttribute("userid", userid);
                    session.setAttribute("login_status", true);
                    user.setLasttime(date.getTime24());
                    userService.update(user);
                    attributes.addFlashAttribute("message", defined.LOGIN_SUCCESS);
                    return "redirect:/chat";
                }
            }
        }
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpSession session, RedirectAttributes attributes, WordDefined defined) {
        session.removeAttribute("userid");
        session.removeAttribute("login_status");
        attributes.addFlashAttribute("message", defined.LOGOUT_SUCCESS);
        return "redirect:/user/login";
    }
}
