package com.github.concept.not.found.shroud;

import com.github.concept.not.found.shroud.builder.ShroudBuilder;

/**
 * Main entry point into this library.
 */
public class Shroud {

	public static ShroudBuilder shroud(final Object... backingInstance) {
		return new ShroudBuilder(backingInstance);
	}

}
