package com.github.concept.not.found.shroud;

import java.lang.reflect.Method;
import java.util.List;

public class ExposedMethodResolver extends DefaultMethodResolver {

	private final List<Method> exposed;

	public ExposedMethodResolver(final List<Method> exposed) {
		this.exposed = exposed;
	}

	@Override
	public Method resolve(final Object original, final Method target, final Object[] paremeters) {
		final Method defaultMethod = super.resolve(original, target, paremeters);
		if (defaultMethod == null) {
			return null;
		}

		if (!exposed.contains(defaultMethod)) {
			return null;
		}

		return defaultMethod;
	}
}
