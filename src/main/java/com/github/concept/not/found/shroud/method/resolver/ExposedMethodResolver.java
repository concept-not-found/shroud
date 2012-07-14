package com.github.concept.not.found.shroud.method.resolver;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Extends the {@link DefaultMethodResolver} to limit which methods are exposed.
 */
public class ExposedMethodResolver implements MethodResolver {

	private final MethodResolver methodResolver;

	private final List<Method> exposed = new ArrayList<Method>();

	public ExposedMethodResolver(final MethodResolver methodResolver, final List<Method> exposed) {
		this.methodResolver = methodResolver;
		this.exposed.addAll(exposed);
	}

	public Method resolve(final Object target, final Method method, final Object[] parameters) {
		final Method defaultMethod = methodResolver.resolve(target, method, parameters);
		if (defaultMethod == null) {
			return null;
		}

		if (!exposed.contains(defaultMethod)) {
			return null;
		}

		return defaultMethod;
	}
}
