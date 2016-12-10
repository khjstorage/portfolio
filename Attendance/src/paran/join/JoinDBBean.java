package paran.join;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import paran.db.DBConnector;

public class JoinDBBean {
	public JoinDBBean() {
	}

	public int userCheck(String pId, String pPasswd) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DBConnector connector = new DBConnector();
		int x = -1;

		try {
			connector.connect();
			pstmt = connector.conn.prepareStatement("select PWD from student where ID =binary ?");
			pstmt.setString(1, pId);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String dbpasswd = rs.getString("PWD");
				if (pPasswd.equals(dbpasswd)) {
					x = 1;		//id, pwd ok
				} else {
					x = 0;		//pwd false
				}
			} else {
				x = -1;			//id false
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connector.disconnect();
		}
		return x;
	}

	public int confirmId(String pId){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DBConnector connector = new DBConnector();
		int x = -1;

		try{
			connector.connect();
			pstmt = connector.conn.prepareStatement("select id from student where id =binary ?");
			pstmt.setString(1, pId);
			rs = pstmt.executeQuery();
			if(rs.next()){
				x = 1;
			}else{
				x = -1;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			connector.disconnect();
		}
		return x;
	}

	public void insertMember(StudentDataBean pStudentBean){
		PreparedStatement pstmt = null;
		DBConnector connector = new DBConnector();
		try {
			connector.connect();
			pstmt = connector.conn.prepareStatement("insert into student values (?,?,?,?,?,?,?)");
			pstmt.setString(1, pStudentBean.getId());
			pstmt.setString(2, pStudentBean.getPwd());
			pstmt.setString(3, pStudentBean.getName());
			pstmt.setString(4, pStudentBean.getBirth_ymd());
			pstmt.setString(5, pStudentBean.getSexual_tp());
			pstmt.setString(6, pStudentBean.getAddr());
			pstmt.setString(7, pStudentBean.getCell_phone());

			pstmt.executeUpdate();
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			connector.disconnect();
		}
	}




}
