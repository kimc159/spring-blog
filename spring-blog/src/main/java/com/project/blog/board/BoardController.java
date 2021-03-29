package com.project.blog.board;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.blog.board.service.BoardService;
import com.project.blog.util.ScriptUtils;

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
		
		return "redirect:/board/list";
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
	public String modify(Model model, @RequestParam(value="seq") int seq, 
			@RequestParam(value="board_writer") String board_writer, HttpSession session, HttpServletResponse response) {
		
		// 게시글 작성자가 아니면 수정 불가
		if(!session.getAttribute("user_id").toString().equals(board_writer)) {
			try {
				ScriptUtils.alertAndMovePage(response, "수정 권한이 없습니다.", "/board/list");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		model.addAttribute("board", service.selectBoard(seq));
		
		return "board/modify";
	}

	@RequestMapping(value="board/modify", method=RequestMethod.POST)
	public String modify(BoardVO bo) {
		
		service.updateBoard(bo);
		
		return "redirect:/board/boardDetail?seq=" + bo.getSeq();
	}
	
	@RequestMapping(value="board/delete")
	public String delete(Model model, @RequestParam(value="seq") int seq,
			@RequestParam(value="board_writer") String board_writer, HttpSession session, HttpServletResponse response) {

		// 게시글 작성자가 아니면 삭제 불가
		if(!session.getAttribute("user_id").toString().equals(board_writer)) {
			try {
				ScriptUtils.alertAndMovePage(response, "삭제 권한이 없습니다.", "/board/list");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		service.deleteBoard(seq);
		
		model.addAttribute("msg", "삭제가 완료되었습니다.");
		model.addAttribute("url", "/board/list");
		
		return "common/redirect";
	}
	
	@RequestMapping(value="board/boardDetail")
	public String detail(Model model, @RequestParam(value="seq") int seq, @RequestParam(value="writer") String writer, HttpSession session) {
		
		model.addAttribute("board", service.selectBoard(seq));
		
		// 내 글인지 여부확인
		if(!session.getAttribute("user_id").toString().equals(writer)) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("hit_seq", seq);
			map.put("hit_writer", writer);
			map.put("hit_replyer", session.getAttribute("user_id").toString());
			
			int selectHit = service.selectHitSeq(map);
			
			System.out.println("hit : " + selectHit);
			
			// 조회수 중복 확인
			if(selectHit == 0) {
				service.hitUp(seq);
				
				service.boardHit(map);
				
			}
		}
		
		 
		return "board/boardDetail";
	}
	

}