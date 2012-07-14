package com.github.concept.not.found.shroud.proxy;

/**
 * This interface an exact abstraction over {@link java.lang.reflect.Proxy}.
 * 
 * The intention is to allow different implementations of Proxy such as cglib {@link net.sf.cglib.proxy.Proxy} to be used.
 */
public interface Proxy {

	Object newProxyInstance(ClassLoader classLoader, Class<?>[] classes, InvocationHandler invocationHandler);

}
