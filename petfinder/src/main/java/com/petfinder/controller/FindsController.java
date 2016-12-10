package com.petfinder.controller;

import java.io.File;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.petfinder.service.FindsService;
import com.petfinder.vo.DisappearanceVO;
import com.petfinder.vo.FindsVO;
import com.petfinder.vo.PagingVO;
/**
 * 실종게시판을 처리하는 Controller 클래스
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
@RequestMapping("/finds")
public class FindsController {

	@Resource(name = "findsService")
	private FindsService findsService;


	@RequestMapping("/list.do")
	public ModelAndView findsList(@ModelAttribute("PagingVO") PagingVO pagingVO, 
								  @RequestParam(value = "pageNo", required = false) String pageNo){
		ModelAndView mv = new ModelAndView("/finds/list");
		pagingVO.setPageSize(6); // 한 페이지에 보일 게시글 수
		pagingVO.setPageNo(1); // 현재 페이지 번호
		if(StringUtils.isNotEmpty(pageNo)){
			pagingVO.setPageNo(Integer.parseInt(pageNo));
		}
		pagingVO.setBlockSize(5); //블록사이즈
		pagingVO.setTotalCount(findsService.postCount()); // 게시물 총 개수
		mv.addObject("paging", pagingVO);
		
		List<PagingVO> boardList = findsService.getBoardList(pagingVO);
		mv.addObject("boardList", boardList);
		
		return mv;
	}


	@RequestMapping("/write.do")
	public String finds_form() {
		return "/finds/write";
	}


	@RequestMapping("/create.do")
	public String findsCreate(@ModelAttribute("findsVO") FindsVO findsVO, HttpServletRequest request) throws Exception {
		findsService.insertFinds(findsVO, request);
		return "redirect:/finds/list.do";
	}


	@RequestMapping("/contents.do")
	public ModelAndView findsContents(HttpServletRequest request, @RequestParam("idx") String idx) throws Exception {
		ModelAndView mv = new ModelAndView();
		Map<String, Object> map = findsService.selectBoardDetail(idx);
		mv.addObject("map", map.get("infoMap"));
		mv.addObject("file", map.get("fileMap"));
		mv.setViewName("/finds/contents");
		return mv;
	}


	@RequestMapping("/delete_auth.do")
	public String findsDeleteAuth(@RequestParam("pwd") String pwd, @RequestParam("idx") String idx){
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("pwd", pwd);
		map.put("idx", idx);
		String postPwd = findsService.passwordAuth(map);
		if(pwd.equals(postPwd)){
			return "redirect:/finds/delete.do?idx="+idx;
		}else{
			return "redirect:/finds/contents.do?idx="+idx;
		}
	}


	@RequestMapping("/delete.do")
	public String findsDelete(HttpServletRequest request, @RequestParam("idx") String idx){
		String strReferer = request.getHeader("referer");
		if(strReferer != null){
			findsService.deleteFinds(idx);
		}
		return "redirect:/finds/list.do";
	}


	@RequestMapping("/update_auth.do")
	public String findsUpdateAuth(HttpServletRequest request, @RequestParam("pwd") String pwd, @RequestParam("idx") String idx){
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("pwd", pwd);
		map.put("idx", idx);
		String postPwd = findsService.passwordAuth(map);
		if(pwd.equals(postPwd)){
			return "redirect:/finds/edit.do?idx="+idx;
		}else{
			return "redirect:/finds/contents.do?idx="+idx;
		}
	}


	@RequestMapping("/edit.do")
	public ModelAndView findsEdit(HttpServletRequest request,  @RequestParam("idx") String idx) throws Exception{
		ModelAndView mv = new ModelAndView("/finds/edit");
		Map<String, Object> map = findsService.selectBoardDetail(idx);
		mv.addObject("map", map);
		String strReferer = request.getHeader("referer");
		if(strReferer == null){
			mv.setViewName("redirect:/finds/list.do");
		}
		return mv;
	}


	@RequestMapping("/update.do")
	public String findsUpdate(@ModelAttribute("findsVO")FindsVO findsVO, HttpServletRequest request) throws Exception {
		findsService.updateFinds(findsVO, request);
		return "redirect:/finds/list.do";
	}


	@RequestMapping("/download.do")
	public void findsDownload(@RequestParam("idx") String idx, HttpServletResponse response) throws Exception{
		Map<String, Object> map = findsService.selectFileInfo(idx);
		String storedFileName = (String)map.get("F_STORED_FILE_NAME");
		String originalFileName = (String)map.get("F_ORIGINAL_FILE_NAME");

		byte fileByte[] = FileUtils.readFileToByteArray(new File("C:\\dev\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\petfinder\\image\\findsfile\\"+storedFileName));

		response.setContentType("application/octet-stream");
		response.setContentLength(fileByte.length);
		response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(originalFileName,"UTF-8")+"\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.getOutputStream().write(fileByte);

		response.getOutputStream().flush();
		response.getOutputStream().close();
	}


	@RequestMapping("/match.do")
	public ModelAndView findsMatch(@ModelAttribute("findsVO")FindsVO findsVO) throws Exception{
		ModelAndView mv = new ModelAndView();
		List<DisappearanceVO> boardList = findsService.matchFinds(findsVO);
		mv.addObject("boardList", boardList);
		mv.setViewName("/disappearance/list");
		return mv;
	}


	@RequestMapping(value="/search.do", method=RequestMethod.GET)
	public ModelAndView findsSearch(@RequestParam("keyWord_search") String keyWord_search,	
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
		
		pagingVO.setTotalCount(findsService.searchPostCount(map)); // 게시물 총 개수
		mv.addObject("paging", pagingVO);
		
		List<FindsVO> boardList = findsService.searchFinds(map, pagingVO);
		mv.addObject("boardList", boardList);
		
		mv.setViewName("/finds/list");
		return mv;
	}

}
