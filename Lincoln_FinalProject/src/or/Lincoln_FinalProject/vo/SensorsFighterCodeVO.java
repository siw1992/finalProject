package or.Lincoln_FinalProject.vo;

public class SensorsFighterCodeVO {
	private int fireFighterCode;
	private int eventCode;
	private SensorsVO sevo;
	
	public int getEventCode() {
		return eventCode;
	}
	public void setEventCode(int eventCode) {
		this.eventCode = eventCode;
	}
	
	public SensorsVO getSevo() {
		return sevo;
	}
	public void setSevo(SensorsVO sevo) {
		this.sevo = sevo;
	}
	public int getFireFighterCode() {
		return fireFighterCode;
	}
	public void setFireFighterCode(int fireFighterCode) {
		this.fireFighterCode = fireFighterCode;
	}
}
