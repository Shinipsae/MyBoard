package org.test.ex.freeboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.test.ex.freeboard.domain.FreeBoardDto;
import org.test.ex.freeboard.service.FreeBoardService;

@Controller
@RequestMapping(value = "/freeBoard")
public class FreeBoardController {

	@Autowired
	private FreeBoardService freeBoardService;
	
	@RequestMapping(value="/")
	public String main(Model model) {
		return list(model);
	}
	
	@RequestMapping(value="/list")
	public String list(Model model) {
		List<FreeBoardDto> result = freeBoardService.list();
		model.addAttribute("result", result);
		return "freeBoard/list";
	}
	
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public String write(Model model) {
		model.addAttribute("freeBoardDto", new FreeBoardDto());
		return "freeBoard/write";
	}
	
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String write(FreeBoardDto freeBoardDto, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "freeBoard/write";
		}
		boolean result = freeBoardService.write(freeBoardDto);
		return "redirect:/freeBoard/list";
	}
	
	@RequestMapping(value="/read/{seq}")
	public String read(Model model, @PathVariable int seq) {
		FreeBoardDto dto = freeBoardService.selectOne(seq);
		model.addAttribute("dto", dto);
		return "freeBoard/read";
	}
	
	@RequestMapping(value="/update/{seq}", method=RequestMethod.GET)
	public String update(Model model, @PathVariable int seq) {
		FreeBoardDto dto = freeBoardService.selectOne(seq);
		model.addAttribute("dto", dto);
		return "freeBoard/update";
	}
	
	@RequestMapping(value="/update/{seq}", method=RequestMethod.POST)
	public String update(Model model, @PathVariable int seq, FreeBoardDto freeBoardDto) {
		FreeBoardDto dto = freeBoardService.selectOne(seq);
		if(dto.getPassword() != freeBoardDto.getPassword()) {
			return "redirect:/freeBoard/update/"+seq;
		}
		dto.setTitle(freeBoardDto.getTitle());
		dto.setContent(freeBoardDto.getContent());
		freeBoardService.update(dto);
		model.addAttribute("dto", dto);
		return "freeBoard/read";
	}
	
	@RequestMapping(value="/delete/{seq}", method=RequestMethod.GET)
	public String delete(Model model, @PathVariable int seq) {
		model.addAttribute("seq", seq);
		return "freeBoard/delete";
	}
	
	@RequestMapping(value="/delete/{seq}", method=RequestMethod.POST)
	public String delete(@PathVariable int seq, int password) {
		boolean result = freeBoardService.delete(seq, password);
		if(!result) {
			return "redirect:/freeBoard/read/"+seq;
		}
		return "redirect:/freeBoard/list";
	}
	
}
