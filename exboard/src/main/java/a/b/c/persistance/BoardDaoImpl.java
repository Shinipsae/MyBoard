package a.b.c.persistance;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDaoImpl implements BoardDao{
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public List<BoardDao> list(){
		return sqlSessionTemplate.selectList("boardDao.list");
	}
}
