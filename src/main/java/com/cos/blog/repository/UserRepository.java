package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.User;

//DAO
//자동 bean 등록된다
//@Repositiory 생략가능
public interface UserRepository extends JpaRepository<User,Integer> {

}
