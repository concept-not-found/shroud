package com.github.concept.not.found.shroud;

public class ShroudBuilder {

	private final Object backingIntance;

	public ShroudBuilder(final Object backingIntance) {
		this.backingIntance = backingIntance;
	}

	public <T> T as(final Class<T> interfaceClass) {
		return (T) new Pretender(backingIntance).pretend(interfaceClass);
	}
}
