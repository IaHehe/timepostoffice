package com.cqust.tpo.service;

import java.util.List;

/**
 * 定义公共Service接口：<br>
 * 调用DAO中的CRUD操作
 * @author 邹东军
 *
 @param <K>要操作的对象的键类型
 * @param <V>要操作的 VO 类型
 */
public interface IService<K, V> {
	/**
	 * 调用数据层的插入操作，操作流程如下:<br>
	 * <li>首先使用 IUserDAO 接口中的 findById() 方法，根据要增加的 id 查看指定的用户信息
	 * 是否存在
	 * <li>如果要增加的雇员信息不存在，则执行 IUserDAO 接口的 doInsert() 方法
	 * @param user 包装数据的对象
	 * @return 如果数据插入成功返回 true，否则返回 false
	 */
	public void insert(V vo);
	
	/**
	 * 执行数据的更新操作，操作时直接调用 IUserDAO 接口的doUpdate() 方法
	 * @param User 包装数据的对象
	 */
	public void update(V vo);
	
	/**
	 * 执行数据的删除操作，删除操作时调用 IUserDAO 接口的 doDelete() 方法
	 * @param id 要删除对象的 id
	 */
	public void delete(K id);
	
	/**
	 * 根据对象的唯一标识符 id 取得某一对象的全部信息，调用 IUserDAO 接口<br>
	 * 的 findById() 方法
	 * @param id 对象的 id
	 * @return 如果数据存在则将数据包装为 User 对象返回，如果不存在返回 null
	 */
	public V get(K id);
	
	/**
	 * 获取数据表中记录的总数，调用 IUserDAO 接口中的 getAllCount() 方法
	 * @return 返回数据表中记录的长度，如没有数据，返回 0
	 */
	public int count();
	
	/**
	 * 获取全部的用户记录：使用 IUserDAO 接口的 findAll()方法操作
	 * @return　返回一个包含全部记录的 List 集合
	 */
	public List<V> list();
	
	/**
	 * 分页查询： 调用 IUserDAO 接口的 findAll(int firstResult, int maxResult) 方法 
	 * @param firstResult 指定从哪一条数据开始读取
	 * @param maxResult 指定一次读取的数据条数
	 * @return 返回包含指定条件查询的数据记录的 List 集合
	 */
	public List<V> list(int firstResult, int maxResult);

}
