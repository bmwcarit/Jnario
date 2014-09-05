/*******************************************************************************
 * Copyright (c) 2014 BMW Car IT and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.jnario.lib;

import static com.google.common.collect.Iterables.contains;

import java.io.File;
import java.net.URL;
import java.text.DateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.regex.Pattern;

import org.eclipse.xtext.xbase.lib.Functions;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

import com.google.common.base.Objects;

/**
 * The default should matchers provided by Jnario. These 
 * can be used in Jnario for writing literate assertions. 
 * 
 * The name of a should method must conform to the following
 * convention: 
 * <p>
 * {@code should_xxxx(Type1 actual, Type2 expected)}.
 * </p>
 * Then it can be used in a spec in the following way:
 * <p>
 * {@code fact actual should xxxx expected}
 * </p>
 * 
 * 
 * @author Sebastian Benz - Initial contribution and API
 * @author Stéphane Galland
 */
public class Should{

	public static boolean operator_doubleArrow(Class<?> actual, Class<?> expected){
		return should_be(actual, expected);
	}

	public static <T> boolean operator_doubleArrow(T actual, T expected){
		return should_be(actual, expected);
	}

	public static boolean operator_doubleArrow(Object actual, Class<?> expectedType){
		return should_be(actual, expectedType);
	}

	public static <T> boolean operator_doubleArrow(T actual, Matcher<? super T> matcher){
		return should_be(actual, matcher);
	}

	public static <T> boolean operator_doubleArrow(T actual, boolean result){
		return should_be(actual, result);
	}

	private static boolean isArray(Object obj) {
		if(obj == null){
			return false;
		}
		return obj.getClass().isArray();
	}

	//	public static boolean operator_doubleArrow(Object actual, Class<?> expected) {
	//		return should_be(actual, expected);
	//	}

	//	public static <T> boolean operator_doubleArrow(T actual, Matcher<? super T> expected) {
	//		return should_be(actual, expected);
	//	}
	//	
	//	public static <T> boolean operator_doubleArrow(T actual, T expected) {
	//		return should_be(actual, expected);
	//	}

	public static <T> boolean should_be(T obj, Functions.Function1<T, Boolean> func){
		return func.apply(obj);
	}

	public static <T> boolean should_be(T actual, T expected){
		if(isArray(actual) && isArray(expected)){
			return Arrays.equals((Object[])actual, (Object[])expected);
		}
		return Objects.equal(actual, expected);
	}

	//	private static boolean haveSameTypeAndAreStrings(Object actual,
	//			Object expected) {
	//		return actual != null && expected != null && actual.getClass().equals(expected.getClass()) && actual instanceof CharSequence;
	//	}

	public static boolean should_be(Class<?> actual, Class<?> expectedType){
		return actual.equals(expectedType);
	}

	public static boolean should_be(Object actual, Class<?> expectedType){
		return expectedType.isInstance(actual);
	}

	public static <T> boolean should_be(T actual, Matcher<? super T> matcher){
		if(matcher == null){
			return actual == null;
		}
		return matcher.matches(actual);
	}

	public static <T> boolean should_contain(Iterable<T> actual, T element){
		return contains(actual, element);
	}

	public static <T> boolean should_contain(Iterable<T> collection, Matcher<? super T> matcher){
		for (T item : collection) {
			if (matcher.matches(item)){
				return true;
			}
		}
		return false;
	}

	public static <T> boolean should_contain(CharSequence actual, CharSequence substring){
		return actual.toString().contains(substring);
	}

	public static <T> boolean should_be(T actual, boolean result){
		if (actual instanceof Boolean) {
			return ((Boolean)actual).equals(result);
		}
		return result;
	}

	public static boolean should_startWith(CharSequence s, String substring){
		return s.toString().startsWith(substring);
	} 

	public static boolean should_endWith(CharSequence s, String substring){
		return s.toString().endsWith(substring);
	}

