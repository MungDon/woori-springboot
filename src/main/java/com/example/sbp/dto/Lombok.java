package com.example.sbp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Lombok {

	private String name;
	
	private int age;
	
	@NonNull
	private String test;
	
}
/*
 		@Getter								:	get() 메서드 자동생성
		@Setter								:	set() 메서드 자동생성
		@NoArgsConstructor				:	기본 생성자를 생성
		@AllArgsConstructor				:	모든 변수를 매개변수로 갖는 생성자를 생성
		@RequiredArgsConstructor	:	클래스에 대한 매개변수가 있는 생성자를 생성
														final, @NonNull 표시된 변수만 매개변수로 사용
														
		@Builder								:	빌더 패턴을 자동으로 생성한다.
  		@ToString								:	객체의 toString() 메서드를 자동으로 생성한다.
  		@EqualsAndHashCode			:	객체의 equals() hashCode() 메서드를 자동으로 생성한다.
  		@Data									:	@Getter, @Setter,	@ToString	,@EqualsAndHashCode,@RequiredArgsConstructor
  														한꺼번에 사용가능하다
  													
  			
 */
