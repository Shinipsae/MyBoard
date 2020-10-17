package org.test.ex.memberboard.persistence;

import java.util.List;

import org.test.ex.memberboard.domain.MemberBoardDto;


public interface MemberBoardDao {
	public List<MemberBoardDto> selectAll();
	public boolean insert(MemberBoardDto dto);
	public boolean update(MemberBoardDto dto);
	public MemberBoardDto selectBySeq(int seq);
	public boolean updateReadCount(int seq);
	public boolean delete(int seq);
}
