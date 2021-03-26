package com.project2.repository;



import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project2.models.Posts;
import com.project2.models.Users;

@Repository("userRepository")
@Transactional
public class UserRepositoryImpl implements UserRepository {
	
	private static Logger log = Logger.getLogger(UserRepositoryImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public UserRepositoryImpl() {
		log.trace("Injection session factory bean");
	}

	@Override
	public void register(Users u) {
		
		sessionFactory.getCurrentSession().save(u);
		
		
	}

	@Override
	public Users findById(int id) {
		try {
			return (Users) sessionFactory.getCurrentSession().createCriteria(Users.class).add(Restrictions.like("id", id))
				.list().get(0);
		}catch(IndexOutOfBoundsException e) {
			log.debug(e);
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Users> findAll() {
		return sessionFactory.getCurrentSession().createCriteria(Users.class).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Posts> findPostApplied(Users u) {
		return (List<Posts>) sessionFactory.getCurrentSession().createCriteria(Posts.class)
				.add(Restrictions.like("employeeId", u.getId()));
	}



}
