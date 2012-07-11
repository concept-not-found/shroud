package com.github.concept.not.found;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.github.concept.not.found.shroud.Shroud;

public class ChainTest {

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

	public class SoundEffects {
		public String attack(final String attack) {
			return "pew pew " + attack;
		}
	}

	@Test
	public void test() {
		final Animal animal = Shroud.shroud(new Dragon()).chain(new SoundEffects()).as(Animal.class);
		assertEquals("speak should be unaffected", "rawr", animal.speak());
		assertEquals("pew pew flames", animal.attack());
	}
}
