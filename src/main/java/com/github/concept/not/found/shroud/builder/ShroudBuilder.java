package com.github.concept.not.found.shroud.builder;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.concept.not.found.shroud.Pretender;
import com.github.concept.not.found.shroud.method.handler.DefaultUnskilledHandler;
import com.github.concept.not.found.shroud.method.handler.UnskilledHandler;
import com.github.concept.not.found.shroud.method.invoker.AdviceMethodInvoker;
import com.github.concept.not.found.shroud.method.invoker.MethodInvoker;
import com.github.concept.not.found.shroud.method.resolver.ChainedMethodResolver;
import com.github.concept.not.found.shroud.method.resolver.DefaultMethodResolver;
import com.github.concept.not.found.shroud.method.resolver.ExposedMethodResolver;
import com.github.concept.not.found.shroud.method.resolver.MapMethodResolver;
import com.github.concept.not.found.shroud.method.resolver.MethodResolver;
import com.github.concept.not.found.shroud.proxy.CglibProxy;
import com.github.concept.not.found.shroud.proxy.Proxy;

/**
 * Provides an fluent builder for this library.
 */
public class ShroudBuilder {

	private final List<Object> backingInstances = new ArrayList<Object>();
	private boolean defaultExpose = true;
	private final List<Method> exposed = new ArrayList<Method>();
	private final List<Object> advices = new ArrayList<Object>();
	private final Map<String, String> maps = new HashMap<String, String>();

	public ShroudBuilder(final Object... backingInstances) {
		this.backingInstances.addAll(Arrays.asList(backingInstances));
		for (final Object backingInstance : backingInstances) {
			final Method[] methods = backingInstance.getClass().getMethods();
			exposed.addAll(Arrays.asList(methods));
		}
	}

	public <T> T as(final Class<T> interfaceClass) {
		final Proxy proxy = new CglibProxy();
		final MethodInvoker methodInvoker = new AdviceMethodInvoker(advices);
		final MethodResolver methodResolver = new ExposedMethodResolver(new ChainedMethodResolver(new MapMethodResolver(maps), new DefaultMethodResolver()), exposed);
		final UnskilledHandler unskilledHandler = new DefaultUnskilledHandler();
		return new Pretender(proxy, backingInstances, methodInvoker, methodResolver, unskilledHandler).pretend(interfaceClass);
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

	public ShroudBuilder map(final String sourceMethod, final String destinationMethod) {
		maps.put(sourceMethod, destinationMethod);
		return this;
	}
}
