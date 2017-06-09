package paran.attendance;

import java.sql.Date;
import java.sql.Timestamp;

public class AttendanceDataBean {
	public AttendanceDataBean(){}
	
	private String id;
	private Date att_dt;
	private String att_yn;
	private String early_yn;
	private Timestamp att_tm;
	private Timestamp exit_tm;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getAtt_dt() {
		return att_dt;
	}
	public void setAtt_dt(Date att_dt) {
		this.att_dt = att_dt;
	}
	public String getAtt_yn() {
		return att_yn;
	}
	public void setAtt_yn(String att_yn) {
		this.att_yn = att_yn;
	}
	public String getEarly_yn() {
		return early_yn;
	}
	public void setEarly_yn(String early_yn) {
		this.early_yn = early_yn;
	}
	public Timestamp getAtt_tm() {
		return att_tm;
	}
	public void setAtt_tm(Timestamp att_tm) {
		this.att_tm = att_tm;
	}
	public Timestamp getExit_tm() {
		return exit_tm;
	}
	public void setExit_tm(Timestamp exit_tm) {
		this.exit_tm = exit_tm;
	}
	

}
