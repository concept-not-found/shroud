package com.github.concept.not.found.shroud;

public class Shroud {

	public static ShroudBuilder shroud(final Object... backingInstance) {
		return new ShroudBuilder(backingInstance);
	}

}
