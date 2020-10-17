package org.test.ex.memberboard.service;

import java.util.List;

import org.test.ex.memberboard.domain.MemberBoardDto;

public interface MemberBoardService {
	public List<MemberBoardDto> list();
	public boolean write(MemberBoardDto dto);
	public boolean update(MemberBoardDto dto);
	public MemberBoardDto selectOne(int seq);
	public boolean delete(int seq, int password);
}
