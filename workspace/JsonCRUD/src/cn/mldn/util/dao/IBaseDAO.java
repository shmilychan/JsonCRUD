package cn.mldn.util.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 * 该接口可以明确的描述出基础的DAO操作方法定义
 * @author mldn
 * @param <K> 表示主键类型
 * @param <V> 表示要使用的VO类型
 */
public interface IBaseDAO<K, V> {
	/**
	 * 执行数据的增加操作，调用INSERT语句处理
	 * @param vo 包含有要增加数据的内容，此对象绝对不允许为空；
	 * @return 数据追加成功会返回更新行数，行数大于0表示成功返回true，否则返回false
	 * @throws SQLException 通过PreparedStatement执行操作返回的是SQL异常，看见SQL异常就查数据层代码
	 */
	public boolean doCreate(V vo) throws SQLException ;
	/**
	 * 执行数据的修改操作，调用UPDATE语句
	 * @param vo 包含有要修改数据的内容，此对象绝对不允许为空；
	 * @return 数据修改成功返回true，否则返回false
	 * @throws SQLException 通过PreparedStatement执行操作返回的是SQL异常，看见SQL异常就查数据层代码
	 */
	public boolean doUpdate(V vo) throws SQLException ;
	/**
	 * 执行数据的删除操作，根据数据的ID进行删除
	 * @param id 要删除的数据ID
	 * @return 如果数据删除成功返回true，如果数据不存在或者无法删除返回false
	 * @throws SQLException 通过PreparedStatement执行操作返回的是SQL异常，看见SQL异常就查数据层代码
	 */
	public boolean doRemove(K id) throws SQLException ;
	/**
	 * 执行数据的批量删除操作
	 * @param ids 所有要删除的编号信息
	 * @return
	 * @throws SQLException
	 */
	public boolean doRemoveBatch(Set<K> ids) throws SQLException ;
	/**
	 * 根据ID进行数据的查询操作，使用SELECT语句
	 * @param id 要查询的数据编号，此内容绝对不可以为空
	 * @return 查询一行记录，并且将这行记录的内容转换为VO类对象，如果该记录不存在返回null
	 * @throws SQLException 通过PreparedStatement执行操作返回的是SQL异常，看见SQL异常就查数据层代码
	 */
	public V findById(K id) throws SQLException ;
	/**
	 * 查询全部的数据信息，不使用限定查询
	 * @return 由于返回多行的查询记录，所以每行记录都要求封装在VO类对象之中，如果没有返回任何查询结果，该集合长度为0(size()==0)，而不是null
	 * @throws SQLException 通过PreparedStatement执行操作返回的是SQL异常，看见SQL异常就查数据层代码
	 */
	public List<V> findAll() throws SQLException ;
	/**
	 * 进行数据的分页查询，不同的数据库分页有不同的实现
	 * @param currentPage 当前所在页，此页数一定要大于0
	 * @param lineSize 每行显示的数据长度，这个长度也就决定了ArrayList初始化的长度
	 * @return 由于返回多行的查询记录，所以每行记录都要求封装在VO类对象之中，如果没有返回任何查询结果，该集合长度为0(size()==0)，而不是null
	 * @throws SQLException 通过PreparedStatement执行操作返回的是SQL异常，看见SQL异常就查数据层代码
	 */
	public List<V> findAllSplit(Integer currentPage,Integer lineSize) throws SQLException ;
	/**
	 * 数据模糊的分页查询操作
	 * @param currentPage 当前所在页，此页数一定要大于0
	 * @param lineSize 每行显示的数据长度，这个长度也就决定了ArrayList初始化的长度
	 * @param column 模糊查询的设置列
	 * @param keyWord 模糊查询关键字
	 * @return 由于返回多行的查询记录，所以每行记录都要求封装在VO类对象之中，如果没有返回任何查询结果，该集合长度为0(size()==0)，而不是null
	 * @throws SQLException 通过PreparedStatement执行操作返回的是SQL异常，看见SQL异常就查数据层代码
	 */
	public List<V> findAllSplit(Integer currentPage,Integer lineSize,String column,String keyWord) throws SQLException ;
	/**
	 * 是进行全部数据统计个数的查询，使用COUNT()函数
	 * @return 表中的全部记录数，如果表中没有记录，返回的就是0
	 * @throws SQLException 通过PreparedStatement执行操作返回的是SQL异常，看见SQL异常就查数据层代码
	 */
	public Integer getAllCount() throws SQLException ;
	/**
	 * 统计模糊查询的数据量，使用COUNT()函数
	 * @param column 模糊查询的设置列
	 * @param keyWord 模糊查询关键字
	 * @return 表中的全部记录数，如果表中没有记录，返回的就是0
	 * @throws SQLException 通过PreparedStatement执行操作返回的是SQL异常，看见SQL异常就查数据层代码
	 */
	public Integer getAllCount(String column,String keyWord) throws SQLException ;
}
 