package org.jnario.lib;

import java.util.Objects;

/*
 * copied from org.junit
 */
public class Assert {
	
	/**
	 * Asserts that a condition is true. If it isn't it throws an
	 * {@link AssertionError} with the given message.
	 * 
	 * @param message
	 *            the identifying message for the {@link AssertionError} (<code>null</code>
	 *            okay)
	 * @param condition
	 *            condition to be checked
	 */
	static public void assertTrue(String message, boolean condition) {
		if (!condition)
			fail((message != null && !message.isEmpty()) ? message : 
				"The condition cannot be false.");
	}
	
	/**
	 * Asserts that a condition is true. If it isn't it throws an
	 * {@link AssertionError} with the given message.
	 * 
	 * @param condition
	 *            condition to be checked
	 */
	static public void assertTrue(boolean condition) {
		assertTrue(null, condition);
	}

	/**
	 * Asserts that a condition is false. If it isn't it throws an
	 * {@link AssertionError} with the given message.
	 * 
	 * @param message
	 *            the identifying message for the {@link AssertionError} (<code>null</code>
	 *            okay)
	 * @param condition
	 *            condition to be checked
	 */
	static public void assertFalse(String message, boolean condition) {
		if (condition)
			fail((message != null && !message.isEmpty()) ? message : 
				"The condition cannot be true.");
	}
	
	/**
	 * Asserts that a condition is false. If it isn't it throws an
	 * {@link AssertionError} with the given message.
	 * 
	 * @param condition
	 *            condition to be checked
	 */
	static public void assertFalse(boolean condition) {
		assertFalse(null, condition);
	}

	/**
	 * Fails a test with the given message.
	 * 
	 * @param message
	 *            the identifying message for the {@link AssertionError} (<code>null</code>
	 *            okay)
	 * @see AssertionError
	 */
	static public void fail(String message) {
		if (message == null || message.isEmpty())
			throw new AssertionError();
		throw new AssertionError(message);
	}
	
	/**
	 * Asserts that an object isn't null. If it is an {@link AssertionError} is
	 * thrown with the given message.
	 * 
	 * @param message
	 *            the identifying message for the {@link AssertionError} (<code>null</code>
	 *            okay)
	 * @param object
	 *            Object to check or <code>null</code>
	 */
	static public void assertNotNull(String message, Object object) {
		assertTrue(message, object != null);
	}

	/**
	 * Asserts that an object isn't null. If it is an {@link AssertionError} is
	 * thrown.
	 * 
	 * @param object
	 *            Object to check or <code>null</code>
	 */
	static public void assertNotNull(Object object) {
		assertNotNull("The object cannot be null.", object);
	}

	/**
	 * Asserts that an object is null. If it is not, an {@link AssertionError}
	 * is thrown with the given message.
	 * 
	 * @param message
	 *            the identifying message for the {@link AssertionError} (<code>null</code>
	 *            okay)
	 * @param object
	 *            Object to check or <code>null</code>
	 */
	static public void assertNull(String message, Object object) {
		assertTrue(message, object == null);
	}

	/**
	 * Asserts that an object is null. If it isn't an {@link AssertionError} is
	 * thrown.
	 * 
	 * @param object
	 *            Object to check or <code>null</code>
	 */
	static public void assertNull(Object object) {
		assertNull("The value cannot be an object instance", object);
	}

	/**
	 * Asserts that an object is the same as another other.
	 * If it isn't an {@link AssertionError} is
	 * thrown.
	 * 
	 * @param message
	 *            error message.
	 * @param expected
	 *            the expected value of the object.
	 * @param actual
	 *            the actual value of the object.
	 */
	static public void assertSame(String message, Object expected, Object actual) {
		assertTrue(
				((message == null || message.isEmpty()) ?
					"Objects are not the same." : message)
				+ "Expected: " + expected
				+ ". Actual: " + actual,
				(expected == actual));
	}

	/**
	 * Asserts that an object is the same as another other.
	 * If it isn't an {@link AssertionError} is
	 * thrown.
	 * 
	 * @param expected
	 *            the expected value of the object.
	 * @param actual
	 *            the actual value of the object.
	 */
	static public void assertSame(Object expected, Object actual) {
		assertSame(null, expected, actual);
	}

	/**
	 * Asserts that an object is equal to another other.
	 * If it isn't an {@link AssertionError} is
	 * thrown.
	 * 
	 * @param message
	 *            error message.
	 * @param expected
	 *            the expected value of the object.
	 * @param actual
	 *            the actual value of the object.
	 */
	static public void assertEquals(String message, Object expected, Object actual) {
		assertTrue(
				((message == null || message.isEmpty()) ?
					"Objects are not equal." : message)
				+ "Expected: " + expected
				+ ". Actual: " + actual,
				Objects.equals(expected, actual));
	}

	/**
	 * Asserts that an object is equal to another other.
	 * If it isn't an {@link AssertionError} is
	 * thrown.
	 * 
	 * @param expected
	 *            the expected value of the object.
	 * @param actual
	 *            the actual value of the object.
	 */
	static public void assertEquals(Object expected, Object actual) {
		assertEquals(null, expected, actual);
	}

}
