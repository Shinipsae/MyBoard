package org.test.ex.memberboard.domain;

import java.sql.Timestamp;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

@Component
public class MemberBoardDto {
	private int seq;
	
	@NotEmpty(message = "제목을 작성해야 합니다.")
	private String title;
	
	@NotEmpty(message = "내용을 작성해야 합니다.")
	private String content;
	
	@NotEmpty(message = "작성자를 작성해야 합니다.")
	private String writer;
	
	private int password;
	private Timestamp regDate;
	private int readCnt;
	private int boardType;
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public int getPassword() {
		return password;
	}
	public void setPassword(int password) {
		this.password = password;
	}
	public Timestamp getRegDate() {
		return regDate;
	}
	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}
	public int getReadCnt() {
		return readCnt;
	}
	public void setReadCnt(int readCnt) {
		this.readCnt = readCnt;
	}
	public int getBoardType() {
		return boardType;
	}
	public void setBoardType(int boardType) {
		this.boardType = boardType;
	}
	public MemberBoardDto(int seq, String title, String content, String writer, int password, Timestamp regDate,
			int readCnt, int boardType) {
		this.seq = seq;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.password = password;
		this.regDate = regDate;
		this.readCnt = readCnt;
		this.boardType = boardType;
	}
	public MemberBoardDto() {	}
	
	@Override
	public String toString() {
		return "FreeBoardDto [seq=" + seq + ", title=" + title + ", content=" + content + ", writer=" + writer
				+ ", password=" + password + ", regDate=" + regDate + ", readCnt=" + readCnt + ", boardType="
				+ boardType + "]";
	}
	
}
