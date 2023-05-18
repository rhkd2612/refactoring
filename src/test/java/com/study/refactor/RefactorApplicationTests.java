package com.study.refactor;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.study.refactor.exception.MyCustomException;

import static org.assertj.core.api.Assertions.assertThat;

class RefactorApplicationTests {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	public RefactorApplicationTests() {
		System.setOut(new PrintStream(outContent));
	}

	@Test
	@DisplayName("정상 케이스")
	void statement(){
		assertThat(output(input1())).contains(expect1());
	}

	@Test
	@DisplayName("예외 케이스")
	void statementThrowException(){
		Assertions.assertThrows(MyCustomException.class, () -> output(input2()));
		Assertions.assertThrows(MyCustomException.class, () -> output(input3()));
	}

	private String[] input1(){
		return new String[]{"hamlet/Hamlet/tragedy/55,as-like/As You Like It/comedy/35,othello/Othello/tragedy/40", "BigCo"};
	}

	private String[] input2(){
		return new String[]{"hamlet/Hamlet/tragedy,as-like/As You Like It/comedy/35,othello/Othello/tragedy/40", "BigCo"};
	}

	private String[] input3(){
		return new String[]{""};
	}

	private String output(String[] input){
		RefactorApplication.main(input);
		return outContent.toString();
	}

	private String[] expect1(){
		return new String[]{"BigCo","65000원","55석","58000원","35석","50000원","40석","173000원","47점"};
	}
}
