package org.test.ex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.test.ex.domain.BoardDto;
import org.test.ex.persistence.BoardDao;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardDao BoardDao;
	
	@Override
	public List<BoardDto> list() {
		return 	BoardDao.selectAll();
	}

	@Override
	public boolean write(BoardDto dto) {
		return BoardDao.insert(dto);
	}

	@Override
	public boolean update(BoardDto dto) {
		BoardDto tmp = BoardDao.selectBySeq(dto.getSeq());
		int password = -1;
		if(tmp == null) {
			return false;
		}
		password = tmp.getPassword();
		if(password != dto.getPassword()) {
			return false;
		}
		return BoardDao.update(dto);
	}

	@Override
	public BoardDto selectOne(int seq) {
		BoardDto dto = BoardDao.selectBySeq(seq);
		if(dto != null) {
			BoardDao.updateReadCount(seq);
			dto.setReadCnt(dto.getReadCnt()+1);
		}
		return dto;
	}

	@Override
	public boolean delete(int seq, int password) {
		BoardDto dto = BoardDao.selectBySeq(seq);
		if(dto.getPassword() != password) {
			return false;
		}
		return BoardDao.delete(seq);
	}

}
