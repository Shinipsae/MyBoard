package org.test.ex.memberboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.test.ex.memberboard.domain.MemberBoardDto;
import org.test.ex.memberboard.persistence.MemberBoardDao;

@Service
public class MemberBoardServiceImpl implements MemberBoardService {

	@Autowired
	private MemberBoardDao memberBoardDao;
	
	@Override
	public List<MemberBoardDto> list() {
		return 	memberBoardDao.selectAll();
	}

	@Override
	public boolean write(MemberBoardDto dto) {
		return memberBoardDao.insert(dto);
	}

	@Override
	public boolean update(MemberBoardDto dto) {
		MemberBoardDto tmp = memberBoardDao.selectBySeq(dto.getSeq());
		int password = -1;
		if(tmp == null) {
			return false;
		}
		password = tmp.getPassword();
		if(password != dto.getPassword()) {
			return false;
		}
		return memberBoardDao.update(dto);
	}

	@Override
	public MemberBoardDto selectOne(int seq) {
		MemberBoardDto dto = memberBoardDao.selectBySeq(seq);
		if(dto != null) {
			memberBoardDao.updateReadCount(seq);
			dto.setReadCnt(dto.getReadCnt()+1);
		}
		return dto;
	}

	@Override
	public boolean delete(int seq, int password) {
		MemberBoardDto dto = memberBoardDao.selectBySeq(seq);
		if(dto.getPassword() != password) {
			return false;
		}
		return memberBoardDao.delete(seq);
	}
	
}
