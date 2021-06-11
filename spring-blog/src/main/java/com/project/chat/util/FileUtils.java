package com.project.chat.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.project.chat.board.BoardVO;

@Component("fileUtils")
public class FileUtils {
	private static final String filePath = "C:\\mp\\file\\"; // 파일이 저장될 위치
	
	public List<Map<String, Object>> parseInsertFileInfo(BoardVO boardVO, 
		MultipartHttpServletRequest mpRequest) throws Exception{
		
		
		Iterator<String> iterator = mpRequest.getFileNames();
		
		MultipartFile multipartFile = null;
		String originalFileName = null;
		String originalFileExtension = null;
		String storedFileName = null;
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Map<String, Object> listMap = null;
		 
		int seq = boardVO.getSeq();
		
		File file = new File(filePath);
		if(file.exists() == false) {
			file.mkdirs();
		}
		
		while(iterator.hasNext()) {
			multipartFile = mpRequest.getFile(iterator.next());
			if(multipartFile.isEmpty() == false) {
				originalFileName = multipartFile.getOriginalFilename();
				originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
				storedFileName = getRandomString() + originalFileExtension; 

				file = new File(filePath + storedFileName);
				multipartFile.transferTo(file);
				listMap = new HashMap<String, Object>(); 
				listMap.put("seq", seq); 
				listMap.put("org_file_name", originalFileName);
				listMap.put("stored_file_name", storedFileName);
				listMap.put("file_size",  multipartFile.getSize());
				list.add(listMap);
			}
		}
		return list;
	}
	public void fileDelete(String fileNames) {
		
			File file = new File(filePath + fileNames); 
			if(file.exists()){ 
				if(file.delete()){ 
					System.out.println("파일삭제 성공"); 
				}else{ 
					System.out.println("파일삭제 실패"); 
				} 
			} else { 
				System.out.println("파일이 존재하지 않습니다."); 
			}
	}
	
	public static String getRandomString() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}