package com.example.sbp.question;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.example.sbp.answer.Answer;
import com.example.sbp.user.SiteUser;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Question {
	
	@ManyToMany
	Set<SiteUser> voter;// 추천
	
	@ManyToOne
	private SiteUser author;	// 작성자
	
	private LocalDateTime modifyDate;
   
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
   
	@Column(length = 200)
	private String subject;
   
	@Column(columnDefinition = "LONGTEXT")
	private String content;
   
	private LocalDateTime createDate;
   
	@OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
	private List<Answer> answerList;
   
}