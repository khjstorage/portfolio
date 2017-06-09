package com.petfinder.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.petfinder.vo.MemberVO;
/**
 * 회원정보에 관한 데이터처리 DAO 클래스
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
@Repository("memberDao")
public class MemberDAO {

	@Autowired
	private SqlSession sqlSession;


	public void insertMember(MemberVO memberVO) {
		sqlSession.insert("member.insertmember", memberVO);
	}

	public void insertFile(Map<String,Object> mapFile) {
		sqlSession.insert("member.insertfile", mapFile);
	}

	public List<MemberVO> getMember(String sessionId) {
		return sqlSession.selectList("member.getmember", sessionId);
	}

	public List<MemberVO> loginMember(HashMap<String, String> map){
		return sqlSession.selectList("member.loginmember", map);
	}

	public void deleteMember(String id){
		sqlSession.delete("member.deletemember", id);
	}

	public void deleteMemberFile(String id){
		sqlSession.delete("member.deletememberfile",id);
	}

	public void updateMember(MemberVO memberVO){
		sqlSession.update("member.updateMember", memberVO);
	}

	public void updateMemberFile(Map<String, Object> mapFile){
		sqlSession.update("member.updateMemberFile", mapFile);
	}

	public List<MemberVO> duplication(String duplicationId){
		return sqlSession.selectList("member.duplication", duplicationId);
	}

}
