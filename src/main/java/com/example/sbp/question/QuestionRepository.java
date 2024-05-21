package com.example.sbp.question;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface QuestionRepository extends JpaRepository<Question, Integer> {
	
	// junit test
	Question findBySubject(String subject);
	
	// subject & content 두개의 Entity 속성(column) 조회 하기 위한 메서드
	Question findBySubjectAndContent(String subject, String content);
	
	// like
	List<Question> findBySubjectLike(String subject);
	
	
	//And       	findBySubjectAndContent    		… where subject = ? and content = ?
	//Or			 	findBySubjectOrContent    		… where subject = ? or content = ?
	//Between	findByCreateDateBetween(LocalDateTime from Date, LocalDateTime toDate) … where CreateDate between ? and ?
	//LessThan	findByIdLessThan(Integer id)	… where Id < ? 	=> id 컬럼에서 조건보다 작은 데이터 조회
	//GreaterThanEqual		findByIdGreaterThanEqual(Integer id) => id 컬럼에서 조건보다 크거나 같은 데이터를 조회
	//Like			findBySubjectLike	(String subject)					… where subject like ?
	//In				findBySubjectIn(String[] subject)					… where subject in ?     => subject 열의 데티어가 주어진 배열에 포함되는 데이터만 조회
	//OrderBy	findBySubjectOrderByCreateDateAsc(String subject)	… where subject = ? order by create_date asc
	
}
