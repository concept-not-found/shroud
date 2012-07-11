package com.github.concept.not.found;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Method;

import org.junit.Test;

import com.github.concept.not.found.shroud.Shroud;

public class AdviceTest {

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
		public String attack(final Object target, final Method method, final Object[] parameters) throws Exception {
			return "pew pew " + method.invoke(target, parameters);
		}
	}

	@Test
	public void test() {
		final Animal animal = Shroud.shroud(new Dragon()).advisedBy(new SoundEffects()).as(Animal.class);
		assertEquals("speak should be unaffected", "rawr", animal.speak());
		assertEquals("pew pew flames", animal.attack());
	}
}
