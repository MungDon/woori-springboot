package com.example.sbp.answer;

import java.time.LocalDateTime;

import com.example.sbp.question.Question;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Answer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(columnDefinition = "TEXT")
	private String content;

	private LocalDateTime createDate;
	
	// 질문 Entity 를 참조하기 위해 Question 추가
	@ManyToOne
	private Question question;

	//answer.getQuestion().getSubject();
	// 답변(Many) 하나(One) 의 질문 = N:1
	//@ManyToOne 
	// 반대의 경우
	// 질문(one)  답변(Many) = 1:N
	//@OneToMany
}
