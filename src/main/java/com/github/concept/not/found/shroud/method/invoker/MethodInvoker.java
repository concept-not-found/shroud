package com.github.concept.not.found.shroud.method.invoker;

import java.lang.reflect.Method;

/**
 * Invokes given method on the target with parameters.
 */
public interface MethodInvoker {

	Object invoke(Object target, Method method, Object[] parameters) throws Exception;
}
