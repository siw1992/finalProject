package or.Lincoln_FinalProject.mvc.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import or.Lincoln_FinalProject.vo.MonitorsUserVO;


@Repository
public class UserDAO {

	@Autowired
	private SqlSessionTemplate ss;
	
    public int idMonitor(MonitorsUserVO mvo) {
        return ss.selectOne("sensors.value", mvo);
    }	
	
}
