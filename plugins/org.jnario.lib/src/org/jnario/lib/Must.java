/*******************************************************************************
 * Copyright (c) 2014 BMW Car IT and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.jnario.lib;

import java.util.Iterator;
import java.util.ListIterator;

import org.eclipse.xtext.xbase.lib.Functions;
import org.hamcrest.Matcher;


/**
 * Tools that provides assertion tools for writting the
 * specifications.
 * The provided tools are similar to the ones of {@link Should}
 * but they are failing based on the API provided by
 * {@link org.junit.Assert}.
 * 
 * @author Stéphane Galland
 */
public final class Must extends org.junit.Assert {

	private Must() {
		//
	}

	/** Ensure that the given object is matching a predicate.
	 *
	 * @param <T> - type of the object.
	 * @param obj - the object to test.
	 * @param func - the predicate.
	 * @return obj
	 */
	public static <T> T mustBe(T obj, Functions.Function1<T, Boolean> func) {
		assertTrue(Should.should_be(obj, func));
		return obj;
	}

	/** Ensure that the given object is equal to another object.
	 *
	 * @param <T> - type of the object.
	 * @param actual - the object to test.
	 * @param expected - the expected object.
	 * @return actual
	 */
	public static <T> T mustBe(T actual, Object expected) {
		assertTrue(Should.should_be(actual, expected));
		return actual;
	}

	/** Ensure that the given type is equals another type.
	 *
	 * @param <T> - expected of the object.
	 * @param actual - the type to test.
	 * @param expectedType - the expected type.
	 * @return actual
	 */
	public static <T> Class<T> mustBe(Class<T> actual, Class<?> expectedType) {
		assertTrue(Should.should_be(actual, expectedType));
		return actual;
	}

	/** Ensure that the given object is of the given type.
	 *
	 * @param <T> - expected type of the object.
	 * @param actual - the object to test.
	 * @param expectedType - the type.
	 * @return actual
	 */
	public static <T> T mustBe(Object actual, Class<T> expectedType) {
		assertTrue(Should.should_be(actual, expectedType));
		return expectedType.cast(actual);
	}

	/** Ensure that the given object is matching the given predicate.
	 *
	 * @param <T> - type of the object.
	 * @param actual - the object to test.
	 * @param matcher - the predicate.
	 * @return actual
	 */
	public static <T> T mustBe(T actual, Matcher<? super T> matcher) {
		assertTrue(Should.should_be(actual, matcher));
		return actual;
	}

	/** Ensure that the given iterable object contains an element equals to the
	 * given value.
	 *
	 * @param <T> - type of the elements in the collection.
	 * @param <I> - type of the collection.
	 * @param actual - the collection to test.
	 * @param element - the element that must be inside the collection.
	 * @return actual
	 */
	public static <T, I extends Iterable<T>> I mustContain(I actual, T element) {
		assertTrue(Should.should_be(actual, element));
		return actual;
	}

	/** Ensure that the given iterable object contains an element that is
	 * matching the given predicate.
	 *
	 * @param <T> - type of the elements in the collection.
	 * @param <I> - type of the collection.
	 * @param collection - the collection to test.
	 * @param matcher - the predicate.
	 * @return collection
	 */
	public static <T, I extends Iterable<T>> I mustContain(I collection, Matcher<? super T> matcher) {
		assertTrue(Should.should_contain(collection, matcher));
		return collection;
	}

	/** Ensure that the given array contains an element equals to the
	 * given value.
	 *
	 * @param <T> - type of the elements in the collection.
	 * @param actual - the collection to test.
	 * @param element - the element that must be inside the collection.
	 * @return actual
	 */
	public static <T> T[] mustContain(T[] actual, T element) {
		assertTrue(Should.should_contain(actual, element));
		return actual;
	}

	/** Ensure that the given array contains an element equals to the
	 * given value.
	 *
	 * @param actual - the collection to test.
	 * @param element - the element that must be inside the collection.
	 * @return actual
	 */
	public static boolean[] mustContain(boolean[] actual, boolean element) {
		assertTrue(Should.should_contain(actual, element));
		return actual;
	}

	/** Ensure that the given array contains an element equals to the
	 * given value.
	 *
	 * @param actual - the collection to test.
	 * @param element - the element that must be inside the collection.
	 * @return actual
	 */
	public static char[] mustContain(char[] actual, char element) {
		assertTrue(Should.should_contain(actual, element));
		return actual;
	}

	/** Ensure that the given array contains an element equals to the
	 * given value.
	 *
	 * @param actual - the collection to test.
	 * @param element - the element that must be inside the collection.
	 * @return actual
	 */
	public static byte[] mustContain(byte[] actual, byte element) {
		assertTrue(Should.should_contain(actual, element));
		return actual;
	}

