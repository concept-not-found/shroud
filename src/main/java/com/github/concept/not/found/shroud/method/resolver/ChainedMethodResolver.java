package com.github.concept.not.found.shroud.method.resolver;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Chains a series of {@link MethodResolver}s.
 * 
 * Will return the first non null method.
 */
public class ChainedMethodResolver implements MethodResolver {

	private final List<MethodResolver> methodResolvers = new ArrayList<MethodResolver>();

	public ChainedMethodResolver(final MethodResolver... methodResolvers) {
		this.methodResolvers.addAll(Arrays.asList(methodResolvers));
	}

	public Method resolve(final Object target, final Method method, final Object[] parameters) {
		for (final MethodResolver methodResolver : methodResolvers) {
			final Method resolvedMethod = methodResolver.resolve(target, method, parameters);
			if (resolvedMethod != null) {
				return resolvedMethod;
			}
		}
		return null;
	}
}
