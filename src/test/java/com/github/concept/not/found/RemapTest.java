package com.github.concept.not.found;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.github.concept.not.found.shroud.Shroud;

public class RemapTest {

	public interface Person {
		String talk();

		String eat();
	}

	public class Lando {
		public String talk() {
			return "words";
		}

		public String eat() {
			return "baloney";
		}
	}

	@Test
	public void test() {
		final Person person = Shroud.shroud(new Lando()).map("talk", "eat").map("eat", "talk").as(Person.class);
		assertEquals("baloney", person.talk());
		assertEquals("words", person.eat());
	}
}
