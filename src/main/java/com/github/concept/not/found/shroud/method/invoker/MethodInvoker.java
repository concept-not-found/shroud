package com.github.concept.not.found.shroud.method.invoker;

import java.lang.reflect.Method;

public interface MethodInvoker {

	Object invoke(Object target, Method method, Object[] parameters) throws Exception;
}
