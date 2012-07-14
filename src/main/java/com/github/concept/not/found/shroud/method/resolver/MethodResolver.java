package com.github.concept.not.found.shroud.method.resolver;

import java.lang.reflect.Method;

/**
 * Selects a method on the target given a pretended method.
 * 
 * Returned method must belong to target.
 */
public interface MethodResolver {

	Method resolve(Object target, Method method, Object[] parameters);
}
