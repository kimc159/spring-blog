package com.project.chat.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.project.chat.board.BoardVO;
import com.project.chat.board.PageMaker;
import com.project.chat.board.SearchCriteria;
import com.project.chat.board.service.BoardService;
import com.project.chat.util.ScriptUtils;

@Controller
public class BoardController {

	public static final String FILE_SERVER_PATH = "C:\\mp\\file"; 
	
	@Autowired
	private BoardService service;
	
	@RequestMapping(value = "board/list") 
	public String list(Model model, @ModelAttribute("scri") SearchCriteria scri) {

		model.addAttribute("list", service.list(scri));

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(scri);
		pageMaker.setTotalCount(service.totalCount(scri)); 

		model.addAttribute("pageMaker", pageMaker);

		return "board/list";
	}

	/*
	 * @RequestMapping(value="board/list") public String list(Model
	 * model, @ModelAttribute("scri") SearchCriteria scri) {
	 * 
	 * model.addAttribute("list", service.list(scri));
	 * 
	 * PageMaker pageMaker = new PageMaker(); pageMaker.setCri(scri);
	 * pageMaker.setTotalCount(service.totalCount(scri));
	 * 
	 * model.addAttribute("pageMaker", pageMaker);
	 * 
	 * return "board/list"; }
	 */

	@RequestMapping(value = "board/write")
	public String write() {

		return "board/write";
	}

	@RequestMapping(value = "board/write", method = RequestMethod.POST)
	public String write(BoardVO bo) {

		service.write(bo);

		return "redirect:/board/list";
	}

	@RequestMapping(value = "board/replyForm")
	public String replyForm(Model model, BoardVO bo) {

		model.addAttribute("board", bo);

		return "board/reply";
	}

	@RequestMapping(value = "board/reply", method = RequestMethod.POST)
	public String reply(BoardVO bo) {

		service.replyUp(bo);
		service.reply(bo);

		return "redirect:/board/list";
	}

