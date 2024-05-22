package com.example.sbp.user;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
@NoArgsConstructor // 기본생성자
@AllArgsConstructor // 모든 매개변수 있는 생성자
public class User {

	private String name;
	
	private int age;
}
