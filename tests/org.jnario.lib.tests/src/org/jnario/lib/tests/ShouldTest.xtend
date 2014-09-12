/*******************************************************************************
 * Copyright (c) 2014 BMW Car IT and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.jnario.lib.tests

import org.hamcrest.Matcher
import org.junit.Test

import static extension org.jnario.lib.Assert.*
import static org.jnario.lib.Should.*

/**
 * @author Stéphane Galland - Initial contribution and API
 */
public class ShouldTest {

	@Test
	def __0() {
		_().assertNull
	}

	@Test
	def nullValue_0() {
		val matcher = nullValue()
		matcher.matches("abc").assertFalse
		matcher.matches(1).assertFalse
		matcher.matches(null).assertTrue
	}

	@Test
	def matches_StringFunction1_0() {
		val matcher1 = matches("abc", [ true ])
		matcher1.matches("abc").assertTrue
		matcher1.matches(1).assertTrue
		val matcher2 = matches("abc", [ false ])
		matcher2.matches("abc").assertFalse
		matcher2.matches(1).assertFalse
	}

	@Test
	def matches_StringFunction1_1() {
		val matcher1 = matches("abc", [ true ])
		matcher1.matches(null).assertTrue
		val matcher2 = matches("abc", [ false ])
		matcher2.matches(null).assertFalse
	}

	@Test
	def should_be_TFunction1_0() {
		val obj1 = new Object
		val obj2 = new Object
		should_be( obj1, [ 
			assertSame(obj1, it)
			true
		] ).assertTrue
		should_be( obj2, [ 
			assertSame(obj2, it)
			false
		] ).assertFalse
	}
	
	@Test
	def should_be_TFunction1_1() {
		should_be( null, [ 
			assertNull(it)
			true
		] ).assertTrue
		should_be( null, [ 
			assertNull(it)
			false
		] ).assertFalse
	}

	@Test
	def should_be_TT_0() {
		val obj1 = "a"
		val obj2 = "b"
		val obj3 = "a"
		should_be(obj1, obj1).assertTrue
		should_be(obj1, obj2).assertFalse
		should_be(obj1, obj3).assertTrue
	}

	@Test
	def should_be_TT_1() {
		val obj1 = #[ "a" ]
		val obj2 = #[ "b" ]
		val obj3 = #[ "a" ]
		should_be(obj1, obj1).assertTrue
		should_be(obj1, obj2).assertFalse
		should_be(obj1, obj3).assertTrue
	}

	@Test
	def should_be_TT_2() {
		val obj1 = "a"
		should_be(null as String, null as String).assertTrue
		should_be(null as String, obj1).assertFalse
		should_be(obj1, null as String).assertFalse
	}

	@Test
	def operator_doubleArrow_TT_0() {
		val obj1 = "a"
		val obj2 = "b"
		val obj3 = "a"
		operator_doubleArrow(obj1, obj1).assertTrue
		operator_doubleArrow(obj1, obj2).assertFalse
		operator_doubleArrow(obj1, obj3).assertTrue
	}

	@Test
	def operator_doubleArrow_TT_1() {
		val obj1 = #[ "a" ]
		val obj2 = #[ "b" ]
		val obj3 = #[ "a" ]
		operator_doubleArrow(obj1, obj1).assertTrue
		operator_doubleArrow(obj1, obj2).assertFalse
		operator_doubleArrow(obj1, obj3).assertTrue
	}

	@Test
	def operator_doubleArrow_TT_2() {
		val obj1 = "a"
		operator_doubleArrow(null as String, null as String).assertTrue
		operator_doubleArrow(null as String, obj1).assertFalse
		operator_doubleArrow(obj1, null as String).assertFalse
	}
	
	@Test
	def should_be_ClassClass_0() {
		val cls1 = typeof(TestRule)
		val cls2 = typeof(Object)
		val cls3 = typeof(TestRule)
		should_be(cls1, cls1).assertTrue
		should_be(cls1, cls2).assertFalse
		should_be(cls1, cls3).assertTrue
	}

	@Test
	def should_be_ClassClass_1() {
		val cls1 = typeof(TestRule)
		should_be(null as Class<?>, null as Class<?>).assertTrue
		should_be(cls1, null as Class<?>).assertFalse
		should_be(null as Class<?>, cls1).assertFalse
	}
	
	@Test
	def operator_doubleArrow_ClassClass_0() {
		val cls1 = typeof(TestRule)
		val cls2 = typeof(Object)
		val cls3 = typeof(TestRule)
		operator_doubleArrow(cls1, cls1).assertTrue
		operator_doubleArrow(cls1, cls2).assertFalse
		operator_doubleArrow(cls1, cls3).assertTrue
	}

	@Test
	def operator_doubleArrow_ClassClass_1() {
		val cls1 = typeof(TestRule)
		operator_doubleArrow(null as Class<?>, null as Class<?>).assertTrue
		operator_doubleArrow(cls1, null as Class<?>).assertFalse
		operator_doubleArrow(null as Class<?>, cls1).assertFalse
	}
	
	@Test
	def should_be_ObjectClass_0() {
		val obj1 = "a"
		val obj2 = 1 as Integer
		val cls1 = typeof(String)
		val cls2 = typeof(Object)
		val cls3 = typeof(Integer)
		should_be(obj1, cls1).assertTrue
		should_be(obj1, cls2).assertTrue
		should_be(obj1, cls3).assertFalse
		should_be(obj2, cls1).assertFalse
		should_be(obj2, cls2).assertTrue
		should_be(obj2, cls3).assertTrue
	}

