package org.test.ex.persistence;

import java.util.List;

import org.test.ex.domain.BoardDto;

public interface BoardDao {
	public List<BoardDto> selectAll();
	public boolean insert(BoardDto dto);
	public boolean update(BoardDto dto);
	public BoardDto selectBySeq(int seq);
	public boolean updateReadCount(int seq);
	public boolean delete(int seq);
}
