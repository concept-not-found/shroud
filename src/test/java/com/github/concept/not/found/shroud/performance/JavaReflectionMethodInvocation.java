package com.github.concept.not.found.shroud.performance;

import java.util.Arrays;

import com.github.concept.not.found.shroud.Pretender;
import com.github.concept.not.found.shroud.method.handler.DefaultUnskilledHandler;
import com.github.concept.not.found.shroud.method.invoker.DefaultMethodInvoker;
import com.github.concept.not.found.shroud.method.resolver.DefaultMethodResolver;
import com.github.concept.not.found.shroud.proxy.ReflectionProxy;
import com.sun.japex.JapexDriverBase;
import com.sun.japex.TestCase;

public class JavaReflectionMethodInvocation extends JapexDriverBase {
	private Count count;

	@Override
	public void prepare(final TestCase testCase) {
		count = new Pretender(new ReflectionProxy(), Arrays.<Object> asList(new StandaloneCount()), new DefaultMethodInvoker(), new DefaultMethodResolver(), new DefaultUnskilledHandler()).pretend(Count.class);
	}

	@Override
	public void run(final TestCase testCase) {
		count.next();
	}
}
