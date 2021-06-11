
package com.project.chat.board.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.project.chat.board.BoardVO;
import com.project.chat.board.SearchCriteria;
import com.project.chat.board.dao.BoardDao;
import com.project.chat.util.FileUtils;

@Service
public class BoardService implements BoardServiceImpl {
	
	@Autowired
	private BoardDao dao;
	
	@Resource(name="fileUtils")
	private FileUtils fileUtils;
	
	@Override
	public List<BoardVO> list(SearchCriteria scri) {
		return dao.list(scri);
	}
	
	@Override
	public int write(BoardVO bo) {
		// TODO Auto-generated method stub
		return dao.write(bo); 
	}
	
	@Override
	public int reply(BoardVO bo) {
		return dao.reply(bo);
	}
	
	@Override
	public int replyUp(BoardVO bo) {
		return dao.replyUp(bo);
	}
	
	@Override
	public BoardVO selectBoard(int seq) {
		return dao.selectBoard(seq);
	}
	@Override
	public int updateBoard(BoardVO bo) {
		return dao.updateBoard(bo);
	}
	@Override
	public int deleteBoard(int seq) {
		return dao.deleteBoard(seq);
	}
	
	@Override
	public int totalCount(SearchCriteria scri) {
		return dao.totalCount(scri);
	}
	@Override
	public int hitUp(int seq) {
		return dao.hitUp(seq);
	}
	@Override
	public int boardHit(Map<String, Object> map) {
		return dao.boardHit(map);
	}
	@Override
	public int selectHitSeq(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.selectHitSeq(map);
	}
	@Override
	public int hitUp2(int seq) {
		return dao.hitUp2(seq);
	}
	@Override
	public int selectHitSeq2(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.selectHitSeq2(map);
	}
	@Override
	public List<BoardVO> fileList(SearchCriteria scri) {
		return dao.fileList(scri);
	}
	
	@Override
	public void fileWrite(BoardVO boardVO, MultipartHttpServletRequest mpRequest) throws Exception {
		
		dao.fileWrite(boardVO);
		
		 List<Map<String, Object>> list = fileUtils.parseInsertFileInfo(boardVO, mpRequest); 
		 int size = list.size();
		 
		 for(int i=0; i<size; i++) { dao.insertFile(list.get(i)); }
		 
	}
	@Override
	public int totalFileCount(SearchCriteria scri) {
		return dao.totalFileCount(scri);
	}
	
	@Override
	public BoardVO selectBoardFile(int seq) {
		return dao.selectBoardFile(seq);
	}
	@Override
	public int reply2(BoardVO bo) {
		return dao.reply2(bo);
	}
	@Override
	public int replyUp2(BoardVO bo) {
		return dao.replyUp2(bo);
	}
	@Override
	public BoardVO selectBoard2(int seq) {
		return dao.selectBoard2(seq);
	}
	@Override
	public void updateBoard2(BoardVO bo, MultipartHttpServletRequest mpRequest) throws Exception{
		
		dao.updateBoard2(bo);

		BoardVO boardFile = dao.selectFile(bo.getSeq());
		
		dao.deleteBoardFile(bo.getSeq()); // 디비 파일 데이터 삭제
		 
		// 실제 파일 삭제
		if(boardFile != null) {
			fileUtils.fileDelete(boardFile.getStored_file_name()); 
		}
		
		 List<Map<String, Object>> list = fileUtils.parseInsertFileInfo(bo, mpRequest); 
		 int size = list.size();
		 
		 for(int i=0; i<size; i++) { dao.insertFile(list.get(i)); }
		  
	}
	
	@Override
	public void deleteBoardFile(int seq) {
 
		BoardVO board = dao.selectBoard2(seq);
		BoardVO boardFile = dao.selectFile(seq);
		
		dao.deleteBoardFile(seq); // 디비 파일 데이터 삭제
		dao.deleteBoard2(seq); // 디비 게시글 삭제
		 
		if(board.getBoardSeq() == 0) { // 부모 글 일경우 댓글 모두 삭제
			deleteBoardComment(board.getBoardGroup()); 
		}
		// 실제 파일 삭제
		if(boardFile != null) {
			
			fileUtils.fileDelete(boardFile.getStored_file_name()); 
		}
	}
	
	@Override
	public int deleteBoardFileAll(int boardGroup) {
		
		return dao.deleteBoardFileAll(boardGroup);
	}
	@Override
	public int deleteBoardComment(int boardGroup) {
		return dao.deleteBoardComment(boardGroup);
	}
//	public void boardFileWrite(MultipartHttpServletRequest mpRequest) {
//		// TODO Auto-generated method stub
//		return dao.boardFileWrite();
//	}
}
