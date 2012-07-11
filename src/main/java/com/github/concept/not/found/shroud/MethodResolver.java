package com.github.concept.not.found.shroud;

import java.lang.reflect.Method;

public interface MethodResolver {

	Method resolve(Object target, Method method, Object[] parameters);
}
