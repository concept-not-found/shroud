package com.github.concept.not.found;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Test;

import com.github.concept.not.found.shroud.Pretender;
import com.github.concept.not.found.shroud.method.handler.UnskilledHandler;
import com.github.concept.not.found.shroud.method.invoker.DefaultMethodInvoker;
import com.github.concept.not.found.shroud.method.resolver.DefaultMethodResolver;
import com.github.concept.not.found.shroud.proxy.CglibProxy;

public class UnskilledHandlerTest {

	@Test(expected = Exception.class)
	public void test() {
		Pretender pretender = new Pretender(new CglibProxy(), Arrays.asList(new Object()), new DefaultMethodInvoker(), new DefaultMethodResolver(), new UnskilledHandler() {
			public Object handle(final List<Object> originals, final Method method, final Object[] args) throws Exception {
				throw new Exception("allow non runtime exceptions to be thrown");
			}
		});

		@SuppressWarnings("unchecked")
		Collection<Object> collection = pretender.pretend(Collection.class);
		collection.clear();
	}
}
