package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@RestController //html이 아니라 data를 리턴해줌
public class DummyControllerTest {

	@Autowired //의존성 주입(DI)
	private UserRepository userRepository;
	
	//save 함수는 id를 전달하지 않으면 insert를 해주고 
	//save 함수는 id를 전달하면 해당 id에 대한 데이터가 있으면 update 해주고
	//save 함수는 id를 전달하면 해당 id에 대한 데이터가 없으면 insert를 함
	//email,password
	//http://localhost:8000/blog/dummy/user/1  1은 id
	@Transactional
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id,@RequestBody User requestUser) {
		System.out.println("id:"+id);
		System.out.println("password:"+requestUser.getPassword());
		System.out.println("email:"+requestUser.getEmail());
		
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정에 실패");
		});
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
	
		// userRepository.save(user);
		
		// 더티 체킹 
		return null;
	}
	
	
	//http://localhost:8000/blog/dummy/users
	@GetMapping("/dummy/users")
	private List<User> list(){
		return userRepository.findAll();
	}
	
	//http://localhost:8000/blog/dummy/user?page=0 (0,1,2...)
	//http://localhost:8000/blog/dummy/user
	// 한페이지당 2건에 데이터를 리턴받아 볼 예정
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size=2,sort="id",direction = Sort.Direction.DESC) Pageable pageable){
	  Page<User> paginguser=userRepository.findAll(pageable);
	  
	  List<User> users = paginguser.getContent(); 
		return users;
	}
	
	
	//{id}주소로 파라메터를 전달 받을 수 있음
	//http:localhost:8000/blog/dummy/user/3
	@GetMapping("dummy/user/{id}")
	public User detail(@PathVariable int id ) {
		//user/4을 찾으면내가 데이터베이스에서 못찾으면 user가 null이 잖아?
		//그럼 null이 리턴되니 프로그램에 문제가 있지 않겠니
		//그래서 Optional로 너의Usere 객체를 감싸서 가져올테니 null인지 아닌지 판단해서 return해
		
		//람다식
	    //	User user = userRepository.findById(id).orElseThrow(()->{
		//	return new IllegalArgumentException("수정에 실패");
		//});
		
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
		@Override
		public IllegalArgumentException get() {
			return new IllegalArgumentException("해당 유저는 없다.");
		}
		});
		// 요청 : 웹브라우저 
		// user 객체 = java 오브젝트
		// 변환 (웹브라우저가 이해할 수 있는 데이터-> json
		// 스프링부트 = MessageConverter라는 애가 응답시에 자동 작동
		// 만약에 자바오브젝트를 리턴하게되면 MessageConverter가 Jackson 라이브러리를 호출해서
		// user 오브젝트를 json으로 변환해서 브라우저에게 던져줌
		return user;
	}
	
	//http://localhost:8000/blog/dummy/join(요청)
	//http의 body에 username,password, email 데이터를 가지고 (요청)
	@PostMapping("/dummy/join")
	public String join(User user)
	{
		System.out.println("ID:"+user.getId());
		System.out.println("username:"+user.getUsername());
		System.out.println("password:"+user.getPassword());
		System.out.println("email:"+user.getEmail());
		System.out.println("role:"+user.getRole());
		System.out.println("createDate:"+user.getCreateDate());
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "완료";
	}
}
