package com.github.concept.not.found.shroud.method.handler;

import java.lang.reflect.Method;
import java.util.List;

public class DefaultUnskilledHandler implements UnskilledHandler {

	public Object handle(final List<Object> originals, final Method method, final Object[] args) {
		throw new UnsupportedOperationException(method.toString());
	}
}
