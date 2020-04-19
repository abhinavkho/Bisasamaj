package com.bisaKhadayate.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bisaKhadayate.bean.User;
import com.bisaKhadayate.constant.Constant;
import com.bisaKhadayate.interfaces.services.UserService;

@Controller
public class LoginController implements Constant {
	
	@Autowired
	UserService userService;
	
	@RequestMapping("/")
	public String loginPage()
	{
		return LOGIN;
	}
	
	@RequestMapping(value="userlogin" , method=RequestMethod.POST)
	public ModelAndView userLogin(@RequestParam("username") String userName , @RequestParam("password") String password, HttpServletRequest request)
	{
		Map<String,String> userdetails = new HashMap<String,String>();
		ModelAndView mv=new ModelAndView();
		User user=userService.userLogin(userName, password);
		if(user==null)
		{
			mv.setViewName("login");
			mv.addObject("errorMsg", "Username and password not match");
		}else
		{
			userdetails.put("name", user.getFirstName()+" "+user.getLastName());
			userdetails.put("username", user.getUserId());
			userdetails.put("password", user.getPassword());
			request.getSession().setAttribute("userDetails", user);
			mv.setViewName(HOME);
			mv.addObject("user", user );
		}
		
		return mv;
	}
	
	@RequestMapping("logout")
	public String logOut(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		session.invalidate();
		
		return LOGIN;
	}
	
	@GetMapping(value="home")
	public ModelAndView homePage(HttpServletRequest request)
	{
		User userdetails = (User) request.getSession().getAttribute("userDetails");
		ModelAndView mv=new ModelAndView();
		mv.setViewName(userdetails==null?LOGIN:HOME);
		mv.addObject("user", userdetails );
		return mv;
	}
	
}
