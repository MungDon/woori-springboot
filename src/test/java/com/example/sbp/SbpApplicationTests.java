package com.example.sbp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.sbp.question.QuestionService;

@SpringBootTest
class SbpApplicationTests {
	
	@Autowired
	private QuestionService questionService;
	
	@Test
	void testJpa() {
		for(int i=1; i<=300; i++) {
			String subject = String.format("테스트 데이터 : [%03d]",i);
			String content = "풉 ㅋ";
			this.questionService.create(subject, content,null);
		}
	}
	
	
	
	
/*
	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private AnswerRepository answerRepository;

	
	

 // 질문 데이터로 답변데이터 찾기
	@Transactional // springframework
	@Test
	void testJpa() {
		Optional<Question> oq = this.questionRepository.findById(2);
		assertTrue(oq.isPresent());
		Question q = oq.get();
		
		// 지연방식 lazy <--> 즉시방식 Eager
		List<Answer> answerList = q.getAnswerList();
		
		assertEquals(1, answerList.size());
		assertEquals("예.", answerList.get(0).getContent());
	}
 /*

 * select
	@Test
	void testJpa() {
		Optional<Answer> oa = this.answerRepository.findById(1);
		assertTrue(oa.isPresent());
		Answer a = oa.get();
		assertEquals(2, a.getQuestion().getId());
	}
	
	
	
	 * answer insert
	@Test
	void testJpa() {
		Optional<Question> oq = this.questionRepository.findById(2);
		assertTrue(oq.isPresent());
		Question q = oq.get();
		
		Answer a = new Answer();
		a.setContent("예.");
		a.setQuestion(q);
		a.setCreateDate(LocalDateTime.now());
		this.answerRepository.save(a);
	}
	
	-----------------ANSWER-----------------------------

	 * delete
	@Test
	void testJpa() {
		assertEquals(2, this.questionRepository.count());
		Optional<Question> oq = this.questionRepository.findById(1);
		assertTrue(oq.isPresent());
		Question q = oq.get();
		this.questionRepository.delete(q);
		assertEquals(1, this.questionRepository.count());
	}
	
 * update
	@Test
	void testJpa(){
		Optional<Question> oq = this.questionRepository.findById(1);
		assertTrue(oq.isPresent());
		
		Question q = oq.get();
		q.setSubject("수정된 제목");
		this.questionRepository.save(q);
	}
 * like 
	@Test
	void testJpa() {
		List<Question> qList = this.questionRepository.findBySubjectLike("sbp%");
		Question q = qList.get(0);
		assertEquals("sbp가 무엇인가", q.getSubject());
	}
	Hibernate: 
    select
        q1_0.id,
        q1_0.content,
        q1_0.create_date,
        q1_0.subject 
    from
        question q1_0 
    where
        q1_0.subject like ? escape '\'
	
	where ? and ?
	@Test
	void testJpa() {
		Question q = this.questionRepository.findBySubjectAndContent("sbp가 무엇인가", "sbp에 대해서 알고 싶다");
		assertEquals(1, q.getId());
	}
	Hibernate: 
    select
        q1_0.id,
        q1_0.content,
        q1_0.create_date,
        q1_0.subject 
    from
        question q1_0 
    where
        q1_0.subject=? 
        and q1_0.content=?*/
	
	/*
	@Test
	void testJpa() {
		Question q  = this.questionRepository.findBySubject("sbp가 무엇인가");
		assertEquals(1, q.getId());
	}
	// findBy + Entity 속성명 :  메서드선언
	 * 
	 * Hibernate: 
    select
        q1_0.id,
        q1_0.content,
        q1_0.create_date,
        q1_0.subject 
    from
        question q1_0 
    where
        q1_0.subject=? 

	@Test
	void testJpa() {
		Optional<Question> oq = this.questionRepository.findById(1);
		if(oq.isPresent()) {
			Question q = oq.get();
			assertEquals("sbp가 무엇인가", q.getSubject());
		}
	}

 *  select
	@Test
	void testJpa() {
		List<Question> all = this.questionRepository.findAll();
		assertEquals(2, all.size());
		
		Question q = all.get(0);
		assertEquals("sbp가 무엇인가", q.getSubject());
		//assertEquals(기댓값, 실제값);
		// 테스트에서 예상한 결과와 실제 데이터가 동일한지 비교, 확인하는 목적으로 사용
		// JPA 또는 DB 에서 데이터를 올바르게 가져오는지를 확인
	}
*/
	
	
	
// insert
//	@Test
//	void testJpa() {
//		//첫번째 레코드
//		Question q1 = new Question();
//		q1.setSubject("sbp가 무엇인가");
//		q1.setContent("sbp에 대해서 알고 싶다");
//		q1.setCreateDate(LocalDateTime.now());
//		this.questionRepository.save(q1);
//		
//		//두번째 레코드
//		Question q2 = new Question();
//		q2.setSubject("스프링부트 모델 질문.");
//		q2.setContent("id 는 자동생성됨?");
//		q2.setCreateDate(LocalDateTime.now());
//		this.questionRepository.save(q2);
//	}

	
	
}
