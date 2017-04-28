package $!{projectPackage}.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import $!{projectPackage}.dao.$!{beanName}Dao;
import $!{projectPackage}.domain.$!{beanName};

@Repository(value = "$!{tableName}Dao")
public class $!{beanName}DaoImpl implements $!{beanName}Dao {
	// 注入sessionFactory
	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<$!{beanName}> queryForPage(int offset, int length) {
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
	public $!{beanName} findByPK(Long id) {
		String hql = ALL_HQL + " where id = :id";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("id", id);
		return ($!{beanName})query.uniqueResult();
	}
	
	@Override
	public boolean updateByPK($!{beanName} $!{tableName}) {
		Session session = sessionFactory.getCurrentSession();
		session.update($!{tableName});
		return true;
	}
	
	@Override
	public void save($!{beanName} $!{tableName}) {
		Session session = sessionFactory.getCurrentSession();
		session.persist($!{tableName});
	}
	
	@Override
	public void delete(Long id) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		$!{beanName} $!{tableName} = session.load($!{beanName}.class, id);
		if ($!{tableName} == null) {
			throw new Exception("实体信息不存在。[id:"+id+"]");
		}
		session.delete($!{tableName});
	}

	private final static String ALL_HQL = "from $!{beanName}";
	private final static String COUNT_HQL = "select count(*) from $!{beanName}";
}