	public static <T> Matcher<? super T> matches(final String desc, final Functions.Function1<T, Boolean> matcher){
		return new BaseMatcher<T>() {

			public void describeTo(Description description) {
				description.appendText(desc);
			}

			@SuppressWarnings("unchecked")
			public boolean matches(Object item) {
				return matcher.apply((T) item);
			}
		};
	}

	/**
	 * A helper method to mark irrelevant values.
	 * 
	 * @return always null
	 */
	public static <T> T _(){
		return null;
	}

	public static <T> Matcher<T> nullValue(){
		return new BaseMatcher<T>() {

			public boolean matches(Object item) {
				return item == null;
			}

			public void describeTo(Description description) {
				description.appendText("null");
			}
		};
	}

	/** Ensure that the given array contains an element equals to the
	 * given value.
	 *
	 * @param <T> - type of the elements in the collection.
	 * @param actual - the collection to test.
	 * @param element - the element that must be inside the collection.
	 * @return <code>true</code> if the condition is validated.
	 */
	public static <T> boolean should_contain(T[] actual, T element) {
		if (actual != null) {
			for(T v : actual) {
				if ((v == null && element == null)
						|| (v != null && v.equals(element))) {
					return true;
				}
			}
		}
		return false;
	}

	/** Ensure that the given array contains an element equals to the
	 * given value.
	 *
	 * @param actual - the collection to test.
	 * @param element - the element that must be inside the collection.
	 * @return <code>true</code> if the condition is validated.
	 */
	public static boolean should_contain(boolean[] actual, boolean element) {
		if (actual != null) {
			for(boolean v : actual) {
				if (v == element) {
					return true;
				}
			}
		}
		return false;
	}

	/** Ensure that the given array contains an element equals to the
	 * given value.
	 *
	 * @param actual - the collection to test.
	 * @param element - the element that must be inside the collection.
	 * @return <code>true</code> if the condition is validated.
	 */
	public static boolean should_contain(char[] actual, char element) {
		if (actual != null) {
			for(char v : actual) {
				if (v == element) {
					return true;
				}
			}
		}
		return false;
	}

	/** Ensure that the given array contains an element equals to the
	 * given value.
	 *
	 * @param actual - the collection to test.
	 * @param element - the element that must be inside the collection.
	 * @return <code>true</code> if the condition is validated.
	 */
	public static boolean should_contain(byte[] actual, byte element) {
		if (actual != null) {
			for(byte v : actual) {
				if (v == element) {
					return true;
				}
			}
		}
		return false;
	}

	/** Ensure that the given array contains an element equals to the
	 * given value.
	 *
	 * @param actual - the collection to test.
	 * @param element - the element that must be inside the collection.
	 * @return <code>true</code> if the condition is validated.
	 */
	public static boolean should_contain(short[] actual, short element) {
		if (actual != null) {
			for(short v : actual) {
				if (v == element) {
					return true;
				}
			}
		}
		return false;
	}

	/** Ensure that the given array contains an element equals to the
	 * given value.
	 *
	 * @param actual - the collection to test.
	 * @param element - the element that must be inside the collection.
	 * @return <code>true</code> if the condition is validated.
	 */
	public static boolean should_contain(int[] actual, int element) {
		if (actual != null) {
			for(int v : actual) {
				if (v == element) {
					return true;
				}
			}
		}
		return false;
	}

	/** Ensure that the given array contains an element equals to the
	 * given value.
	 *
	 * @param actual - the collection to test.
	 * @param element - the element that must be inside the collection.
	 * @return <code>true</code> if the condition is validated.
	 */
	public static boolean should_contain(long[] actual, long element) {
		if (actual != null) {
			for(long v : actual) {
				if (v == element) {
					return true;
				}
			}
		}
		return false;
	}

