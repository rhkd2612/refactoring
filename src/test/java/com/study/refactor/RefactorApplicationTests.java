package com.study.refactor;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class RefactorApplicationTests {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	public RefactorApplicationTests() {
		System.setOut(new PrintStream(outContent));
	}

	@Test
	void statement() throws Exception {
		assertThat(output1()).contains(expect1());
	}

	private String[] input1(){
		return new String[]{"hamlet/Hamlet/tragedy/55,as-like/As You Like It/comedy/35,othello/Othello/tragedy/40", "BigCo"};
	}
	private String output1() throws Exception {
		RefactoringApplication.main(input1());
		return outContent.toString();
	}
	private String[] expect1(){
		return new String[]{"BigCo","65000원","55석","58000원","35석","50000원","40석","173000원","47점"};
	}
}
