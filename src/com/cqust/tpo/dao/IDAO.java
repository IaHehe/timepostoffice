package com.cqust.tpo.dao;

import java.util.List;

/**
 * 定义公共DAO接口:<br>
 * 在开发之中，数据表的基本字段的 CRUD 操作（增加、修改全部数据、删除、根据 ID 查询、查询<br>
 * 全部、统计数据个数）对于大部分数据层都是通用的，那么可以统一定义一个公共的 IDao接口。
 * @author 邹东军
 * @param <k>要操作的数据表的主键类型
 * @param <v>要操作的 VO 类型
 *
 */
public interface IDAO<K, V> {
	/**
	 * 执行数据的插入操作
	 * @param vo 所要插入的数据的 VO 对象
	 * @throws Exception 操作如果出现了异常，返回给被调用处执行处理
	 */
	public boolean doInsert(V vo) throws Exception;

	/**
	 * 执行数据的更新操作
	 * @param vo 要更新的数据的 VO 对象
	 * @return 如果数据更新成功返回 true，否则返回 false
	 */
	public boolean doUpdate(V vo) throws Exception;
	
	/**
	 * 根据对象的 id 标识，删除一个对象信息
	 * @param id 要删除的对象的 id 号
	 * @throws Exception 操作如果出现了异常，返回给被调用处执行处理
	 */
	public boolean doDelete(K id) throws Exception;
	
	/**
	 * 根据对象 id 查询一个对象的完整信息
	 * @param id 要查询的对象 id
	 * @return 如果有指定的对象信息，将所有的对象信息包装到 V 实例化对象中返回， <br>
	 * 如果没有指定的对象信息，返回值为 null
	 * @throws Exception 操作如果出现了异常，返回给被调用处执行处理
	 */
	public V findById(K id) throws Exception;
	
	/**
	 * 查询全部的对象信息
	 * @return 多个对象信息使用 List 返回如果 List 集合的 size() 长度为 0，则表示没有数据返回
	 * @throws Exception 操作如果出现了异常，返回给被调用处执行处理
	 */
	public List<V> findAll() throws Exception;
	
	/**
	 * 分页查询所有用户信息
	 * @param firstResult 指定查询的开始位置
	 * @param maxREsult 指定每页显示的记录条数
	 * @return 返回多个对象信息的 List 集合，如果 List 集合的size() 长度为0，表示没有数据返回
	 * @throws Exception 操作如果出现了异常，返回给被调用处执行处理
	 */
	public List<V> findAll(int firstResult, int maxResult) throws Exception;
	
	/**
	 * 查询总记录的条数
	 * @return 根据数据量的多少返回数据的长度，如果没有数据返回 0
	 * @throws Exception 操作中如果出现了异常，返回给被调用处执行处理
	 */
	public int getAllCount() throws Exception;
}
