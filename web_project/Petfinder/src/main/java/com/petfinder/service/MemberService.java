package com.petfinder.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.petfinder.vo.MemberVO;
/**
 * �쉶�썝�젙蹂� CRUD �슂泥��쓣 泥섎━�븯�뒗 鍮꾩쫰�땲�뒪 �씤�꽣�럹�씠�뒪
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
public interface MemberService {
	
	public List<MemberVO> getMember(String sessionId) throws Exception;

	public List<MemberVO> loginMember(HashMap<String, String> map);
	
	public void insertMember(MemberVO memberVO, HttpServletRequest request) throws Exception;
	
	public void updateMember(MemberVO memberVO, HttpServletRequest requesr) throws Exception;
	
	public void deleteMember(String id);
	
	public List<MemberVO> duplication(String duplicationId);


}


