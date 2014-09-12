/*******************************************************************************
 * Copyright (c) 2014 BMW Car IT and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.jnario.lib.tests;

import java.util.Iterator;
import java.util.Objects;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.jnario.lib.Assert;

public class TestMatcher<T> implements Matcher<T> {

	private final T expectedValue;
	private final Iterable<T> iterable;
	private final boolean value;
	
	public TestMatcher(boolean value, T expectedValue) {
		this.value = value;
		this.expectedValue = expectedValue;
		this.iterable = null;
	}

	public TestMatcher(boolean value, Iterable<T> expectedValues) {
		this.value = value;
		this.expectedValue = null;
		this.iterable = expectedValues;
	}

	public void describeTo(Description arg0) {
		throw new UnsupportedOperationException();
	}

	public void _dont_implement_Matcher___instead_extend_BaseMatcher_() {
		throw new UnsupportedOperationException();
	}

	public void describeMismatch(Object arg0, Description arg1) {
		throw new UnsupportedOperationException();
	}

	public boolean matches(Object arg0) {
		if (this.iterable != null) {
			Iterator<T> it = this.iterable.iterator();
			boolean found = false;
			while (!found && it.hasNext()) {
				T o = it.next();
				if (Objects.equals(o, arg0)) {
					found = true;
				}
			}
			Assert.assertTrue("matched element not expected.", found);
		} else if (this.expectedValue == null) {
			Assert.assertNull(arg0);
		} else {
			Assert.assertSame(this.expectedValue, arg0);
		}
		return this.value;
	}
	
}
