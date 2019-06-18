package com.wb.struts2.basic;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class BaseDAO extends DBconn {

	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	/**
	 * 关闭并释放连接资源
	 */
	public void close() {
		DBclose(conn, ps, null);
	}

	/**
	 * 执行添加操作的无参SQL语句
	 * 
	 * @param sql
	 * @return 影响的行数
	 */
	public int insert(String sql) {
		conn = getConnection();
		int cnt = 0;
		try {
			ps = conn.prepareStatement(sql);
			cnt = ps.executeUpdate();
			close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cnt;
	}

	/**
	 * 执行添加操作的有参SQL语句
	 * 
	 * @param sql
	 * @param parmas
	 * @return 影响的行数
	 */
	public int insert(String sql, Object[] parmas) {
		conn = getConnection();
		int cnt = 0;
		try {
			ps = conn.prepareStatement(sql);
			for (int i = 1; i <= parmas.length; i++) {
				ps.setObject(i, parmas[i - 1]);
			}
			cnt = ps.executeUpdate();
			close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cnt;
	}

	/**
	 * 执行修改操作的无参SQL语句
	 * 
	 * @param sql
	 * @return 影响的行数
	 */
	public int update(String sql) {
		conn = getConnection();
		int cnt = 0;
		try {
			ps = conn.prepareStatement(sql);
			cnt = ps.executeUpdate();
			close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cnt;
	}

	/**
	 * 执行修改操作的有参SQL语句
	 * 
	 * @param sql
	 * @param parmas
	 * @return 影响的行数
	 */
	public int update(String sql, Object[] parmas) {
		conn = getConnection();
		int cnt = 0;
		try {
			ps = conn.prepareStatement(sql);
			for (int i = 1; i <= parmas.length; i++) {
				ps.setObject(i, parmas[i - 1]);
			}
			cnt = ps.executeUpdate();
			close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cnt;
	}

	/**
	 * 执行删除操作的无参SQL语句
	 * 
	 * @param sql
	 * @return 影响的行数
	 */
	public int delete(String sql) {
		conn = getConnection();
		int cnt = 0;
		try {
			ps = conn.prepareStatement(sql);
			cnt = ps.executeUpdate();
			close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cnt;
	}

	/**
	 * 执行删除操作的有参SQL语句
	 * 
	 * @param sql
	 * @param parmas
	 * @return 影响的行数
	 */
	public int delete(String sql, Object[] parmas) {
		conn = getConnection();
		int cnt = 0;
		try {
			ps = conn.prepareStatement(sql);
			for (int i = 1; i <= parmas.length; i++) {
				ps.setObject(i, parmas[i - 1]);
			}
			cnt = ps.executeUpdate();
			close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cnt;
	}
	
	/*--------------------------------------事务操作---------------------------------*/
	/**
	 * 开始事务
	 * @return 带手动提交的连接对象
	 */
	public Connection beginTrans(){
		conn=getConnection();
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	/**
	 * 执行带事务的带参数非查询操作
	 * @param sql 带参SQL指令
	 * @param parmas SQL参数
	 * @return 影响的行数
	 * @throws SQLException 
	 */	
	public int executeTrans(String sql,Object[] parmas) throws SQLException{
		int cnt=0;		
		ps=conn.prepareStatement(sql);
		for(int i=1;i<=parmas.length;i++){
			ps.setObject(i, parmas[i-1]);
		}
		cnt=ps.executeUpdate();				
		return cnt;
	}
	/**
	 * 执行带事务的非查询操作
	 * @param sql
	 * @return 影响的行数
	 * @throws SQLException
	 */
	public int executeTrans(String sql) throws SQLException{
		int cnt=0;		
		ps=conn.prepareStatement(sql);		
		cnt=ps.executeUpdate();			
		return cnt;
	}
	/**
	 * 提交事务
	 */
	public void commitTrans(){
		try {
			conn.commit();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	/**
	 * 事务回滚
	 */
	public void rollbackTrans(){
		try {
			conn.rollback();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}	
	/*--------------------------------------end---------------------------------*/

	/**
	 * 批量执行增删改操作
	 * 
	 * @param sql
	 *            Sql指令
	 * @param parmas
	 *            参数集
	 * @return 执行指令的影响行数
	 */
	public int executeBatch(String sql, Object[][] parmas) {

		conn = getConnection();
		int cnt = 0;
		try {
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < parmas.length; i++) {
				for (int j = 1; j <= parmas[i].length; j++) {
					ps.setObject(j, parmas[i][j - 1]);
				}
				ps.addBatch();
			}

			int[] num = ps.executeBatch();
			for (int i = 0; i < num.length; i++) {
				cnt += num[i];
			}
			close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cnt;
	}

	/**
	 * 执行查询操作的无参SQL语句
	 * 
	 * @param sql
	 * @return 影响的行数
	 */
	public ResultSet select(String sql) {
		conn = getConnection();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			// close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	/**
	 * 执行查询操作的有参SQL语句
	 * 
	 * @param sql
	 * @param parmas
	 * @return 影响的行数
	 */
	public ResultSet select(String sql, Object[] parmas) {
		conn = getConnection();
		try {
			ps = conn.prepareStatement(sql);
			for (int i = 1; i <= parmas.length; i++) {
				ps.setObject(i, parmas[i - 1]);
			}
			rs = ps.executeQuery();
			// close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	
	/** 使用分页存储过程sp_page进行分页查询
	 * @param tablename 表名
	 * @param columns 查询到的字段名，多个字段用","隔开
	 * @param Where 查询条件
	 * @param pagesize 每页记录条数
	 * @param nowpage 当前页
	 * @param ordercolumn 排序字段
	 * @param ordercolumntype 排序字段类型：0-数字类型,1-字符类型,2-日期时间类型 
	 * @param order 排序方式,0-顺序,1-倒序 	 
	 * @param pagecount 总页数引用
	 * @return 当前页的结果集，使用完毕，关闭数据库对象
	 */
	public ResultSet selectPage(String tablename,String columns,String Where,
			int pagesize,int nowpage, String ordercolumn,int ordercolumntype,int order,int[] pagecount){	
		conn=getConnection();
		try {
			CallableStatement cs=conn.prepareCall("{call sp_page(?,?,?,?,?,?,?,?,?)}");
			cs.setObject(1, tablename);
			cs.setObject(2, ordercolumn);
			cs.setObject(3, ordercolumntype);
			cs.setObject(4, order);
			cs.setObject(5, columns);
			cs.setObject(6, pagesize);
			cs.setObject(7, nowpage);
			cs.setObject(8, Where);
			cs.registerOutParameter(9, Types.INTEGER);		
			cs.execute();			
			pagecount[0]=cs.getInt(9);
			rs=cs.executeQuery();
			
			//close();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return rs;
	}
	
	//事务测试
	public static void main(String[] args){
		BaseDAO dao=new BaseDAO();
		dao.beginTrans();
		try{
			int a=dao.executeTrans("update T_Student set name='小三' where id='1000'");
			int b=dao.executeTrans("delete from T_Student where id='1001'");
			if(a>0&&b>0){
				dao.commitTrans();
			}else{
				dao.rollbackTrans();
			}
		}catch(SQLException e){
			dao.rollbackTrans();
		}
		
	} 
	
	
}
