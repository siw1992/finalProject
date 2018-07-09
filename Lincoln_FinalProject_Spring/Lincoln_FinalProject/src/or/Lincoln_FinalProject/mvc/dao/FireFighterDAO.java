package or.Lincoln_FinalProject.mvc.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import or.Lincoln_FinalProject.vo.FireFighterVO;


@Repository
public class FireFighterDAO {
	@Autowired
	private SqlSessionTemplate ss;
	
	public int isAlready(int fireFighterCode) {
		return ss.selectOne("FireFighter.search",fireFighterCode);
	}
	public void isReady(FireFighterVO fvo) {
		ss.insert("FireFighter.value",fvo);
	}
	
}
