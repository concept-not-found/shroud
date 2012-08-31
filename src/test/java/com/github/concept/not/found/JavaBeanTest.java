package com.github.concept.not.found;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import com.github.concept.not.found.shroud.Shroud;

public class JavaBeanTest {

	@SuppressWarnings("unused")
	public class PropertiesOnly {
		private final boolean secure = true;

		private final String characterEncoding = "utf-8";
	}

	@Test
	public void test() throws Exception {
		final PropertiesOnly properties = new PropertiesOnly();
		final HttpServletRequest request = Shroud.shroud(properties).as(HttpServletRequest.class);
		assertTrue(request.isSecure());

		assertEquals("utf-8", request.getCharacterEncoding());
		request.setCharacterEncoding("yoda-speak");
		assertEquals("yoda-speak", ReflectionTestUtils.getField(properties, "characterEncoding"));
	}
}
