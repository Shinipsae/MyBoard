package a.b.c.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import a.b.c.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value="/board/list")
	@ResponseBody // 결과 문자열을 그대로 브라우저에 응답 (REST API기반 활용시)
	public String list() {
		return boardService.boardList().toString();
	}
}
