package a.b.c.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import a.b.c.domain.BoardVO;
import a.b.c.persistance.BoardDao;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDao boardDao;
	
	@Override
	public List<BoardVO> boardList() {
		// 여기에 로직 ~
		return boardDao.list();
	}
	
}