	/** Ensure that the given array contains an element equals to the
	 * given value.
	 *
	 * @param actual - the collection to test.
	 * @param element - the element that must be inside the collection.
	 * @return <code>true</code> if the condition is validated.
	 */
	public static boolean should_contain(float[] actual, float element) {
		if (actual != null) {
			for(float v : actual) {
				if (v == element) {
					return true;
				}
			}
		}
		return false;
	}

	/** Ensure that the given array contains an element equals to the
	 * given value.
	 *
	 * @param actual - the collection to test.
	 * @param element - the element that must be inside the collection.
	 * @return <code>true</code> if the condition is validated.
	 */
	public static boolean should_contain(double[] actual, double element) {
		if (actual != null) {
			for(double v : actual) {
				if (v == element) {
					return true;
				}
			}
		}
		return false;
	}

	/** Ensure that the given object is a byte equals to the given value.
	 *
	 * @param <T> - type of the object.
	 * @param actual - the object to test.
	 * @param expected - the expected value.
	 * @return <code>true</code> if the condition is validated.
	 */
	public static boolean should_be(Object actual, byte expected) {
		if (actual instanceof Byte) {
			return Byte.valueOf(expected) == actual;
		}
		return false;
	}

	/** Ensure that the given object is a short integer equals to the given value.
	 *
	 * @param <T> - type of the object.
	 * @param actual - the object to test.
	 * @param expected - the expected value.
	 * @return <code>true</code> if the condition is validated.
	 */
	public static boolean should_be(Object actual, short expected) {
		if (actual instanceof Short) {
			return Short.valueOf(expected) == actual;
		}
		return false;
	}

	/** Ensure that the given object is an integer equals to the given value.
	 *
	 * @param <T> - type of the object.
	 * @param actual - the object to test.
	 * @param expected - the expected value.
	 * @return <code>true</code> if the condition is validated.
	 */
	public static boolean should_be(Object actual, int expected) {
		if (actual instanceof Integer) {
			return Integer.valueOf(expected) == actual;
		}
		return false;
	}

	/** Ensure that the given object is a long integer equals to the given value.
	 *
	 * @param <T> - type of the object.
	 * @param actual - the object to test.
	 * @param expected - the expected value.
	 * @return <code>true</code> if the condition is validated.
	 */
	public static boolean should_be(Object actual, long expected) {
		if (actual instanceof Long) {
			return Long.valueOf(expected) == actual;
		}
		return false;
	}

	/** Ensure that the given object is a single-precision
	 * floating point number equals to the given value.
	 *
	 * @param <T> - type of the object.
	 * @param actual - the object to test.
	 * @param expected - the expected value.
	 * @return <code>true</code> if the condition is validated.
	 */
	public static boolean should_be(Object actual, float expected) {
		if (actual instanceof Float) {
			return Float.valueOf(expected) == actual;
		}
		return false;
	}

	/** Ensure that the given object is a double-precision
	 * floating point number equals to the given value.
	 *
	 * @param <T> - type of the object.
	 * @param actual - the object to test.
	 * @param expected - the expected value.
	 * @return <code>true</code> if the condition is validated.
	 */
	public static boolean should_be(Object actual, double expected) {
		if (actual instanceof Double) {
			return Double.valueOf(expected) == actual;
		}
		return false;
	}

	/** Ensure that the given object is a character equals to the given value.
	 *
	 * @param <T> - type of the object.
	 * @param actual - the object to test.
	 * @param expected - the expected value.
	 * @return <code>true</code> if the condition is validated.
	 */
	public static boolean should_be(Object actual, char expected) {
		if (actual instanceof Character) {
			return Character.valueOf(expected) == actual;
		}
		return false;
	}

	/** Ensure that the given string representation of 
	 * the given object is equal to the expected value.
	 * @param <T> - type of the object.
	 * @param actual - the object to test.
	 * @param expected - the expected value.
	 * @return <code>true</code> if the condition is validated.
	 */
	public static boolean should_be(Object actual, String expected) {
		if (actual != null && expected != null) {
			return actual.toString().equals(expected);
		}
		return actual == expected;
	}

