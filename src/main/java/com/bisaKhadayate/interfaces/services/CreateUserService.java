package com.bisaKhadayate.interfaces.services;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.bisaKhadayate.bean.User;

public interface CreateUserService {

	Integer checkDuplicateEmailId(String emailId);

	Integer checkDuplicateUsername(String username);

	void saveUser(User user);

	void updateUser(User user, HttpSession session);

	void uploadFile(User user, MultipartFile file);
	
	List<String> getImagesFile(User user);
	
	void generatePdf(User user);
	
	String generateTemplate(User user);
	
	String sendmail() ;
	
	List<Map<String,Object>> searchFilterResult(Character gender,boolean isActive , String firstName , String lastName ,String caste , String gotra,int age,int index );
	
	void deleteFile(String filePath);
	
	Integer searchFilterResultCount(Character gender,boolean isActive , String firstName , String lastName ,String caste , String gotra ,int age);

}
