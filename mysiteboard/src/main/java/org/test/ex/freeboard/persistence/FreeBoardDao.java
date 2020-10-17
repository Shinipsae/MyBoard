package org.test.ex.freeboard.persistence;

import java.util.List;

import org.test.ex.freeboard.domain.FreeBoardDto;


public interface FreeBoardDao {
	public List<FreeBoardDto> selectAll();
	public boolean insert(FreeBoardDto dto);
	public boolean update(FreeBoardDto dto);
	public FreeBoardDto selectBySeq(int seq);
	public boolean updateReadCount(int seq);
	public boolean delete(int seq);
}
