package org.test.ex.memberboard.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.test.ex.freeboard.domain.FreeBoardDto;
import org.test.ex.memberboard.domain.MemberBoardDto;
import org.test.ex.memberboard.service.MemberBoardService;

@Controller
@RequestMapping(value = "/memberBoard")
public class MemberBoardController {

	@Autowired
	private MemberBoardService memberBoardService;
	
	@RequestMapping(value="/")
	public String main(Model model) {
		return list(model);
	}
	
	@RequestMapping(value="/list")
	public String list(Model model) {
		List<MemberBoardDto> result = memberBoardService.list();
		model.addAttribute("result", result);
		return "memberBoard/list";
	}
	
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public String write(Model model) {
		model.addAttribute("memberBoardDto", new MemberBoardDto());
		return "memberBoard/write";
	}
	
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String write(@Valid MemberBoardDto memberBoardDto, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "memberBoard/write";
		}
		boolean result = memberBoardService.write(memberBoardDto);
		return "redirect:/memberBoard/list";
	}
	
	@RequestMapping(value="/read/{seq}")
	public String read(Model model, @PathVariable int seq) {
		MemberBoardDto dto = memberBoardService.selectOne(seq);
		model.addAttribute("dto", dto);
		return "memberBoard/read";
	}
	
	@RequestMapping(value="/update/{seq}", method=RequestMethod.GET)
	public String update(Model model, @PathVariable int seq) {
		MemberBoardDto dto = memberBoardService.selectOne(seq);
		model.addAttribute("dto", dto);
		return "memberBoard/update";
	}
	
	@RequestMapping(value="/update/{seq}", method=RequestMethod.POST)
	public String update(Model model, @PathVariable int seq, MemberBoardDto memberBoardDto) {
		MemberBoardDto dto = memberBoardService.selectOne(seq);
		if(dto.getPassword() != memberBoardDto.getPassword()) {
			return "redirect:/memberBoard/update/"+seq;
		}
		dto.setTitle(memberBoardDto.getTitle());
		dto.setContent(memberBoardDto.getContent());
		memberBoardService.update(dto);
		model.addAttribute("dto", dto);
		return "memberBoard/read";
	}
	
	@RequestMapping(value="/delete/{seq}", method=RequestMethod.GET)
	public String delete(Model model, @PathVariable int seq) {
		model.addAttribute("seq", seq);
		return "memberBoard/delete";
	}
	
	@RequestMapping(value="/delete/{seq}", method=RequestMethod.POST)
	public String delete(@PathVariable int seq, int password) {
		boolean result = memberBoardService.delete(seq, password);
		if(!result) {
			return "redirect:/memberBoard/read/"+seq;
		}
		return "redirect:/memberBoard/list";
	}
	
}
