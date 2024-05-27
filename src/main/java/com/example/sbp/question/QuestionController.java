package com.example.sbp.question;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/question/*")
public class QuestionController {
	
	private final QuestionService questionService;
	
	@GetMapping("/")			// http://localhost:8080/
	public String root() { // question  main page = question list
		return "redirect:/question/list";
	}
	
	@GetMapping("list")
	public String list(Model model) {
		List<Question>questionList = this.questionService.getList();
		model.addAttribute("questionList", questionList);		
		return "question_list";
	}
	
	@GetMapping("detail/{id}")
	public String detail(@PathVariable(value = "id")Integer id, Model model) {
		Question question = this.questionService.getQuestion(id);
		model.addAttribute("question", question);
		return "question_detail";
	}

}
