package com.github.concept.not.found.shroud.method.invoker;

import java.lang.reflect.Method;

/**
 * The default {@link MethodInvoker} just invokes the method.
 */
public class DefaultMethodInvoker implements MethodInvoker {

	public Object invoke(final Object target, final Method method, final Object[] parameters) throws Exception {
		return method.invoke(target, parameters);
	}
}
