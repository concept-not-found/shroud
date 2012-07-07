package com.github.concept.not.found.shroud;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Arrays;

import net.sf.cglib.proxy.InvocationHandler;
import net.sf.cglib.proxy.Proxy;

public class Pretender implements Serializable {

	private static final UnskilledHandler UNSUPPORTED_UNSKILLED_HANDLER = new UnskilledHandler() {
		public Object handle(final Object original, final Method method, final Object[] args) {
			throw new UnsupportedOperationException(method.toString());
		}
	};

	public final Object original;

	public Pretender(final Object original) {
		this.original = original;
	}

	public static <T> T pretend(final Object original, final Class<T> pretendInterface) {
		return new Pretender(original).pretend(pretendInterface);
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
				if (originalMethod == null) {
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
