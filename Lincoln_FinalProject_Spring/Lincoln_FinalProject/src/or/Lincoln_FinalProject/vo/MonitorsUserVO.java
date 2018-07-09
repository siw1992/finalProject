package or.Lincoln_FinalProject.vo;

public class MonitorsUserVO {
	private int fireStationCode; //로그인후 소방관 정보
	private int monitorCode; //로그인후 모니터링 정보
	private String monitorId;
	private String monitorPwd;
	
	public final int getFireStationCode() {
		return fireStationCode;
	}
	public final void setFireStationCode(int fireStationCode) {
		this.fireStationCode = fireStationCode;
	}
	public final int getMonitorCode() {
		return monitorCode;
	}
	public final void setMonitorCode(int monitorCode) {
		this.monitorCode = monitorCode;
	}
	public final String getMonitorId() {
		return monitorId;
	}
	public final void setMonitorId(String monitorId) {
		this.monitorId = monitorId;
	}
	public final String getMonitorPwd() {
		return monitorPwd;
	}
	public final void setMonitorPwd(String monitorPwd) {
		this.monitorPwd = monitorPwd;
	}
	
	
}
