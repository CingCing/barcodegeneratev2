package org.project.barcodegeneratev2.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.project.barcodegeneratev2.entity.QrText;
import org.project.barcodegeneratev2.model.QrTextInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class QrTextDAO {
	
	@Autowired
	private SessionFactory sessionFactory;	
	
	public void insertQrText(QrTextInfo qrTextInfo) {
		String username = qrTextInfo.getUsername();
		String context = qrTextInfo.getContext();
		QrText qrtext = new QrText();
		
//		boolean isNew = false;
		qrtext.setUsername(username);
		qrtext.setContext(context);
		
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(qrtext); //insert into table
	};
}
