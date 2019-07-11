package com.jll.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 * Created by Daffy on 2019/7/10.
 */
@Controller
public class HtmlController {
    @RequestMapping(value="/" ,method=RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("userId", 2);
        return "index";
    }
}
