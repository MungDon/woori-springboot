package com.example.sbp.answer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.sbp.question.Question;
import com.example.sbp.question.QuestionService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/answer/*")
public class AnswerController {

	private final QuestionService questionService;
	
	private final AnswerService answerService;
	
	@PostMapping("create/{id}")
	public String createAnswer(@PathVariable(value = "id")Integer id, Model model, @RequestParam(value = "content")String content) {
		Question question = this.questionService.getQuestion(id);
		this.answerService.create(content, question);
		return String.format("redirect:/question/detail/%s",id);
	}
}
