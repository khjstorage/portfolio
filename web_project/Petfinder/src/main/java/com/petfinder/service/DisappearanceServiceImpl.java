package com.petfinder.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.petfinder.dao.DisappearanceDAO;
import com.petfinder.utill.DisappearanceFileUtils;
import com.petfinder.vo.DisappearanceVO;
import com.petfinder.vo.FindsVO;
import com.petfinder.vo.PagingVO;
/**
 * 분실정보 CRUD 요청을 처리하는 비즈니스 클래스
 * 
 * @author  김현진
 * @since 2016.11.14
 * @version 1.0
 * @see <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   2016.11.14        김현진             최초 생성
 * 
 * </pre>
 */
@Service("disappearanceService")
public class DisappearanceServiceImpl implements DisappearanceService {
	
    @Resource(name="disappearanceFileUtils")
    private DisappearanceFileUtils disappearanceFileUtils;
	
	@Resource(name="disappearanceDAO")
	private DisappearanceDAO disappearanceDAO;

	@Override
	public List<DisappearanceVO> disappearanceList() {
		return disappearanceDAO.disappearanceList();
	}

	@Override
	public Map<String, Object> selectBoardDetail(String pIdx)throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>(); 
		
		Map<String, Object> map = disappearanceDAO.selectBoardDetail(pIdx);
		resultMap.put("infoMap", map);
		
		List<Object> fileMap = disappearanceDAO.selectBoardDetailFile(pIdx);
		resultMap.put("fileMap", fileMap);
	    
	    return resultMap;
	}
	
	@Override
	public void insertDisappearance(DisappearanceVO disappearanceVO, HttpServletRequest request) throws Exception {
		disappearanceDAO.insertDisappearance(disappearanceVO);
		Map<String,Object> mapFile = disappearanceFileUtils.parseInsertFileInfo(disappearanceVO, request);
		if(mapFile!=null){
			disappearanceDAO.insertDisappearanceFile(mapFile);
		}
		
 	        	             
	}

	@Override
	public void updateDisappearance(DisappearanceVO disappearanceVO, HttpServletRequest request) throws Exception {
		disappearanceDAO.updateDisappearance(disappearanceVO);
        Map<String,Object> mapFile = disappearanceFileUtils.parseInsertFileInfo(disappearanceVO, request);
        if(mapFile!=null){
        	disappearanceDAO.updateDisappearanceFile(mapFile);
        }
	}

	@Override
	public void deleteDisappearance(String pIdx) {
		disappearanceDAO.deleteDisappearanceFile(pIdx);
		disappearanceDAO.deleteDisappearance(pIdx);
	}
	
	@Override
	public List<FindsVO> matchDisappearance(DisappearanceVO disappearanceVO){
		return disappearanceDAO.matchDisappearance(disappearanceVO);
	}


	@Override
	public Map<String, Object> selectFileInfo(String idx) throws Exception{
		return disappearanceDAO.selectFileInfo(idx);
	}
	

	@Override
	public String idCheck(String idx){
		return disappearanceDAO.idCheck(idx);
	}

	@Override
	public List<DisappearanceVO> searchDisappearance(HashMap<String, String> map, PagingVO pagingVO){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("map", map);
		resultMap.put("pagingVO", pagingVO);
		return disappearanceDAO.searchDisappearance(resultMap);
	}

	@Override
	public int postCount() {
		return disappearanceDAO.postCount();
	}

	@Override
	public List<PagingVO> getBoardList(PagingVO pagingVO) {
		return disappearanceDAO.getBoardList(pagingVO);
	}

	@Override
	public int searchPostCount(HashMap<String, String> map) {
		return disappearanceDAO.searchPostCount(map);
	}

}
