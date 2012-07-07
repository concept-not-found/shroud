package com.github.concept.not.found;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.github.concept.not.found.shroud.Shroud;

public class SimpleShroudTest {

	public interface Animal {
		String speak();
	}

	public class Dragon {
		public String speak() {
			return "rawr";
		}
	}

	@Test
	public void test() {
		final Animal animal = Shroud.shroud(new Dragon()).as(Animal.class);
		assertEquals("rawr", animal.speak());
	}
}
