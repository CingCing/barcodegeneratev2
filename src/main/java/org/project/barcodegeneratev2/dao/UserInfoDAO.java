package org.project.barcodegeneratev2.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.project.barcodegeneratev2.entity.User;
import org.project.barcodegeneratev2.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserInfoDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	public UserInfo findUserInfo(String userName) {
		String sql="Select new" + UserInfo.class.getName() 
				+ "(u.username,u.password) " + "from" 
				+ User.class.getName() + "u where u.username = :username";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(sql);
		query.setParameter("username", userName);
		
		return (UserInfo) query.uniqueResult();
	}
	
//	public List<String> getUserRoles(String userName){
//		String sql = "Select r.userRole" + "from" 
//					+ UserRole....
//		return null;
//	}
}
