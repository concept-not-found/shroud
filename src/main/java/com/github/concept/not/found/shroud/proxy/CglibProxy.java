package com.github.concept.not.found.shroud.proxy;

import java.lang.reflect.Method;


public class CglibProxy implements Proxy {

	public Object newProxyInstance(final ClassLoader classLoader, final Class<?>[] classes, final InvocationHandler invocationHandler) {
		return net.sf.cglib.proxy.Proxy.newProxyInstance(classLoader, classes, new net.sf.cglib.proxy.InvocationHandler() {
			public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
				return invocationHandler.invoke(proxy, method, args);
			}
		});
	}

}
