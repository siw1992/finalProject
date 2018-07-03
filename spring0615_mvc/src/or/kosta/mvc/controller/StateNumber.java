package or.kosta.mvc.controller;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class StateNumber {
	   private static StateNumber stateNumber;
	   private static final String PATH = "\\\\192.168.0.132\\raspberrypi\\test.txt";
	   
	   public synchronized static StateNumber getStateNumber() {
	      if(stateNumber == null) stateNumber = new StateNumber();
	      return stateNumber;
	   }
	   
	   public void save(String status) {
	      try(DataOutputStream dos = new DataOutputStream(new FileOutputStream(PATH))) {
	         dos.writeInt(Integer.parseInt(status));
	    	 //dos.writeBytes(status);
	         System.out.println("저장된 상태값 : "+status);
	      }catch(Exception e) {
	         
	      }
	   }
	   
	   public String view() {
	      String status="";   
	      try(DataInputStream dis = new DataInputStream(new FileInputStream(PATH))){
	         //status = String.valueOf(dis.readInt());
	    	  //StringBuilder sb = new StringBuilder();
	    	  status = dis.readLine();
	      }catch(Exception e) {
	         
	      }
	      return status;
	   }
	}
