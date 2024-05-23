package com.example.sbp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.sbp.dto.Lombok;

@Controller
@RequestMapping("/lombok")
public class LombokController { // lombok.java 클래스와 함께 보시오

	@GetMapping("/lom01")
	public String lom01(Model model) {
		Lombok lombok = new Lombok();
		lombok.setAge(5);
		lombok.setName("라이언");
		lombok.setTest("ㅎㅎㅎ");
		model.addAttribute("lombok",lombok);
		return "lom01"; 
	}
	
	@GetMapping("/lom02")
	public String lom02(Model model) {
		// @NoArgsContructor - 기본생성자
		Lombok lombok = new Lombok();
		lombok.setName("춘식이");
		lombok.setAge(3);
		lombok.setTest("lombok");
		//@NonNull 설정에 의해 null 대입 시 소스코드에는 오류가 없지만
		// 실행시 NPE 발생
		//user.setTest(null);
		model.addAttribute("lombok", lombok);
		return "lom02";
	}
	
	@GetMapping("/lom03")
	public String lom03(Model model) {
		// @AllArgsContructor 설정에 의해 모든 변수를 매개변수로 받는 생성자 생성
		Lombok lombok = new Lombok("망그러진곰",3,"hello");
		model.addAttribute("lombok", lombok);
		return "lom03";
	}
	
	@GetMapping("/lom04")
	public String lom04(Model model) {
		//@Builder 생성자를 사용하지 않고 빌더 패턴을 자동으로 생성한다 - 몇개만 골라서 선택가능
		Lombok lombok = Lombok.builder().name("춘식")
													   .age(4)
													   .test("ㅎㅇ").build();
		model.addAttribute("lombok", lombok);
		return"lom04";
	}
	
}
