package com.github.concept.not.found.shroud.method.resolver;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Extends the {@link DefaultMethodResolver} to limit which methods are exposed.
 */
public class ExposedMethodResolver extends DefaultMethodResolver {

	private final List<Method> exposed = new ArrayList<Method>();

	public ExposedMethodResolver(final List<Method> exposed) {
		this.exposed.addAll(exposed);
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
