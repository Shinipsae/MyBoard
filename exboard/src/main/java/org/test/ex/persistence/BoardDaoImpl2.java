package org.test.ex.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.jdbc.SQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.test.ex.domain.BoardDto;

@Repository
public class BoardDaoImpl2 implements BoardDao{
	
	@Autowired
	private JdbcTemplate jdbcTemplate = null;

	@Override
	public boolean insert(BoardDto dto) {
		int result = jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(
						"insert into \"BOARD\" (\"SEQ\", \"TITLE\", \"CONTENT\", \"WRITER\", \"PASSWORD\", \"REGDATE\", \"READCNT\")");
				pstmt.setString(1, dto.getTitle());
				pstmt.setString(2, dto.getContent());
				pstmt.setString(3, dto.getWriter());
				pstmt.setInt(4, dto.getPassword());
				return pstmt;
			}
		});

		return (result == 1) ? true : false;
	}

	@Override
	public boolean update(BoardDto dto) {
		int result = jdbcTemplate.update(
				"update \"BOARD\" set \"TITLE\"=?,\"CONTENT\"=?,\"REGDATE\"=sysdate where \"SEQ\"=?",
				dto.getTitle(), dto.getContent(), dto.getSeq());
		return (result == 1) ? true : false;
	}

	@Override
	public BoardDto selectBySeq(int seq) {
		BoardDto result = jdbcTemplate.queryForObject(
			"select \"SEQ\", \"TITLE\", \"CONTENT\", \"WRITER\", \"PASSWORD\", \"REGDATE\", \"READCNT\" from \"BOARD\" where \"SEQ\"=?", 
				new RowMapper<BoardDto>() {

					@Override
					public BoardDto mapRow(ResultSet rs, int rowNum) throws SQLException {
						BoardDto dto = new BoardDto(
								rs.getString(2),
								rs.getString(3),
								rs.getString(4),
								rs.getInt(5));
						return dto;
					}
			
		}, seq);
		return result;
	}

	@Override
	public boolean updateReadCount(int seq) {
		int result = jdbcTemplate.update("update \"BOARD\" set \"READCNT\"=\"READCNT\"+1 where \"SEQ\"=?", seq);
		return (result == 1) ? true : false;
	}

	@Override
	public boolean delete(int seq) {
		int result = jdbcTemplate.update("delete from \"BOARD\" where \"SEQ\"=?", seq);
		return (result == 1) ? true : false;
	}

	@Override
	public List<BoardDto> selectAll() {
		return null;
	}

}
