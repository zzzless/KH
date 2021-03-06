package com.kh.sample02.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kh.sample02.service.CommentService;
import com.kh.sample02.vo.CommentVo;
import com.kh.sample02.vo.MemberVo;


// 모든 메서드에 @ResponseBody 가 붙어있다.
// 비동기 요청에 대한 전용 컨트롤러 => jsp의 경로를 리턴하는 것이 아닌 , 데이터를 리턴한다.
// 응답 데이터가 String 타입인 경우에도 jsp로 포워드 하는 것이 아니고,
// String 데이터를 응답한다.
@RestController
@RequestMapping("/comment")
public class CommentController {
	
	@Inject
	private CommentService commentService;
	
	// 댓글 목록
	@RequestMapping(value="/getCommentList/{b_no}", method=RequestMethod.GET)
	public List<CommentVo> getCommentList(@PathVariable("b_no") int b_no) throws Exception {
		/*List<CommentVo> list = new ArrayList<>();
		for(int i=1; i<=10; i++) {
			CommentVo vo = new CommentVo(i, b_no, "test", "댓글"+i, null);
			list.add(vo);
		}*/
		List<CommentVo> list = commentService.getCommentList(b_no);
		//System.out.println(list);
		return list;
	}
	
	// 댓글 쓰기 - c_content, b_no, user_id
	// 비동기 방식으로 들어오는 요청데이터를 받기 위해선 @RequestBody 를 써야한다.
	@RequestMapping(value="/insertComment", method=RequestMethod.POST)
	public String insertComment(@RequestBody CommentVo commentVo, HttpSession session) throws Exception {
		//System.out.println(commentVo);
		MemberVo memberVo = (MemberVo)session.getAttribute("loginVo");
		commentVo.setUser_id(memberVo.getUser_id());
		commentService.insertComment(commentVo);
		return "success";
	}
	
	@RequestMapping(value="/updateComment", method=RequestMethod.POST)
	public String updateComment(@RequestBody CommentVo commentVo) throws Exception{
		System.out.println(commentVo);
		commentService.updateComment(commentVo);
		return "success";
	}
	
	@RequestMapping(value="/deleteComment/{c_no}/{b_no}", method=RequestMethod.GET)
	public String commentDelete(@PathVariable("c_no") int c_no,
								@PathVariable("b_no") int b_no) throws Exception {
		System.out.println("[Del_cno] : "+c_no);
		System.out.println("[Del_bno] : "+b_no);
		commentService.deleteComment(c_no, b_no);
		
		return "success";	
	}
	
	/* 이런 식으로 해도 된다.
	@RequestMapping(value="/deleteComment", method=RequestMethod.GET)
	public String commentDelete2(int c_no) throws Exception {
		System.out.println("[##Del_cno] : "+c_no);
		//commentService.deleteComment(c_no);
		
		return "success";	
	}
	*/
}
