package com.example.sbp.answer;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.sbp.question.Question;
import com.example.sbp.question.QuestionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/answer/*")
public class AnswerController {

	private final QuestionService questionService;
	
	private final AnswerService answerService;
	
	@PostMapping("create/{id}")
	public String createAnswer(@PathVariable(value = "id")Integer id, Model model, @Valid AnswerForm answerForm,BindingResult bindingResult) {
		Question question = this.questionService.getQuestion(id);
		if(bindingResult.hasErrors()) {
			model.addAttribute("question", question);
			return "question_detail";
		}
		this.answerService.create(answerForm.getContent(), question);
		return String.format("redirect:/question/detail/%s",id);
	}
}