	@Test
	def should_be_ObjectClass_1() {
		val obj1 = "a"
		val cls1 = typeof(String)
		should_be(null as Object, null as Class<?>).assertFalse
		should_be(obj1, null as Class<?>).assertFalse
		should_be(null as Object, cls1).assertFalse
	}

	@Test
	def operator_doubleArrow_ObjectClass_0() {
		val obj1 = "a"
		val obj2 = 1 as Integer
		val cls1 = typeof(String)
		val cls2 = typeof(Object)
		val cls3 = typeof(Integer)
		operator_doubleArrow(obj1, cls1).assertTrue
		operator_doubleArrow(obj1, cls2).assertTrue
		operator_doubleArrow(obj1, cls3).assertFalse
		operator_doubleArrow(obj2, cls1).assertFalse
		operator_doubleArrow(obj2, cls2).assertTrue
		operator_doubleArrow(obj2, cls3).assertTrue
	}

	@Test
	def operator_doubleArrow_ObjectClass_1() {
		val obj1 = "a"
		val cls1 = typeof(String)
		operator_doubleArrow(null as Object, null as Class<?>).assertFalse
		operator_doubleArrow(obj1, null as Class<?>).assertFalse
		operator_doubleArrow(null as Object, cls1).assertFalse
	}

	@Test
	def should_be_TMatcher_0() {
		val obj1 = "a"
		val obj2 = "b"
		should_be( obj1, new TestMatcher(true, obj1)).assertTrue
		should_be( obj2, new TestMatcher(false, obj2)).assertFalse
	}

	@Test
	def should_be_TMatcher_1() {
		val obj1 = "a"
		should_be( null, null as Matcher<String>).assertTrue
		should_be( obj1, null as Matcher<String>).assertFalse
		should_be( null, new TestMatcher(true, null)).assertTrue
		should_be( null, new TestMatcher(false, null)).assertFalse
	}

	@Test
	def operator_doubleArrow_TMatcher_0() {
		val obj1 = "a"
		val obj2 = "b"
		operator_doubleArrow( obj1, new TestMatcher(true, obj1)).assertTrue
		operator_doubleArrow( obj2, new TestMatcher(false, obj2)).assertFalse
	}

	@Test
	def operator_doubleArrow_TMatcher_1() {
		val obj1 = "a"
		operator_doubleArrow( null, null as Matcher<String>).assertTrue
		operator_doubleArrow( obj1, null as Matcher<String>).assertFalse
		operator_doubleArrow( null, new TestMatcher(true, null)).assertTrue
		operator_doubleArrow( null, new TestMatcher(false, null)).assertFalse
	}

	@Test
	def should_contain_IterableT_0() {
		val obj1 = #[ "a", "b", "c" ]
		should_contain(obj1, "a").assertTrue
		should_contain(obj1, "b").assertTrue
		should_contain(obj1, "c").assertTrue
		should_contain(obj1, "d").assertFalse
	}

	@Test
	def should_contain_IterableT_1() {
		val String[] obj1 = #[ "a", "b", "c" ]
		should_contain(obj1, "a").assertTrue
		should_contain(obj1, "b").assertTrue
		should_contain(obj1, "c").assertTrue
		should_contain(obj1, "d").assertFalse
	}

	@Test
	def should_contain_IterableT_2() {
		val obj1 = #[ "a", "b", "c" ]
		should_contain(null as Iterable<String>, null as String).assertFalse
		should_contain(obj1, null as String).assertFalse
		should_contain(null as Iterable<String>, "a").assertFalse
	}

