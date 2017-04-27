package cn.mldn.util.dao.abs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Set;

import cn.mldn.util.dbc.DatabaseConnection;

public abstract class AbstractDAO {
	protected PreparedStatement pstmt ;
	protected Connection conn ;
	public AbstractDAO() {
		this.conn = DatabaseConnection.getConnection() ;
	}
	
	/**
	 * 操作的主键一般是string类型或者int类型
	 * @param tableName 表明
	 * @param pkColumName 主键列，一般都是id
	 * @param ids 删除的主键信息
	 * @return
	 * @throws SQLException
	 */
	public boolean handleDelete(String tableName,String pkColumName,Set<Integer> ids) throws SQLException{
		StringBuffer buffer = new StringBuffer();
		buffer.append("DELETE FROM ").append(tableName).append(" WHERE ").append(pkColumName).append(" IN (");
		Iterator<Integer> iterator = ids.iterator();
		while (iterator.hasNext()) {
			buffer.append(iterator.next()).append(",");
		}
		buffer.delete(buffer.length() - 1, buffer.length());
		buffer.append(")");
		PreparedStatement pstmt = DatabaseConnection.getConnection().prepareStatement(buffer.toString());
		return pstmt.executeUpdate() > 0;
	}
}
