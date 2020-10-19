package org.test.ex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.test.ex.domain.BoardDto;
import org.test.ex.service.BoardService;

@Controller
@RequestMapping(value = "/board")
public class BoardController {

	@Autowired
	private BoardService BoardService;
	
	@RequestMapping(value="/")
	public String main(Model model) {
		return list(model);
	}
	
	@RequestMapping(value="/list")
	public String list(Model model) {
		List<BoardDto> result = BoardService.list();
		model.addAttribute("result", result);
		return "board/list";
	}
	
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public String write(Model model) {
		model.addAttribute("BoardDto", new BoardDto());
		return "board/write";
	}
	
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String write(BoardDto BoardDto, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "board/write";
		}
		boolean result = BoardService.write(BoardDto);
		return "redirect:/board/list";
	}
	
	@RequestMapping(value="/read/{seq}")
	public String read(Model model, @PathVariable int seq) {
		BoardDto dto = BoardService.selectOne(seq);
		model.addAttribute("dto", dto);
		return "board/read";
	}
	
	@RequestMapping(value="/update/{seq}", method=RequestMethod.GET)
	public String update(Model model, @PathVariable int seq) {
		BoardDto dto = BoardService.selectOne(seq);
		model.addAttribute("dto", dto);
		return "board/update";
	}
	
	@RequestMapping(value="/update/{seq}", method=RequestMethod.POST)
	public String update(Model model, @PathVariable int seq, BoardDto BoardDto) {
		BoardDto dto = BoardService.selectOne(seq);
		if(dto.getPassword() != BoardDto.getPassword()) {
			return "redirect:/board/update/"+seq;
		}
		dto.setTitle(BoardDto.getTitle());
		dto.setContent(BoardDto.getContent());
		BoardService.update(dto);
		model.addAttribute("dto", dto);
		return "board/read";
	}
	
	@RequestMapping(value="/delete/{seq}", method=RequestMethod.GET)
	public String delete(Model model, @PathVariable int seq) {
		model.addAttribute("seq", seq);
		return "board/delete";
	}
	
	@RequestMapping(value="/delete/{seq}", method=RequestMethod.POST)
	public String delete(@PathVariable int seq, int password) {
		boolean result = BoardService.delete(seq, password);
		if(!result) {
			return "redirect:/board/read/"+seq;
		}
		return "redirect:/board/list";
	}
}
