package $!{projectPackage}.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import $!{projectPackage}.dao.$!{beanName}Dao;
import $!{projectPackage}.domain.$!{beanName};
import $!{projectPackage}.utils.PageBean;

@Service(value = "$!{tableName}Service")
public class $!{beanName}Service {
	@Autowired
	private $!{beanName}Dao $!{tableName}Dao;
	
	/**
	 * 分页查询
	 * @param pageSize
	 * @param page
	 * @return
	 */
	public PageBean<$!{beanName}> queryForPage(int pageSize, int page) {
		int count = $!{tableName}Dao.getCount(); // 总记录数
		int currentPage = PageBean.countCurrentPage(page);
		int totalPage = PageBean.countTotalPage(pageSize, count); // 总页数
		int offset = PageBean.countOffset(pageSize, currentPage); // 当前页开始记录
		int length = pageSize; // 每页记录数
		List<$!{beanName}> list = $!{tableName}Dao.queryForPage(offset, length); // 该分页的记录
		// 把分页信息保存到Bean中
		PageBean<$!{beanName}> pageBean = new PageBean<$!{beanName}>();
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
	public $!{beanName} findByPK(Long id) {
		if (id == null) {
			return null;
		}
		return $!{tableName}Dao.findByPK(id);
	}
	
	
	public boolean updateByPK($!{beanName} $!{tableName}) {
		return $!{tableName}Dao.updateByPK($!{tableName});
	}
	
	
	public void save($!{beanName} $!{tableName}) {
		$!{tableName}Dao.save($!{tableName});
	}

	public void delete(Long[] idArr) throws Exception{
		for (Long id : idArr) {
			$!{tableName}Dao.delete(id);
		}
	}

}