	/** Replies the stack trace element for the given level.
	 * <p>
	 * The given <var>level</var> permits to specify which class to reply:
	 * <ul>
	 * <li>{@code 0}: the class where is defined the function ({@code f<sub>0</sub>})
	 * that has called one function of {@code Caller}</li>
	 * <li>{@code 1}: the class where is defined the function ({@code f<sub>1</sub>})
	 * that has called {@code f<sub>0</sub>}</li>
	 * <li>{@code 2}: the class where is defined the function ({@code f<sub>2</sub>})
	 * that has called {@code f<sub>1</sub>}</li>
	 * <li>etc.</li>
	 * </ul>
	 * 
	 * Copied from https://github.com/gallandarakhneorg/afc/tree/master/core/vmutils/src/main/java/org/arakhne/afc/vmutil
	 *
	 * @param level is the desired level.
	 * @return the stack trace element; or {@code null}.
	 */
	private static StackTraceElement getTraceElementAt(int level) {
		if (level<0) return null;
		try {
			StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
			int j = -1;
			boolean found = false;
			Class<?> type;
			for(int i=0; i<stackTrace.length; ++i) {
				if (found) {
					if (i-j==level) return stackTrace[i];
				}
				else {
					type = Class.forName(stackTrace[i].getClassName());
					if (type!=null && Should.class.isAssignableFrom(type)) {
						j = i+1;
					}
					else if (j>=0) {
						// First ocurrence of a class in the stack, after the
						// inner invocation of StackTraceCaller
						found = true;
						if (i-j==level) return stackTrace[i];
					}
				}
			}
		}
		catch(AssertionError e) {
			throw e;
		}
		catch(Throwable _) {
			//
		}
		return null;
	}

	/** Copied from https://github.com/gallandarakhneorg/afc/tree/master/core/vmutils/src/main/java/org/arakhne/afc/vmutil
	 * @param level
	 * @return
	 */
	private static Class<?> getCallerClass(int level) {
		StackTraceElement element = getTraceElementAt(level);
		if (element==null) throw new IllegalArgumentException();
		Class<?> type = Class.forName(element.getClassName());
		if (type==null) throw new IllegalArgumentException();
		return type;
	}

	/** Ensure that the given caller specification has a valid link
	 * to another Jnario specification with the given name.
	 *
	 * @param referencedLink - URL
	 * @return <code>true</code> if the condition is validated.
	 */
	public static boolean should_beJnarioLink(String referencedLink) {
		Class<?> callingSpecification = getCallerClass(2);
		if (referencedLink == null || callingSpecification == null) {
			return false;
		}
		//
		String ref = referencedLink;
		if (ref.startsWith("#")) { //$NON-NLS-1$
			ref = "./" + callingSpecification.getSimpleName() + ".html" + ref; //$NON-NLS-1$ //$NON-NLS-2$
		}

		String[] fragments = null;
		if (ref.contains(".html#")) { //$NON-NLS-1$
			String[] parts = ref.split(java.util.regex.Matcher.quoteReplacement(".html#")); //$NON-NLS-1$
			if (parts.length != 2) {
				return false;
			}
			fragments = parts[1].split(java.util.regex.Matcher.quoteReplacement("_") + "+"); //$NON-NLS-1$ //$NON-NLS-2$
			StringBuilder b = new StringBuilder();
			for (String s : fragments) {
				if (s.length() > 1) {
					b.append(s.substring(0, 1).toUpperCase() + s.substring(1));
				} else {
					b.append(s.toUpperCase());
				}
			}
			if (parts[0].endsWith("Spec")) { //$NON-NLS-1$
				ref = parts[0].substring(0, parts[0].length() - 4)
						+ b.toString() + "Spec.html"; //$NON-NLS-1$
			} else {
				ref = parts[0] + b.toString() + "Spec.html"; //$NON-NLS-1$
			}
		}
		//
		if (!isJnarioSpec(callingSpecification, ref)) {

			// The specification could be a function of the Java class.
			if (fragments != null) {
				StringBuilder operationName = new StringBuilder();
				for (String fragment : fragments) {
					if (operationName.length() > 0) {
						operationName.append(fragment.substring(0, 1).toUpperCase() + fragment.substring(1).toLowerCase());
					} else {
						operationName.append(fragment.toLowerCase());
					}
				}
				String operationNameStr = "_" + operationName.toString(); //$NON-NLS-1$
				try {
					callingSpecification.getMethod(operationNameStr);
					return true;
				} catch (Throwable _) {
					// Failure
				}
			}
			
			return false;
		}
		return true;
	}

