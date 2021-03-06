package com.github.concept.not.found.shroud.method.handler;

import java.lang.reflect.Method;
import java.util.List;

/**
 * The default {@link UnskilledHandler} simpily throws an {@link UnsupportedOperationException}.
 */
public class DefaultUnskilledHandler implements UnskilledHandler {

	public Object handle(final List<Object> originals, final Method method, final Object[] args) throws Exception {
		throw new UnsupportedOperationException(method.toString());
	}
}
