package com.example.sbp.question;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.sbp.answer.AnswerForm;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/question")
public class QuestionController {
	
	private final QuestionService questionService;
	
	@GetMapping("/list")
	public String list(Model model,@RequestParam(value = "page",defaultValue = "0") int page) {
		Page<Question>paging = this.questionService.getList(page);
		model.addAttribute("paging", paging);		
		return "question_list";
	}
	
	@GetMapping("/detail/{id}")
	public String detail(@PathVariable(value = "id")Integer id, Model model, AnswerForm answerForm) {
		Question question = this.questionService.getQuestion(id);
		model.addAttribute("question", question);
		return "question_detail";
	}
	
	@GetMapping("/create")
	public String questionCreate(QuestionForm questionForm) {
		return "question_form";
	}
	
	@PostMapping("/create")
	public String questionCreate(@Valid QuestionForm questionForm,BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return"question_form";
		}
		questionService.create(questionForm.getSubject(),questionForm.getContent());
		return"redirect:/question/list";
	}
	
}
