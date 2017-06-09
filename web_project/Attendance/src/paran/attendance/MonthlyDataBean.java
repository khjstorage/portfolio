package paran.attendance;

public class MonthlyDataBean {
	private String id;
	private String name;
	private String att_cnt;
	private String early_cnt;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAtt_cnt() {
		return att_cnt;
	}
	public void setAtt_cnt(String att_cnt) {
		this.att_cnt = att_cnt;
	}
	public String getEarly_cnt() {
		return early_cnt;
	}
	public void setEarly_cnt(String early_cnt) {
		this.early_cnt = early_cnt;
	}
	
}
