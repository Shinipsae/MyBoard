package org.test.ex.freeboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.test.ex.freeboard.domain.FreeBoardDto;
import org.test.ex.freeboard.persistence.FreeBoardDao;

@Service
public class FreeBoardServiceImpl implements FreeBoardService {

	@Autowired
	private FreeBoardDao freeBoardDao;
	
	@Override
	public List<FreeBoardDto> list() {
		return 	freeBoardDao.selectAll();
	}

	@Override
	public boolean write(FreeBoardDto dto) {
		return freeBoardDao.insert(dto);
	}

	@Override
	public boolean update(FreeBoardDto dto) {
		FreeBoardDto tmp = freeBoardDao.selectBySeq(dto.getSeq());
		int password = -1;
		if(tmp == null) {
			return false;
		}
		password = tmp.getPassword();
		if(password != dto.getPassword()) {
			return false;
		}
		return freeBoardDao.update(dto);
	}

	@Override
	public FreeBoardDto selectOne(int seq) {
		FreeBoardDto dto = freeBoardDao.selectBySeq(seq);
		if(dto != null) {
			freeBoardDao.updateReadCount(seq);
			dto.setReadCnt(dto.getReadCnt()+1);
		}
		return dto;
	}

	@Override
	public boolean delete(int seq, int password) {
		FreeBoardDto dto = freeBoardDao.selectBySeq(seq);
		if(dto.getPassword() != password) {
			return false;
		}
		return freeBoardDao.delete(seq);
	}
	
}
