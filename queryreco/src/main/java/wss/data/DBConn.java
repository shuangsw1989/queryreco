package wss.data;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConn {
	String dbDriver ="org.gjt.mm.mysql.Driver";
	String dbUrl="jdbc:mysql://localhost:3306/queryreco";
	String dbName="root";
	String dbPassword="root";

	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	/**
	 * ��ݿ�����ӵĻ�ȡ����
	 * @return conn ��ݿ�����?
	 */
	public Connection getConn(){
		Connection conn= null;
		try {
			Class.forName(dbDriver);
			conn=DriverManager.getConnection(dbUrl,dbName,dbPassword);
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return conn;

	}
	/**
	 * ��ݵ�sql����Ԥ����Ĳ���?
	 * @param conn
	 * @param sql
	 * @return ps
	 */
	public PreparedStatement getPre(Connection conn,String sql){
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement(sql);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return ps;
	}
	/**
	 * ��ݿ��conn�ĹرյĲ���
	 * @param conn
	 */
	public void close(Connection conn){
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
	}
	/**
	 * ��ݿ��ps�ĹرյĲ���
	 * @param ps
	 */
	public void close(PreparedStatement ps){
		if(ps!=null){
			try {
				ps.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
	}
	/**
	 * ��ݿ��stmt�Ĺرղ���
	 * @param stmt
	 */
	public void close(Statement stmt){
		if(stmt!=null){
			try {
				stmt.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
	}
	/**
	 * ��ݿ�Ĺرս��Ĳ���
	 * @param rs
	 */
	public void close(ResultSet rs){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
	}



	public PreparedStatement getPs() {
		return ps;
	}
	public void setPs(PreparedStatement ps) {
		this.ps = ps;
	}
	public ResultSet getRs() {
		return rs;
	}
	public void setRs(ResultSet rs) {
		this.rs = rs;
	}
	public void setConn(Connection conn) {
		this.conn = conn;
	}
	public static void main(String[] args) {

	}

}
