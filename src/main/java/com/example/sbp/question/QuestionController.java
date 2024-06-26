package com.example.sbp.question;

import java.security.Principal;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import com.example.sbp.answer.AnswerForm;
import com.example.sbp.user.SiteUser;
import com.example.sbp.user.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/question")
public class QuestionController {
	
	private final QuestionService questionService;
	private final UserService userService;
	@GetMapping("/list")
	public String list(Model model,@RequestParam(value = "page",defaultValue = "0") int page, @RequestParam(value = "kw", defaultValue = "")String kw) {
		Page<Question>paging = this.questionService.getList(page,kw);
		model.addAttribute("paging", paging);		
		model.addAttribute("kw", kw); // 화면에 검색어 유지하기위해 보냄
		return "question_list";
	}
	
	@GetMapping("/detail/{id}")
	public String detail(@PathVariable(value = "id")Integer id, Model model, AnswerForm answerForm) {
		Question question = this.questionService.getQuestion(id);
		model.addAttribute("question", question);
		return "question_detail";
	}
	
	@PreAuthorize("isAuthenticated()")// 로그인 상태가아닐시 로그인페이지로 넘김
	@GetMapping("/create")
	public String questionCreate(QuestionForm questionForm) {
		return "question_form";
	}
	
	@PreAuthorize("isAuthenticated()")// 로그인 상태가아닐시 로그인페이지로 넘김
	@PostMapping("/create")
	public String questionCreate(@Valid QuestionForm questionForm,BindingResult bindingResult,Principal principal) {
		if(bindingResult.hasErrors()) {
			return"question_form";
		}
		SiteUser siteUser = this.userService.getUser(principal.getName());
		questionService.create(questionForm.getSubject(),questionForm.getContent(), siteUser);
		return"redirect:/question/list";
	}
	
	   @PreAuthorize("isAuthenticated()")
	   @GetMapping("/modify/{id}")
	   public String questionModify(QuestionForm questionForm,@PathVariable("id")Integer id,Principal principal) {
	      Question question = this.questionService.getQuestion(id);
	      if( !question.getAuthor().getUsername().equals(principal.getName()) ) {
	         throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다.");
	      }
	      questionForm.setSubject(question.getSubject());
	      questionForm.setContent(question.getContent());
	      return "question_form";
	   }
	   
	   @PreAuthorize("isAuthenticated()")
	   @PostMapping("/modify/{id}")
	   public String questionModify(@Valid QuestionForm questionForm,
	                        BindingResult bindingResult,
	                        @PathVariable("id")Integer id,
	                        Principal principal) {
	      if( bindingResult.hasErrors() ) {
	         return "question_form";
	      }
	      Question question = this.questionService.getQuestion(id);
	      if( !question.getAuthor().getUsername().equals(principal.getName()) ) {
	         throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다.");
	      }
	      this.questionService.modify(question, questionForm.getSubject(),
	                           questionForm.getContent());
	      return String.format("redirect:/question/detail/%s", id);
	   }
	   
	   @PreAuthorize("isAuthenticated()")
	   @GetMapping("/delete/{id}")
	   public String questionDelete(@PathVariable(value = "id")Integer id, Principal principal) {
		   Question question = this.questionService.getQuestion(id);
		   if(!question.getAuthor().getUsername().equals(principal.getName())) {
			   throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"삭제 권한이 없습니다");
		   }
		   	this.questionService.delete(question);
		   	return "redirect:/question/list" ;
	   }
	   
	   @PreAuthorize("isAuthenticated()")
	   @GetMapping("/vote/{id}")
	   public String questionVote(@PathVariable(value = "id")Integer id,Principal principal) {
		   Question question = this.questionService.getQuestion(id);
		   SiteUser siteUser = this.userService.getUser(principal.getName());
		   this.questionService.vote(question, siteUser);
		   return String.format("redirect:/question/detail/%s", id);
	   }
}