package com.example.sbp.lamda;

/*
 	람다식
 		(타입 매개변수) -> {실행문;} 
 		(int a) -> {System.out.prinlnt(a);}
 		(a) -> {System.out.prinlnt(a);}
 		() -> {System.out.prinlnt(a);} / 매개변수 없을때
 		(x,y) ->{return x+y;} == (x, y) -> return x+y;
  */
interface Test1 {

	// 매개변수 없음
	public abstract void name();
}

interface Test2 {

	public abstract void name(String name);
}

interface Test3 {
	public abstract void name(String name, int a);

}

interface Test4 {
	// 정수를 리턴하는 추상메서드
	public abstract int sum(int a, int b);

}

public class Ex01 {
	public static void main(String[] args) {

		// 익명 클래스로 Test1 인터페이스 구현
		Test1 t = new Test1() {
			// name 메서드 구현
			public void name() {
				System.out.println("ㅋ1");
			}
		};
		t.name();
		// 람다식으로 Test1 인터페이스 구현
		Test1 t1 = () -> {
			System.out.println("ㅋ2");
		};
		
		t1.name();
		//람다식으로 Test2 인터페이스 구현 - 매개변수 1개
		Test2 t2 = n ->{
			System.out.println(n);
		};
		t2.name("ㅋ3");
		
		//람다식으로 Test3 인터페이스 구현 - 타입이다른 매개변수 2개
		Test3 t3 = (n,a) ->{
			for(int i = 0; i <a; i++) {
				System.out.println(n);
			}
		};
		t3.name("ㅋ4",5);
		
		//람다식으로 Test3 인터페이스 구현 - 리턴이있는 메서드
		Test4 t4 = (a,b) -> {
			return a+b;};
			System.out.println(t4.sum(1, 2));
		//ver2
		Test4 t5 = (a,b) -> a+b;
		System.out.println(t5.sum(1, 2));
	}
}