	private static boolean isJnarioSpec(Class<?> callingSpecification, String reference) {
		String url = reference;
		//
		if (!url.endsWith(".html")) { //$NON-NLS-1$
			return false;
		}
		//
		url = url.substring(0, url.length() - 5);
		File caller = new File(callingSpecification.getName().replaceAll(
				"\\.", File.separator)).getParentFile(); //$NON-NLS-1$
		File resolved = new File(caller, url.replaceAll("\\/", File.separator)); //$NON-NLS-1$
		String resolvedPath = resolved.getPath();
		resolvedPath = resolvedPath.replaceAll(java.util.regex.Matcher.quoteReplacement(File.separator), "."); //$NON-NLS-1$
		try {
			Class.forName(resolvedPath);
			return true;
		} catch (Throwable _) {
			return false;
		}
	}

	/** Ensure that the given string is a valid hyper-link.
	 * The link must start with "http://" and not end with "/".
	 *
	 * @param referencedLink - the string to test.
	 * @return <code>true</code> if the condition is validated.
	 */
	public static boolean should_beHttpLink(String referencedLink) {
		return should_startWith(referencedLink, "http://") //$NON-NLS-1$
				&& !should_endWith(referencedLink, "/"); //$NON-NLS-1$
	}

	/** Ensure that the given string is a Maven snapshot version.
	 *
	 * @param version - the version string.
	 * @return <code>true</code> if the condition is validated.
	 */
	public static boolean should_beSnapshotVersion(String version) {
		return should_endWith(version, "-SNAPSHOT"); //$NON-NLS-1$
	}

	/**
     * Replies the URL of a resource.
     * <p>
     * You may use Unix-like syntax to write the resource path, ie.
     * you may use slashes to separate filenames.
     * <p>
     * The name of <var>packagename</var> is translated into a resource
     * path (by replacing the dots by slashes) and the given path
     * is append to. For example, the two following codes are equivalent:<pre><code>
     * Resources.getResources(Package.getPackage("org.arakhne.afc"), "/a/b/c/d.png");
     * Resources.getResources("org/arakhne/afc/a/b/c/d.png");
     * </code></pre>
     * <p>
     * If the <var>classLoader</var> parameter is <code>null</code>,
     * the class loader replied by {@link ClassLoaderFinder} is used.
     * If this last is <code>null</code>, the class loader of
     * the Resources class is used.
     * 
     * Copied from https://github.com/gallandarakhneorg/afc/blob/master/core/vmutils/src/main/java/org/arakhne/afc/vmutil/Resources.java
     *
     * @param classLoader is the research scope. If <code>null</code>,
     * the class loader replied by {@link ClassLoaderFinder} is used.
     * @param packagename is the package in which the resource should be located.
     * @param path is the relative path of the resource in the package. 
     * @return the url of the resource or <code>null</code> if the resource was
     * not found in class paths.
     */
    private static URL getResource(ClassLoader classLoader, Package packagename, String path) {
    	if (packagename==null || path==null) return null;
    	StringBuilder b = new StringBuilder();
    	b.append(packagename.getName().replaceAll(
    			Pattern.quote("."), //$NON-NLS-1$
    			java.util.regex.Matcher.quoteReplacement("/")));
    	if (!path.startsWith("/")) {
    		b.append("/");
    	}
    	b.append(path);
    	return getResource(packagename.getClass().getClassLoader(), b.toString());
    }
    
