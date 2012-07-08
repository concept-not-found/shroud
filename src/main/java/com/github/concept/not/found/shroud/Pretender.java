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

	private static final MethodResolver DEFAULT_METHOD_RESOLVER = new MethodResolver() {
		public Method resolve(final Object original, final Method target, final Object[] paremeters) {
			for (final Method originalMethod : original.getClass().getMethods()) {
				boolean methodNamesMatch = originalMethod.getName().equals(target.getName());
				if (methodNamesMatch) {
					boolean parametersMatch = Arrays.asList(originalMethod.getParameterTypes()).equals(Arrays.asList(target.getParameterTypes()));
					if (parametersMatch) {
						return originalMethod;
					}
				}
			}
			return null;
		}
	};
	public final Object original;
	public final List<Method> exposed;
	public final MethodResolver methodResolver = DEFAULT_METHOD_RESOLVER;

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
			public Object invoke(final Object proxy, final Method method, final Object[] parameters) throws Throwable {
				final Method originalMethod = methodResolver.resolve(original, method, parameters);
				if (originalMethod == null || !exposed.contains(originalMethod)) {
					return unskilledHandler.handle(original, method, parameters);
				}
				return originalMethod.invoke(original, parameters);
			}
		});
	}

}
