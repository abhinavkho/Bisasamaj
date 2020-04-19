package com.bisaKhadayate.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bisaKhadayate.constant.Constant;
import com.bisaKhadayate.interfaces.services.CreateUserService;
import com.bisaKhadayate.interfaces.services.UserService;

@Controller
public class SearchPageController implements Constant{
	
	@Autowired
	UserService userService;
	
	@Autowired
	CreateUserService createUserService;
	
	
	@PostMapping(value = "getpendingdata")
	@ResponseBody
	public List<Map<String, Object>> getPendingData(@RequestBody Map<String, String> filterCriteria) {
		
		List<Map<String, Object>> searchResult=null;

		if(filterCriteria.get("searchType").equalsIgnoreCase("F"))
		{
			searchResult=createUserService.searchFilterResult(filterCriteria.get("gender").charAt(0), true, filterCriteria.get("firstName"), filterCriteria.get("lastName"), filterCriteria.get("caste"), filterCriteria.get("gotra"),Integer.parseInt(filterCriteria.get("age")), Integer.parseInt(filterCriteria.get("startIndex")));
		}else
		{
			searchResult=userService.searchResult(filterCriteria.get("gender").charAt(0), Integer.parseInt(filterCriteria.get("startIndex")));
		}
		return searchResult;
	}
	
	
	

	
}
