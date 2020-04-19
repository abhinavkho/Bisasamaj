package com.bisaKhadayate.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.bisaKhadayate.bean.User;
import com.bisaKhadayate.constant.Constant;
import com.bisaKhadayate.interfaces.services.CreateUserService;
import com.bisaKhadayate.interfaces.services.UserService;

@Controller
public class UserController implements Constant {
	
	@Autowired
	UserService userService;
	
	@Autowired
	CreateUserService createUserService;
	
	
	@RequestMapping("createuser")
	public ModelAndView createUser(HttpServletRequest request)
	{
		User user =(User)request.getSession().getAttribute("userDetails");
		ModelAndView mv=new ModelAndView();
		mv.addObject("filesName",createUserService.getImagesFile(user));
		mv.addObject(user);
		mv.setViewName(CREATE_USER);
		return mv;
	}
	
	@RequestMapping(value="checkDuplicateEmailId/{emailId}",method = RequestMethod.POST)
	@ResponseBody
	public boolean checkDuplicateEmailId(@PathVariable String emailId)
	{
		return createUserService.checkDuplicateEmailId(emailId)==1?true:false;
	}
	
	@RequestMapping(value="checkDuplicateUsername/{username}",method = RequestMethod.POST)
	@ResponseBody
	public boolean checkDuplicateUsername(@PathVariable String username)
	{
		return createUserService.checkDuplicateUsername(username)==1?true:false;
	}
	
	@RequestMapping(value="submituser" , method= RequestMethod.POST)
	@ResponseBody
	public String submitUser(@RequestBody User userDetails,HttpServletRequest request)
	{
		createUserService.saveUser(userDetails);
		return "User created successfully";
	}
	
	@RequestMapping(value="editviewUser")
	public ModelAndView viewEditUserDetail(HttpServletRequest request)
	{
		User user = (User) request.getSession().getAttribute("userDetails");
		ModelAndView mv=new ModelAndView();
		List<String> imagePathList=createUserService.getImagesFile(user);
		mv.addObject("filesName",imagePathList);
		Map<String, String> imagePathListMap=new HashMap<String, String>();
		for (String imagePath : imagePathList) {
			String image[] = imagePath.split("/");
			imagePathListMap.put(image[image.length-1],imagePath);
		}
		mv.addObject("imagePathListMap", imagePathListMap );
		mv.setViewName(EDIT_UESR_DETAILS);
		mv.addObject("user", user );
		mv.addObject("gender",Character.toString(user.getGender()));
		return mv;
	}
	
	@RequestMapping(value="updateuser" , method= RequestMethod.POST)
	@ResponseBody
	public String updateUser(@RequestBody User userDetails,HttpServletRequest request)
	{
		createUserService.updateUser(userDetails,request.getSession());
		User userdetails = (User) request.getSession().getAttribute("userDetails");
		userdetails =userService.userLogin(userDetails.getUserId(), userDetails.getPassword());
		request.getSession().setAttribute("userDetails", userdetails);
		return "User updated successfully";
	}
	
	
	  @RequestMapping(value="uploadfile" , method=RequestMethod.POST)
	  public ModelAndView  fileUpload(@RequestParam("fileupload") MultipartFile file,HttpSession session)
	{
		User user = (User) session.getAttribute("userDetails");
		ModelAndView mv = new ModelAndView();
		createUserService.uploadFile(user, file);
		List<String> imagePathList = createUserService.getImagesFile(user);
		mv.addObject("filesName", imagePathList);
		Map<String, String> imagePathListMap = new HashMap<String, String>();
		for (String imagePath : imagePathList) {
			String image[] = imagePath.split("/");
			imagePathListMap.put(image[image.length - 1], imagePath);
		}
		mv.addObject("imagePathListMap", imagePathListMap);
		mv.addObject("user", user);
		mv.addObject("gender", Character.toString(user.getGender()));
		mv.setViewName(EDIT_UESR_DETAILS);
		return mv;
	  }
	  
	  
	  @PostMapping(value="searchpage")
		public ModelAndView searchResult(@RequestParam Character gender,HttpServletRequest request)
		{
			User userdetails = (User) request.getSession().getAttribute("userDetails");
			ModelAndView mv=new ModelAndView();
			List<Map<String, Object>> searchResult=userService.searchResult(gender,0);
			mv.addObject("userDetail", searchResult);
			mv.addObject("user", userdetails );
			mv.addObject("gender", gender );
			mv.addObject("type", "NF");
			mv.addObject("totalResult", userService.searchResultCount(gender, true));
			mv.setViewName(SEARCH_PAGE);
			mv.addObject("searchfilter", new HashMap<String, String>());
			return mv;
		}
	  
	  
	  @PostMapping(value="searchFilterResult")
			public ModelAndView searchFilterResult(@RequestBody MultiValueMap<String, String> formData,HttpServletRequest request)
			{
				User userdetails = (User) request.getSession().getAttribute("userDetails");
				ModelAndView mv=new ModelAndView();
				List<Map<String, Object>> searchResult=createUserService.searchFilterResult(formData.get("gender").get(0).charAt(0), true, formData.get("firstName").get(0),formData.get("lastName").get(0),formData.get("caste").get(0),formData.get("gotra").get(0),Integer.parseInt(formData.get("age").get(0)),0);
				mv.addObject("userDetail", searchResult);
				mv.addObject("user", userdetails );
				mv.addObject("type", "F");
				mv.addObject("totalResult", createUserService.searchFilterResultCount(formData.get("gender").get(0).charAt(0), true, formData.get("firstName").get(0),formData.get("lastName").get(0),formData.get("caste").get(0),formData.get("gotra").get(0),Integer.parseInt(formData.get("age").get(0))));
				mv.addObject("gender", formData.get("gender").get(0).charAt(0) );
				mv.setViewName(SEARCH_PAGE);
				mv.addObject("searchfilter", formData);
				return mv;
			}
	  
