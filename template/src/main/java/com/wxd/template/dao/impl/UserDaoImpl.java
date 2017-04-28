package com.wxd.template.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wxd.template.dao.UserDao;
import com.wxd.template.domain.User;

@Repository(value = "userDao")
public class UserDaoImpl implements UserDao {
	// 注入sessionFactory
	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<User> queryForPage(int offset, int length) {
		Query q = sessionFactory.getCurrentSession().createQuery(ALL_HQL);
		q.setFirstResult(offset);
		q.setMaxResults(length);
		return q.list();
	}

	@Override
	public int getCount() {
		Query q = sessionFactory.getCurrentSession().createQuery(COUNT_HQL);
		return Integer.parseInt(q.list().get(0).toString());
	}
	
	
	@Override
	public User findByPK(Long id) {
		String hql = ALL_HQL + " where id = :id";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("id", id);
		return (User)query.uniqueResult();
	}
	
	@Override
	public boolean updateByPK(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.update(user);
		return true;
	}
	
	@Override
	public void save(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.persist(user);
	}
	
	@Override
	public void delete(Long id) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		User user = session.load(User.class, id);
		if (user == null) {
			throw new Exception("实体信息不存在。[id:"+id+"]");
		}
		session.delete(user);
	}

	private final static String ALL_HQL = "from User";
	private final static String COUNT_HQL = "select count(*) from User";
}
