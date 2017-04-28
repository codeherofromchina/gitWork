package com.wxd.template.dao;

import java.util.List;

import com.wxd.template.domain.User;

public interface UserDao {
	/**
	 * 查找总记录条数
	 * @return
	 */
	public int getCount();
	
	/**
	 * 分页查询
	 * @param offset	偏移量
	 * @param length	最多数量
	 * @return
	 */
	public List<User> queryForPage(int offset,int length);

	/**
	 * 主键查询
	 * @param id
	 * @return
	 */
	public User findByPK(Long id);
	
	/**
	 * 根据主键更新实体
	 * @param user
	 * @return
	 */
	public boolean updateByPK(User user);
	
	
	public void save(User user) ;
	
	
	public void delete(Long id) throws Exception;
}
