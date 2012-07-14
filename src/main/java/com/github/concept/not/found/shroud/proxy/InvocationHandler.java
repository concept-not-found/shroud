package com.github.concept.not.found.shroud.proxy;

import java.lang.reflect.Method;

/**
 * This interface an exact abstraction over {@link java.lang.reflect.InvocationHandler}.
 * 
 * The intention is to allow different implementations of InvocationHandler such as cglib {@link net.sf.cglib.proxy.InvocationHandler} to be used.
 */
public interface InvocationHandler {

	Object invoke(final Object proxy, final Method method, final Object[] parameters) throws Throwable;

}
