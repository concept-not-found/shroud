package com.github.concept.not.found.shroud;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class AdviceMethodInvoker implements MethodInvoker {

	private final List<Object> advices = new ArrayList<Object>();

	public AdviceMethodInvoker(final List<Object> advices) {
		this.advices.addAll(advices);
	}

	public Object invoke(final Object target, final Method method, final Object[] parameters) throws Exception {
		for (final Object advice : advices) {
			for (final Method adviceMethod : advice.getClass().getMethods()) {
				if (adviceMethod == null) {
					continue;
				}
				if (!method.getName().equals(adviceMethod.getName())) {
					continue;
				}

				return adviceMethod.invoke(advice, new Object[] {
					target,
					method,
					parameters
				});
			}
		}
		return method.invoke(target, parameters);
	}
}
