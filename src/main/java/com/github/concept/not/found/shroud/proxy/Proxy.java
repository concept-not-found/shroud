package com.github.concept.not.found.shroud.proxy;


public interface Proxy {

	Object newProxyInstance(ClassLoader classLoader, Class<?>[] classes, InvocationHandler invocationHandler);

}
