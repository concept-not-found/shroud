package com.github.concept.not.found.shroud;

import java.lang.reflect.Method;
import java.util.List;

public class ExposedMethodResolver extends DefaultMethodResolver {

	private final List<Method> exposed;

	public ExposedMethodResolver(final List<Method> exposed) {
		this.exposed = exposed;
	}

	@Override
	public Method resolve(final Object target, final Method method, final Object[] parameters) {
		final Method defaultMethod = super.resolve(target, method, parameters);
		if (defaultMethod == null) {
			return null;
		}

		if (!exposed.contains(defaultMethod)) {
			return null;
		}

		return defaultMethod;
	}
}
