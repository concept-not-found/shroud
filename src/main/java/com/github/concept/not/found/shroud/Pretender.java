package com.github.concept.not.found.shroud;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.List;

import net.sf.cglib.proxy.InvocationHandler;
import net.sf.cglib.proxy.Proxy;

public class Pretender implements Serializable {

	private final List<Object> originals;

	private final MethodInvoker methodInvoker;

	private final MethodResolver methodResolver;

	private final UnskilledHandler unskilledHandler;

	public Pretender(final List<Object> originals, final MethodInvoker methodInvoker, final MethodResolver methodResolver, final UnskilledHandler unskilledHandler) {
		this.originals = originals;
		this.methodInvoker = methodInvoker;
		this.methodResolver = methodResolver;
		this.unskilledHandler = unskilledHandler;
	}

	public <T> T pretend(final Class<T> pretendInterface) {
		return pretendInterface.cast(Proxy.newProxyInstance(pretendInterface.getClassLoader(), new Class[] {
			pretendInterface
		}, new InvocationHandler() {
			public Object invoke(final Object proxy, final Method method, final Object[] parameters) throws Throwable {
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
