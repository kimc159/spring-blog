package com.project.chat.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

public class DownloadView extends AbstractView {
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// download file 가져오기
		File file = (File)model.get("downloadFile");
		String org_file_name = (String) model.get("org_file_name");
		
        if(file != null) {
        	
            String fileName = null;
            String userAgent = request.getHeader("User-Agent");
            
            if(userAgent.indexOf("MSIE") > -1 || userAgent.indexOf("Trident") > -1){ // ie인지 확인
                fileName = URLEncoder.encode(org_file_name, "utf-8").replaceAll("\\+", "%20"); // 한글파일명 깨지지 않도록
            }else if(userAgent.indexOf("Chrome") > -1) { // 크롬인지 확인
            	StringBuffer sb = new StringBuffer();
            	for(int i=0; i<org_file_name.length(); i++) {
            		char c = org_file_name.charAt(i);
            		if(c > '~') {
            			sb.append(URLEncoder.encode(""+c, "UTF-8"));
            		}else {
            			sb.append(c);
            		}
            	}
            	fileName = sb.toString();
            }else {
            	fileName = new String(file.getName().getBytes("utf-8"));
            }
            response.setContentType(getContentType());
            response.setContentLength((int)file.length());
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";"); // 해당 파일이 첨부파일임을 명시해줘야됨
            response.setHeader("Content-Transfer-Encoding", "binary");
            
            OutputStream out = response.getOutputStream();
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file);
                FileCopyUtils.copy(fis, out); // 를 사용하면 파일 자체를 웹브라우저에서 읽어들인다.
            } catch(Exception e){
                e.printStackTrace();
            }finally{
                if(fis != null){
                    try{
                        fis.close();
                    }catch(Exception e){
                    	e.printStackTrace();
                    }
                }
                
                if(out != null) {
                	out.flush();
                }
            }
            
        }
	}
}
