package org.project.barcodegeneratev2.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.project.barcodegeneratev2.entity.User;
import org.project.barcodegeneratev2.entity.UserRole;
import org.project.barcodegeneratev2.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserInfoDAO {
	@Autowired
	private SessionFactory sessionFactory; //Tương ứng với 1 phiên làm việc với csdl
	
	public UserInfoDAO() {
	 
	}
	
	public void insertUserInfo(UserInfo userInfo) {
		String username = userInfo.getUsername();
		String password = userInfo.getPassword();
		User user = new User();
//		UserRole userRole = new UserRole();
		
//		boolean isNew = false;
		user.setUsername(username);
		user.setPassword(password);
		
//		userRole.setRoleId("");
//		userRole.setUser(user);
//		userRole.setUserRole("USER");
		
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(user); //insert into table
//		session.persist(userRole);
	};
	
	//Tìm user trong csdl
	public UserInfo findUserInfo(String userName) {
		String sql="Select new " + UserInfo.class.getName() + " (u.username,u.password) " + "from " 
				+ User.class.getName() + " u where u.username = :username";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(sql);
		query.setParameter("username", userName);
		
		return (UserInfo) query.uniqueResult();
	}
	// lấy ra danh sách các quyền trong csdl
	public List<String> getUserRoles(String userName) {
        String sql = "Select r.userRole "//
                + " from " + UserRole.class.getName() + " r where r.user.username = :username ";
 
        Session session = sessionFactory.getCurrentSession();
 
        Query query = session.createQuery(sql);
        query.setParameter("username", userName);
         
        List<String> roles = query.list();
 
        return roles;
    }

	public boolean exists(String username) {		
		
		return findUserInfo(username) != null;
	}
	
}
