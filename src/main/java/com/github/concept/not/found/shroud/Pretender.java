package com.github.concept.not.found.shroud;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.List;

import com.github.concept.not.found.shroud.method.handler.UnskilledHandler;
import com.github.concept.not.found.shroud.method.invoker.MethodInvoker;
import com.github.concept.not.found.shroud.method.resolver.MethodResolver;
import com.github.concept.not.found.shroud.proxy.InvocationHandler;
import com.github.concept.not.found.shroud.proxy.Proxy;

/**
 * A pretender can pretend to be any interface and is the core of this library.
 * The implementation of the pretended interfaces are backed by one or more objects.
 * On how methods on the interface are mapped to objects is handled by the {@link MethodResolver} interface.
 * Once a suitable method has been found it is invoked by the {@link MethodInvoker} interface.
 * If no suitable methods are found the implementation is handled by {@link UnskilledHandler}.
 * At the very heart of this implementation the pretending of the interfaces is provided by {@link Proxy}.
 */
public class Pretender implements Serializable {

	private final Proxy proxy;

	private final List<Object> originals;

	private final MethodInvoker methodInvoker;

	private final MethodResolver methodResolver;

	private final UnskilledHandler unskilledHandler;

	public Pretender(final Proxy proxy, final List<Object> originals, final MethodInvoker methodInvoker, final MethodResolver methodResolver, final UnskilledHandler unskilledHandler) {
		this.proxy = proxy;
		this.originals = originals;
		this.methodInvoker = methodInvoker;
		this.methodResolver = methodResolver;
		this.unskilledHandler = unskilledHandler;
	}

	public <T> T pretend(final Class<T> pretendInterface) {
		return pretendInterface.cast(proxy.newProxyInstance(pretendInterface.getClassLoader(), new Class[] {
			pretendInterface
		}, new InvocationHandler() {
			public Object invoke(final Object proxyInstance, final Method method, final Object[] parameters) throws Throwable {
				for (final Object original : originals) {
					final Method originalMethod = methodResolver.resolve(original, method, parameters);
					if (originalMethod == null) {
						continue;
					}
					return methodInvoker.invoke(original, originalMethod, parameters);
				}

				return unskilledHandler.handle(originals, method, parameters);
			}
		}));
	}

}