	  @PostMapping(value="loadfullprofileofuser/{id}")
	  @ResponseBody
		public User loadFullProfile(@PathVariable Integer id)
		{
			Optional<User> userOptional=userService.getUser(id);
			User user=userOptional.get();
			user.setFileNameList(createUserService.getImagesFile(user));
			return user;
		}
	  
	  
	  @PostMapping(value="email/{id}")
	  @ResponseBody
		public String email(@PathVariable Integer id)
		{
		  return createUserService.sendmail();
		}
	 
	  @GetMapping(value="download")
	  @ResponseBody
		public void download(@RequestParam Integer id,HttpServletRequest request, HttpServletResponse response) throws IOException
		{
			Optional<User> userOptional=userService.getUser(id);
			User user=userOptional.get();
			createUserService.generatePdf(user);
			response.setContentType("application/pdf");  
			PrintWriter out = response.getWriter();  
			String filename = user.getFirstName()+user.getLastName()+".pdf";   
			response.setContentType("APPLICATION/OCTET-STREAM");   
			response.setHeader("Content-Disposition","attachment; filename=\"" + filename + "\""); 
			File tmpDir = new ClassPathResource("/static").getFile();
			String pdfPath =tmpDir.getAbsolutePath()+"\\pdf";
			FileInputStream fileInputStream = new FileInputStream(pdfPath+"\\" + filename);  
			int i;   
			while ((i=fileInputStream.read()) != -1) {  
			out.write(i);   
			}   
			fileInputStream.close();   
			out.close();
		}
	 
	  
	
	  
	  
	  @RequestMapping(value="deletephoto" , method=RequestMethod.POST)
	  public ModelAndView  deletePhoto(@RequestParam("picname") String filePath,HttpSession session)
	{
		User user = (User) session.getAttribute("userDetails");
		ModelAndView mv = new ModelAndView();
		createUserService.deleteFile(filePath);
		List<String> imagePathList = createUserService.getImagesFile(user);
		mv.addObject("filesName", imagePathList);
		Map<String, String> imagePathListMap = new HashMap<String, String>();
		for (String imagePath : imagePathList) {
			String image[] = imagePath.split("/");
			imagePathListMap.put(image[image.length - 1], imagePath);
		}
		mv.addObject("imagePathListMap", imagePathListMap);
		mv.addObject("user", user);
		mv.addObject("gender", Character.toString(user.getGender()));
		mv.setViewName(EDIT_UESR_DETAILS);
		return mv;
	}
	  
	  
	  @GetMapping(value="contactus")
		public ModelAndView contactus(HttpServletRequest request)
		{
			User userdetails = (User) request.getSession().getAttribute("userDetails");
			ModelAndView mv=new ModelAndView();
			mv.setViewName(CONTACT_US);
			mv.addObject("user", userdetails );
			return mv;
		}
	  
	  @GetMapping(value="createadvertise")
			public ModelAndView createAdvertise(HttpServletRequest request)
			{
				User userdetails = (User) request.getSession().getAttribute("userDetails");
				ModelAndView mv=new ModelAndView();
				mv.setViewName(ADVERTISE);
				mv.addObject("user", userdetails );
				return mv;
			}
}
