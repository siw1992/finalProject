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
import java.io.PrintWriter;

public class StateNumber {
	private static StateNumber stateNumber;
	private static String PATH;
	private static final String GYRO = "C:\\kosta182\\spring\\workspace\\spring0615_mvc\\logs\\gyro.txt";
	private static final String PULSE = "C:\\kosta182\\spring\\workspace\\spring0615_mvc\\logs\\pulse.txt";
	private static final String SMOKE = "C:\\kosta182\\spring\\workspace\\spring0615_mvc\\logs\\smoke.txt";
	private static final String TEMP = "C:\\kosta182\\spring\\workspace\\spring0615_mvc\\logs\\temp.txt";

	public synchronized static StateNumber getStateNumber() {
		if (stateNumber == null)
			stateNumber = new StateNumber();
		return stateNumber;
	}

	public void save(String sensorName, String status) {
		switch (sensorName) {
		case "g":
			PATH = GYRO;
			break;
		case "p":
			PATH = PULSE;
			break;
		case "s":
			PATH = SMOKE;
			break;
		case "t":
			PATH = TEMP;
			break;
		}
		// 한줄한줄, 이어서 저장한다
		BufferedWriter bw = null;
		PrintWriter pw = null;
		try {
			bw = new BufferedWriter(new FileWriter(PATH, true));
			pw = new PrintWriter(bw, true);
			pw.write(status + "\n");
			if (bw != null)
				bw.close();
			if (pw != null)
				pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String view() {
		String status = "";
		StringBuffer sb = new StringBuffer();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(PATH));
			while ((status = br.readLine()) != null)
				sb.append(status).append("\n");
			if (br != null)
				br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
}
