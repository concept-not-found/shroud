package com.github.concept.not.found.shroud;

import java.lang.reflect.Method;

public class DefaultMethodInvoker implements MethodInvoker {

	public Object invoke(final Object target, final Method method, final Object[] parameters) throws Exception {
		return method.invoke(target, parameters);
	}
}
