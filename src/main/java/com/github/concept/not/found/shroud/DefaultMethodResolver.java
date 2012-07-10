package com.github.concept.not.found.shroud;

import java.lang.reflect.Method;
import java.util.Arrays;

public class DefaultMethodResolver implements MethodResolver {

	public Method resolve(final Object original, final Method target, final Object[] paremeters) {
		for (final Method originalMethod : original.getClass().getMethods()) {
			boolean methodNamesMatch = originalMethod.getName().equals(target.getName());
			if (methodNamesMatch) {
				boolean parametersMatch = Arrays.asList(originalMethod.getParameterTypes()).equals(Arrays.asList(target.getParameterTypes()));
				if (parametersMatch) {
					return originalMethod;
				}
			}
		}
		return null;
	}
}
