package com.github.concept.not.found;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.github.concept.not.found.shroud.Shroud;

public class ExposeMethodTest {

	public interface Animal {
		String speak();

		String attack();
	}

	public class Dragon {
		public String speak() {
			return "rawr";
		}

		public String attack() {
			return "flames";
		}
	}

	@Test
	public void testSpeakStillWorks() {
		final Animal animal = Shroud.shroud(new Dragon()).expose("speak").as(Animal.class);
		assertEquals("rawr", animal.speak());
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testAttackFails() {
		final Animal animal = Shroud.shroud(new Dragon()).expose("speak").as(Animal.class);
		animal.attack();
	}

}
