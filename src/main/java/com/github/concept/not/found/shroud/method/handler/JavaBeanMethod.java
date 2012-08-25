package com.github.concept.not.found.shroud.method.handler;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.apache.commons.lang.StringUtils;

/**
 * This class is for matching methods to JavaBeanMethods and invoking them.
 */
public enum JavaBeanMethod {

	is() {
		@Override
		boolean matches(final Method method, final Object[] args) {
			return endsWithCapitalizedProperty(method, name());
		}

		@Override
		public Object invoke(final Object instance, final Field field, final Object[] args) throws Exception {
			return field.getBoolean(instance);
		}

		@Override
		String getProperty(final Method method) {
			return getProperty(method, name());
		}
	},
	get() {
		@Override
		boolean matches(final Method method, final Object[] args) {
			return endsWithCapitalizedProperty(method, name());
		}

		@Override
		public Object invoke(final Object instance, final Field field, final Object[] args) throws Exception {
			return field.get(instance);
		}

		@Override
		String getProperty(final Method method) {
			return getProperty(method, name());
		}
	},
	set() {
		@Override
		boolean matches(final Method method, final Object[] args) {
			return args.length == 1 && endsWithCapitalizedProperty(method, name());
		}

		@Override
		public Object invoke(final Object instance, final Field field, final Object[] args) throws Exception {
			field.set(instance, args[0]);
			return null;
		}

		@Override
		String getProperty(final Method method) {
			return getProperty(method, name());
		}
	};

	abstract Object invoke(final Object instance, final Field field, final Object[] args) throws Exception;

	abstract boolean matches(final Method method, final Object[] args);

	abstract String getProperty(final Method method);

	protected static boolean endsWithCapitalizedProperty(final Method method, final String prefix) {
		return method.getName().matches(prefix + "[A-Z].*");
	}

	protected static String getProperty(final Method method, final String prefix) {
		return StringUtils.uncapitalize(method.getName().substring(prefix.length()));
	}

}
