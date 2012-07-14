package com.github.concept.not.found.shroud.method.resolver;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * The default {@link MethodResolver} will exact match on method name and parameter types.
 */
public class DefaultMethodResolver implements MethodResolver {

	public Method resolve(final Object target, final Method method, final Object[] parameters) {
		for (final Method targetMethod : target.getClass().getMethods()) {
			boolean methodNamesMatch = targetMethod.getName().equals(method.getName());
			if (methodNamesMatch) {
				boolean parametersMatch = Arrays.asList(targetMethod.getParameterTypes()).equals(Arrays.asList(method.getParameterTypes()));
				if (parametersMatch) {
					return targetMethod;
				}
			}
		}
		return null;
	}
}
