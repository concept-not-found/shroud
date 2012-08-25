package com.github.concept.not.found.shroud.method.handler;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/**
 * JavaBeanUnskilledHandler attempts to match JavaBean accessors to declared fields, regardless of their visibility.
 */
public class JavaBeanUnskilledHandler extends DefaultUnskilledHandler {

	@Override
	public Object handle(final List<Object> originals, final Method method, final Object[] args) throws Exception {
		for (final JavaBeanMethod javaBeanMethod : JavaBeanMethod.values()) {
			if (javaBeanMethod.matches(method, args)) {
				final String property = javaBeanMethod.getProperty(method);
				for (final Object original : originals) {
					final Field field = findField(original, property);
					if (field == null) {
						continue;
					}

					field.setAccessible(true);
					return javaBeanMethod.invoke(original, field, args);
				}
			}
		}
		return super.handle(originals, method, args);
	}

	private Field findField(final Object original, final String property) {
		try {
			return original.getClass().getDeclaredField(property);
		} catch (NoSuchFieldException e) {
			return null;
		}
	}
}
