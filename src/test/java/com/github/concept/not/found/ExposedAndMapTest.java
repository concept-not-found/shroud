package com.github.concept.not.found;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.github.concept.not.found.shroud.Shroud;

public class ExposedAndMapTest {

	public interface Duck {
		String waddle();

		String quack();
	}

	public class Dragon {
		public String move() {
			return "stomp";
		}

		public String speak() {
			return "rawr";
		}
	}

	@Test
	public void mapThenExposeTest() {
		final Duck duck = Shroud.shroud(new Dragon()).map("quack", "speak").expose("speak").as(Duck.class);
		assertEquals("rawr", duck.quack());
	}

	@Test
	public void exposeThenMapTest() {
		final Duck duck = Shroud.shroud(new Dragon()).expose("speak").map("quack", "speak").as(Duck.class);
		assertEquals("rawr", duck.quack());
	}

	@Test(expected = UnsupportedOperationException.class)
	public void exposeThenUnexposedMapTest() {
		final Duck duck = Shroud.shroud(new Dragon()).expose("speak").map("waddle", "move").as(Duck.class);
		duck.waddle();
	}

	@Test(expected = UnsupportedOperationException.class)
	public void mapThenOnlyExposeOtherMethodTest() {
		final Duck duck = Shroud.shroud(new Dragon()).map("waddle", "move").expose("speak").as(Duck.class);
		duck.waddle();
	}
}
