package or.kosta.mvc.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import or.kosta.vo.cSensorVO;

public class StateNumber {
	private static StateNumber stateNumber;
	private static String PATH;
	// 소방관1,소방관2(이름과,각 센서 PATH 갖고있음)
	private cSensorVO[] f = new cSensorVO[2];
	private String NAME;// 이름은 임의로 정한것 뿐임.IP값이든 뭐든 OK
	private String GYRO;//자이로센서값
	private String PULSE;//심박센서값
	private String SMOKE;//연기센서값
	private String TEMP;//온도센서값

	public synchronized static StateNumber getStateNumber() {
		if (stateNumber == null) {
			stateNumber = new StateNumber();
			for (int i = 0; i < 2; i++) {
				stateNumber.f[i] = new cSensorVO();
				switch (i) {
				case 0:
					stateNumber.NAME = "192.168.0.132";
					stateNumber.GYRO = "C:\\kosta182\\spring\\workspace\\spring0615_mvc\\logs\\gyro_1.txt";
					stateNumber.PULSE = "C:\\kosta182\\spring\\workspace\\spring0615_mvc\\logs\\pulse_1.txt";
					stateNumber.SMOKE = "C:\\kosta182\\spring\\workspace\\spring0615_mvc\\logs\\smoke_1.txt";
					stateNumber.TEMP = "C:\\kosta182\\spring\\workspace\\spring0615_mvc\\logs\\temp_1.txt";
					break;
				case 1:
					stateNumber.NAME = "Watson";
					stateNumber.GYRO = "C:\\kosta182\\spring\\workspace\\spring0615_mvc\\logs\\gyro_2.txt";
					stateNumber.PULSE = "C:\\kosta182\\spring\\workspace\\spring0615_mvc\\logs\\pulse_2.txt";
					stateNumber.SMOKE = "C:\\kosta182\\spring\\workspace\\spring0615_mvc\\logs\\smoke_2.txt";
					stateNumber.TEMP = "C:\\kosta182\\spring\\workspace\\spring0615_mvc\\logs\\temp_2.txt";
					break;
				}
				stateNumber.f[i].setName(stateNumber.NAME);
				stateNumber.f[i].setGYRO(stateNumber.GYRO);
				stateNumber.f[i].setPULSE(stateNumber.PULSE);
				stateNumber.f[i].setSMOKE(stateNumber.SMOKE);
				stateNumber.f[i].setTEMP(stateNumber.TEMP);
			}
		}
		return stateNumber;
	}

	public void save(String name, String sensorName, String status) {
		// 한줄한줄, 이어서 저장한다
		if (status.equals("")) {
			System.out.println("공백");
			return;
		}
		BufferedWriter bw = null;
		PrintWriter pw = null;
		setPath(name, sensorName, status);
		try {
			bw = new BufferedWriter(new FileWriter(PATH, true));
			pw = new PrintWriter(bw, true);
			pw.write(status.trim());
			pw.println("");
			if (bw != null)
				bw.close();
			if (pw != null)
				pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String view(String name, String sensorName) {
		String status = "";
		StringBuffer sb = new StringBuffer();
		BufferedReader br = null;
		setPath(name,sensorName);
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(PATH)));
			while ((status = br.readLine()) != null)
				sb.append(status).append("\n");
			if (br != null)
				br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("name is : " + name +" , path is : " + PATH);
		System.out.println(sensorName + " - " + sb.toString().trim());
		return sb.toString();
	}

	// 실시간 센서값을 컨트롤러에게 넘겨준다(소방관이름,센서(g:0,p:1,s:2,t:3)
	public static String getSensorValues(String name, int sNum) {
		// 누구의 정보가 들어왔는가?
		int index = stateNumber.fNum(name);
		switch (sNum) {
		case 0:
			return stateNumber.f[index].getG();
		case 1:
			return stateNumber.f[index].getP();
		case 2:
			return stateNumber.f[index].getS();
		case 3:
			return stateNumber.f[index].getT();
		}
		return "n";
	}

	// 파일경로를 지정한다(소방관이름,센서이름,센서값)
	private void setPath(String name, String sensorName, String status) {
		// 누구의 정보가 들어왔는가?
		int index = fNum(name);
		switch (sensorName) {
		case "g":
			PATH = f[index].getGYRO();
			f[index].setG(status);
			break;
		case "p":
			PATH = f[index].getPULSE();
			f[index].setP(status);
			break;
		case "s":
			PATH = f[index].getSMOKE();
			f[index].setS(status);
			break;
		case "t":
			PATH = f[index].getTEMP();
			f[index].setT(status);
			break;
		}
	}
	// 파일경로를 지정한다(소방관이름,센서이름)
	private void setPath(String name, String sensorName) {
		// 누구의 정보가 들어왔는가?
		int index = fNum(name);
		switch (sensorName) {
		case "g":
			PATH = f[index].getGYRO();
			break;
		case "p":
			PATH = f[index].getPULSE();
			break;
		case "s":
			PATH = f[index].getSMOKE();
			break;
		case "t":
			PATH = f[index].getTEMP();
			break;
		}
	}

	private int fNum(String name) {
		// 누구의 정보가 들어왔는가?
		int index = 0;
		switch (name) {
		case "192.168.0.132":
			return 0;
		case "Watson":
			return 1;
		}
		return -1;
	}
}
