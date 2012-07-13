package com.github.concept.not.found.shroud.proxy;

import java.lang.reflect.Method;


public class ReflectionProxy implements Proxy {

	public Object newProxyInstance(final ClassLoader classLoader, final Class<?>[] classes, final InvocationHandler invocationHandler) {
		return java.lang.reflect.Proxy.newProxyInstance(classLoader, classes, new java.lang.reflect.InvocationHandler() {
			public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
				return invocationHandler.invoke(proxy, method, args);
			}
		});
	}

}
