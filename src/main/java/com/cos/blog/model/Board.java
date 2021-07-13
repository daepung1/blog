package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
public class Board {

	@Id //primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length =100)
	private String title;
	
	@Lob
	private String content;
	
	@ColumnDefault("0")
	private int count; //조회수
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="userId")
	private User user; //db는 오브젝트 저장안됌 자바는 오브젝트저장함 =>충돌남 => orm 사용해서 해결
	
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER) //mappedBy 는 연관관계의 주인이 아니다 fk가 아니다 컬럼 만들지마라
	private List<Reply> reply;
	
	@CreationTimestamp
	private Timestamp createDate;
}
