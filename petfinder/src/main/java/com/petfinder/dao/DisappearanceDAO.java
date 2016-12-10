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
 * 분실정보에 관한 데이터처리 DAO 클래스
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
@Repository("disappearanceDAO")
public class DisappearanceDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	public void insertDisappearance(DisappearanceVO disappearanceVO) {
		sqlSession.insert("disappearance.insertDisappearance", disappearanceVO);
	}

	public void insertDisappearanceFile(Map<String,Object> mapFile) {
		sqlSession.insert("disappearance.insertDisappearanceFile", mapFile);
	}

	public List<DisappearanceVO> disappearanceList(){
		return sqlSession.selectList("disappearance.disappearanceList");
	}

	public Map<String, Object> selectBoardDetail(String pIdx) {
		return sqlSession.selectOne("disappearance.selectBoardDetail", pIdx);
	}

	public List<Object> selectBoardDetailFile(String pIdx){
		return sqlSession.selectList("disappearance.selectBoardDetailFile", pIdx);
	}
	
	public void deleteDisappearanceFile(String pIdx) {
		sqlSession.delete("disappearance.deleteDisappearanceFile", pIdx);
	}

	public void deleteDisappearance(String pIdx) {
		sqlSession.delete("disappearance.deleteDisappearance", pIdx);
	}
	
	public void updateDisappearance(DisappearanceVO disappearanceVO) {
		sqlSession.update("disappearance.updateDisappearance", disappearanceVO);
	}

	public void updateDisappearanceFile(Map<String, Object> mapFile) {
		sqlSession.update("disappearance.updateDisappearanceFile", mapFile);
	}
	
	public List<FindsVO> matchDisappearance(DisappearanceVO disappearanceVO){
		return sqlSession.selectList("finds.matchFinds",disappearanceVO);
	}
	
	public Map<String, Object> selectFileInfo(String idx){
		return sqlSession.selectOne("disappearance.selectFileInfo", idx);
	}

	public String idCheck(String idx){
		return sqlSession.selectOne("disappearance.idcheckDisappearance", idx);
	}
	
	public List<DisappearanceVO> searchDisappearance(Map<String, Object> resultMap){
		return sqlSession.selectList("disappearance.searchDisappearance", resultMap);
	}
	
	
	public int postCount() {
		return sqlSession.selectOne("disappearance.postCount");
	}

	public List<PagingVO> getBoardList(PagingVO pagingVO) {
		return sqlSession.selectList("disappearance.getBoardList", pagingVO);
	}

	public int searchPostCount(HashMap<String, String> map) {
		return sqlSession.selectOne("disappearance.searchPostCount", map);
	}

}
