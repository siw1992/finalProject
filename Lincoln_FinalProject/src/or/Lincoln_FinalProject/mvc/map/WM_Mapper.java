package or.Lincoln_FinalProject.mvc.map;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import or.Lincoln_FinalProject.vo.SensorsVO;

@Mapper
public interface WM_Mapper {
	@Select("Select * from sensors ")
	public List<SensorsVO> getMemoList();
	
}
