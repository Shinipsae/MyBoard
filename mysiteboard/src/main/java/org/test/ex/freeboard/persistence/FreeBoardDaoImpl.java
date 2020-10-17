package org.test.ex.freeboard.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.test.ex.BoardType;
import org.test.ex.freeboard.domain.FreeBoardDto;

@Repository
public class FreeBoardDaoImpl implements FreeBoardDao{

	@Autowired
	private JdbcTemplate jdbcTemplate = null;
	
	@Override
	public List<FreeBoardDto> selectAll() {
		List<FreeBoardDto> result = jdbcTemplate.query(
				"select \"SEQ\", \"TITLE\", \"CONTENT\", \"WRITER\", \"PASSWORD\", \"REGDATE\", \"READCNT\", \"BOARDTYPE\" from \"BOARD\" where \"BOARDTYPE\" = ? order by \"REGDATE\" desc", 
				new RowMapper<FreeBoardDto>() {

					@Override
					public FreeBoardDto mapRow(ResultSet rs, int rowNum) throws SQLException {
						FreeBoardDto dto = new FreeBoardDto(
								rs.getInt(1),
								rs.getString(2),
								rs.getString(3),
								rs.getString(4),
								rs.getInt(5),
								rs.getTimestamp(6),
								rs.getInt(7),
								rs.getInt(8));
						return dto;
					}
					
				}, BoardType.FREE_BOARD);
		//System.out.println(result.toString());
		return result;
	}

	@Override
	public boolean insert(FreeBoardDto dto) {
		int result = jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(
						"insert into \"BOARD\" (\"SEQ\", \"TITLE\", \"CONTENT\", \"WRITER\", \"PASSWORD\", \"REGDATE\", \"READCNT\", \"BOARDTYPE\")\r\n" + 
						"values (\"BOARD_SEQ\".nextval,?,?,?,?,sysdate,1,?)");
				pstmt.setString(1, dto.getTitle());
				pstmt.setString(2, dto.getContent());
				pstmt.setString(3, dto.getWriter());
				pstmt.setInt(4, dto.getPassword());
				pstmt.setInt(5, BoardType.FREE_BOARD);
				return pstmt;
			}
		});

		return (result == 1) ? true : false;
	}

	@Override
	public boolean update(FreeBoardDto dto) {
		int result = jdbcTemplate.update(
				"update \"BOARD\" set \"TITLE\"=?,\"CONTENT\"=?,\"REGDATE\"=sysdate where \"SEQ\"=?",
				dto.getTitle(), dto.getContent(), dto.getSeq());
		return (result == 1) ? true : false;
	}

	@Override
	public FreeBoardDto selectBySeq(int seq) {
		FreeBoardDto result = jdbcTemplate.queryForObject(
			"select \"SEQ\", \"TITLE\", \"CONTENT\", \"WRITER\", \"PASSWORD\", \"REGDATE\", \"READCNT\", \"BOARDTYPE\" from \"BOARD\" where \"SEQ\"=?", 
				new RowMapper<FreeBoardDto>() {

					@Override
					public FreeBoardDto mapRow(ResultSet rs, int rowNum) throws SQLException {
						FreeBoardDto dto = new FreeBoardDto(
								rs.getInt(1),
								rs.getString(2),
								rs.getString(3),
								rs.getString(4),
								rs.getInt(5),
								rs.getTimestamp(6),
								rs.getInt(7),
								rs.getInt(8));
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
	
	

}
