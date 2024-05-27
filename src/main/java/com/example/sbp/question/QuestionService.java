package com.example.sbp.question;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.sbp.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionService {
	
	private final QuestionRepository questionRepository;
	
	public List<Question> getList(){
	 return this.questionRepository.findAll();
	}
	
	public Question getQuestion(Integer id) {
		Optional<Question> question = this.questionRepository.findById(id);	
		if(question.isPresent()) {
			return question.get();
		}else {
			throw new DataNotFoundException("아이디가 없다");
		}
	}
}