package com.github.concept.not.found;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.github.concept.not.found.shroud.Shroud;

public class SplitTest {

	public interface Getter {
		String getAnswer();
	}

	public interface Setter {
		void setAnswer(String answer);
	}

	public class Computer {
		private String answer = "unknown";

		public String getAnswer() {
			return answer;
		}

		public void setAnswer(final String answer) {
			this.answer = answer;
		}
	}

	@Test
	public void test() {
		final Computer computer = new Computer();

		final Getter getter = Shroud.shroud(computer).as(Getter.class);
		final Setter setter = Shroud.shroud(computer).as(Setter.class);

		setter.setAnswer("42");
		assertEquals("42", getter.getAnswer());
	}
}