	@RequestMapping(value = "board/modify")
	public String modify(Model model, @RequestParam(value = "seq") int seq,
			@RequestParam(value = "board_writer") String board_writer, HttpSession session,
			HttpServletResponse response) {

		// �Խñ� �ۼ��ڰ� �ƴϸ� ���� �Ұ�
		if (!session.getAttribute("user_id").toString().equals(board_writer)) {
			try {
				ScriptUtils.alertAndMovePage(response, "접근할 수 없습니다.", "/board/list");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		model.addAttribute("board", service.selectBoard(seq));

		return "board/modify";
	}

	@RequestMapping(value = "board/modify", method = RequestMethod.POST)
	public String modify(BoardVO bo) {

		service.updateBoard(bo);

		return "redirect:/boardFile/boardDetail?seq=" + bo.getSeq() + "&writer=" + bo.getWriter();
	}

	@RequestMapping(value = "board/delete")
	public String delete(Model model, @RequestParam(value = "seq") int seq,
			@RequestParam(value = "board_writer") String board_writer, HttpSession session,
			HttpServletResponse response) {

		// �Խñ� �ۼ��ڰ� �ƴϸ� ���� �Ұ�
		if (!session.getAttribute("user_id").toString().equals(board_writer)) {
			try {
				ScriptUtils.alertAndMovePage(response, "접근할 수 없습니다.", "/board/list");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		service.deleteBoard(seq);

		model.addAttribute("msg", "삭제가 완료되었습니다.");
		model.addAttribute("url", "/board/list");

		return "common/redirect";
	}

	@RequestMapping(value = "board/boardDetail")
	public String detail(Model model, @RequestParam(value = "seq") int seq,
			@RequestParam(value = "writer") String writer, HttpSession session) {

		model.addAttribute("board", service.selectBoard(seq));

		// �� ������ ����Ȯ��
		if (!session.getAttribute("user_id").toString().equals(writer)) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("hit_seq", seq);
			map.put("hit_writer", writer);
			map.put("hit_replyer", session.getAttribute("user_id").toString());

			int selectHit = service.selectHitSeq(map);

			System.out.println("hit : " + selectHit);

			// ��ȸ�� �ߺ� Ȯ��
			if (selectHit == 0) {
				service.hitUp(seq);

				service.boardHit(map);

			}
		}

		return "board/boardDetail";
	}

	/*
	 * 파일 업로드 게시판
	 * 
	 */

	@RequestMapping(value = "boardFile/list")
	public String fileList(Model model, @ModelAttribute(value = "scri") SearchCriteria scri) {
		List<BoardVO> list = service.fileList(scri);

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(scri);
		pageMaker.setTotalCount(service.totalFileCount(scri));

		model.addAttribute("pageMaker", pageMaker);
		model.addAttribute("list", list);

		return "boardFile/list";
	}

	@RequestMapping(value = "boardFile/write", method = RequestMethod.GET)
	public String fileWrite() {

		return "boardFile/write";
	}

	@RequestMapping(value = "boardFile/write", method = RequestMethod.POST)
	public String fileWrite(BoardVO boardVO, MultipartHttpServletRequest mpRequest) throws Exception {

		service.fileWrite(boardVO, mpRequest);

		return "redirect: /boardFile/list";
	}

	@RequestMapping(value = "boardFile/boardDetail")
	public String boardFileDetail(Model model, @RequestParam(value = "seq") int seq,
			@RequestParam(value = "writer") String writer, HttpSession session) {

		model.addAttribute("board", service.selectBoardFile(seq));

		if (!session.getAttribute("user_id").equals(writer)) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("hit_seq", seq);
			map.put("hit_writer", writer);
			map.put("hit_replyer", session.getAttribute("user_id"));

			int result = service.selectHitSeq2(map);

			if (result == 0) {
				service.boardHit(map);
				service.hitUp2(seq);
			}
		}

		return "boardFile/boardDetail";
	}

	@RequestMapping(value = "boardFile/replyForm")
	public String replyForm2(Model model, BoardVO bo) {

		model.addAttribute("board", bo);

		return "boardFile/reply";
	}

	@RequestMapping(value = "boardFile/reply", method = RequestMethod.POST)
	public String reply2(Model model, BoardVO bo) {

		service.replyUp2(bo);
		service.reply2(bo);

		return "redirect: /boardFile/list";
	}

	@RequestMapping(value = "boardFile/modify")
	public String modify2(Model model, BoardVO bo, HttpSession session) {
		String writer = bo.getWriter();
		int seq = bo.getSeq();
		BoardVO board = service.selectBoard2(seq);

		if (!session.getAttribute("user_id").toString().equals(writer)) {

			model.addAttribute("url", "/boardFile/list");
			model.addAttribute("msg", "접근할 수 없습니다.");

			return "common/redirect";
		} else {
			model.addAttribute("board", board);
		}

		return "boardFile/modify";
	}

	@RequestMapping(value = "boardFile/modify", method = RequestMethod.POST)
	public String modify2(BoardVO bo, MultipartHttpServletRequest mpRequest) throws Exception {
		service.updateBoard2(bo, mpRequest);

		return "redirect: /boardFile/boardDetail?seq=" + bo.getSeq() + "&writer=" + bo.getWriter();
	}

	@RequestMapping(value = "boardFile/delete")
	public String deleteBoard2(@RequestParam("seq") int seq, @RequestParam("writer") String writer,
			@RequestParam("boardGroup") int boardGroup, HttpServletResponse response, HttpSession session) {

		if (!session.getAttribute("user_id").toString().equals(writer)) {

			try {
				ScriptUtils.alertAndMovePage(response, "접근할 수 없습니다.", "/boardFile/list");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {

			service.deleteBoardFile(seq);
		}

		return "redirect: /boardFile/list";
	}

	@RequestMapping(value = "boardFile/download")
	public ModelAndView download(@RequestParam HashMap<Object, Object> params, ModelAndView mv) {

		String fileName = (String) params.get("stored_file_name");
		String org_file_name = (String) params.get("org_file_name");
		String fullPath = FILE_SERVER_PATH + "/" + fileName;
		File file = new File(fullPath);

		mv.setViewName("downloadView");
		mv.addObject("downloadFile", file);
		mv.addObject("org_file_name", org_file_name);

		return mv;
	}

}