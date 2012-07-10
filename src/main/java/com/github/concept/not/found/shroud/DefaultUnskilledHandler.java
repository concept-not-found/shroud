package com.github.concept.not.found.shroud;

import java.lang.reflect.Method;

public class DefaultUnskilledHandler implements UnskilledHandler {

	public Object handle(final Object[] originals, final Method method, final Object[] args) {
		throw new UnsupportedOperationException(method.toString());
	}
}
