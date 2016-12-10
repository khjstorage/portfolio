package com.petfinder.utill;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.petfinder.vo.MemberVO;

@Component("memberfileUtils")
public class MemberFileUtils {
	private static final String filePath = "C:\\dev\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\petfinder\\image\\memberfile\\";

	public Map<String,Object> parseInsertFileInfo(MemberVO memberVO, HttpServletRequest request) throws Exception{
		
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
		Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
		
		MultipartFile multipartFile = null;
		String originalFileName = null;
		String originalFileExtension = null;
		String storedFileName = null;
		
		Map<String, Object> mapFile = null;
		
		String m_Id = memberVO.getId();
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
				
				mapFile = new HashMap<String,Object>();
				mapFile.put("M_ID", m_Id);
				mapFile.put("M_ORIGINAL_FILE_NAME", originalFileName);
				mapFile.put("M_STORED_FILE_NAME", storedFileName);
				mapFile.put("M_FILE_SIZE", multipartFile.getSize());

			}else{
				
			}
		}
		return mapFile;
	}
}