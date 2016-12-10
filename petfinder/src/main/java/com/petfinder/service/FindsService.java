package com.petfinder.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.petfinder.vo.DisappearanceVO;
import com.petfinder.vo.FindsVO;
import com.petfinder.vo.PagingVO;
/**
 * 발견정보 CRUD 요청을 처리하는 비즈니스 인터페이스
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
public interface FindsService {

	public List<FindsVO> findsList();
	
	public Map<String, Object> selectBoardDetail(String parameter) throws Exception;
	
	public void insertFinds(FindsVO findsVO, HttpServletRequest request) throws Exception;
	
	public void updateFinds(FindsVO findsVO, HttpServletRequest request) throws Exception;
	
	public void deleteFinds(String idx);
	
	public List<DisappearanceVO> matchFinds(FindsVO findsVO) throws Exception;
	
	public Map<String, Object> selectFileInfo(String idx) throws Exception;

	public List<FindsVO> searchFinds(HashMap<String, String> map, PagingVO pagingVO);
	
	public String passwordAuth(HashMap<String, String> map);

	public int postCount();

	public List<PagingVO> getBoardList(PagingVO pagingVO);

	public int searchPostCount(HashMap<String, String> map);

}
