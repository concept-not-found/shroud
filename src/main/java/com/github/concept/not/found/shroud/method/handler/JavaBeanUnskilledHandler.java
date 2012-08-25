package com.github.concept.not.found.shroud.method.handler;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * JavaBeanUnskilledHandler attempts to match JavaBean accessors to declared fields, regardless of their visibility.
 */
public class JavaBeanUnskilledHandler extends DefaultUnskilledHandler {

	@Override
	public Object handle(final List<Object> originals, final Method method, final Object[] args) throws Exception {

		final String methodName = method.getName();
		if (methodName.matches("get[A-Z].*")) {
			final String property = getProperty(methodName, "get");
			for (final Object original : originals) {
				final Field field = findField(original, property);
				if (field != null) {
					field.setAccessible(true);
					return field.get(original);
				}
			}
		} else if (methodName.matches("is[A-Z].*")) {
			final String property = getProperty(methodName, "is");
			for (final Object original : originals) {
				final Field field = findField(original, property);
				if (field != null) {
					field.setAccessible(true);
					return field.getBoolean(original);
				}
			}
		} else if (args.length == 1 && methodName.matches("set[A-Z].*")) {
			final String property = getProperty(methodName, "set");
			for (final Object original : originals) {
				final Field field = findField(original, property);
				if (field != null) {
					field.setAccessible(true);
					field.set(original, args[0]);
					return null;
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

	private String getProperty(final String methodName, final String prefix) {
		return StringUtils.uncapitalize(methodName.substring(prefix.length()));
	}
}
