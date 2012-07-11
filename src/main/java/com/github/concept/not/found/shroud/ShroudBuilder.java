package com.github.concept.not.found.shroud;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShroudBuilder {

	private final List<Object> backingInstances = new ArrayList<Object>();
	private boolean defaultExpose = true;
	private final List<Method> exposed = new ArrayList<Method>();
	private final List<Object> advices = new ArrayList<Object>();

	public ShroudBuilder(final Object... backingInstances) {
		this.backingInstances.addAll(Arrays.asList(backingInstances));
		for (final Object backingInstance : backingInstances) {
			final Method[] methods = backingInstance.getClass().getMethods();
			exposed.addAll(Arrays.asList(methods));
		}
	}

	public <T> T as(final Class<T> interfaceClass) {
		final MethodInvoker methodInvoker = new AdviceMethodInvoker(advices);
		final ExposedMethodResolver methodResolver = new ExposedMethodResolver(exposed);
		final DefaultUnskilledHandler unskilledHandler = new DefaultUnskilledHandler();
		return new Pretender(backingInstances, methodInvoker, methodResolver, unskilledHandler).pretend(interfaceClass);
	}

	public ShroudBuilder expose(final String methodName) {
		if (defaultExpose) {
			exposed.clear();
			defaultExpose = false;
		}
		for (final Object backingInstance : backingInstances) {
			final Method[] methods = backingInstance.getClass().getMethods();
			for (final Method method : methods) {
				if (method.getName().equals(methodName)) {
					exposed.add(method);
				}
			}
		}
		return this;
	}

	public ShroudBuilder advisedBy(final Object advice) {
		advices.add(advice);
		return this;
	}
}
