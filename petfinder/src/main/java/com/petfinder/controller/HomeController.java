package com.petfinder.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.petfinder.service.DisappearanceService;
import com.petfinder.service.FindsService;
import com.petfinder.vo.DisappearanceVO;
import com.petfinder.vo.FindsVO;
/**
 * 메인페이지를 처리하는 Controller 클래스
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
public class HomeController {
	
	@Resource(name = "findsService")
	private FindsService findsService;
	
    @Resource(name="disappearanceService")
    private DisappearanceService disappearanceService;

	@RequestMapping("/main.do")
	public ModelAndView main() {
		ModelAndView mv = new ModelAndView();
		
		List<DisappearanceVO> dlist = disappearanceService.disappearanceList();
		mv.addObject("dlist", dlist);
		
		List<FindsVO> flist = findsService.findsList();
		mv.addObject("flist", flist);
		
		mv.setViewName("/main");
		return mv;
	}
	
	@RequestMapping("/etc/about.do")
	public String about() {
		return "/etc/about";
	}

}
