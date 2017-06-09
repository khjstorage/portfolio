package com.petfinder.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.petfinder.dao.FindsDAO;
import com.petfinder.utill.FindsFileUtils;
import com.petfinder.vo.DisappearanceVO;
import com.petfinder.vo.FindsVO;
import com.petfinder.vo.PagingVO;

/**
 * 발견정보 CRUD 요청을 처리하는 비즈니스 클래스
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
@Service("findsService")
public class FindsServiceImpl implements FindsService{
	
	@Resource(name="findsFileUtils")
	private FindsFileUtils findsFileUtils;
	
	@Resource(name="findsDAO")
	private FindsDAO findsDAO;

	@Override
	public List<FindsVO> findsList() {
		return findsDAO.findsList();
	}
	
	@Override
	public Map<String, Object> selectBoardDetail(String parameter) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>(); 
		
		Map<String, Object> map = findsDAO.selectBoardDetail(parameter);
		resultMap.put("infoMap", map);
		
		List<Object> fileMap = findsDAO.selectBoardDetailFile(parameter);
		resultMap.put("fileMap", fileMap);
		
		return resultMap;
	}
	
	
	@Override
	public int postCount() {
		return findsDAO.postCount();
	}

	@Override
	public List<PagingVO> getBoardList(PagingVO pagingVO) {
		return findsDAO.getBoardList(pagingVO);
	}
	
	@Override
	public void insertFinds(FindsVO findsVO, HttpServletRequest request) throws Exception {
		findsDAO.insertFinds(findsVO);
		Map<String,Object> mapFile = findsFileUtils.parseInsertFileInfo(findsVO, request);
		if(mapFile != null){
			findsDAO.insertFindsFile(mapFile);
		}
	}

	@Override
	public void updateFinds(FindsVO findsVO, HttpServletRequest request) throws Exception {
		findsDAO.updateFinds(findsVO);
		Map<String, Object> mapFile = findsFileUtils.parseInsertFileInfo(findsVO, request);
		if(mapFile != null){
			findsDAO.updateFindsFile(mapFile);
		}
	}
	
	@Override
	public void deleteFinds(String idx) {
		findsDAO.deleteFindsFile(idx);
		findsDAO.deleteFinds(idx);
	}
	
	@Override
	public List<DisappearanceVO> matchFinds(FindsVO findsVO) throws Exception{
		return findsDAO.matchFinds(findsVO);
	}

	@Override
	public Map<String, Object> selectFileInfo(String idx) throws Exception {
	    return findsDAO.selectFileInfo(idx);
	}

	@Override
	public List<FindsVO> searchFinds(HashMap<String, String> map, PagingVO pagingVO){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("map", map);
		resultMap.put("pagingVO", pagingVO);
		return findsDAO.searchFinds(resultMap);
	}
	
	@Override
	public String passwordAuth(HashMap<String, String> map) {
		return findsDAO.passwordAuth(map);
	}

	@Override
	public int searchPostCount(HashMap<String, String> map) {
		return findsDAO.searchPostCount(map);
	}

}
