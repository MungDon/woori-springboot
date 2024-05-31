package com.example.sbp.question;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.sbp.DataNotFoundException;
import com.example.sbp.answer.Answer;
import com.example.sbp.user.SiteUser;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionService {

	private final QuestionRepository questionRepository;

	private Specification<Question> search(String kw) {
		return new Specification<>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Question> q, CriteriaQuery<?> query, CriteriaBuilder cb) {// Root (기준이 되는 Entity(테이블)      
				query.distinct(true);		// 중복제거
				Join<Question, SiteUser> u1 = q.join("author", JoinType.LEFT);	 		// question q left outer join site_user u1 on q.author_id = u1.id
				Join<Question, Answer> a = q.join("answerList", JoinType.LEFT);		// question q left outer join answer a on q.id = a.question_id
				Join<Answer, SiteUser> u2 = a.join("author", JoinType.LEFT);				// answer a left outer join site_user u2 on a.author_id = u1.id
				return cb.or(cb.like(q.get("subject"), "%" + kw + "%"), 		// 제목	
									cb.like(q.get("content"), "%" + kw + "%"),		// 내용
									cb.like(u1.get("username"), "%" + kw + "%"), 	// 질문작성자
									cb.like(a.get("content"), "%" + kw + "%"),		// 답변 내용
									cb.like(u2.get("username"), "%" + kw + "%"));	// 답변 작성자
			}
		};
	}

	public Page<Question> getList(int page, String kw) {

		List<Sort.Order> sorts = new ArrayList<Sort.Order>();
		sorts.add(Sort.Order.desc("createDate"));

		Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
		Specification<Question> spec = search(kw);
		return this.questionRepository.findAll(spec, pageable);
	}

	public List<Question> getList() {
		return this.questionRepository.findAll();
	}

	public Question getQuestion(Integer id) {
		Optional<Question> question = this.questionRepository.findById(id);
		if (question.isPresent()) {
			return question.get();
		} else {
			throw new DataNotFoundException("아이디가 없다");
		}
	}

	public void create(String subject, String content, SiteUser siteUser) {
		Question question = new Question();
		question.setSubject(subject);
		question.setContent(content);
		question.setCreateDate(LocalDateTime.now());
		question.setAuthor(siteUser);
		this.questionRepository.save(question);
	}

	public void modify(Question question, String subject, String content) {
		question.setSubject(subject);
		question.setContent(content);
		question.setModifyDate(LocalDateTime.now());
		this.questionRepository.save(question);
	}

	public void delete(Question question) {
		this.questionRepository.delete(question);
	}

	public void vote(Question question, SiteUser siteUser) {
		question.getVoter().add(siteUser);
		this.questionRepository.save(question);
	}
}
