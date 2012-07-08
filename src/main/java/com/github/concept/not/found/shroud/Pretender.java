package com.github.concept.not.found.shroud;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import net.sf.cglib.proxy.InvocationHandler;
import net.sf.cglib.proxy.Proxy;

public class Pretender implements Serializable {

	private static final UnskilledHandler UNSUPPORTED_UNSKILLED_HANDLER = new UnskilledHandler() {
		public Object handle(final Object original, final Method method, final Object[] args) {
			throw new UnsupportedOperationException(method.toString());
		}
	};

	public final Object original;
	public final List<Method> exposed;

	public Pretender(final Object original, final List<Method> exposed) {
		this.original = original;
		this.exposed = exposed;
	}

	public <T> T pretend(final Class<T> pretendInterface) {
		return pretend(pretendInterface, UNSUPPORTED_UNSKILLED_HANDLER);
	}

	@SuppressWarnings("unchecked")
	public <T> T pretend(final Class<T> pretendInterface, final UnskilledHandler unskilledHandler) {
		return (T) Proxy.newProxyInstance(pretendInterface.getClassLoader(), new Class[] {
			pretendInterface
		}, new InvocationHandler() {
			public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
				final Method originalMethod = findMatchingMethod(method);
				if (originalMethod == null || !exposed.contains(originalMethod)) {
					return unskilledHandler.handle(original, method, args);
				}
				return originalMethod.invoke(original, args);
			}
		});
	}

	private Method findMatchingMethod(final Method method) {
		for (final Method originalMethod : original.getClass().getMethods()) {
			boolean methodNamesMatch = originalMethod.getName().equals(method.getName());
			if (methodNamesMatch) {
				boolean parametersMatch = Arrays.asList(originalMethod.getParameterTypes()).equals(Arrays.asList(method.getParameterTypes()));
				if (parametersMatch) {
					return originalMethod;
				}
			}
		}
		return null;
	}

}
