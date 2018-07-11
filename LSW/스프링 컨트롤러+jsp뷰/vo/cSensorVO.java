package or.kosta.vo;

public class cSensorVO {
	private String name;
	private String GYRO,PULSE,SMOKE,TEMP;
	private String g,p,s,t;
	
	public String getG() {
		return g;
	}

	public void setG(String g) {
		this.g = g;
	}

	public String getP() {
		return p;
	}

	public void setP(String p) {
		this.p = p;
	}

	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}

	public String getT() {
		return t;
	}

	public void setT(String t) {
		this.t = t;
	}

	public cSensorVO() {
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGYRO() {
		return GYRO;
	}
	public void setGYRO(String gYRO) {
		GYRO = gYRO;
	}
	public String getPULSE() {
		return PULSE;
	}
	public void setPULSE(String pULSE) {
		PULSE = pULSE;
	}
	public String getSMOKE() {
		return SMOKE;
	}
	public void setSMOKE(String sMOKE) {
		SMOKE = sMOKE;
	}
	public String getTEMP() {
		return TEMP;
	}
	public void setTEMP(String tEMP) {
		TEMP = tEMP;
	}
	
}
