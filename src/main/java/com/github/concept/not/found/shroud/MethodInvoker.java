package com.github.concept.not.found.shroud;

import java.lang.reflect.Method;

public interface MethodInvoker {

	Object invoke(Object target, Method method, Object[] parameters) throws Exception;
}
