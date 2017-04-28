package $!{projectPackage}.dao;

import java.util.List;

import $!{projectPackage}.domain.$!{beanName};

public interface $!{beanName}Dao {
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
	public List<$!{beanName}> queryForPage(int offset,int length);

	/**
	 * 主键查询
	 * @param id
	 * @return
	 */
	public $!{beanName} findByPK(Long id);
	
	/**
	 * 根据主键更新实体
	 * @param $!{tableName}
	 * @return
	 */
	public boolean updateByPK($!{beanName} $!{tableName});
	
	
	public void save($!{beanName} $!{tableName}) ;
	
	
	public void delete(Long id) throws Exception;
}
