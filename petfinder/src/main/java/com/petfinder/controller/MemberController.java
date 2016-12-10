package com.petfinder.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.petfinder.service.MemberService;
import com.petfinder.vo.MemberVO;
/**
 * 회원관리를 처리하는 Controller 클래스
 * 
 * @author  김현진
 * @since 2016.11.14
 * @version 1.0
 * @see 
 * <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   2016.11.14        김현진             최초 생성
 * 
 * </pre>
 */
@Controller
@RequestMapping("/member")
public class MemberController {

	@Resource(name="memberService")
	private MemberService memberService;


	@RequestMapping("/register.do")
	public String register() {
		return "/member/register";
	}
	
	@RequestMapping("/signup")
	public String signup(@ModelAttribute("memberVO")MemberVO memberVO, HttpServletRequest request) throws Exception{
		memberService.insertMember(memberVO, request);
		return "redirect:/main.do";
	}

	@RequestMapping("/login.do")
	public String login() {
		return "/member/login";
	}

	@RequestMapping("/loginProcess.do")
	public ModelAndView loginProcess(@RequestParam("id") String id, @RequestParam("pwd") String pwd, HttpSession session) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("pwd", pwd);
		if(memberService.loginMember(map).size()==1){
			session.setAttribute("id",id);
			return new ModelAndView("redirect:/main.do");
		}else{
			session.setAttribute("idfail",id);
			return new ModelAndView("redirect:/member/login.do");
		}
	}
	
	@RequestMapping("/mypage.do")
	public ModelAndView mypage(HttpSession session) throws Exception {
		ModelAndView mv = new ModelAndView();
		List<MemberVO> list = memberService.getMember((String) session.getAttribute("id"));
		mv.addObject("memberlist", list);
		mv.setViewName("/member/mypage");
		return mv;
	}

	@RequestMapping("/logout.do")
	public String logout(HttpSession session){
		session.removeAttribute("id");
		session.invalidate();
		return "redirect:/main.do";
	}

	@RequestMapping("/delete.do")
	public String delete(HttpSession session){
		String id = (String) session.getAttribute("id");
		memberService.deleteMember(id);
		session.removeAttribute("id");
		session.invalidate();
		return "redirect:/main.do";
	}

	@RequestMapping("/update.do")
	public String update(@ModelAttribute("memberVO")MemberVO memberVO, HttpServletRequest request) throws Exception{
		memberService.updateMember(memberVO, request);
		return "redirect:/main.do";
	}
	
	@RequestMapping("/duplication.do")
	@ResponseBody
	public int duplication(HttpServletRequest request){
		String duplicationId = request.getParameter("id");
		List<MemberVO> list = memberService.duplication(duplicationId);
		if(list.size()==0){
			return -1;
		}else{
			return 1;
		}
	}

}
