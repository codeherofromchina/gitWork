package com.wxd.template.service;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wxd.template.domain.User;
import com.wxd.template.utils.PageBean;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-test.xml" })
public class UserServiceTest {
	@Resource(name="userService")
	private UserService userService;

	@Test
	public void testQueryForPage() {
		PageBean<User> pageBean = userService.queryForPage(10,1);
		System.out.println("currentPage:" + pageBean.getCurrentPage());
		System.out.println("pageSize:" + pageBean.getPageSize());
		System.out.println("totalPage:" + pageBean.getTotalPage());
		System.out.println("allRow:" + pageBean.getAllRow());
		
		List<User> list = pageBean.getList();
		if (pageBean.getAllRow() > 0 ) {
			System.out.println(list);
		} else {
			System.out.println("结果集为空！");
		}
		
	}

}