	/** Ensure that the given array contains an element equals to the
	 * given value.
	 *
	 * @param actual - the collection to test.
	 * @param element - the element that must be inside the collection.
	 * @return actual
	 */
	public static short[] mustContain(short[] actual, short element) {
		assertTrue(Should.should_contain(actual, element));
		return actual;
	}

	/** Ensure that the given array contains an element equals to the
	 * given value.
	 *
	 * @param actual - the collection to test.
	 * @param element - the element that must be inside the collection.
	 * @return actual
	 */
	public static int[] mustContain(int[] actual, int element) {
		assertTrue(Should.should_contain(actual, element));
		return actual;
	}

	/** Ensure that the given array contains an element equals to the
	 * given value.
	 *
	 * @param actual - the collection to test.
	 * @param element - the element that must be inside the collection.
	 * @return actual
	 */
	public static long[] mustContain(long[] actual, long element) {
		assertTrue(Should.should_contain(actual, element));
		return actual;
	}

	/** Ensure that the given array contains an element equals to the
	 * given value.
	 *
	 * @param actual - the collection to test.
	 * @param element - the element that must be inside the collection.
	 * @return actual
	 */
	public static float[] mustContain(float[] actual, float element) {
		assertTrue(Should.should_contain(actual, element));
		return actual;
	}

	/** Ensure that the given array contains an element equals to the
	 * given value.
	 *
	 * @param actual - the collection to test.
	 * @param element - the element that must be inside the collection.
	 * @return actual
	 */
	public static double[] mustContain(double[] actual, double element) {
		assertTrue(Should.should_contain(actual, element));
		return actual;
	}

	/** Ensure that the given string contains a substring.
	 *
	 * @param <T> - type of the string.
	 * @param actual - the string to test.
	 * @param substring - the expected substring.
	 * @return actual
	 */
	public static <T extends CharSequence> T mustContain(T actual, CharSequence substring) {
		assertTrue(Should.should_contain(actual, substring));
		return actual;
	}

	/** Ensure that the given object is a boolean object equals to the given value.
	 *
	 * @param <T> - type of the object.
	 * @param actual - the object to test.
	 * @param expected - the expected value.
	 * @return actual
	 */
	public static <T> T mustBe(T actual, boolean expected) {
		assertTrue(Should.should_be(actual, expected));
		return actual;
	}

	/** Ensure that the given object is a byte equals to the given value.
	 *
	 * @param <T> - type of the object.
	 * @param actual - the object to test.
	 * @param expected - the expected value.
	 * @return actual
	 */
	public static <T> T mustBe(T actual, byte expected) {
		assertTrue(Should.should_be(actual, expected));
		return actual;
	}

	/** Ensure that the given object is a short integer equals to the given value.
	 *
	 * @param <T> - type of the object.
	 * @param actual - the object to test.
	 * @param expected - the expected value.
	 * @return actual
	 */
	public static <T> T mustBe(T actual, short expected) {
		assertTrue(Should.should_be(actual, expected));
		return actual;
	}

	/** Ensure that the given object is an integer equals to the given value.
	 *
	 * @param <T> - type of the object.
	 * @param actual - the object to test.
	 * @param expected - the expected value.
	 * @return actual
	 */
	public static <T> T mustBe(T actual, int expected) {
		assertTrue(Should.should_be(actual, expected));
		return actual;
	}

	/** Ensure that the given object is a long integer equals to the given value.
	 *
	 * @param <T> - type of the object.
	 * @param actual - the object to test.
	 * @param expected - the expected value.
	 * @return actual
	 */
	public static <T> T mustBe(T actual, long expected) {
		assertTrue(Should.should_be(actual, expected));
		return actual;
	}

	/** Ensure that the given object is a single-precision
	 * floating point number equals to the given value.
	 *
	 * @param <T> - type of the object.
	 * @param actual - the object to test.
	 * @param expected - the expected value.
	 * @return actual
	 */
	public static <T> T mustBe(T actual, float expected) {
		assertTrue(Should.should_be(actual, expected));
		return actual;
	}

	/** Ensure that the given object is a double-precision
	 * floating point number equals to the given value.
	 *
	 * @param <T> - type of the object.
	 * @param actual - the object to test.
	 * @param expected - the expected value.
	 * @return actual
	 */
	public static <T> T mustBe(T actual, double expected) {
		assertTrue(Should.should_be(actual, expected));
		return actual;
	}

	/** Ensure that the given object is a character equals to the given value.
	 *
	 * @param <T> - type of the object.
	 * @param actual - the object to test.
	 * @param expected - the expected value.
	 * @return actual
	 */
	public static <T> T mustBe(T actual, char expected) {
		assertTrue(Should.should_be(actual, expected));
		return actual;
	}

