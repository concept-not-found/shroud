package com.github.concept.not.found.shroud;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShroudBuilder {

	private final Object backingIntance;
	private boolean defaultExpose = true;
	private final List<Method> exposed = new ArrayList<Method>();

	public ShroudBuilder(final Object backingIntance) {
		this.backingIntance = backingIntance;
		exposed.addAll(Arrays.asList(backingIntance.getClass().getMethods()));
	}

	public <T> T as(final Class<T> interfaceClass) {
		final ExposedMethodResolver methodResolver = new ExposedMethodResolver(exposed);
		final DefaultUnskilledHandler unskilledHandler = new DefaultUnskilledHandler();
		return new Pretender(backingIntance, methodResolver, unskilledHandler).pretend(interfaceClass);
	}

	public ShroudBuilder expose(final String methodName) {
		if (defaultExpose) {
			exposed.clear();
			defaultExpose = false;
		}
		for (final Method method : backingIntance.getClass().getMethods()) {
			if (method.getName().equals(methodName)) {
				exposed.add(method);
			}
		}
		return this;
	}
}
