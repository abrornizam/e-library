package com.abror.demo.controller;

/**
 * @author ANIZAM
 *
 */

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.abror.demo.model.User;
import com.abror.demo.repository.BukuRepository;
import com.abror.demo.repository.PengarangRepository;
//import com.abror.demo.model.User;
import com.abror.demo.service.BukuService;
import com.abror.demo.service.PeminjamanService;
//import com.abror.demo.service.SecurityService;
//import com.abror.demo.service.UserService;
import com.abror.demo.service.SecurityService;
import com.abror.demo.service.UserService;

@Controller
public class ApplicationController {
	
    @Autowired
    UserService userService;

    @Autowired
    SecurityService securityService;

//    @Autowired
//    UserValidator userValidator;
    
    @Autowired
    BukuRepository bukuRepository;
    
    @Autowired
    BukuService bukuService;
    
    @Autowired
    PeminjamanService peminjamanService;
    
    @Autowired
    PengarangRepository pengarangRepository;
        
    @RequestMapping(value = {"/","/index"}, method = RequestMethod.GET)
    public String index() {
    	return "/home";
    }
    
    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String about() {
    	return "/about";
    }
    
    @RequestMapping(value = "/admin/", method = RequestMethod.GET)
    public String admin() {
        return "/admin";
    }
    
    @RequestMapping(value = "/admin/user", method = RequestMethod.GET)
    public String detailAdmin() {
        return "/user";
    }
    
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(ModelMap model) {
    	User user = new User();
        model.addAttribute("user", user);
        return "/template/registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String saveAccount(@Valid User user, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
        	System.out.println("GAGAL SAVE BUNG !");
            return "/template/registration";
        }
        
        userService.save(user);
        securityService.autologin(user.getUsername(), user.getPasswordConfirm());
        return "redirect:/template/admin";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        return "/template/login";
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginSuccess(@Valid User user, ModelMap model) {
    	securityService.autologin(user.getUsername(), user.getPasswordConfirm());
    	return "/template/admin";
    }
    
    @GetMapping("/logout")
    public String logout() {
    	return "/template/home";
    }   
    
}
