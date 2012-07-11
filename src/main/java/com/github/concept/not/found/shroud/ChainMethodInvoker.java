package com.github.concept.not.found.shroud;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ChainMethodInvoker extends DefaultMethodInvoker {

	private final List<Object> chains = new ArrayList<Object>();

	public ChainMethodInvoker(final List<Object> chains) {
		this.chains.addAll(chains);
	}

	@Override
	public Object invoke(final Object target, final Method method, final Object[] parameters) throws Exception {
		final Object result = super.invoke(target, method, parameters);
		for (final Object chain : chains) {
			for (final Method chainMethod : chain.getClass().getMethods()) {
				if (chainMethod == null) {
					continue;
				}
				if (!method.getName().equals(chainMethod.getName())) {
					continue;
				}

				return chainMethod.invoke(chain, new Object[] {
					result
				});
			}
		}
		return result;
	}
}
