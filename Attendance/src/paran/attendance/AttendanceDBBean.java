package paran.attendance;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import paran.db.DBConnector;

public class AttendanceDBBean {

	public AttendanceDBBean() {
	}

	public void lookupAtt(String pId, List<AttendanceDataBean> pList) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AttendanceDataBean attInst = null;
		DBConnector connector = new DBConnector();


		try {
			connector.connect();
			String sql = "select a.ID, b.ATT_DT, b.ATT_YN, b.EARLY_YN, b.ATT_TM, b.EXIT_TM "
					+ "from student a inner join attendance b on a.id = b.id " + "where a.id=binary ?";
			pstmt = connector.conn.prepareStatement(sql);
			pstmt.setString(1, pId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				attInst = new AttendanceDataBean();
				attInst.setId(rs.getString("id"));
				attInst.setAtt_dt(rs.getDate("att_dt"));
				attInst.setAtt_yn(rs.getString("att_yn"));
				attInst.setEarly_yn(rs.getString("early_yn"));
				attInst.setAtt_tm(rs.getTimestamp("att_tm"));
				attInst.setExit_tm(rs.getTimestamp("exit_tm"));
				pList.add(attInst);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connector.disconnect();
		}
	}

	public String[] monthMylist(String pId, String pDate) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DBConnector connector = new DBConnector();
		String name = null;

		String att_cnt = null;
		String early_cnt = null;

		try {
			connector.connect();
			String sql = "SELECT A.NAME, B.ATT_CNT, B.EARLY_CNT "
					+ "FROM STUDENT A RIGHT JOIN "
					+ "(SELECT SUM(CASE WHEN A.ATT_YN = 'Y' THEN 1 ELSE 0 END) ATT_CNT,"
					+ "SUM(CASE WHEN A.EARLY_YN = 'Y' THEN 1 ELSE 0 END) EARLY_CNT "
					+ "FROM ATTENDANCE A "
					+ "WHERE A.ATT_DT >= STR_TO_DATE(?,'%Y-%m-%d') "
					+ "AND A.ATT_DT <= STR_TO_DATE(?,'%Y-%m-%d') "
					+ "AND A.ID = ?)B ON ID=?";
			String firstDate = pDate + "-01";
			String secondDate = pDate + "-31";
			pstmt = connector.conn.prepareStatement(sql);

			pstmt.setString(1, firstDate);
			pstmt.setString(2, secondDate);
			pstmt.setString(3, pId);
			pstmt.setString(4, pId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				name =rs.getString("NAME");
				att_cnt =rs.getString("ATT_CNT");
				early_cnt =rs.getString("EARLY_CNT");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connector.disconnect();
		}
		String arr[] = { name, att_cnt, early_cnt };
		return arr;
	}

	public void monthAllList(String pDate,  List<MonthlyDataBean> pList) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DBConnector connector = new DBConnector();
		MonthlyDataBean attInst = null;

		try {
			connector.connect();
			String sql = "SELECT A.ID, A.NAME, B.ATT_CNT, B.EARLY_CNT FROM STUDENT A LEFT JOIN ( SELECT A.ID, "
					+ "SUM(CASE WHEN A.ATT_YN = 'Y' THEN 1 " + "ELSE 0 END) ATT_CNT, "
					+ "SUM(CASE WHEN A.EARLY_YN = 'Y' THEN 1 " + "ELSE 0 END) EARLY_CNT " + "FROM ATTENDANCE A "
					+ "WHERE A.ATT_DT >= STR_TO_DATE(?,'%Y-%m-%d') "
					+ "AND A.ATT_DT <= STR_TO_DATE(?,'%Y-%m-%d') " + "GROUP BY ID ) B ON A.ID = B.ID";
			pstmt = connector.conn.prepareStatement(sql);
			String firstDate = pDate + "-01";
			String secondDate = pDate + "-31";
			pstmt = connector.conn.prepareStatement(sql);
			pstmt.setString(1, firstDate);
			pstmt.setString(2, secondDate);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				attInst = new MonthlyDataBean();
				attInst.setId(rs.getString("ID"));
				attInst.setName(rs.getString("NAME"));
				attInst.setAtt_cnt(rs.getString("ATT_CNT"));
				attInst.setEarly_cnt(rs.getString("EARLY_CNT"));
				pList.add(attInst);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connector.disconnect();
		}
	}

	public void attCheck(String pId) {
		PreparedStatement pstmt = null;
		DBConnector connector = new DBConnector();

		java.text.SimpleDateFormat dateformatter = new java.text.SimpleDateFormat("yyyy-MM-dd");
		String today = dateformatter.format(new java.util.Date());
		try {
			connector.connect();
			String sql = "INSERT INTO attendance (id, ATT_DT, ATT_YN, ATT_TM) " + "values (?,?,'Y',CURRENT_TIMESTAMP)";
			pstmt = connector.conn.prepareStatement(sql);
			pstmt.setString(1, pId);
			pstmt.setString(2, today);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connector.disconnect();
		}
	}

	public void exitCheck(String pId) {
		PreparedStatement pstmt = null;
		DBConnector connector = new DBConnector();


		java.text.SimpleDateFormat dateformatter = new java.text.SimpleDateFormat("yyyy-MM-dd");
		String today = dateformatter.format(new java.util.Date());

		try {
			connector.connect();
			String sql = "UPDATE student a INNER JOIN attendance b on a.ID = b.ID "
					+ "set b.EXIT_TM = CURRENT_TIMESTAMP " + "WHERE a.ID=binary ? AND b.ATT_DT = ?";
			pstmt = connector.conn.prepareStatement(sql);
			pstmt.setString(1, pId);
			pstmt.setString(2, today);
			pstmt.executeUpdate();

			sql = "UPDATE student a INNER JOIN attendance b on a.ID = b.ID " + "set b.EARLY_YN = 'Y'"
					+ " WHERE a.ID=binary ? AND CURRENT_TIMESTAMP < ? ";
			pstmt = connector.conn.prepareStatement(sql);
			pstmt.setString(1, pId);
			today += " 16:00:00";
			pstmt.setString(2, today);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connector.disconnect();
		}
	}

	public String[] onOffAttCheckBtn(String pId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DBConnector connector = new DBConnector();

		String att_yn = null;
		String timeString = null;

		java.text.SimpleDateFormat dateformatter = new java.text.SimpleDateFormat("yyyy-MM-dd");
		String today = dateformatter.format(new java.util.Date());
		try {
			connector.connect();
			String sql = "SELECT b.ATT_YN, b.EXIT_TM " + "FROM student a INNER JOIN attendance b on a.ID = b.ID "
					+ "WHERE a.ID=binary ? AND b.ATT_DT = ?";
			pstmt = connector.conn.prepareStatement(sql);
			pstmt.setString(1, pId);
			pstmt.setString(2, today);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				att_yn = rs.getString(1);
				SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
				timeString = timeFormat.format(rs.getTimestamp(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connector.disconnect();
		}
		String arr[] = { att_yn, timeString };
		return arr;
	}

}
