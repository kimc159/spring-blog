package com.project.blog.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.blog.board.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	@RequestMapping(value="board/list")
	public String list(Model model, @ModelAttribute("scri") SearchCriteria scri) {
		
		model.addAttribute("list", service.list(scri));
		
		PageMaker pageMaker = new PageMaker(); 
		pageMaker.setCri(scri);
		pageMaker.setTotalCount(service.totalCount(scri));
		
		model.addAttribute("pageMaker", pageMaker);
		
		return "board/list";
	}

	@RequestMapping(value="board/write")
	public String write() {
		
		return "board/write";
	}
	
	@RequestMapping(value="board/write", method=RequestMethod.POST)
	public String write(BoardVO bo) {
		
		service.write(bo);
		
		return "redirect:/list";
	}

	@RequestMapping(value="board/replyForm")
	public String replyForm(Model model, BoardVO bo) {
		
		model.addAttribute("board", bo);
		
		return "board/reply";
	}
	
	@RequestMapping(value="board/reply", method=RequestMethod.POST)
	public String reply(BoardVO bo) {
		 
		service.replyUp(bo);
		service.reply(bo); 
		
		return "redirect:/board/list"; 
	}
	
	@RequestMapping(value="board/modify")
	public String modify(Model model, @RequestParam(value="seq") int seq) {
		
		model.addAttribute("board", service.selectBoard(seq));
		
		return "board/modify";
	}

	@RequestMapping(value="board/modify", method=RequestMethod.POST)
	public String modify(BoardVO bo) {
		
		service.updateBoard(bo);
		
		return "redirect: /boardDetail?seq=" + bo.getSeq();
	}
	
	@RequestMapping(value="board/boardDetail")
	public String detail(Model model, @RequestParam(value="seq") int seq) {
		
		model.addAttribute("board", service.selectBoard(seq));
		
		service.countUp(seq);  
		 
		return "board/boardDetail";
	}
	

	@RequestMapping(value="board/delete")
	public String detail(@RequestParam(value="seq") int seq) {
		
		service.deleteBoard(seq);
		 
		return "redirect: /board/list";
	}
}