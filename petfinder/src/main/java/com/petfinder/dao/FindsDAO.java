package com.petfinder.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.petfinder.vo.DisappearanceVO;
import com.petfinder.vo.FindsVO;
import com.petfinder.vo.PagingVO;
/**
 * 발견정보에 관한 데이터처리 DAO 클래스
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
@Repository("findsDAO")
public class FindsDAO {

	@Autowired
	private SqlSession sqlSession;
	
	public void insertFinds(FindsVO findsVO){
		sqlSession.insert("finds.insertFinds", findsVO);
	}

	public void insertFindsFile(Map<String,Object> map){
		sqlSession.insert("finds.insertFindsFile", map);
	}
	
	public List<FindsVO> findsList() {
		return sqlSession.selectList("finds.findsList");
	}
	
	public Map<String, Object> selectBoardDetail(String parameter){
		return sqlSession.selectOne("finds.selectBoardDetail", parameter);
	}

	public List<Object> selectBoardDetailFile(String parameter){
		return sqlSession.selectList("finds.selectBoardDetailFile", parameter);
	}
	
	public void deleteFinds(String idx) {
		sqlSession.delete("finds.deleteFinds", idx);
	}
	
	public void deleteFindsFile(String idx) {
		sqlSession.delete("finds.deleteFindsFile", idx);
	}

	public void updateFinds(FindsVO findsVO) {
		sqlSession.update("finds.updateFinds", findsVO);
	}

	public void updateFindsFile(Map<String, Object> mapFile) {
		sqlSession.update("finds.updateFindsFile", mapFile);
	}
	
	public List<DisappearanceVO> matchFinds(FindsVO findsVO){
		return sqlSession.selectList("disappearance.matchDisappearance", findsVO);
	}
	
	public Map<String, Object> selectFileInfo(String idx) throws Exception{
	    return sqlSession.selectOne("finds.selectFileInfo", idx);
	}

	public List<FindsVO> searchFinds(Map<String, Object> resultMap){
		return sqlSession.selectList("finds.searchFinds", resultMap);
	}
	
	public String passwordAuth(HashMap<String, String> map) {
		return sqlSession.selectOne("finds.passwordAuth", map);
	}

	public int postCount() {
		return sqlSession.selectOne("finds.postCount");
	}

	public List<PagingVO> getBoardList(PagingVO pagingVO) {
		return sqlSession.selectList("finds.getBoardList", pagingVO);
	}

	public int searchPostCount(HashMap<String, String> map) {
		return sqlSession.selectOne("finds.searchPostCount", map);
	}

}