	/** Ensure that the given object is a single-precision
	 * floating point number equals to the given value.
	 *
	 * @param <T> - type of the object.
	 * @param actual - the object to test.
	 * @param expected - the expected value.
	 * @return actual
	 */
	public static <T> T mustBe(T actual, String expected) {
		assertTrue(Should.should_be(actual, expected));
		return actual;
	}

	/** Ensure that the given string starts with the given substring.
	 *
	 * @param <T> - type of the string.
	 * @param s - the string to test.
	 * @param substring - the string to be at the beginning.
	 * @return s
	 */
	public static <T extends CharSequence> T mustStartWith(T s, String substring) {
		assertTrue(Should.should_startWith(s, substring));
		return s;
	}

	/** Ensure that the given string ends with the given substring.
	 *
	 * @param <T> - type of the string.
	 * @param s - the string to test.
	 * @param substring - the string to be at the end.
	 * @return s
	 */
	public static <T extends CharSequence> T mustEndWith(T s, String substring) {
		assertTrue(Should.should_endWith(s, substring));
		return s;
	}

	/** Ensure that the given string does not start with the given substring.
	 *
	 * @param <T> - type of the string.
	 * @param s - the string to test.
	 * @param substring - the string to be at the beginning.
	 * @return s
	 */
	public static <T extends CharSequence> T mustNotStartWith(T s, String substring) {
		assertFalse(Should.should_startWith(s, substring));
		return s;
	}

	/** Ensure that the given string does not end with the given substring.
	 *
	 * @param <T> - type of the string.
	 * @param s - the string to test.
	 * @param substring - the string to be at the end.
	 * @return s
	 */
	public static <T extends CharSequence> T mustNotEndWith(T s, String substring) {
		assertFalse(Should.should_endWith(s, substring));
		return s;
	}

	/** Ensure that the given caller specification has a valid link
	 * to another Jnario specification with the given name.
	 *
	 * @param referencedLink - URL
	 * @return refencedLink
	 */
	public static String mustBeJnarioLink(String referencedLink) {
		assertTrue(Should.should_beJnarioLink(referencedLink));
		return referencedLink;
	}

	/** Ensure that the given string is a valid hyper-link.
	 * The link must start with "http://" and not end with "/".
	 *
	 * @param referencedLink - the string to test.
	 * @return refencedLink
	 */
	public static String mustBeHttpLink(String referencedLink) {
		assertTrue(Should.should_beHttpLink(referencedLink));
		return referencedLink;
	}

	/** Ensure that the given string is a MAven snapshot version.
	 *
	 * @param version - the version string.
	 * @return version
	 */
	public static String mustBeSnapshotVersion(String version) {
		assertTrue(Should.should_beSnapshotVersion(version));
		return version;
	}

	/** Ensure that the given string is not a MAven snapshot version.
	 *
	 * @param version - the version string.
	 * @return version
	 */
	public static String mustNotBeSnapshotVersion(String version) {
		assertFalse(Should.should_beSnapshotVersion(version));
		return version;
	}

	/** Ensure that the given caller specification has a valid link
	 * to a picture with the given name.
	 *
	 * @param referencedLink - URL
	 * @return refencedLink
	 */
	public static String mustBePicture(String referencedLink) {
		assertTrue(Should.should_bePicture(referencedLink));
		return referencedLink;
	}

	/** Ensure that the given string is a string representation of an integer,
	 * without sign symbols.
	 *
	 * @param str - the string.
	 * @return str
	 */
	public static String mustBeInteger(String str) {
		assertTrue(Should.should_beInteger(str));
		return str;
	}

	/** Ensure that the given string is a string representation of a date.
	 * A date has the format <code>yyyy-[m]m-[d]d</code>
	 *
	 * @param str - the string.
	 * @return str
	 */
	public static String mustBeDate(String str) {
		assertTrue(Should.should_beDate(str));
		return str;
	}

	/** Ensure that the iterator replies the expected values in the given order.
	 *
	 * @param <T> - the type of the elements in the iterator.
	 * @param actual - the iterator to test.
	 * @param expected - the expected values.
	 * @return actual
	 */
	public static <T> Iterator<T> mustContain(Iterator<T> actual, Object[] expected) {
		assertTrue(Should.should_contain(actual, expected));
		return actual;
	}

	/** Ensure that the iterator replies the expected values in the given order.
	 *
	 * @param <T> - the type of the elements in the iterator.
	 * @param actual - the iterator to test.
	 * @param expected - the expected values.
	 * @return actual
	 */
	public static <T> ListIterator<T> mustContain(ListIterator<T> actual, Object[] expected) {
		assertTrue(Should.should_contain(actual, expected));
		return actual;
	}

}
