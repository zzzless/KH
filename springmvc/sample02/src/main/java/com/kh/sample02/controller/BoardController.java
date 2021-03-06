package com.kh.sample02.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.sample02.service.BoardService;
import com.kh.sample02.vo.BoardVo;
import com.kh.sample02.vo.LikeVo;
import com.kh.sample02.vo.MemberVo;
import com.kh.sample02.vo.PagingDto;

@Controller
@RequestMapping(value="/board")
public class BoardController {
	
	@Inject
	private BoardService boardService;
	
	@RequestMapping(value = "/listAll")
	public String listAll(PagingDto pagingDto, Model model) throws Exception {
		int count = boardService.getCount(pagingDto);
		pagingDto.setCount(count);
		System.out.println(pagingDto);
		List<BoardVo> list = boardService.listAll(pagingDto);
		//System.out.println(list);
		model.addAttribute("list", list);
		return "board/listAll";
	}
	
	@RequestMapping(value = "/writeForm", method = RequestMethod.GET)
	public String writeForm() throws Exception {
		return "board/writeForm";
	}
	
	@RequestMapping(value = "/writeRun", method = RequestMethod.POST)
	public String writeRun(BoardVo boardVo, RedirectAttributes rttr, HttpSession session) throws Exception{
		System.out.println("작성될 글 VO:"+boardVo);
		
		MemberVo memberVo = (MemberVo)session.getAttribute("loginVo");
		boardVo.setUser_id(memberVo.getUser_id());
		System.out.println("ID삽입된 VO:"+boardVo);
		boardService.writeRun(boardVo);
		// => 여기서 도중에 에러가 난다면 아래 코드로 넘어가지 않는다.
		rttr.addFlashAttribute("resultWrite", "success");
		return "redirect:/board/listAll";
	}
	
	@RequestMapping(value = "/content", method = RequestMethod.GET)
	public String content(@ModelAttribute("pagingDto") PagingDto pagingDto,int b_no, HttpSession session,Model model) throws Exception {
		//System.out.println(b_no);
		// @ModelAttribute 파라미터로 넘어온값을 view로 바로 전달하고 싶을때 사용한다.
		// 하지만 없어도 잘되는거 보니 자동으로 해주기도 하는듯 하다.
		BoardVo boardVo = boardService.content(b_no);
		MemberVo memberVo = (MemberVo)session.getAttribute("loginVo");
		String user_id = memberVo.getUser_id();
		LikeVo likeVo = new LikeVo(b_no, user_id);
		
		LikeVo resultLikeVo = boardService.checkLike(likeVo);
		model.addAttribute("boardVo", boardVo);
		model.addAttribute("resultLikeVo", resultLikeVo);
		//model.addAttribute("paingDto", pagingDto);
		return "board/content";
	}
	
	@RequestMapping(value = "/modifyRun", method = RequestMethod.POST)
	public String modifyRun(BoardVo boardVo, RedirectAttributes rttr) throws Exception {
		boardService.modifyRun(boardVo);
		rttr.addFlashAttribute("modifyResult", "success");
		return "redirect:/board/content?b_no="+boardVo.getB_no();
	}
	
	@RequestMapping(value = "/RemoveRun", method = RequestMethod.GET)
	public String RemoveRun(int b_no, RedirectAttributes rttr) throws Exception {
		boardService.removeRun(b_no);
		rttr.addFlashAttribute("removeResult", "success");
		return "redirect:/board/listAll";
	}
	
	@ResponseBody
	@RequestMapping(value = "/updateLike", method = RequestMethod.GET)
	public int updateLike(int b_no, HttpSession session, Model model) throws Exception {
		MemberVo loginVo = (MemberVo)session.getAttribute("loginVo");
		String user_id = loginVo.getUser_id();
		LikeVo likeVo = new LikeVo(b_no, user_id);
		System.out.println("likeVo: "+likeVo);
		LikeVo resultLikeVo = boardService.checkLike(likeVo);
		model.addAttribute("resultLikeVo", resultLikeVo);
		return boardService.updateLikeCount(likeVo);
	}
}
