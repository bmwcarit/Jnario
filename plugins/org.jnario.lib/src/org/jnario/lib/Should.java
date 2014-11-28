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
import java.lang.reflect.Method;
import java.net.URL;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.eclipse.xtext.xbase.lib.Functions;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

import com.google.common.base.Objects;
import com.google.common.collect.Iterators;
import com.google.common.io.Files;
import com.google.common.primitives.Booleans;
import com.google.common.primitives.Bytes;
import com.google.common.primitives.Chars;
import com.google.common.primitives.Doubles;
import com.google.common.primitives.Floats;
import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;
import com.google.common.primitives.Shorts;

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
		if (actual == null) {
			return expectedType == null;
		}
		return actual.equals(expectedType);
	}

	public static boolean should_be(Object actual, Class<?> expectedType){
		if (expectedType == null || actual == null) {
			return false;
		}
		return expectedType.isInstance(actual);
	}

	public static <T> boolean should_be(T actual, Matcher<? super T> matcher){
		if(matcher == null){
			return actual == null;
		}
		return matcher.matches(actual);
	}

	public static <T> boolean should_contain(Iterable<T> actual, T element){
		if (actual == null) {
			return false;
		}
		return contains(actual, element);
	}

	public static <T> boolean should_contain(Iterable<T> collection, Matcher<? super T> matcher){
		if (collection != null) {
			for (T item : collection) {
				if (matcher == null) {
					if (item == null) {
						return true;
					}
				}
				else if (matcher.matches(item)){
					return true;
				}
			}
		}
		return false;
	}

	public static <T> boolean should_contain(CharSequence actual, CharSequence substring){
		if (actual == null || substring == null) {
			return false;
		}
		return actual.toString().contains(substring);
	}

	public static <T> boolean should_be(T actual, boolean result){
		if (actual instanceof Boolean) {
			return ((Boolean)actual).equals(result);
		}
		return result;
	}

	public static boolean should_startWith(CharSequence s, String substring){
		if (s == null || substring == null) {
			return false;
		}
		return s.toString().startsWith(substring);
	} 

	public static boolean should_endWith(CharSequence s, String substring){
		if (s == null || substring == null) {
			return false;
		}
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

	/** Ensure that the given caller specification has a valid link
	 * to another Jnario specification with the given name, or
	 * to a Java resource.
	 *
	 * @param url - the url to check.
	 * @param source - the object that is containing the URL.
	 * @return the validation result.
	 */
	public static boolean should_beAccessibleFrom(String url, Object source) {
		return isJnarioLink(url, source) || isResourceLink(url, source);
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
				java.util.regex.Matcher.quoteReplacement("/"))); //$NON-NLS-1$
		if (!path.startsWith("/")) { //$NON-NLS-1$
			b.append("/"); //$NON-NLS-1$
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
					url = loader.getResource("resources/" + resourcePath); //$NON-NLS-1$
				}
				return url;
	}

	private static boolean isResourceLink(String url, Object source) {
		if (source == null || url == null) {
			return false;
		}
		// Check if it is a URL of a file path
		try {
			URL fileURL;
			try {
				fileURL = new URL(url);
			} catch (Throwable _) {
				fileURL = new URL("file:" + url); //$NON-NLS-1$
			}
			if ("file".equalsIgnoreCase(fileURL.getProtocol())) { //$NON-NLS-1$
				// Get local resource
				URL u = getResource(
						Should.class.getClassLoader(),
						source.getClass().getPackage(),
						fileURL.getPath());
				if (u != null) {
					return true;
				}
			}
		} catch (Throwable _) {
			//
		}
		return false;
	}

	private static boolean isJnarioLink(String url, Object source) {
		if (source == null || url == null) {
			return false;
		}
		//
		try {
			String ref = url;
			if (ref.startsWith("#")) { //$NON-NLS-1$
				ref = "./" + source.getClass().getSimpleName() + ".html" + ref; //$NON-NLS-1$ //$NON-NLS-2$
			}

			String baseRef;
			String[] fragments = null;
			if (ref.contains(".html#")) { //$NON-NLS-1$
				String[] parts = ref.split(java.util.regex.Matcher.quoteReplacement(".html#")); //$NON-NLS-1$
				if (2 != parts.length) {
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
					baseRef = parts[0] + ".html";
					ref = parts[0].substring(0, parts[0].length() - 4)
							+ b.toString() + "Spec.html"; //$NON-NLS-1$
				} else {
					baseRef = parts[0] + "Spec.html";
					ref = parts[0] + b.toString() + "Spec.html"; //$NON-NLS-1$
				}
			} else {
				baseRef = source.getClass().getName().replaceAll("\\.", "/") + ".html";
			}
			//
			Class<?> jnarioSpec = getJnarioSpec(source.getClass(), ref);
			if (jnarioSpec == null) {

				// The specification could be a function of the Java class.
				if (fragments != null) {
					jnarioSpec = getJnarioSpec(source.getClass(), baseRef);
					if (jnarioSpec != null) {
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
							jnarioSpec.getDeclaredMethod(operationNameStr);
							return true;
						} catch (Throwable _) {
							// Failure
						}
					}
				}

				return false;
			}
			return true;
		} catch (Throwable _) {
			return false;
		}
	}

	/** Replies the type that corresponds to the specified class.
	 * If the name corresponds to a primitive type, the low-level type
	 * will be replied.
	 * This method extends
	 * {@link Class#forName(String)} with autoboxing support.
	 * 
	 * Copied from {@link "https://github.com/gallandarakhneorg/afc/blob/master/core/vmutils/src/main/java/org/arakhne/afc/vmutil/ReflectionUtil.java"}.
	 * 
	 * @param name is the name of the class to load.
	 * @return the loaded class
	 * @throws ClassNotFoundException  if name names an
	 * unknown class or primitive
	 */
	private static Class<?> forName(String name) throws ClassNotFoundException {
		if (name == null || "".equals(name) || "null".equals(name) || "void".equals(name)) { //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			return void.class;
		}
		if ("boolean".equals(name)) { //$NON-NLS-1$
			return boolean.class;
		}
		if ("byte".equals(name)) { //$NON-NLS-1$
			return byte.class;
		}
		if ("char".equals(name)) { //$NON-NLS-1$
			return char.class;
		}
		if ("double".equals(name)) { //$NON-NLS-1$
			return double.class;
		}
		if ("float".equals(name)) { //$NON-NLS-1$
			return float.class;
		}
		if ("int".equals(name)) { //$NON-NLS-1$
			return int.class;
		}
		if ("long".equals(name)) { //$NON-NLS-1$
			return long.class;
		}
		if ("short".equals(name)) { //$NON-NLS-1$
			return short.class;
		}
		return Class.forName(name);
	}
	
	private static Class<?> getJnarioSpec(Class<?> callingSpecification, String reference) {
		String url = reference;
		//
		if (!url.endsWith(".html")) { //$NON-NLS-1$
			return null;
		}
		//
		url = url.substring(0, url.length() - 5);
		File caller = new File(callingSpecification.getName().replaceAll(
				"\\.", File.separator)).getParentFile(); //$NON-NLS-1$
		File resolved = new File(caller, url.replaceAll("\\/", File.separator)); //$NON-NLS-1$
		String resolvedPath = Files.simplifyPath(resolved.getPath());
		resolvedPath = resolvedPath.replaceAll(java.util.regex.Matcher.quoteReplacement(File.separator), "."); //$NON-NLS-1$
		try {
			return forName(resolvedPath);
		} catch (Throwable _) {
			return null;
		}
	}

	/** Ensure that the iterator replies the expected values in the given order.
	 *
	 * @param actual - the iterator to test.
	 * @param expected - the expected values.
	 * @return the validation status
	 */
	public static boolean should_iterate(Iterator<?> actual, Object expected) {
		return should_iterate(actual, expected, true);
	}

	/** Ensure that the iterator replies the expected values in the given order.
	 *
	 * @param actual - the iterator to test.
	 * @param expected - the expected values.
	 * @param significantOrder - indicates if the order of the elements is significant.
	 * @return the validation status
	 */
	public static boolean should_iterate(Iterator<?> actual, Object expected, boolean significantOrder) {
		Object obj;
		Iterator<?> it;
		if (expected == null) {
			it = Collections.emptyIterator();
		} else if (expected instanceof Iterable<?>) {
			it = ((Iterable<?>) expected).iterator();
		} else if (expected instanceof byte[]) {
			it = Bytes.asList((byte[])expected).iterator();
		} else if (expected instanceof short[]) {
			it = Shorts.asList((short[])expected).iterator();
		} else if (expected instanceof int[]) {
			it = Ints.asList((int[])expected).iterator();
		} else if (expected instanceof long[]) {
			it = Longs.asList((long[])expected).iterator();
		} else if (expected instanceof float[]) {
			it = Floats.asList((float[])expected).iterator();
		} else if (expected instanceof double[]) {
			it = Doubles.asList((double[])expected).iterator();
		} else if (expected instanceof boolean[]) {
			it = Booleans.asList((boolean[])expected).iterator();
		} else if (expected.getClass().isArray()) {
			it = Iterators.forArray((Object[])expected);
		} else if (expected instanceof char[]) {
			it = Chars.asList((char[])expected).iterator();
		} else if (expected instanceof Map<?, ?>) {
			it = ((Map<?, ?>) expected).entrySet().iterator();
		} else {
			it = Collections.singleton(expected).iterator();
		}

		if (significantOrder) {
			// Significant order
			Object eObj;
			while (actual.hasNext()) {
				obj = actual.next();
				if (!it.hasNext()) {
					return false;
				}
				eObj = it.next();
				if (!Objects.equal(obj, eObj)) {
					return false;
				}
			}
			return !it.hasNext();
		}

		// Unsignificant order
		List<Object> expectedElements = new LinkedList<Object>();
		while (it.hasNext()) {
			expectedElements.add(it.next());
		}
		boolean found;
		while (actual.hasNext()) {
			obj = actual.next();
			Iterator<Object> i = expectedElements.iterator();
			found = false;
			while (!found && i.hasNext()) {
				Object eObj = i.next();
				if (Objects.equal(obj, eObj)) {
					i.remove();
					found = true;
				}
			}
			if (!found) {
				return false;
			}
		}
		return expectedElements.isEmpty();
	}

	/** Ensure that the string has the format of a date.
	 *
	 * @param actual - the string to parse.
	 * @param dateFormat - the expected format of the date, as
	 * described in {@link SimpleDateFormat}. If <code>null</code>, the
	 * default date format is considered.
	 * @return the validation status
	 */
	public static boolean should_beDate(String actual, String dateFormat) {
		if (actual == null || actual.isEmpty()) {
			return false;
		}
		try {
			DateFormat format;
			if (dateFormat == null || dateFormat.isEmpty()) {
				format = DateFormat.getDateInstance();
			} else {
				format = new SimpleDateFormat(dateFormat);
			}
			return format.parse(actual) != null;
		} catch (Throwable _)  {
			//
		}
		return false;
	}

	/** Ensure that the string has the format of an URL.
	 *
	 * @param actual - the string to parse.
	 * @param requiredSchemes - is a list of schemes that are supported.
	 * If a scheme is prefix with the <code>!</code> character (without space),
	 * then the scheme is not allowed.
	 * If not given, all the schemes are allowed.
	 * @return the validation status
	 */
	public static boolean should_beURL(String actual, String requiredSchemes) {
		if (actual == null || actual.isEmpty()) {
			return false;
		}
		try {
			URL u;
			try {
				u = new URL(actual);
			} catch (Throwable _) {
				return false;
			}
			if (requiredSchemes != null && !requiredSchemes.isEmpty()) {
				boolean mustHaveScheme = false;
				for(String s : requiredSchemes.trim().split("\\s*,\\s*")) { //$NON-NLS-1$
					if (!s.isEmpty()) {
						if (s.startsWith("!")) { //$NON-NLS-1$
							if (s.substring(1).equalsIgnoreCase(u.getProtocol())) {
								return false;
							}
						} else {
							mustHaveScheme = true;
							if (s.equalsIgnoreCase(u.getProtocol())) {
								return true;
							}
						}
					}
				}
				return !mustHaveScheme;
			}
			return true;
		} catch (Throwable _)  {
			//
		}
		return false;
	}

	/** Ensure that the string has the format of a number.
	 *
	 * @param actual - the string to parse.
	 * @param numberFormat - the expected format of the number, as
	 * described in {@link DecimalFormat}. If <code>null</code>, the
	 * default date format is considered.
	 * @return the validation status
	 */
	public static boolean should_beNumber(String actual, String numberFormat) {
		if (actual == null || actual.isEmpty()) {
			return false;
		}
		try {
			NumberFormat format;
			if (numberFormat == null || numberFormat.isEmpty()) {
				format = NumberFormat.getNumberInstance();
			} else {
				format = new DecimalFormat(numberFormat);
			}
			return format.parse(actual) != null;
		} catch (Throwable _)  {
			//
		}
		return false;
	}

	/** Ensure that the given type has the given type has the given method.
	 * 
	 * @param type - the type to check.
	 * @param name - the name and prototype, e.g. <code>fct(java.lang.String):int</code>.
	 * @return the validation status.
	 */
	@SuppressWarnings("rawtypes")
	public static boolean should_haveMethod(Class<?> type, String name) {
		try {
			if (type == null || name == null || name.isEmpty()) {
				return false;
			}
			Pattern p = Pattern.compile(
					"^([_a-zA-Z0-9]+)\\s*" //$NON-NLS-1$
					+ "(?:\\(\\s*([_a-zA-Z0-9.]+\\s*" //$NON-NLS-1$
					+ "(?:,\\s*[_a-zA-Z0-9.]+\\s*)*)\\))?" //$NON-NLS-1$
					+ "(?:\\s*:\\s*([_a-zA-Z0-9.]+))?$"); //$NON-NLS-1$
			java.util.regex.Matcher m = p.matcher(name);
			if (m.matches()) {
				String fctName = m.group(1);
				String paramText;
				try {
					paramText = m.group(2).trim();
				} catch (Throwable _) {
					paramText = ""; //$NON-NLS-1$
				}
				String returnText;
				try {
					returnText = m.group(3).trim();
				} catch (Throwable _) {
					returnText = ""; //$NON-NLS-1$
				}
				String[] params;
				if (paramText.isEmpty()) {
					params = new String[0];
				} else {
					params = paramText.split("\\s*,\\s*"); //$NON-NLS-1$
				}
				Class[] types = new Class[params.length]; 
				for(int i=0; i<params.length; ++i) {
					types[i] = forName(params[i]);
				}
				Method method = type.getDeclaredMethod(fctName, types);
				if (method == null) {
					return false;
				}
				if (returnText == null || returnText.isEmpty()) {
					return void.class.equals(method.getReturnType())
							|| Void.class.equals(method.getReturnType());
				}
				Class<?> rType = forName(returnText);
				return rType.equals(method.getReturnType());
			}
		} catch (Throwable e) {
			//
		}
		return false;
	}

	/** Ensure that the given type extends specific types.
	 * 
	 * @param type - the type to check.
	 * @param expectedTypes - the qualified names of the expected types, separated by comas.
	 * @return the validation status.
	 */
	public static boolean should_extend(Class<?> type, String expectedTypes) {
		if (type == null) {
			return false;
		}
		try {
			Class<?> st = type.getSuperclass();
			List<Class<?>> types = new LinkedList<Class<?>>();
			if (st == null || Object.class.equals(st)) {
				if (type.isInterface()) {
					types.addAll(Arrays.asList(type.getInterfaces()));
				}
			} else {
				types.add(st);
			}
			//
			if (expectedTypes == null || expectedTypes.isEmpty()) {
				return types.isEmpty();
			}
			for (String expectedType : expectedTypes.split("\\s*,\\s*")) { //$NON-NLS-1$
				Class<?> et = forName(expectedType);
				if (!types.remove(et)) {
					return false;
				}
			}
			return types.isEmpty();
		} catch (Throwable e) {
			//
		}
		return false;
	}

	/** Ensure that the given type implements specific types.
	 * 
	 * @param type - the type to check.
	 * @param expectedTypes - the qualified names of the expected types, separated by comas.
	 * @return the validation status.
	 */
	public static boolean should_implement(Class<?> type, String expectedTypes) {
		if (type == null || type.isInterface()) {
			return false;
		}
		try {
			List<Class<?>> types = new LinkedList<Class<?>>();
			types.addAll(Arrays.asList(type.getInterfaces()));
			//
			if (expectedTypes == null || expectedTypes.isEmpty()) {
				return types.isEmpty();
			}
			for (String expectedType : expectedTypes.split("\\s*,\\s*")) { //$NON-NLS-1$
				Class<?> et = forName(expectedType);
				if (!types.remove(et)) {
					return false;
				}
			}
			return types.isEmpty();
		} catch (Throwable e) {
			//
		}
		return false;
	}

	/** Ensure that the given string is a valid version number,
	 * including Maven version numbers.
	 * 
	 * @param actual - the string to test.
	 * @param allowSnapshot - indicates if the <code>-SNAPSHOT</code> postfix
	 * is considered as valid (this postfix is used for Maven unstable versions).
	 * @return the validation status.
	 */
	public static boolean should_beValidVersionNumber(String actual, boolean allowSnapshot) {
		if (actual == null) {
			return false;
		}
		boolean isSnapshot = actual.endsWith("-SNAPSHOT");
		if (!allowSnapshot && isSnapshot) {
			return false;
		}
		String versionNumber;
		if (isSnapshot) {
			versionNumber = actual.substring(0, actual.length() - 9);
		} else {
			versionNumber = actual;
		}
		StringBuilder componentPattern = new StringBuilder("^"); //$NON-NLS-1$
		// First dot-separated component
		componentPattern.append("[0-9]+(?:[\\-:_+~][0-9a-zA-Z]+)*");
		// Next dot-separated components
		componentPattern.append("(?:\\.[0-9a-zA-Z]+(?:[\\-:_+~][0-9a-zA-Z]+)*)*");
		componentPattern.append("$"); //$NON-NLS-1$
		return Pattern.matches(componentPattern.toString(), versionNumber);
	}

	/** Ensure that the given type has the number of members.
	 * 
	 * @param type - the type to check.
	 * @param expectedNbOfElements - the expected number of elements.
	 * @return the validation status.
	 */
	public static boolean should_haveNbMembers(Class<?> type, int expectedNbOfElements) {
		if (type == null) {
			return false;
		}
		try {
			int nb = type.getDeclaredConstructors().length
					+ type.getDeclaredFields().length
					+ type.getDeclaredMethods().length
					+ type.getDeclaredAnnotations().length
					+ type.getDeclaredClasses().length;
			return nb == expectedNbOfElements;
		} catch (Throwable e) {
			//
		}
		return false;
	}

}
