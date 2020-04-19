package com.bisaKhadayate.interfaces.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bisaKhadayate.bean.User;

@Repository
public interface UserDao extends CrudRepository<User,Integer>  {
	
	@Query("SELECT u from User u where u.userId = ?1 and u.password = ?2 ")
	User userLogin(String userName, String password);
	
	@Query("SELECT count(*) from User u where u.emailId = ?1 ")
	Integer checkDuplicateEmailId(String emailId);
	
	@Query("SELECT count(*) from User u where u.userId = ?1 ")
	 Integer checkDuplicateUsername(String username);
	
	@Query(value="Select u.id, u.firstName , u.lastName , u.dob , u.gotra , u.contactNumber ,u.gender , u.displayPic from User u where u.gender = ?1 and u.isactives = ?2 limit ?3,10", nativeQuery = true)
	List<Map<String,Object>> searchResult(Character gender,boolean isActive,int index );
	
	@Query(value="Select u.id, u.firstName , u.lastName , u.dob , u.gotra , u.contactNumber , u.gender , u.displayPic from User u where u.gender = ?1 and u.isactives = ?2 and"
			+ " (1=CASE  WHEN ?3='' THEN 1 ELSE u.firstName=?3  END )"
			+ " and (1=CASE  WHEN ?4='' THEN 1 ELSE u.lastName=?4  END )"
			+ " and (1=CASE  WHEN ?5='' THEN 1 ELSE u.caste=?5  END )"
			+ " and (1=CASE  WHEN ?6='' THEN 1 ELSE u.gotra=?6  END ) and (1=CASE WHEN ?7=0 THEN 1 ELSE floor(datediff(CURDATE(),CONVERT(u.dob, DATE))/365)>=?7 END )limit ?8,10", nativeQuery = true)
	List<Map<String,Object>> searchFilterResult(Character gender,boolean isActive , String firstName , String lastName ,String caste , String gotra ,int age,int index );

	
	@Query(value="Select count(*) from User u where u.gender = ?1 and u.isactives = ?2 ", nativeQuery = true)
	Integer searchResultCount(Character gender,boolean isActive);
	
	@Query(value="Select count(*) from User u where u.gender = ?1 and u.isactives = ?2 and"
			+ " (1=CASE  WHEN ?3='' THEN 1 ELSE u.firstName=?3  END )"
			+ " and (1=CASE  WHEN ?4='' THEN 1 ELSE u.lastName=?4  END )"
			+ " and (1=CASE  WHEN ?5='' THEN 1 ELSE u.caste=?5  END )"
			+ " and (1=CASE  WHEN ?6='' THEN 1 ELSE u.gotra=?6  END ) and (1=CASE WHEN ?7=0 THEN 1 ELSE floor(datediff(CURDATE(),CONVERT(u.dob, DATE))/365)>=?7 END )", nativeQuery = true)
	Integer searchFilterResultCount(Character gender,boolean isActive , String firstName , String lastName ,String caste , String gotra  ,int age);


}
