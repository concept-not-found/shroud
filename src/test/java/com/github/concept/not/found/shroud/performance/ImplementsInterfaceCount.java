package com.github.concept.not.found.shroud.performance;

public class ImplementsInterfaceCount implements Count {

	private int next = 0;

	public int next() {
		return next++;
	}
}