    /**
     * Copied from https://github.com/gallandarakhneorg/afc/blob/master/core/vmutils/src/main/java/org/arakhne/afc/vmutil/StandardJREResourceWrapper.java
     */
    private static URL getResource(ClassLoader classLoader, String path) {
    	if (path==null) return null;
    	String resourcePath = path;
    	if (path.startsWith("/")) { //$NON-NLS-1$
    		resourcePath = path.substring(1);
    	}
    	
    	ClassLoader loader = (classLoader==null)
    			? Should.class.getClassLoader()
    			: classLoader;
    	assert(loader!=null);
    			
    	URL url = loader.getResource(resourcePath);
    	
    	if (url==null) {
    		// Try to find in ./resources sub directory
    		url = loader.getResource("resources/"+resourcePath);
    	}
    	return url;
    }
	
	/** Ensure that the given caller specification has a valid link
	 * to a picture with the given name.
	 *
	 * @param referencedLink - URL
	 * @return <code>true</code> if the condition is validated.
	 */
	public static boolean should_bePicture(String referencedLink) {
		Class<?> callingSpecification = getCallerClass(2);
		if (referencedLink == null || callingSpecification == null) {
			return false;
		}
		// Check if it is a URL of a file path
		try {
			URL fileURL = new URL(referencedLink);
			if ("file".equalsIgnoreCase(fileURL.getProtocol())) {
				// Get local resource
				URL u = getResource(Should.class.getClassLoader(), callingSpecification.getPackage(), referencedLink);
				if (u != null) { //$NON-NLS-1$
					return true;
				}
			}
		} catch (Throwable _) {
			//
		}
		return false;
	}

	/** Ensure that the given string is a string representation of an integer,
	 * without sign symbols.
	 *
	 * @param str - the string.
	 * @return <code>true</code> if the condition is validated.
	 */
	public static boolean should_beInteger(String str) {
		return Pattern.matches("^[0-9]+$", str); //$NON-NLS-1$
	}

	/** Ensure that the given string is a string representation of a date.
	 * A date has the format <code>yyyy-[m]m-[d]d</code>
	 *
	 * @param str - the string.
	 * @return <code>true</code> if the condition is validated.
	 */
	public static boolean should_beDate(String str) {
		Date d = null;
		try {
			d = DateFormat.getDateInstance().parse(str);
		} catch (Throwable _) {
			d = null;
		}
		return (d != null);
	}

	/** Ensure that the iterator replies the expected values in the given order.
	 *
	 * @param actual - the iterator to test.
	 * @param expected - the expected values.
	 * @return <code>true</code> if the condition is validated.
	 */
	public static boolean should_contain(Iterator<?> actual, Object[] expected) {
		Object obj;
		int index = 0;
		while (actual.hasNext()) {
			obj = actual.next();
			if (index <= expected.length) {
				if (!should_be(expected[index], obj)) {
					return false;
				}
			} else {
				return false;
			}
			index++;
		}
		return false;
	}

	/** Ensure that the iterator replies the expected values in the given order.
	 *
	 * @param <T> - the type of the elements in the iterator.
	 * @param actual - the iterator to test.
	 * @param expected - the expected values.
	 * @return <code>true</code> if the condition is validated.
	 */
	public static boolean should_contain(ListIterator<?> actual, Object[] expected) {
		Object obj;
		int index = 0;
		while (actual.hasNext()) {
			obj = actual.next();
			if (index <= expected.length) {
				if (!should_be(expected[index], obj)) {
					return false;
				}
			} else {
				return false;
			}
			index++;
		}
		return false;
	}

}
