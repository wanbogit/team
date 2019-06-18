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
	 * �رղ��ͷ�������Դ
	 */
	public void close() {
		DBclose(conn, ps, null);
	}

	/**
	 * ִ����Ӳ������޲�SQL���
	 * 
	 * @param sql
	 * @return Ӱ�������
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
	 * ִ����Ӳ������в�SQL���
	 * 
	 * @param sql
	 * @param parmas
	 * @return Ӱ�������
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
	 * ִ���޸Ĳ������޲�SQL���
	 * 
	 * @param sql
	 * @return Ӱ�������
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
	 * ִ���޸Ĳ������в�SQL���
	 * 
	 * @param sql
	 * @param parmas
	 * @return Ӱ�������
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
	 * ִ��ɾ���������޲�SQL���
	 * 
	 * @param sql
	 * @return Ӱ�������
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
	 * ִ��ɾ���������в�SQL���
	 * 
	 * @param sql
	 * @param parmas
	 * @return Ӱ�������
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
	
	/*--------------------------------------�������---------------------------------*/
	/**
	 * ��ʼ����
	 * @return ���ֶ��ύ�����Ӷ���
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
	 * ִ�д�����Ĵ������ǲ�ѯ����
	 * @param sql ����SQLָ��
	 * @param parmas SQL����
	 * @return Ӱ�������
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
	 * ִ�д�����ķǲ�ѯ����
	 * @param sql
	 * @return Ӱ�������
	 * @throws SQLException
	 */
	public int executeTrans(String sql) throws SQLException{
		int cnt=0;		
		ps=conn.prepareStatement(sql);		
		cnt=ps.executeUpdate();			
		return cnt;
	}
	/**
	 * �ύ����
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
	 * ����ع�
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
	 * ����ִ����ɾ�Ĳ���
	 * 
	 * @param sql
	 *            Sqlָ��
	 * @param parmas
	 *            ������
	 * @return ִ��ָ���Ӱ������
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
	 * ִ�в�ѯ�������޲�SQL���
	 * 
	 * @param sql
	 * @return Ӱ�������
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
	 * ִ�в�ѯ�������в�SQL���
	 * 
	 * @param sql
	 * @param parmas
	 * @return Ӱ�������
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
	
	
	/** ʹ�÷�ҳ�洢����sp_page���з�ҳ��ѯ
	 * @param tablename ����
	 * @param columns ��ѯ�����ֶ���������ֶ���","����
	 * @param Where ��ѯ����
	 * @param pagesize ÿҳ��¼����
	 * @param nowpage ��ǰҳ
	 * @param ordercolumn �����ֶ�
	 * @param ordercolumntype �����ֶ����ͣ�0-��������,1-�ַ�����,2-����ʱ������ 
	 * @param order ����ʽ,0-˳��,1-���� 	 
	 * @param pagecount ��ҳ������
	 * @return ��ǰҳ�Ľ������ʹ����ϣ��ر����ݿ����
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
	
	//�������
	public static void main(String[] args){
		BaseDAO dao=new BaseDAO();
		dao.beginTrans();
		try{
			int a=dao.executeTrans("update T_Student set name='С��' where id='1000'");
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