	@Test
	def should_contain_IterableMatcher_0() {
		val obj1 = #[ "a", "b", "c" ]
		should_contain(obj1, new TestMatcher(true, #["a","b","c"])).assertTrue
		should_contain(obj1, new TestMatcher(false, #["a","b","c"])).assertFalse
	}

	@Test
	def should_contain_IterableMatcher_1() {
		val obj1 = #[ "a", "b", "c" ]
		should_contain(null as Iterable<String>, null as Matcher<String>).assertFalse
		should_contain(null as Iterable<String>, new TestMatcher(true, #["a","b","c"])).assertFalse
		should_contain(obj1, null as Matcher<String>).assertFalse
	}

	@Test
	def should_contain_CharSequenceCharSequence_0() {
		val obj1 = "abcdef"
		val obj2 = "abcdef"
		val obj3 = "ghi"
		val obj4 = "cd"
		should_contain(obj1, obj1).assertTrue
		should_contain(obj1, obj2).assertTrue
		should_contain(obj1, obj3).assertFalse
		should_contain(obj1, obj4).assertTrue
	}

	@Test
	def should_contain_CharSequenceCharSequence_1() {
		val obj1 = "abcdef"
		should_contain(null as String, null as String).assertFalse
		should_contain(obj1, null as String).assertFalse
		should_contain(null as String, obj1).assertFalse
	}

	@Test
	def should_be_Tboolean_0() {
		val obj1 = "abcdef"
		val obj2 = true
		val obj3 = true as Object
		val obj4 = false
		val obj5 = false as Object
		should_be(obj1, true).assertTrue
		should_be(obj1, false).assertFalse
		should_be(obj2, true).assertTrue
		should_be(obj2, false).assertFalse
		should_be(obj3, true).assertTrue
		should_be(obj3, false).assertFalse
		should_be(obj4, true).assertFalse
		should_be(obj4, false).assertTrue
		should_be(obj5, true).assertFalse
		should_be(obj5, false).assertTrue
	}

	@Test
	def should_be_Tboolean_1() {
		should_be(null as Boolean, true).assertTrue
		should_be(null as Boolean, false).assertFalse
	}

	@Test
	def operator_doubleArrow_Tboolean_0() {
		val obj1 = "abcdef"
		val obj2 = true
		val obj3 = true as Object
		val obj4 = false
		val obj5 = false as Object
		operator_doubleArrow(obj1, true).assertTrue
		operator_doubleArrow(obj1, false).assertFalse
		operator_doubleArrow(obj2, true).assertTrue
		operator_doubleArrow(obj2, false).assertFalse
		operator_doubleArrow(obj3, true).assertTrue
		operator_doubleArrow(obj3, false).assertFalse
		operator_doubleArrow(obj4, true).assertFalse
		operator_doubleArrow(obj4, false).assertTrue
		operator_doubleArrow(obj5, true).assertFalse
		operator_doubleArrow(obj5, false).assertTrue
	}

	@Test
	def operator_doubleArrow_Tboolean_1() {
		operator_doubleArrow(null as Boolean, true).assertTrue
		operator_doubleArrow(null as Boolean, false).assertFalse
	}

	@Test
	def should_startWith_CharSequenceString_0() {
		val obj1 = "abcdef"
		val obj2 = "abcdef"
		val obj3 = "ghi"
		val obj4 = "ac"
		val obj5 = "ab"
		should_startWith(obj1, obj1).assertTrue
		should_startWith(obj1, obj2).assertTrue
		should_startWith(obj1, obj3).assertFalse
		should_startWith(obj1, obj4).assertFalse
		should_startWith(obj1, obj5).assertTrue
	} 

	@Test
	def should_startWith_CharSequenceString_1() {
		val obj1 = "abcdef"
		should_startWith(null as String, null as String).assertFalse
		should_startWith(obj1, null as String).assertFalse
		should_startWith(null as String, obj1).assertFalse
	} 

	@Test
	def should_endWith_CharSequenceString_0() {
		val obj1 = "abcdef"
		val obj2 = "abcdef"
		val obj3 = "ghi"
		val obj4 = "zf"
		val obj5 = "ef"
		should_endWith(obj1, obj1).assertTrue
		should_endWith(obj1, obj2).assertTrue
		should_endWith(obj1, obj3).assertFalse
		should_endWith(obj1, obj4).assertFalse
		should_endWith(obj1, obj5).assertTrue
	}

	@Test
	def should_endWith_CharSequenceString_1() {
		val obj1 = "abcdef"
		should_endWith(null as String, null as String).assertFalse
		should_endWith(obj1, null as String).assertFalse
		should_endWith(null as String, obj1).assertFalse
	}

	@Test
	def should_iterate_IteratorObject_0() {
		val obj1 = #[ "a", "b", "c" ]
		val obj2 = #[ "a", "b", "c" ]
		val obj3 = #[ "a", "c", "b" ]
		val obj4 = #[ "a", "b" ]
		val obj5 = #[ "a", "b", "c", "d" ]
		should_iterate(obj1.iterator(), obj1).assertTrue
		should_iterate(obj1.iterator(), obj2).assertTrue
		should_iterate(obj1.iterator(), obj3).assertFalse
		should_iterate(obj1.iterator(), obj4).assertFalse
		should_iterate(obj1.iterator(), obj5).assertFalse
	}

	@Test
	def should_iterate_IteratorObject_1() {
		val String[] obj1 = #[ "a", "b", "c" ]
		val String[] obj2 = #[ "a", "b", "c" ]
		val String[] obj3 = #[ "a", "c", "b" ]
		val String[] obj4 = #[ "a", "b" ]
		val String[] obj5 = #[ "a", "b", "c", "d" ]
		should_iterate(obj1.iterator(), obj1).assertTrue
		should_iterate(obj1.iterator(), obj2).assertTrue
		should_iterate(obj1.iterator(), obj3).assertFalse
		should_iterate(obj1.iterator(), obj4).assertFalse
		should_iterate(obj1.iterator(), obj5).assertFalse
	}

	@Test
	def should_iterate_IteratorObject_2() {
		val obj1 = #{ "a", "b", "c" }
		val obj2 = #{ "a", "b", "c" }
		val obj3 = #{ "a", "c", "b" }
		val obj4 = #{ "a", "b" }
		val obj5 = #{ "a", "b", "c", "d" }
		should_iterate(obj1.iterator(), obj1).assertTrue
		should_iterate(obj1.iterator(), obj2).assertTrue
		should_iterate(obj1.iterator(), obj3).assertTrue
		should_iterate(obj1.iterator(), obj4).assertFalse
		should_iterate(obj1.iterator(), obj5).assertFalse
	}

	@Test
	def should_iterate_IteratorObject_3() {
		val obj1 = #{ "a" -> 1, "b" -> 2, "c" -> 3 }
		val obj2 = #{ "a" -> 1, "b" -> 2, "c" -> 3 }
		val obj3 = #{ "a" -> 1, "c" -> 3, "b" -> 2 }
		val obj4 = #{ "a" -> 1, "b" -> 2 }
		val obj5 = #{ "a" -> 1, "b" -> 2, "c" -> 3, "d" -> 4 }
		should_iterate(obj1.entrySet.iterator(), obj1).assertTrue
		should_iterate(obj1.entrySet.iterator(), obj2).assertTrue
		should_iterate(obj1.entrySet.iterator(), obj3).assertTrue
		should_iterate(obj1.entrySet.iterator(), obj4).assertFalse
		should_iterate(obj1.entrySet.iterator(), obj5).assertFalse
	}

	@Test
	def should_iterate_IteratorObject_4() {
		val obj1 = #[ "a", "b", "c" ]
		should_iterate(obj1.iterator(), "a").assertFalse
	}

	@Test
	def should_iterate_IteratorObject_5() {
		val String[] obj1 = #[ "a", "b", "c" ]
		should_iterate(obj1.iterator(), "a").assertFalse
	}

	@Test
	def should_iterate_IteratorObject_6() {
		val obj1 = #{ "a" -> 1, "b" -> 2, "c" -> 3 }
		should_iterate(obj1.entrySet.iterator(), "a" -> 1).assertFalse
	}

	@Test
	def should_iterate_IteratorObjectboolean_significantOrder_0() {
		val obj1 = #[ "a", "b", "c" ]
		val obj2 = #[ "a", "b", "c" ]
		val obj3 = #[ "a", "c", "b" ]
		val obj4 = #[ "a", "b" ]
		val obj5 = #[ "a", "b", "c", "d" ]
		should_iterate(obj1.iterator(), obj1, true).assertTrue
		should_iterate(obj1.iterator(), obj2, true).assertTrue
		should_iterate(obj1.iterator(), obj3, true).assertFalse
		should_iterate(obj1.iterator(), obj4, true).assertFalse
		should_iterate(obj1.iterator(), obj5, true).assertFalse
	}

	@Test
	def should_iterate_IteratorObjectboolean_significantOrder_1() {
		val String[] obj1 = #[ "a", "b", "c" ]
		val String[] obj2 = #[ "a", "b", "c" ]
		val String[] obj3 = #[ "a", "c", "b" ]
		val String[] obj4 = #[ "a", "b" ]
		val String[] obj5 = #[ "a", "b", "c", "d" ]
		should_iterate(obj1.iterator(), obj1, true).assertTrue
		should_iterate(obj1.iterator(), obj2, true).assertTrue
		should_iterate(obj1.iterator(), obj3, true).assertFalse
		should_iterate(obj1.iterator(), obj4, true).assertFalse
		should_iterate(obj1.iterator(), obj5, true).assertFalse
	}

	@Test
	def should_iterate_IteratorObjectboolean_significantOrder_2() {
		val obj1 = #{ "a", "b", "c" }
		val obj2 = #{ "a", "b", "c" }
		val obj3 = #{ "a", "c", "b" }
		val obj4 = #{ "a", "b" }
		val obj5 = #{ "a", "b", "c", "d" }
		should_iterate(obj1.iterator(), obj1, true).assertTrue
		should_iterate(obj1.iterator(), obj2, true).assertTrue
		should_iterate(obj1.iterator(), obj3, true).assertTrue
		should_iterate(obj1.iterator(), obj4, true).assertFalse
		should_iterate(obj1.iterator(), obj5, true).assertFalse
	}

	@Test
	def should_iterate_IteratorObjectboolean_significantOrder_3() {
		val obj1 = #{ "a" -> 1, "b" -> 2, "c" -> 3 }
		val obj2 = #{ "a" -> 1, "b" -> 2, "c" -> 3 }
		val obj3 = #{ "a" -> 1, "c" -> 3, "b" -> 2 }
		val obj4 = #{ "a" -> 1, "b" -> 2 }
		val obj5 = #{ "a" -> 1, "b" -> 2, "c" -> 3, "d" -> 4 }
		should_iterate(obj1.entrySet.iterator(), obj1, true).assertTrue
		should_iterate(obj1.entrySet.iterator(), obj2, true).assertTrue
		should_iterate(obj1.entrySet.iterator(), obj3, true).assertTrue
		should_iterate(obj1.entrySet.iterator(), obj4, true).assertFalse
		should_iterate(obj1.entrySet.iterator(), obj5, true).assertFalse
	}

	@Test
	def should_iterate_IteratorObjectboolean_significantOrder_4() {
		val obj1 = #[ "a", "b", "c" ]
		should_iterate(obj1.iterator(), "a", true).assertFalse
	}

	@Test
	def should_iterate_IteratorObjectboolean_significantOrder_5() {
		val String[] obj1 = #[ "a", "b", "c" ]
		should_iterate(obj1.iterator(), "a", true).assertFalse
	}

	@Test
	def should_iterate_IteratorObjectboolean_significantOrder_6() {
		val obj1 = #{ "a" -> 1, "b" -> 2, "c" -> 3 }
		should_iterate(obj1.entrySet.iterator(), "a" -> 1, true).assertFalse
	}

	@Test
	def should_iterate_IteratorObjectboolean_unsignificantOrder_0() {
		val obj1 = #[ "a", "b", "c" ]
		val obj2 = #[ "a", "b", "c" ]
		val obj3 = #[ "a", "c", "b" ]
		val obj4 = #[ "a", "b" ]
		val obj5 = #[ "a", "b", "c", "d" ]
		should_iterate(obj1.iterator(), obj1, false).assertTrue
		should_iterate(obj1.iterator(), obj2, false).assertTrue
		should_iterate(obj1.iterator(), obj3, false).assertTrue
		should_iterate(obj1.iterator(), obj4, false).assertFalse
		should_iterate(obj1.iterator(), obj5, false).assertFalse
	}

	@Test
	def should_iterate_IteratorObjectboolean_unsignificantOrder_1() {
		val String[] obj1 = #[ "a", "b", "c" ]
		val String[] obj2 = #[ "a", "b", "c" ]
		val String[] obj3 = #[ "a", "c", "b" ]
		val String[] obj4 = #[ "a", "b" ]
		val String[] obj5 = #[ "a", "b", "c", "d" ]
		should_iterate(obj1.iterator(), obj1, false).assertTrue
		should_iterate(obj1.iterator(), obj2, false).assertTrue
		should_iterate(obj1.iterator(), obj3, false).assertTrue
		should_iterate(obj1.iterator(), obj4, false).assertFalse
		should_iterate(obj1.iterator(), obj5, false).assertFalse
	}

	@Test
	def should_iterate_IteratorObjectboolean_unsignificantOrder_2() {
		val obj1 = #{ "a", "b", "c" }
		val obj2 = #{ "a", "b", "c" }
		val obj3 = #{ "a", "c", "b" }
		val obj4 = #{ "a", "b" }
		val obj5 = #{ "a", "b", "c", "d" }
		should_iterate(obj1.iterator(), obj1, false).assertTrue
		should_iterate(obj1.iterator(), obj2, false).assertTrue
		should_iterate(obj1.iterator(), obj3, false).assertTrue
		should_iterate(obj1.iterator(), obj4, false).assertFalse
		should_iterate(obj1.iterator(), obj5, false).assertFalse
	}

	@Test
	def should_iterate_IteratorObjectboolean_unsignificantOrder_3() {
		val obj1 = #{ "a" -> 1, "b" -> 2, "c" -> 3 }
		val obj2 = #{ "a" -> 1, "b" -> 2, "c" -> 3 }
		val obj3 = #{ "a" -> 1, "c" -> 3, "b" -> 2 }
		val obj4 = #{ "a" -> 1, "b" -> 2 }
		val obj5 = #{ "a" -> 1, "b" -> 2, "c" -> 3, "d" -> 4 }
		should_iterate(obj1.entrySet.iterator(), obj1, false).assertTrue
		should_iterate(obj1.entrySet.iterator(), obj2, false).assertTrue
		should_iterate(obj1.entrySet.iterator(), obj3, false).assertTrue
		should_iterate(obj1.entrySet.iterator(), obj4, false).assertFalse
		should_iterate(obj1.entrySet.iterator(), obj5, false).assertFalse
	}

	@Test
	def should_iterate_IteratorObjectboolean_unsignificantOrder_4() {
		val obj1 = #[ "a", "b", "c" ]
		should_iterate(obj1.iterator(), "a", false).assertFalse
	}

	@Test
	def should_iterate_IteratorObjectboolean_unsignificantOrder_5() {
		val String[] obj1 = #[ "a", "b", "c" ]
		should_iterate(obj1.iterator(), "a", false).assertFalse
	}

	@Test
	def should_iterate_IteratorObjectboolean_unsignificantOrder_6() {
		val obj1 = #{ "a" -> 1, "b" -> 2, "c" -> 3 }
		should_iterate(obj1.entrySet.iterator(), "a" -> 1, false).assertFalse
	}

	@Test
	def should_beNumber_0() {
		should_beNumber("abc", "00").assertFalse
		should_beNumber("1", "00").assertTrue
		should_beNumber("10", "00").assertTrue
	}

	@Test
	def should_beNumber_1() {
		should_beNumber(null as String, null as String).assertFalse
		should_beNumber("1", null as String).assertTrue
		should_beNumber(null as String, "00").assertFalse
	}

	@Test
	def should_haveMethod_0() {
		should_haveMethod(typeof(Object), "equals()").assertFalse
		should_haveMethod(typeof(Object), "equals(Object)").assertFalse
		should_haveMethod(typeof(Object), "equals(java.lang.Object)").assertFalse
		should_haveMethod(typeof(Object), "equals() : int").assertFalse
		should_haveMethod(typeof(Object), "equals(Object) : int").assertFalse
		should_haveMethod(typeof(Object), "equals(java.lang.Object) : int").assertFalse
		should_haveMethod(typeof(Object), "equals() : boolean").assertFalse
		should_haveMethod(typeof(Object), "equals(Object) : boolean").assertFalse
		should_haveMethod(typeof(Object), "equals(java.lang.Object) : boolean").assertTrue
	}

	@Test
	def should_haveMethod_1() {
		should_haveMethod(null as Class<?>, null as String).assertFalse
		should_haveMethod(typeof(Object), null as String).assertFalse
		should_haveMethod(null as Class<?>, "equals(java.lang.Object) : boolean").assertFalse
	}

	@Test
	def should_extend_0() {
		should_extend(typeof(Integer), "java.lang.Number").assertTrue
		should_extend(typeof(Integer), "java.lang.Number,java.lang.Comparable").assertFalse
		should_extend(typeof(Integer), "java.util.Serializable").assertFalse
		should_extend(typeof(Integer), "java.util.Serializable,java.lang.Comparable").assertFalse
	}

	@Test
	def should_implement_0() {
		should_implement(typeof(Integer), "java.lang.Number").assertFalse
		should_implement(typeof(Integer), "java.lang.Number,java.lang.Comparable").assertFalse
		should_implement(typeof(Integer), "java.lang.Comparable").assertTrue
		should_implement(typeof(Integer), "java.io.Serializable,java.lang.Comparable").assertFalse
	}

	@Test
	def should_haveNbMembers_0() {
		should_haveNbMembers(typeof(TestRule), 3).assertFalse // Due to hidden default constructor
		should_haveNbMembers(typeof(TestRule), 4).assertTrue // Due to hidden default constructor
	}

	@Test
	def should_haveNbMembers_1() {
		should_haveNbMembers(null as Class<?>, 3).assertFalse
		should_haveNbMembers(null as Class<?>, 4).assertFalse
	}

	@Test
	def should_beDate_0() {
		val obj1 = "2014-09-12"
		val obj2 = "14-09-12"
		val obj3 = "20140912"
		val obj4 = "abc"
		should_beDate(obj1, "YYYY-mm-dd").assertTrue
		should_beDate(obj2, "YYYY-mm-dd").assertTrue
		should_beDate(obj3, "YYYY-mm-dd").assertFalse
		should_beDate(obj4, "YYYY-mm-dd").assertFalse
		should_beDate(obj1, "YY-mm-dd").assertTrue
		should_beDate(obj2, "YY-mm-dd").assertTrue
		should_beDate(obj3, "YY-mm-dd").assertFalse
		should_beDate(obj4, "YY-mm-dd").assertFalse
	}

	@Test
	def should_beDate_1() {
		val obj1 = "2014-09-12"
		should_beDate(null as String, null as String).assertFalse
		should_beDate(obj1, null as String).assertFalse
		should_beDate(null as String, "YYYY-mm-dd").assertFalse
	}

	@Test
	def should_beValidVersionNumber_noMavenSnapshot_0() {
		val obj1 = "abc"
		val obj2 = "14.09.12"
		val obj3 = "2014-09-12"
		val obj4 = "2014.09-12"
		val obj5 = "2014.09+12"
		val obj6 = "2014.09:12"
		val obj7 = "1..0"
		val obj8 = "1.0._3"
		val obj9 = "1.0_.3"
		val obj10 = "1.0.+3"
		val obj11 = "1.0+.3"
		val obj12 = "1.0.:3"
		val obj13 = "1.0:.3"
		val obj14 = "1.0-"
		val obj15 = "-1.0"
		val obj16 = "1.a"
		should_beValidVersionNumber(obj1, false).assertFalse
		should_beValidVersionNumber(obj2, false).assertTrue
		should_beValidVersionNumber(obj3, false).assertTrue
		should_beValidVersionNumber(obj4, false).assertTrue
		should_beValidVersionNumber(obj5, false).assertTrue
		should_beValidVersionNumber(obj6, false).assertTrue
		should_beValidVersionNumber(obj7, false).assertFalse
		should_beValidVersionNumber(obj8, false).assertFalse
		should_beValidVersionNumber(obj9, false).assertFalse
		should_beValidVersionNumber(obj10, false).assertFalse
		should_beValidVersionNumber(obj11, false).assertFalse
		should_beValidVersionNumber(obj12, false).assertFalse
		should_beValidVersionNumber(obj13, false).assertFalse
		should_beValidVersionNumber(obj14, false).assertFalse
		should_beValidVersionNumber(obj15, false).assertFalse
		should_beValidVersionNumber(obj16, false).assertTrue
	}

	@Test
	def should_beValidVersionNumber_noMavenSnapshot_1() {
		val obj1 = "abc-SNAPSHOT"
		val obj2 = "14.09.12-SNAPSHOT"
		val obj3 = "2014-09-12-SNAPSHOT"
		val obj4 = "2014.09-12-SNAPSHOT"
		val obj5 = "2014.09+12-SNAPSHOT"
		val obj6 = "2014.09:12-SNAPSHOT"
		val obj7 = "1..0-SNAPSHOT"
		val obj8 = "1.0._3-SNAPSHOT"
		val obj9 = "1.0_.3-SNAPSHOT"
		val obj10 = "1.0.+3-SNAPSHOT"
		val obj11 = "1.0+.3-SNAPSHOT"
		val obj12 = "1.0.:3-SNAPSHOT"
		val obj13 = "1.0:.3-SNAPSHOT"
		val obj14 = "1.0--SNAPSHOT"
		val obj15 = "-1.0-SNAPSHOT"
		val obj16 = "1.a-SNAPSHOT"
		should_beValidVersionNumber(obj1, false).assertFalse
		should_beValidVersionNumber(obj2, false).assertFalse
		should_beValidVersionNumber(obj3, false).assertFalse
		should_beValidVersionNumber(obj4, false).assertFalse
		should_beValidVersionNumber(obj5, false).assertFalse
		should_beValidVersionNumber(obj6, false).assertFalse
		should_beValidVersionNumber(obj7, false).assertFalse
		should_beValidVersionNumber(obj8, false).assertFalse
		should_beValidVersionNumber(obj9, false).assertFalse
		should_beValidVersionNumber(obj10, false).assertFalse
		should_beValidVersionNumber(obj11, false).assertFalse
		should_beValidVersionNumber(obj12, false).assertFalse
		should_beValidVersionNumber(obj13, false).assertFalse
		should_beValidVersionNumber(obj14, false).assertFalse
		should_beValidVersionNumber(obj15, false).assertFalse
		should_beValidVersionNumber(obj16, false).assertFalse
	}

	@Test
	def should_beValidVersionNumber_noMavenSnapshot_2() {
		should_beValidVersionNumber(null as String, false).assertFalse
	}

	@Test
	def should_beValidVersionNumber_mavenSnapshot_0() {
		val obj1 = "abc"
		val obj2 = "14.09.12"
		val obj3 = "2014-09-12"
		val obj4 = "2014.09-12"
		val obj5 = "2014.09+12"
		val obj6 = "2014.09:12"
		val obj7 = "1..0"
		val obj8 = "1.0._3"
		val obj9 = "1.0_.3"
		val obj10 = "1.0.+3"
		val obj11 = "1.0+.3"
		val obj12 = "1.0.:3"
		val obj13 = "1.0:.3"
		val obj14 = "1.0-"
		val obj15 = "-1.0"
		val obj16 = "1.a"
		should_beValidVersionNumber(obj1, true).assertFalse
		should_beValidVersionNumber(obj2, true).assertTrue
		should_beValidVersionNumber(obj3, true).assertTrue
		should_beValidVersionNumber(obj4, true).assertTrue
		should_beValidVersionNumber(obj5, true).assertTrue
		should_beValidVersionNumber(obj6, true).assertTrue
		should_beValidVersionNumber(obj7, true).assertFalse
		should_beValidVersionNumber(obj8, true).assertFalse
		should_beValidVersionNumber(obj9, true).assertFalse
		should_beValidVersionNumber(obj10, true).assertFalse
		should_beValidVersionNumber(obj11, true).assertFalse
		should_beValidVersionNumber(obj12, true).assertFalse
		should_beValidVersionNumber(obj13, true).assertFalse
		should_beValidVersionNumber(obj14, true).assertFalse
		should_beValidVersionNumber(obj15, true).assertFalse
		should_beValidVersionNumber(obj16, true).assertTrue
	}

	@Test
	def should_beValidVersionNumber_mavenSnapshot_1() {
		val obj1 = "abc-SNAPSHOT"
		val obj2 = "14.09.12-SNAPSHOT"
		val obj3 = "2014-09-12-SNAPSHOT"
		val obj4 = "2014.09-12-SNAPSHOT"
		val obj5 = "2014.09+12-SNAPSHOT"
		val obj6 = "2014.09:12-SNAPSHOT"
		val obj7 = "1..0-SNAPSHOT"
		val obj8 = "1.0._3-SNAPSHOT"
		val obj9 = "1.0_.3-SNAPSHOT"
		val obj10 = "1.0.+3-SNAPSHOT"
		val obj11 = "1.0+.3-SNAPSHOT"
		val obj12 = "1.0.:3-SNAPSHOT"
		val obj13 = "1.0:.3-SNAPSHOT"
		val obj14 = "1.0--SNAPSHOT"
		val obj15 = "-1.0-SNAPSHOT"
		val obj16 = "1.a-SNAPSHOT"
		should_beValidVersionNumber(obj1, true).assertFalse
		should_beValidVersionNumber(obj2, true).assertTrue
		should_beValidVersionNumber(obj3, true).assertTrue
		should_beValidVersionNumber(obj4, true).assertTrue
		should_beValidVersionNumber(obj5, true).assertTrue
		should_beValidVersionNumber(obj6, true).assertTrue
		should_beValidVersionNumber(obj7, true).assertFalse
		should_beValidVersionNumber(obj8, true).assertFalse
		should_beValidVersionNumber(obj9, true).assertFalse
		should_beValidVersionNumber(obj10, true).assertFalse
		should_beValidVersionNumber(obj11, true).assertFalse
		should_beValidVersionNumber(obj12, true).assertFalse
		should_beValidVersionNumber(obj13, true).assertFalse
		should_beValidVersionNumber(obj14, true).assertFalse
		should_beValidVersionNumber(obj15, true).assertFalse
		should_beValidVersionNumber(obj16, true).assertTrue
	}

	@Test
	def should_beValidVersionNumber_mavenSnapshot_2() {
		should_beValidVersionNumber(null as String, true).assertFalse
	}

	@Test
	def should_beURL_0() {
		val url1 = "http://www.jnario.org"
		val url2 = "ftp://www.jnario.org"
		should_beURL(url1, "http").assertTrue
		should_beURL(url2, "http").assertFalse
		should_beURL(url1, "ftp").assertFalse
		should_beURL(url2, "ftp").assertTrue
		should_beURL(url1, "ssh").assertFalse
		should_beURL(url2, "ssh").assertFalse
	}

	@Test
	def should_beURL_1() {
		val url1 = "http://www.jnario.org"
		val url2 = "ftp://www.jnario.org"
		should_beURL(url1, "!http").assertFalse
		should_beURL(url2, "!http").assertTrue
		should_beURL(url1, "!ftp").assertTrue
		should_beURL(url2, "!ftp").assertFalse
		should_beURL(url1, "!ssh").assertTrue
		should_beURL(url2, "!ssh").assertTrue
	}

	@Test
	def should_beURL_2() {
		val url1 = "http://www.jnario.org"
		val url2 = "ftp://www.jnario.org"
		should_beURL(url1, "http,ftp").assertTrue
		should_beURL(url2, "http,ftp").assertTrue
		should_beURL(url1, "http,ssh").assertTrue
		should_beURL(url2, "http,ssh").assertFalse
		should_beURL(url1, "ftp,ssh").assertFalse
		should_beURL(url2, "ftp,ssh").assertTrue
	}

	@Test
	def should_beURL_3() {
		val url1 = "http://www.jnario.org"
		val url2 = "ftp://www.jnario.org"
		should_beURL(url1, "!http,!ftp").assertFalse
		should_beURL(url2, "!http,!ftp").assertFalse
		should_beURL(url1, "!http,!ssh").assertFalse
		should_beURL(url2, "!http,!ssh").assertTrue
		should_beURL(url1, "!ftp,!ssh").assertTrue
		should_beURL(url2, "!ftp,!ssh").assertFalse
	}

	@Test
	def should_beURL_4() {
		val url1 = "http://www.jnario.org"
		val url2 = "ftp://www.jnario.org"
		should_beURL(url1, "http,!ftp").assertTrue
		should_beURL(url2, "http,!ftp").assertFalse
		should_beURL(url1, "!http,ftp").assertFalse
		should_beURL(url2, "!http,ftp").assertTrue
		should_beURL(url1, "http,!ssh").assertTrue
		should_beURL(url2, "http,!ssh").assertFalse
		should_beURL(url1, "!http,ssh").assertFalse
		should_beURL(url2, "!http,ssh").assertFalse
		should_beURL(url1, "ftp,!ssh").assertFalse
		should_beURL(url2, "ftp,!ssh").assertTrue
		should_beURL(url1, "!ftp,ssh").assertFalse
		should_beURL(url2, "!ftp,ssh").assertFalse
	}

	@Test
	def should_beURL_5() {
		val url1 = "http://www.jnario.org"
		val url2 = "ftp://www.jnario.org"
		should_beURL(url1, "http,ftp,ssh").assertTrue
		should_beURL(url1, "http,ftp,!ssh").assertTrue
		should_beURL(url1, "http,!ftp,ssh").assertTrue
		should_beURL(url1, "http,!ftp,!ssh").assertTrue
		should_beURL(url1, "!http,ftp,ssh").assertFalse
		should_beURL(url1, "!http,ftp,!ssh").assertFalse
		should_beURL(url1, "!http,!ftp,ssh").assertFalse
		should_beURL(url1, "!http,!ftp,!ssh").assertFalse
		should_beURL(url2, "http,ftp,ssh").assertTrue
		should_beURL(url2, "http,ftp,!ssh").assertTrue
		should_beURL(url2, "http,!ftp,ssh").assertFalse
		should_beURL(url2, "http,!ftp,!ssh").assertFalse
		should_beURL(url2, "!http,ftp,ssh").assertTrue
		should_beURL(url2, "!http,ftp,!ssh").assertTrue
		should_beURL(url2, "!http,!ftp,ssh").assertFalse
		should_beURL(url2, "!http,!ftp,!ssh").assertFalse
	}

	@Test
	def should_beURL_6() {
		val url1 = "http://www.jnario.org"
		val url2 = "ftp://www.jnario.org"
		val url3 = "//www.jnario.org"
		val url4 = "\\\\mylaptop\\hello.txt"
		val url5 = "file:C:\\\\mylaptop\\hello.txt"
		should_beURL(url1, null as String).assertTrue
		should_beURL(url2, null as String).assertTrue
		should_beURL(url3, null as String).assertFalse
		should_beURL(url4, null as String).assertFalse
		should_beURL(url5, null as String).assertTrue
	}

	@Test
	def should_beURL_7() {
		val url1 = "http://www.jnario.org"
		should_beURL(null as String, null as String).assertFalse
		should_beURL(url1, null as String).assertTrue
		should_beURL(null, "http").assertFalse
	}

	@Test
	def should_beAccessibleFrom_0() {
		val url1 = "./TestSpec.html"
		val url2 = "./TestSpec2.html"
		should_beAccessibleFrom(url1, this).assertTrue
		should_beAccessibleFrom(url2, this).assertFalse
	}

	@Test
	def should_beAccessibleFrom_1() {
		val url1 = "./TestSpec.html"
		val url2 = "./TestSpec2.html"
		should_beAccessibleFrom(url1, new Object()).assertFalse
		should_beAccessibleFrom(url2, new Object()).assertFalse
	}

	@Test
	def should_beAccessibleFrom_2() {
		val url1 = "./TestSpec.html"
		should_beAccessibleFrom(null as String, null as Object).assertFalse
		should_beAccessibleFrom(url1, null as Object).assertFalse
		should_beAccessibleFrom(null as String, this).assertFalse
	}

	@Test
	def should_beAccessibleFrom_3() {
		val url1 = "./TestSpec.html#my_Func"
		val url2 = "./TestSpec.html#my_Func2"
		should_beAccessibleFrom(url1, this).assertTrue
		should_beAccessibleFrom(url2, this).assertFalse
	}

	@Test
	def should_beAccessibleFrom_4() {
		val url1 = "./TestSpec.html#my_Func"
		val url2 = "./TestSpec.html#my_Func2"
		should_beAccessibleFrom(url1, new Object()).assertFalse
		should_beAccessibleFrom(url2, new Object()).assertFalse
	}

	@Test
	def should_beAccessibleFrom_5() {
		val url1 = "./TestSpec.html#my_Func"
		should_beAccessibleFrom(null as String, null as Object).assertFalse
		should_beAccessibleFrom(url1, null as Object).assertFalse
		should_beAccessibleFrom(null as String, this).assertFalse
	}

	@Test
	def should_beAccessibleFrom_6() {
		val url1 = "./TestSpec.class"
		val url2 = "./TestSpec2.class"
		should_beAccessibleFrom(url1, this).assertTrue
		should_beAccessibleFrom(url2, this).assertFalse
	}

}
