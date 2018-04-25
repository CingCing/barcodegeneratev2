package org.project.barcodegeneratev2.authentication;

import org.project.barcodegeneratev2.dao.UserInfoDAO;
import org.project.barcodegeneratev2.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	private UserInfoDAO userInfoDAO;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		UserInfo userInfo = userInfoDAO.findUserInfo(userName);
		System.out.println("UserInfo = " + userInfo);
		
		if(userInfo == null) {
			throw new UsernameNotFoundException("User" + userName  + "not found");
		}
		
//		// [USER,ADMIN,..]
//		List<String> roles = userInfoDAO.getUserRoles(username);
//
//		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
//		if (roles != null) {
//			for (String role : roles) {
//				// ROLE_USER, ROLE_ADMIN,..
//				GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);
//				grantList.add(authority);
//			}
//		}    
		
		UserDetails userDetails =(UserDetails) new User(userInfo.getUserName(), userInfo.getPassword(), null);
		return userDetails;
	}
	
}
