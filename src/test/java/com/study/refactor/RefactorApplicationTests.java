package com.study.refactor;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

//@SpringBootTest
class RefactorApplicationTests {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	public RefactorApplicationTests() {
		System.setOut(new PrintStream(outContent));
	}

	@Test
	void statement() throws Exception {
		assertThat(output(input1())).contains(expect1());
	}

	@Test
	void statementThrowException() throws Exception {
		Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> output(input2()));
		Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> output(input3()));
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

	private String output(String[] input) throws Exception {
		RefactorApplication.main(input);
		return outContent.toString();
	}

	private String[] expect1(){
		return new String[]{"BigCo","65000원","55석","58000원","35석","50000원","40석","173000원","47점"};
	}
}
