package org.test.ex.freeboard.service;

import java.util.List;

import org.test.ex.freeboard.domain.FreeBoardDto;

public interface FreeBoardService {
	public List<FreeBoardDto> list();
	public boolean write(FreeBoardDto dto);
	public boolean update(FreeBoardDto dto);
	public FreeBoardDto selectOne(int seq);
	public boolean delete(int seq, int password);
}
