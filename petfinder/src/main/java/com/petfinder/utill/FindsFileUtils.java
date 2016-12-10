package com.petfinder.utill;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.petfinder.vo.FindsVO;


@Component("findsFileUtils")
public class FindsFileUtils {
	private static final String filePath = "C:\\dev\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\petfinder\\image\\findsfile\\";

	public Map<String,Object> parseInsertFileInfo(FindsVO findsVO, HttpServletRequest request) throws Exception{
		
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
		Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
		
		MultipartFile multipartFile = null;
		String originalFileName = null;
		String originalFileExtension = null;
		String storedFileName = null;
		
		Map<String, Object> mapFile = null;
		
		String boardidx = findsVO.getIdx();
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
				mapFile.put("F_BOARD_IDX", boardidx);
				mapFile.put("F_ORIGINAL_FILE_NAME", originalFileName);
				mapFile.put("F_STORED_FILE_NAME", storedFileName);
				mapFile.put("F_FILE_SIZE", multipartFile.getSize());

			}else{
				
			}
		}
		return mapFile;
	}
}