package com.petfinder.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.petfinder.dao.MemberDAO;
import com.petfinder.utill.MemberFileUtils;
import com.petfinder.vo.MemberVO;
/**
 * �쉶�썝�젙蹂� CRUD �슂泥��쓣 泥섎━�븯�뒗 鍮꾩쫰�땲�뒪 �겢�옒�뒪
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
@Service("memberService")
public class MemberServiceImpl implements MemberService {

	@Resource(name="memberfileUtils")
	private MemberFileUtils memberfileUtils;

	@Resource(name="memberDao")
	private MemberDAO memberDao;

	@Override
	public List<MemberVO> loginMember(HashMap<String, String> map){
		return memberDao.loginMember(map);
	}

	@Override
	public List<MemberVO> getMember(String sessionId) throws Exception {
		return memberDao.getMember(sessionId);
	}
	
	@Override
	public void insertMember(MemberVO memberVO, HttpServletRequest request) throws Exception {
		memberDao.insertMember(memberVO);
		Map<String,Object> mapFile = memberfileUtils.parseInsertFileInfo(memberVO, request);
		if(mapFile!=null){
			memberDao.insertFile(mapFile);
		}
	}

	@Override
	public void updateMember(MemberVO memberVO, HttpServletRequest request) throws Exception{
		memberDao.updateMember(memberVO);
		Map<String,Object> mapFile = memberfileUtils.parseInsertFileInfo(memberVO, request);
		if(mapFile!=null){
			memberDao.updateMemberFile(mapFile);
		}
	}

	@Override
	public void deleteMember(String id){
		memberDao.deleteMemberFile(id);
		memberDao.deleteMember(id);
	}

	@Override
	public List<MemberVO> duplication(String duplicationId) {
		return memberDao.duplication(duplicationId);
	}

}
