package com.petfinder.utill;

import java.io.File;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.petfinder.vo.DisappearanceVO;

@Component("disappearanceFileUtils")
public class DisappearanceFileUtils {
	private static final String filePath = "C:\\dev\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\petfinder\\image\\disappearancefile\\";
	public Map<String,Object> parseInsertFileInfo(DisappearanceVO disappearanceVO, HttpServletRequest request) throws Exception{
		
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
		Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
		
		MultipartFile multipartFile = null;
		String originalFileName = null;
		String originalFileExtension = null;
		String storedFileName = null;
		
		Map<String, Object> map = null;		
		String boardidx = disappearanceVO.getIdx();
		File file = new File(filePath);
		
		if(file.exists() == false){
			file.mkdirs();
		}

		while(iterator.hasNext()){
			multipartFile = multipartHttpServletRequest.getFile(iterator.next());
			if(multipartFile.isEmpty() == false){
				
				originalFileName = multipartFile.getOriginalFilename();
				originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
				storedFileName = CommonUtils.getRandomString() + originalFileExtension;
				
				file = new File(filePath + storedFileName);
				multipartFile.transferTo(file);
				
				map = new HashMap<String,Object>();
				map.put("D_BOARD_IDX", boardidx);
				map.put("D_ORIGINAL_FILE_NAME", originalFileName);
				map.put("D_STORED_FILE_NAME", storedFileName);
				map.put("D_FILE_SIZE", multipartFile.getSize());
			}else{
			}
		}
		return map;
	}
	
	public Map<String,Object> parseDownloadFileInfo(String idx, HttpServletResponse response) throws Exception{
		Map<String, Object> map = new HashMap<String,Object>();
		String storedFileName = (String)map.get("D_STORED_FILE_NAME");
	    String originalFileName = (String)map.get("D_ORIGINAL_FILE_NAME");
	     
	    byte fileByte[] = FileUtils.readFileToByteArray(new File("C:\\dev\\disappearancefile\\"+storedFileName));
	     
	    response.setContentType("application/octet-stream");
	    response.setContentLength(fileByte.length);
	    response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(originalFileName,"UTF-8")+"\";");
	    response.setHeader("Content-Transfer-Encoding", "binary");
	    response.getOutputStream().write(fileByte);
	     
	    response.getOutputStream().flush();
	    response.getOutputStream().close();
		return map;
	}
	
	
}