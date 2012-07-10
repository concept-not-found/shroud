package com.github.concept.not.found;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.github.concept.not.found.shroud.Shroud;

public class CompositionTest {

	public interface Animal {
		String speak();

		String attack();
	}

	public class Dragon {
		public String attack() {
			return "flames";
		}

	}

	public class Kitten {
		public String speak() {
			return "meow";
		}
	}

	@Test
	public void test() {
		final Animal animal = Shroud.shroud(new Dragon(), new Kitten()).as(Animal.class);
		assertEquals("attack should be backed by Dragon", "flames", animal.attack());
		assertEquals("speak should be backed by Kitten", "meow", animal.speak());
	}
}
