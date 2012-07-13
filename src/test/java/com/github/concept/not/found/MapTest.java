package com.github.concept.not.found;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.github.concept.not.found.shroud.Shroud;

public class MapTest {

	public interface Duck {
		String quack();
	}

	public class Dragon {
		public String speak() {
			return "rawr";
		}
	}

	@Test
	public void test() {
		final Duck duck = Shroud.shroud(new Dragon()).map("quack", "speak").as(Duck.class);
		assertEquals("rawr", duck.quack());
	}
}
