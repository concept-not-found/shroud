package com.github.concept.not.found.shroud.performance;

import com.sun.japex.JapexDriverBase;
import com.sun.japex.TestCase;

public class ControlMethodInvocation extends JapexDriverBase {

	private Count count;

	@Override
	public void prepare(final TestCase testCase) {
		count = new ImplementsInterfaceCount();
	}

	@Override
	public void run(final TestCase testCase) {
		count.next();
	}
}
