package com.example.sbp.answer;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.sbp.question.Question;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnswerService {

	private final AnswerRepository answerRepository;
	
	public void create(String content, Question question) {
		Answer answer = new Answer();
 		answer.setContent(content);
		answer.setCreateDate(LocalDateTime.now());
		answer.setQuestion(question);
		this.answerRepository.save(answer);
	}
}