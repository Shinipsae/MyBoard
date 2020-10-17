package a.b.c.service;

import java.util.List;

import a.b.c.domain.BoardVO;
import a.b.c.persistance.BoardDao;

public interface BoardService {
	public List<BoardDao> boardList(){
		// 여기에 로직~
		return boardDao.list();
	}
}
