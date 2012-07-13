package com.github.concept.not.found.shroud.method.handler;

import java.lang.reflect.Method;
import java.util.List;

public interface UnskilledHandler {

	Object handle(List<Object> originals, Method method, Object[] args);

}
