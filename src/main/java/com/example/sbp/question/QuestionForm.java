package com.example.sbp.question;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class QuestionForm {

	@NotEmpty(message = "제목을 필수항목 입니다")
	@Size(max=200, message = "글자수가너무길어여")// byte
	private String subject;
	
	@NotEmpty(message = "내용은 필수항목 입니다")
	private String content;
}
