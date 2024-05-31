package com.example.sbp.answer;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import com.example.sbp.question.Question;
import com.example.sbp.question.QuestionService;
import com.example.sbp.user.SiteUser;
import com.example.sbp.user.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/answer/*")
public class AnswerController {

	private final QuestionService questionService;
	private final UserService userService;
	private final AnswerService answerService;

	@PreAuthorize("isAuthenticated()") // 로그인 상태가아닐시 로그인페이지로 넘김
	@PostMapping("create/{id}")
	public String createAnswer(@PathVariable(value = "id") Integer id, Model model, @Valid AnswerForm answerForm,
			BindingResult bindingResult, Principal principal) {
		Question question = this.questionService.getQuestion(id);
		SiteUser siteUser = this.userService.getUser(principal.getName());
		if (bindingResult.hasErrors()) {
			model.addAttribute("question", question);
			return "question_detail";
		}
		Answer answer = this.answerService.create(answerForm.getContent(), question, siteUser);
		
		return String.format("redirect:/question/detail/%s#answer_%s", id, answer.getId());
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping("/modify/{id}")
	public String answerModify(AnswerForm answerForm, @PathVariable(value = "id") Integer id, Principal principal) {
		Answer answer = this.answerService.getAnswer(id);
		if (!answer.getAuthor().getUsername().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "권한이 읎음");
		}
		answerForm.setContent(answer.getContent());
		return "answer_form";
	}

	@PreAuthorize("isAuthenticated()")
	@PostMapping("/modify/{id}")
	public String answerModify(@Valid AnswerForm answerForm, BindingResult bindingResult,
			@PathVariable(value = "id") Integer id, Principal principal) {
		if (bindingResult.hasErrors()) {
			return "answer_form";
		}
		Answer answer = this.answerService.getAnswer(id);
		Question question = answer.getQuestion();
		Integer questionId = question.getId();
		if (!answer.getAuthor().getUsername().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "권한이 읎음");
		}
		this.answerService.modify(answer, answerForm.getContent());
		return String.format("redirect:/question/detail/%s#answer_%s", questionId, answer.getId());
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping("/delete/{id}")
	public String answerDelete(@PathVariable(value = "id") Integer id, Principal principal) {
		Answer answer = this.answerService.getAnswer(id);
		Integer questionId = answer.getQuestion().getId();
		if (!answer.getAuthor().getUsername().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "권한이 읎음");
		}
		this.answerService.delete(answer);
		return String.format("redirect:/question/detail/%s", questionId);
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/vote/{id}")
	public String answerVote(@PathVariable(value = "id")Integer id, Principal principal) {
		Answer answer = this.answerService.getAnswer(id);
		Integer questionId = answer.getQuestion().getId();
		SiteUser siteUser = this.userService.getUser(principal.getName());
		this.answerService.vote(answer, siteUser);
		return String.format("redirect:/question/detail/%s#answer_%s", questionId, answer.getId());
	}
	
}
