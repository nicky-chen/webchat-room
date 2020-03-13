package cn.nicky.webchat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author nicky_chin
 * @description:
 * @date: 2020/3/12 上午9:58
 * @since JDK 1.8
 */
@Controller
@RequestMapping(value = "")
public class RouteController {

    @RequestMapping(value = "")
    public String index() {
        return "redirect:/user/login";
    }

    @RequestMapping(value = "/about")
    public String about() {
        return "about";
    }

    @RequestMapping(value = "/help")
    public String help() {
        return "help";
    }

    @RequestMapping(value = "/system")
    public String system() {
        return "system-setting";
    }

}
