package com.wxd.template.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxd.template.dao.UserDao;
import com.wxd.template.domain.User;
import com.wxd.template.utils.PageBean;

@Service(value = "userService")
public class UserService {
	@Autowired
	private UserDao userDao;
	
	/**
	 * 分页查询
	 * @param pageSize
	 * @param page
	 * @return
	 */
	public PageBean<User> queryForPage(int pageSize, int page) {
		int count = userDao.getCount(); // 总记录数
		int currentPage = PageBean.countCurrentPage(page);
		int totalPage = PageBean.countTotalPage(pageSize, count); // 总页数
		int offset = PageBean.countOffset(pageSize, currentPage); // 当前页开始记录
		int length = pageSize; // 每页记录数
		List<User> list = userDao.queryForPage(offset, length); // 该分页的记录
		// 把分页信息保存到Bean中
		PageBean<User> pageBean = new PageBean<User>();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		pageBean.setAllRow(count);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.init();
		return pageBean;
	}
	
	/**
	 * 通过主键查找实体
	 * @param id
	 * @return
	 */
	public User findByPK(Long id) {
		if (id == null) {
			return null;
		}
		return userDao.findByPK(id);
	}
	
	
	public boolean updateByPK(User user) {
		return userDao.updateByPK(user);
	}
	
	
	public void save(User user) {
		userDao.save(user);
	}

	public void delete(Long[] idArr) throws Exception{
		for (Long id : idArr) {
			userDao.delete(id);
		}
	}

}
