package com.tydic.spc.web.controller;

import com.tydic.spc.domain.User;
import com.tydic.spc.mapper.UserMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * 首页
 * User: chengjie
 * Date: 13-3-25
 * Time: 下午4:13
 */
@Controller
@RequestMapping(value = "home")
public class HomeController {
    @Resource
    private UserMapper userMapper;
    @RequestMapping(value = "/home.json", method = RequestMethod.GET)
    public User home(Model model) {
        return userMapper.findUser("251");
    }
}
