package com.github.concept.not.found.shroud.method.resolver;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MapMethodResolver implements MethodResolver {

	private final Map<String, String> maps = new HashMap<String, String>();

	public MapMethodResolver(final Map<String, String> maps) {
		this.maps.putAll(maps);
	}

	public Method resolve(final Object target, final Method method, final Object[] parameters) {
		final String mappedMethod = maps.get(method.getName());
		if (mappedMethod != null) {
			for (final Method targetMethod : target.getClass().getMethods()) {
				boolean methodNamesMatch = targetMethod.getName().equals(mappedMethod);
				if (methodNamesMatch) {
					boolean parametersMatch = Arrays.asList(targetMethod.getParameterTypes()).equals(Arrays.asList(method.getParameterTypes()));
					if (parametersMatch) {
						return targetMethod;
					}
				}
			}
		}

		return null;
	}
}
