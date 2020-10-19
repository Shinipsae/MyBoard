package org.test.ex.service;

import java.util.List;

import org.test.ex.domain.BoardDto;

public interface BoardService {
	public List<BoardDto> list();
	public boolean write(BoardDto dto);
	public boolean update(BoardDto dto);
	public BoardDto selectOne(int seq);
	public boolean delete(int seq, int password);
}
