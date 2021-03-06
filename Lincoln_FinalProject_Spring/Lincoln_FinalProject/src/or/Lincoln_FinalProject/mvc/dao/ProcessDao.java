package or.Lincoln_FinalProject.mvc.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import or.Lincoln_FinalProject.vo.EmergencyVO;
import or.Lincoln_FinalProject.vo.EventVO;
import or.Lincoln_FinalProject.vo.SensorsFighterCodeVO;
import or.Lincoln_FinalProject.vo.SensorsVO;

@Repository
public class ProcessDao {
	@Autowired
	SqlSessionTemplate ss;
	
	public void eventInsert(EventVO evvo) {
		ss.insert("process.eventInsert",evvo);
	}
	
	public void emergencyInsert(EmergencyVO emvo) {
		ss.insert("process.emergencyInsert",emvo);
	}
	
	public void sensorsInsert(SensorsFighterCodeVO sfvo) {
		ss.insert("process.sensorsInsert",sfvo);
	}
}
