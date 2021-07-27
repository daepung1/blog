package com.cos.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.User;

//DAO
//자동 bean 등록된다
//@Repositiory 생략가능
public interface UserRepository extends JpaRepository<User,Integer> {
    //SELECT * FROM user WHERE username = 1?;
	Optional<User> findByUsername(String username);
}



//JPA Naming 쿼리 전략
//SELECT * FROM user WHERE username = ? AND password =? ; 이런 쿼리가 동작

//	User findByUsernameAndPassword(String username,String password);

/*
 * 이렇게도 가능
@Query(value="SELECT * FROM user WHERE username = ? AND password =?",nativeQuery=true)
User login(String username,String password);
*/