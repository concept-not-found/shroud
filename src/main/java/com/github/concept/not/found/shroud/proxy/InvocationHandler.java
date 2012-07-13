package com.github.concept.not.found.shroud.proxy;

import java.lang.reflect.Method;

public interface InvocationHandler {

	Object invoke(final Object proxy, final Method method, final Object[] parameters) throws Throwable;

}
