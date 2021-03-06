package com.cos.blog.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.Board;
import com.cos.blog.model.Reply;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.BoardService;
import com.cos.blog.service.UserService;

@RestController
public class BoardApiController {

	@Autowired
	private BoardService boardService;
	
	@PostMapping("/api/board")
	public ResponseDto<Integer> save(@RequestBody Board board,@AuthenticationPrincipal PrincipalDetail principal ) {
		boardService.글쓰기(board,principal.getUser());
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	@DeleteMapping("/api/board/{id}")
	public ResponseDto<Integer> deleteById(@PathVariable int id){
		boardService.글삭제하기(id);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	@PutMapping("/api/board/{id}")
	public ResponseDto<Integer> update(@PathVariable int id,@RequestBody Board board){
	 boardService.글수정하기(id,board);
	 return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	/*
	// 다음시간에 스프링 시큐리티 이용해서 로그인
	@PostMapping("/api/user/login")
	public ResponseDto<Integer> login(@RequestBody User user,HttpSession session){
		System.out.println("UserApiController : login 호출됨");
		User principal = userService.로그인(user);//principal (접근주체)
		
		if(principal != null) {
			session.setAttribute("principal", principal);
		}
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	*/
	
	//데이터 받을 때 컨트롤러에서 dto를 만들어서 받는게 좋음
	//dto 사용하지 않은 이유는 프로젝트 규모가 작기때문
	@PostMapping("/api/board/{boardid}/reply")
	public ResponseDto<Integer> replySave(@PathVariable int boardid,@RequestBody Reply reply,@AuthenticationPrincipal PrincipalDetail principal ) {
		
	   
		boardService.댓글쓰기(principal.getUser(),boardid,reply);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	@DeleteMapping("/api/board/{boardid}/reply/{replyid}")
	public ResponseDto<Integer> replyDelete(@PathVariable int replyid) {
		boardService.댓글삭제(replyid);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
}
