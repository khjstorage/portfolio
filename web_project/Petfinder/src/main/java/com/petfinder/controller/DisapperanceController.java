package com.petfinder.controller;

import java.io.File;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.petfinder.service.DisappearanceService;
import com.petfinder.vo.DisappearanceVO;
import com.petfinder.vo.FindsVO;
import com.petfinder.vo.PagingVO;
/**
 * 분실게시판을 처리하는 Controller 클래스
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
@RequestMapping("/disappearance")
public class DisapperanceController {

    @Resource(name="disappearanceService")
    private DisappearanceService disappearanceService;
	    

	@RequestMapping("/list.do")
	public ModelAndView disappearanceList(@ModelAttribute("PagingVO") PagingVO pagingVO, 
										  @RequestParam(value = "pageNo", required = false) String pageNo) {
		ModelAndView mv = new ModelAndView();
		pagingVO.setPageSize(6); // 한 페이지에 보일 게시글 수
		pagingVO.setPageNo(1); // 현재 페이지 번호
		if(StringUtils.isNotEmpty(pageNo)){
			pagingVO.setPageNo(Integer.parseInt(pageNo));
		}
		pagingVO.setBlockSize(5); //블록사이즈
		pagingVO.setTotalCount(disappearanceService.postCount()); // 게시물 총 개수
		mv.addObject("paging", pagingVO);
		
		List<PagingVO> boardList = disappearanceService.getBoardList(pagingVO);
		mv.addObject("boardList", boardList);
		
		mv.setViewName("/disappearance/list");
		return mv;
	}
	

	@RequestMapping("/contents.do")
	public ModelAndView disappearanceContents(HttpSession session, @RequestParam("idx") String idx) throws Exception {
		String id = (String)session.getAttribute("id");
		String idcheck = disappearanceService.idCheck(idx);
		if(idcheck.equals(id)){
			session.setAttribute("idcheck", "permission");
		}
		ModelAndView mv = new ModelAndView();
		Map<String,Object> map = disappearanceService.selectBoardDetail(idx);
		mv.addObject("map", map.get("infoMap"));
		mv.addObject("file", map.get("fileMap"));;
		mv.setViewName("/disappearance/contents");
		return mv;
	}
			
	
	@RequestMapping("/write.do")
	public String disappearanceWrite(HttpSession session) {
		if(session.getAttribute("id")!=null){
			return "/disappearance/write";
		}else{
			return "redirect:/member/login.do";
		}
	}
		
	@RequestMapping("/create.do")
	public String disappearanceCreate(@ModelAttribute("disappearanceVO")DisappearanceVO disappearanceVO, HttpServletRequest request) throws Exception {
		disappearanceService.insertDisappearance(disappearanceVO, request);
		return "redirect:/disappearance/list.do";
	}

	@RequestMapping("/delete.do")
	public String disappearanceDelete(@RequestParam("idx") String idx) {
		disappearanceService.deleteDisappearance(idx);
		return "redirect:/disappearance/list.do";
	}
	

	@RequestMapping("/edit.do")
	public ModelAndView disappearanceEdit(HttpServletRequest request, @RequestParam("idx") String idx) throws Exception {
		ModelAndView mv = new ModelAndView("/disappearance/edit");
		Map<String,Object> map = disappearanceService.selectBoardDetail(idx);
		mv.addObject("map", map);
		String strReferer = request.getHeader("referer");
		if(strReferer == null){
			mv.setViewName("redirect:/disappearance/list.do");
		}
		return mv;
	}
	

	@RequestMapping("/update.do")
	public String disappearanceUpdate(@ModelAttribute("disappearanceVO")DisappearanceVO disappearanceVO, HttpServletRequest request) throws Exception {
		disappearanceService.updateDisappearance(disappearanceVO, request);
		return "redirect:/disappearance/list.do";
	}
	

	@RequestMapping("/match.do")
	public ModelAndView disappearanceMatch(@ModelAttribute("disappearanceVO")DisappearanceVO disappearanceVO) throws Exception {
		ModelAndView mv = new ModelAndView();
		List<FindsVO> boardList = disappearanceService.matchDisappearance(disappearanceVO);
		mv.addObject("boardList", boardList);
		mv.setViewName("/finds/list");
		return mv;
	}
	

	@RequestMapping("/download.do")
	public void disappearanceDownload(@RequestParam("idx") String idx, HttpServletResponse response) throws Exception{
		Map<String, Object> map = disappearanceService.selectFileInfo(idx);
	    String storedFileName = (String)map.get("D_STORED_FILE_NAME");
	    String originalFileName = (String)map.get("D_ORIGINAL_FILE_NAME");
	     
	    byte fileByte[] = FileUtils.readFileToByteArray(new File("C:\\dev\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\petfinder\\image\\disappearancefile\\"+storedFileName));
	     
	    response.setContentType("application/octet-stream");
	    response.setContentLength(fileByte.length);
	    response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(originalFileName,"UTF-8")+"\";");
	    response.setHeader("Content-Transfer-Encoding", "binary");
	    response.getOutputStream().write(fileByte);
	     
	    response.getOutputStream().flush();
	    response.getOutputStream().close();
	}
	

	@RequestMapping(value="/search.do", method=RequestMethod.GET)
	public ModelAndView disappearanceSearch(@RequestParam("keyWord_search") String keyWord_search,
											@RequestParam("selection_search") String selection_search,
											@ModelAttribute("PagingVO") PagingVO pagingVO,
											@RequestParam(value = "pageNo", required = false) String pageNo) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("keyWord_search", keyWord_search);
		map.put("selection_search", selection_search);

		pagingVO.setPageSize(6); // 한 페이지에 보일 게시글 수
		pagingVO.setPageNo(1); // 현재 페이지 번호
		if(StringUtils.isNotEmpty(pageNo)){
			pagingVO.setPageNo(Integer.parseInt(pageNo));
		}
		pagingVO.setBlockSize(5); //블록사이즈
		pagingVO.setTotalCount(disappearanceService.searchPostCount(map)); // 게시물 총 개수
		mv.addObject("paging", pagingVO);
		
		
		List<DisappearanceVO> boardList = disappearanceService.searchDisappearance(map, pagingVO);
		mv.addObject("boardList", boardList);
		
		mv.setViewName("/disappearance/list");
		return mv;
	}
	
}
