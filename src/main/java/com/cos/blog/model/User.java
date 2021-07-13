package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity //User 클래스가 MySQL 테이블에 생성된다.
// @DynamicInsert
public class User {

	@Id //primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB 넘버링 전략을 따라감 
	private int id; // 시퀀스,auto_increment 넘버링 전략
	
	@Column(nullable = false, length =30)
	private String username; //아이디
	
	@Column(nullable = false, length =100)
	private String password;
	
	@Column(nullable = false, length =50)
	private String email;
	
	//@ColumnDefault("'user'")
	@Enumerated(EnumType.STRING) //사용할곳에서User의 객체인user이고 user.setRole(RoleType.USER);
	private RoleType role; //admin,user
	
	@CreationTimestamp
	private Timestamp createDate;
}
