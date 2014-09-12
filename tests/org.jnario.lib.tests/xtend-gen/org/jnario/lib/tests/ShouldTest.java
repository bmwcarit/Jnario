/**
 * Copyright (c) 2014 BMW Car IT and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.jnario.lib.tests;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.Pair;
import org.hamcrest.Matcher;
import org.jnario.lib.Assert;
import org.jnario.lib.Should;
import org.jnario.lib.tests.TestMatcher;
import org.jnario.lib.tests.TestRule;
import org.junit.Test;

/**
 * @author Stéphane Galland - Initial contribution and API
 */
@SuppressWarnings("all")
public class ShouldTest {
  @Test
  public void __0() {
    Object __ = Should.<Object>_();
    Assert.assertNull(__);
  }
  
  @Test
  public void nullValue_0() {
    final Matcher<Object> matcher = Should.<Object>nullValue();
    boolean _matches = matcher.matches("abc");
    Assert.assertFalse(_matches);
    boolean _matches_1 = matcher.matches(Integer.valueOf(1));
    Assert.assertFalse(_matches_1);
    boolean _matches_2 = matcher.matches(null);
    Assert.assertTrue(_matches_2);
  }
  
  @Test
  public void matches_StringFunction1_0() {
    final Function1<Object, Boolean> _function = new Function1<Object, Boolean>() {
      public Boolean apply(final Object it) {
        return Boolean.valueOf(true);
      }
    };
    final Matcher<? super Object> matcher1 = Should.<Object>matches("abc", _function);
    boolean _matches = matcher1.matches("abc");
    Assert.assertTrue(_matches);
    boolean _matches_1 = matcher1.matches(Integer.valueOf(1));
    Assert.assertTrue(_matches_1);
    final Function1<Object, Boolean> _function_1 = new Function1<Object, Boolean>() {
      public Boolean apply(final Object it) {
        return Boolean.valueOf(false);
      }
    };
    final Matcher<? super Object> matcher2 = Should.<Object>matches("abc", _function_1);
    boolean _matches_2 = matcher2.matches("abc");
    Assert.assertFalse(_matches_2);
    boolean _matches_3 = matcher2.matches(Integer.valueOf(1));
    Assert.assertFalse(_matches_3);
  }
  
  @Test
  public void matches_StringFunction1_1() {
    final Function1<Object, Boolean> _function = new Function1<Object, Boolean>() {
      public Boolean apply(final Object it) {
        return Boolean.valueOf(true);
      }
    };
    final Matcher<? super Object> matcher1 = Should.<Object>matches("abc", _function);
    boolean _matches = matcher1.matches(null);
    Assert.assertTrue(_matches);
    final Function1<Object, Boolean> _function_1 = new Function1<Object, Boolean>() {
      public Boolean apply(final Object it) {
        return Boolean.valueOf(false);
      }
    };
    final Matcher<? super Object> matcher2 = Should.<Object>matches("abc", _function_1);
    boolean _matches_1 = matcher2.matches(null);
    Assert.assertFalse(_matches_1);
  }
  
  @Test
  public void should_be_TFunction1_0() {
    final Object obj1 = new Object();
    final Object obj2 = new Object();
    final Function1<Object, Boolean> _function = new Function1<Object, Boolean>() {
      public Boolean apply(final Object it) {
        boolean _xblockexpression = false;
        {
          Assert.assertSame(obj1, it);
          _xblockexpression = true;
        }
        return Boolean.valueOf(_xblockexpression);
      }
    };
    boolean _should_be = Should.<Object>should_be(obj1, _function);
    Assert.assertTrue(_should_be);
    final Function1<Object, Boolean> _function_1 = new Function1<Object, Boolean>() {
      public Boolean apply(final Object it) {
        boolean _xblockexpression = false;
        {
          Assert.assertSame(obj2, it);
          _xblockexpression = false;
        }
        return Boolean.valueOf(_xblockexpression);
      }
    };
    boolean _should_be_1 = Should.<Object>should_be(obj2, _function_1);
    Assert.assertFalse(_should_be_1);
  }
  
  @Test
  public void should_be_TFunction1_1() {
    final Function1<Object, Boolean> _function = new Function1<Object, Boolean>() {
      public Boolean apply(final Object it) {
        boolean _xblockexpression = false;
        {
          Assert.assertNull(it);
          _xblockexpression = true;
        }
        return Boolean.valueOf(_xblockexpression);
      }
    };
    boolean _should_be = Should.<Object>should_be(null, _function);
    Assert.assertTrue(_should_be);
    final Function1<Object, Boolean> _function_1 = new Function1<Object, Boolean>() {
      public Boolean apply(final Object it) {
        boolean _xblockexpression = false;
        {
          Assert.assertNull(it);
          _xblockexpression = false;
        }
        return Boolean.valueOf(_xblockexpression);
      }
    };
    boolean _should_be_1 = Should.<Object>should_be(null, _function_1);
    Assert.assertFalse(_should_be_1);
  }
  
  @Test
  public void should_be_TT_0() {
    final String obj1 = "a";
    final String obj2 = "b";
    final String obj3 = "a";
    boolean _should_be = Should.<String>should_be(obj1, obj1);
    Assert.assertTrue(_should_be);
    boolean _should_be_1 = Should.<String>should_be(obj1, obj2);
    Assert.assertFalse(_should_be_1);
    boolean _should_be_2 = Should.<String>should_be(obj1, obj3);
    Assert.assertTrue(_should_be_2);
  }
  
  @Test
  public void should_be_TT_1() {
    final List<String> obj1 = Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("a"));
    final List<String> obj2 = Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("b"));
    final List<String> obj3 = Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("a"));
    boolean _should_be = Should.<List<String>>should_be(obj1, obj1);
    Assert.assertTrue(_should_be);
    boolean _should_be_1 = Should.<List<String>>should_be(obj1, obj2);
    Assert.assertFalse(_should_be_1);
    boolean _should_be_2 = Should.<List<String>>should_be(obj1, obj3);
    Assert.assertTrue(_should_be_2);
  }
  
  @Test
  public void should_be_TT_2() {
    final String obj1 = "a";
    boolean _should_be = Should.<String>should_be(((String) null), ((String) null));
    Assert.assertTrue(_should_be);
    boolean _should_be_1 = Should.<String>should_be(((String) null), obj1);
    Assert.assertFalse(_should_be_1);
    boolean _should_be_2 = Should.<String>should_be(obj1, ((String) null));
    Assert.assertFalse(_should_be_2);
  }
  
  @Test
  public void operator_doubleArrow_TT_0() {
    final String obj1 = "a";
    final String obj2 = "b";
    final String obj3 = "a";
    boolean _doubleArrow = Should.<String>operator_doubleArrow(obj1, obj1);
    Assert.assertTrue(_doubleArrow);
    boolean _doubleArrow_1 = Should.<String>operator_doubleArrow(obj1, obj2);
    Assert.assertFalse(_doubleArrow_1);
    boolean _doubleArrow_2 = Should.<String>operator_doubleArrow(obj1, obj3);
    Assert.assertTrue(_doubleArrow_2);
  }
  
  @Test
  public void operator_doubleArrow_TT_1() {
    final List<String> obj1 = Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("a"));
    final List<String> obj2 = Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("b"));
    final List<String> obj3 = Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("a"));
    boolean _doubleArrow = Should.<List<String>>operator_doubleArrow(obj1, obj1);
    Assert.assertTrue(_doubleArrow);
    boolean _doubleArrow_1 = Should.<List<String>>operator_doubleArrow(obj1, obj2);
    Assert.assertFalse(_doubleArrow_1);
    boolean _doubleArrow_2 = Should.<List<String>>operator_doubleArrow(obj1, obj3);
    Assert.assertTrue(_doubleArrow_2);
  }
  
  @Test
  public void operator_doubleArrow_TT_2() {
    final String obj1 = "a";
    boolean _doubleArrow = Should.<String>operator_doubleArrow(((String) null), ((String) null));
    Assert.assertTrue(_doubleArrow);
    boolean _doubleArrow_1 = Should.<String>operator_doubleArrow(((String) null), obj1);
    Assert.assertFalse(_doubleArrow_1);
    boolean _doubleArrow_2 = Should.<String>operator_doubleArrow(obj1, ((String) null));
    Assert.assertFalse(_doubleArrow_2);
  }
  
  @Test
  public void should_be_ClassClass_0() {
    final Class<TestRule> cls1 = TestRule.class;
    final Class<Object> cls2 = Object.class;
    final Class<TestRule> cls3 = TestRule.class;
    boolean _should_be = Should.should_be(cls1, cls1);
    Assert.assertTrue(_should_be);
    boolean _should_be_1 = Should.should_be(cls1, cls2);
    Assert.assertFalse(_should_be_1);
    boolean _should_be_2 = Should.should_be(cls1, cls3);
    Assert.assertTrue(_should_be_2);
  }
  
  @Test
  public void should_be_ClassClass_1() {
    final Class<TestRule> cls1 = TestRule.class;
    boolean _should_be = Should.should_be(((Class<?>) null), ((Class<?>) null));
    Assert.assertTrue(_should_be);
    boolean _should_be_1 = Should.should_be(cls1, ((Class<?>) null));
    Assert.assertFalse(_should_be_1);
    boolean _should_be_2 = Should.should_be(((Class<?>) null), cls1);
    Assert.assertFalse(_should_be_2);
  }
  
  @Test
  public void operator_doubleArrow_ClassClass_0() {
    final Class<TestRule> cls1 = TestRule.class;
    final Class<Object> cls2 = Object.class;
    final Class<TestRule> cls3 = TestRule.class;
    boolean _doubleArrow = Should.operator_doubleArrow(cls1, cls1);
    Assert.assertTrue(_doubleArrow);
    boolean _doubleArrow_1 = Should.operator_doubleArrow(cls1, cls2);
    Assert.assertFalse(_doubleArrow_1);
    boolean _doubleArrow_2 = Should.operator_doubleArrow(cls1, cls3);
    Assert.assertTrue(_doubleArrow_2);
  }
  
  @Test
  public void operator_doubleArrow_ClassClass_1() {
    final Class<TestRule> cls1 = TestRule.class;
    boolean _doubleArrow = Should.operator_doubleArrow(((Class<?>) null), ((Class<?>) null));
    Assert.assertTrue(_doubleArrow);
    boolean _doubleArrow_1 = Should.operator_doubleArrow(cls1, ((Class<?>) null));
    Assert.assertFalse(_doubleArrow_1);
    boolean _doubleArrow_2 = Should.operator_doubleArrow(((Class<?>) null), cls1);
    Assert.assertFalse(_doubleArrow_2);
  }
  
  @Test
  public void should_be_ObjectClass_0() {
    final String obj1 = "a";
    final Integer obj2 = ((Integer) Integer.valueOf(1));
    final Class<String> cls1 = String.class;
    final Class<Object> cls2 = Object.class;
    final Class<Integer> cls3 = Integer.class;
    boolean _should_be = Should.should_be(obj1, cls1);
    Assert.assertTrue(_should_be);
    boolean _should_be_1 = Should.should_be(obj1, cls2);
    Assert.assertTrue(_should_be_1);
    boolean _should_be_2 = Should.should_be(obj1, cls3);
    Assert.assertFalse(_should_be_2);
    boolean _should_be_3 = Should.should_be(obj2, cls1);
    Assert.assertFalse(_should_be_3);
    boolean _should_be_4 = Should.should_be(obj2, cls2);
    Assert.assertTrue(_should_be_4);
    boolean _should_be_5 = Should.should_be(obj2, cls3);
    Assert.assertTrue(_should_be_5);
  }
  
  @Test
  public void should_be_ObjectClass_1() {
    final String obj1 = "a";
    final Class<String> cls1 = String.class;
    boolean _should_be = Should.should_be(((Object) null), ((Class<?>) null));
    Assert.assertFalse(_should_be);
    boolean _should_be_1 = Should.should_be(obj1, ((Class<?>) null));
    Assert.assertFalse(_should_be_1);
    boolean _should_be_2 = Should.should_be(((Object) null), cls1);
    Assert.assertFalse(_should_be_2);
  }
  
  @Test
  public void operator_doubleArrow_ObjectClass_0() {
    final String obj1 = "a";
    final Integer obj2 = ((Integer) Integer.valueOf(1));
    final Class<String> cls1 = String.class;
    final Class<Object> cls2 = Object.class;
    final Class<Integer> cls3 = Integer.class;
    boolean _doubleArrow = Should.operator_doubleArrow(obj1, cls1);
    Assert.assertTrue(_doubleArrow);
    boolean _doubleArrow_1 = Should.operator_doubleArrow(obj1, cls2);
    Assert.assertTrue(_doubleArrow_1);
    boolean _doubleArrow_2 = Should.operator_doubleArrow(obj1, cls3);
    Assert.assertFalse(_doubleArrow_2);
    boolean _doubleArrow_3 = Should.operator_doubleArrow(obj2, cls1);
    Assert.assertFalse(_doubleArrow_3);
    boolean _doubleArrow_4 = Should.operator_doubleArrow(obj2, cls2);
    Assert.assertTrue(_doubleArrow_4);
    boolean _doubleArrow_5 = Should.operator_doubleArrow(obj2, cls3);
    Assert.assertTrue(_doubleArrow_5);
  }
  
  @Test
  public void operator_doubleArrow_ObjectClass_1() {
    final String obj1 = "a";
    final Class<String> cls1 = String.class;
    boolean _doubleArrow = Should.operator_doubleArrow(((Object) null), ((Class<?>) null));
    Assert.assertFalse(_doubleArrow);
    boolean _doubleArrow_1 = Should.operator_doubleArrow(obj1, ((Class<?>) null));
    Assert.assertFalse(_doubleArrow_1);
    boolean _doubleArrow_2 = Should.operator_doubleArrow(((Object) null), cls1);
    Assert.assertFalse(_doubleArrow_2);
  }
  
  @Test
  public void should_be_TMatcher_0() {
    final String obj1 = "a";
    final String obj2 = "b";
    TestMatcher<String> _testMatcher = new TestMatcher<String>(true, obj1);
    boolean _should_be = Should.<String>should_be(obj1, _testMatcher);
    Assert.assertTrue(_should_be);
    TestMatcher<String> _testMatcher_1 = new TestMatcher<String>(false, obj2);
    boolean _should_be_1 = Should.<String>should_be(obj2, _testMatcher_1);
    Assert.assertFalse(_should_be_1);
  }
  
  @Test
  public void should_be_TMatcher_1() {
    final String obj1 = "a";
    boolean _should_be = Should.<String>should_be(null, ((Matcher<String>) null));
    Assert.assertTrue(_should_be);
    boolean _should_be_1 = Should.<String>should_be(obj1, ((Matcher<String>) null));
    Assert.assertFalse(_should_be_1);
    TestMatcher<Object> _testMatcher = new TestMatcher<Object>(true, null);
    boolean _should_be_2 = Should.<Object>should_be(null, _testMatcher);
    Assert.assertTrue(_should_be_2);
    TestMatcher<Object> _testMatcher_1 = new TestMatcher<Object>(false, null);
    boolean _should_be_3 = Should.<Object>should_be(null, _testMatcher_1);
    Assert.assertFalse(_should_be_3);
  }
  
  @Test
  public void operator_doubleArrow_TMatcher_0() {
    final String obj1 = "a";
    final String obj2 = "b";
    TestMatcher<String> _testMatcher = new TestMatcher<String>(true, obj1);
    boolean _doubleArrow = Should.<String>operator_doubleArrow(obj1, _testMatcher);
    Assert.assertTrue(_doubleArrow);
    TestMatcher<String> _testMatcher_1 = new TestMatcher<String>(false, obj2);
    boolean _doubleArrow_1 = Should.<String>operator_doubleArrow(obj2, _testMatcher_1);
    Assert.assertFalse(_doubleArrow_1);
  }
  
  @Test
  public void operator_doubleArrow_TMatcher_1() {
    final String obj1 = "a";
    boolean _doubleArrow = Should.<String>operator_doubleArrow(null, ((Matcher<String>) null));
    Assert.assertTrue(_doubleArrow);
    boolean _doubleArrow_1 = Should.<String>operator_doubleArrow(obj1, ((Matcher<String>) null));
    Assert.assertFalse(_doubleArrow_1);
    TestMatcher<Object> _testMatcher = new TestMatcher<Object>(true, null);
    boolean _doubleArrow_2 = Should.<Object>operator_doubleArrow(null, _testMatcher);
    Assert.assertTrue(_doubleArrow_2);
    TestMatcher<Object> _testMatcher_1 = new TestMatcher<Object>(false, null);
    boolean _doubleArrow_3 = Should.<Object>operator_doubleArrow(null, _testMatcher_1);
    Assert.assertFalse(_doubleArrow_3);
  }
  
  @Test
  public void should_contain_IterableT_0() {
    final List<String> obj1 = Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("a", "b", "c"));
    boolean _should_contain = Should.<String>should_contain(obj1, "a");
    Assert.assertTrue(_should_contain);
    boolean _should_contain_1 = Should.<String>should_contain(obj1, "b");
    Assert.assertTrue(_should_contain_1);
    boolean _should_contain_2 = Should.<String>should_contain(obj1, "c");
    Assert.assertTrue(_should_contain_2);
    boolean _should_contain_3 = Should.<String>should_contain(obj1, "d");
    Assert.assertFalse(_should_contain_3);
  }
  
  @Test
  public void should_contain_IterableT_1() {
    final String[] obj1 = { "a", "b", "c" };
    boolean _should_contain = Should.<String>should_contain(((Iterable<String>)Conversions.doWrapArray(obj1)), "a");
    Assert.assertTrue(_should_contain);
    boolean _should_contain_1 = Should.<String>should_contain(((Iterable<String>)Conversions.doWrapArray(obj1)), "b");
    Assert.assertTrue(_should_contain_1);
    boolean _should_contain_2 = Should.<String>should_contain(((Iterable<String>)Conversions.doWrapArray(obj1)), "c");
    Assert.assertTrue(_should_contain_2);
    boolean _should_contain_3 = Should.<String>should_contain(((Iterable<String>)Conversions.doWrapArray(obj1)), "d");
    Assert.assertFalse(_should_contain_3);
  }
  
  @Test
  public void should_contain_IterableT_2() {
    final List<String> obj1 = Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("a", "b", "c"));
    boolean _should_contain = Should.<String>should_contain(((Iterable<String>) null), ((String) null));
    Assert.assertFalse(_should_contain);
    boolean _should_contain_1 = Should.<String>should_contain(obj1, ((String) null));
    Assert.assertFalse(_should_contain_1);
    boolean _should_contain_2 = Should.<String>should_contain(((Iterable<String>) null), "a");
    Assert.assertFalse(_should_contain_2);
  }
  
  @Test
  public void should_contain_IterableMatcher_0() {
    final List<String> obj1 = Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("a", "b", "c"));
    TestMatcher<String> _testMatcher = new TestMatcher<String>(true, Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("a", "b", "c")));
    boolean _should_contain = Should.<String>should_contain(obj1, _testMatcher);
    Assert.assertTrue(_should_contain);
    TestMatcher<String> _testMatcher_1 = new TestMatcher<String>(false, Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("a", "b", "c")));
    boolean _should_contain_1 = Should.<String>should_contain(obj1, _testMatcher_1);
    Assert.assertFalse(_should_contain_1);
  }
  
  @Test
  public void should_contain_IterableMatcher_1() {
    final List<String> obj1 = Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("a", "b", "c"));
    boolean _should_contain = Should.<String>should_contain(((Iterable<String>) null), ((Matcher<String>) null));
    Assert.assertFalse(_should_contain);
    TestMatcher<String> _testMatcher = new TestMatcher<String>(true, Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("a", "b", "c")));
    boolean _should_contain_1 = Should.<String>should_contain(((Iterable<String>) null), _testMatcher);
    Assert.assertFalse(_should_contain_1);
    boolean _should_contain_2 = Should.<String>should_contain(obj1, ((Matcher<String>) null));
    Assert.assertFalse(_should_contain_2);
  }
  
  @Test
  public void should_contain_CharSequenceCharSequence_0() {
    final String obj1 = "abcdef";
    final String obj2 = "abcdef";
    final String obj3 = "ghi";
    final String obj4 = "cd";
    boolean _should_contain = Should.<Object>should_contain(obj1, obj1);
    Assert.assertTrue(_should_contain);
    boolean _should_contain_1 = Should.<Object>should_contain(obj1, obj2);
    Assert.assertTrue(_should_contain_1);
    boolean _should_contain_2 = Should.<Object>should_contain(obj1, obj3);
    Assert.assertFalse(_should_contain_2);
    boolean _should_contain_3 = Should.<Object>should_contain(obj1, obj4);
    Assert.assertTrue(_should_contain_3);
  }
  
  @Test
  public void should_contain_CharSequenceCharSequence_1() {
    final String obj1 = "abcdef";
    boolean _should_contain = Should.<Object>should_contain(((String) null), ((String) null));
    Assert.assertFalse(_should_contain);
    boolean _should_contain_1 = Should.<Object>should_contain(obj1, ((String) null));
    Assert.assertFalse(_should_contain_1);
    boolean _should_contain_2 = Should.<Object>should_contain(((String) null), obj1);
    Assert.assertFalse(_should_contain_2);
  }
  
  @Test
  public void should_be_Tboolean_0() {
    final String obj1 = "abcdef";
    final boolean obj2 = true;
    final Object obj3 = ((Object) Boolean.valueOf(true));
    final boolean obj4 = false;
    final Object obj5 = ((Object) Boolean.valueOf(false));
    boolean _should_be = Should.<String>should_be(obj1, true);
    Assert.assertTrue(_should_be);
    boolean _should_be_1 = Should.<String>should_be(obj1, false);
    Assert.assertFalse(_should_be_1);
    boolean _should_be_2 = Should.<Boolean>should_be(Boolean.valueOf(obj2), true);
    Assert.assertTrue(_should_be_2);
    boolean _should_be_3 = Should.<Boolean>should_be(Boolean.valueOf(obj2), false);
    Assert.assertFalse(_should_be_3);
    boolean _should_be_4 = Should.<Object>should_be(obj3, true);
    Assert.assertTrue(_should_be_4);
    boolean _should_be_5 = Should.<Object>should_be(obj3, false);
    Assert.assertFalse(_should_be_5);
    boolean _should_be_6 = Should.<Boolean>should_be(Boolean.valueOf(obj4), true);
    Assert.assertFalse(_should_be_6);
    boolean _should_be_7 = Should.<Boolean>should_be(Boolean.valueOf(obj4), false);
    Assert.assertTrue(_should_be_7);
    boolean _should_be_8 = Should.<Object>should_be(obj5, true);
    Assert.assertFalse(_should_be_8);
    boolean _should_be_9 = Should.<Object>should_be(obj5, false);
    Assert.assertTrue(_should_be_9);
  }
  
  @Test
  public void should_be_Tboolean_1() {
    boolean _should_be = Should.<Boolean>should_be(((Boolean) null), true);
    Assert.assertTrue(_should_be);
    boolean _should_be_1 = Should.<Boolean>should_be(((Boolean) null), false);
    Assert.assertFalse(_should_be_1);
  }
  
  @Test
  public void operator_doubleArrow_Tboolean_0() {
    final String obj1 = "abcdef";
    final boolean obj2 = true;
    final Object obj3 = ((Object) Boolean.valueOf(true));
    final boolean obj4 = false;
    final Object obj5 = ((Object) Boolean.valueOf(false));
    boolean _doubleArrow = Should.<String>operator_doubleArrow(obj1, true);
    Assert.assertTrue(_doubleArrow);
    boolean _doubleArrow_1 = Should.<String>operator_doubleArrow(obj1, false);
    Assert.assertFalse(_doubleArrow_1);
    boolean _doubleArrow_2 = Should.<Boolean>operator_doubleArrow(Boolean.valueOf(obj2), true);
    Assert.assertTrue(_doubleArrow_2);
    boolean _doubleArrow_3 = Should.<Boolean>operator_doubleArrow(Boolean.valueOf(obj2), false);
    Assert.assertFalse(_doubleArrow_3);
    boolean _doubleArrow_4 = Should.<Object>operator_doubleArrow(obj3, true);
    Assert.assertTrue(_doubleArrow_4);
    boolean _doubleArrow_5 = Should.<Object>operator_doubleArrow(obj3, false);
    Assert.assertFalse(_doubleArrow_5);
    boolean _doubleArrow_6 = Should.<Boolean>operator_doubleArrow(Boolean.valueOf(obj4), true);
    Assert.assertFalse(_doubleArrow_6);
    boolean _doubleArrow_7 = Should.<Boolean>operator_doubleArrow(Boolean.valueOf(obj4), false);
    Assert.assertTrue(_doubleArrow_7);
    boolean _doubleArrow_8 = Should.<Object>operator_doubleArrow(obj5, true);
    Assert.assertFalse(_doubleArrow_8);
    boolean _doubleArrow_9 = Should.<Object>operator_doubleArrow(obj5, false);
    Assert.assertTrue(_doubleArrow_9);
  }
  
  @Test
  public void operator_doubleArrow_Tboolean_1() {
    boolean _doubleArrow = Should.<Boolean>operator_doubleArrow(((Boolean) null), true);
    Assert.assertTrue(_doubleArrow);
    boolean _doubleArrow_1 = Should.<Boolean>operator_doubleArrow(((Boolean) null), false);
    Assert.assertFalse(_doubleArrow_1);
  }
  
  @Test
  public void should_startWith_CharSequenceString_0() {
    final String obj1 = "abcdef";
    final String obj2 = "abcdef";
    final String obj3 = "ghi";
    final String obj4 = "ac";
    final String obj5 = "ab";
    boolean _should_startWith = Should.should_startWith(obj1, obj1);
    Assert.assertTrue(_should_startWith);
    boolean _should_startWith_1 = Should.should_startWith(obj1, obj2);
    Assert.assertTrue(_should_startWith_1);
    boolean _should_startWith_2 = Should.should_startWith(obj1, obj3);
    Assert.assertFalse(_should_startWith_2);
    boolean _should_startWith_3 = Should.should_startWith(obj1, obj4);
    Assert.assertFalse(_should_startWith_3);
    boolean _should_startWith_4 = Should.should_startWith(obj1, obj5);
    Assert.assertTrue(_should_startWith_4);
  }
  
  @Test
  public void should_startWith_CharSequenceString_1() {
    final String obj1 = "abcdef";
    boolean _should_startWith = Should.should_startWith(((String) null), ((String) null));
    Assert.assertFalse(_should_startWith);
    boolean _should_startWith_1 = Should.should_startWith(obj1, ((String) null));
    Assert.assertFalse(_should_startWith_1);
    boolean _should_startWith_2 = Should.should_startWith(((String) null), obj1);
    Assert.assertFalse(_should_startWith_2);
  }
  
  @Test
  public void should_endWith_CharSequenceString_0() {
    final String obj1 = "abcdef";
    final String obj2 = "abcdef";
    final String obj3 = "ghi";
    final String obj4 = "zf";
    final String obj5 = "ef";
    boolean _should_endWith = Should.should_endWith(obj1, obj1);
    Assert.assertTrue(_should_endWith);
    boolean _should_endWith_1 = Should.should_endWith(obj1, obj2);
    Assert.assertTrue(_should_endWith_1);
    boolean _should_endWith_2 = Should.should_endWith(obj1, obj3);
    Assert.assertFalse(_should_endWith_2);
    boolean _should_endWith_3 = Should.should_endWith(obj1, obj4);
    Assert.assertFalse(_should_endWith_3);
    boolean _should_endWith_4 = Should.should_endWith(obj1, obj5);
    Assert.assertTrue(_should_endWith_4);
  }
  
  @Test
  public void should_endWith_CharSequenceString_1() {
    final String obj1 = "abcdef";
    boolean _should_endWith = Should.should_endWith(((String) null), ((String) null));
    Assert.assertFalse(_should_endWith);
    boolean _should_endWith_1 = Should.should_endWith(obj1, ((String) null));
    Assert.assertFalse(_should_endWith_1);
    boolean _should_endWith_2 = Should.should_endWith(((String) null), obj1);
    Assert.assertFalse(_should_endWith_2);
  }
  
  @Test
  public void should_iterate_IteratorObject_0() {
    final List<String> obj1 = Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("a", "b", "c"));
    final List<String> obj2 = Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("a", "b", "c"));
    final List<String> obj3 = Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("a", "c", "b"));
    final List<String> obj4 = Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("a", "b"));
    final List<String> obj5 = Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("a", "b", "c", "d"));
    Iterator<String> _iterator = obj1.iterator();
    boolean _should_iterate = Should.should_iterate(_iterator, obj1);
    Assert.assertTrue(_should_iterate);
    Iterator<String> _iterator_1 = obj1.iterator();
    boolean _should_iterate_1 = Should.should_iterate(_iterator_1, obj2);
    Assert.assertTrue(_should_iterate_1);
    Iterator<String> _iterator_2 = obj1.iterator();
    boolean _should_iterate_2 = Should.should_iterate(_iterator_2, obj3);
    Assert.assertFalse(_should_iterate_2);
    Iterator<String> _iterator_3 = obj1.iterator();
    boolean _should_iterate_3 = Should.should_iterate(_iterator_3, obj4);
    Assert.assertFalse(_should_iterate_3);
    Iterator<String> _iterator_4 = obj1.iterator();
    boolean _should_iterate_4 = Should.should_iterate(_iterator_4, obj5);
    Assert.assertFalse(_should_iterate_4);
  }
  
  @Test
  public void should_iterate_IteratorObject_1() {
    final String[] obj1 = { "a", "b", "c" };
    final String[] obj2 = { "a", "b", "c" };
    final String[] obj3 = { "a", "c", "b" };
    final String[] obj4 = { "a", "b" };
    final String[] obj5 = { "a", "b", "c", "d" };
    Iterator<String> _iterator = ((List<String>)Conversions.doWrapArray(obj1)).iterator();
    boolean _should_iterate = Should.should_iterate(_iterator, obj1);
    Assert.assertTrue(_should_iterate);
    Iterator<String> _iterator_1 = ((List<String>)Conversions.doWrapArray(obj1)).iterator();
    boolean _should_iterate_1 = Should.should_iterate(_iterator_1, obj2);
    Assert.assertTrue(_should_iterate_1);
    Iterator<String> _iterator_2 = ((List<String>)Conversions.doWrapArray(obj1)).iterator();
    boolean _should_iterate_2 = Should.should_iterate(_iterator_2, obj3);
    Assert.assertFalse(_should_iterate_2);
    Iterator<String> _iterator_3 = ((List<String>)Conversions.doWrapArray(obj1)).iterator();
    boolean _should_iterate_3 = Should.should_iterate(_iterator_3, obj4);
    Assert.assertFalse(_should_iterate_3);
    Iterator<String> _iterator_4 = ((List<String>)Conversions.doWrapArray(obj1)).iterator();
    boolean _should_iterate_4 = Should.should_iterate(_iterator_4, obj5);
    Assert.assertFalse(_should_iterate_4);
  }
  
  @Test
  public void should_iterate_IteratorObject_2() {
    final Set<String> obj1 = Collections.<String>unmodifiableSet(CollectionLiterals.<String>newHashSet("a", "b", "c"));
    final Set<String> obj2 = Collections.<String>unmodifiableSet(CollectionLiterals.<String>newHashSet("a", "b", "c"));
    final Set<String> obj3 = Collections.<String>unmodifiableSet(CollectionLiterals.<String>newHashSet("a", "c", "b"));
    final Set<String> obj4 = Collections.<String>unmodifiableSet(CollectionLiterals.<String>newHashSet("a", "b"));
    final Set<String> obj5 = Collections.<String>unmodifiableSet(CollectionLiterals.<String>newHashSet("a", "b", "c", "d"));
    Iterator<String> _iterator = obj1.iterator();
    boolean _should_iterate = Should.should_iterate(_iterator, obj1);
    Assert.assertTrue(_should_iterate);
    Iterator<String> _iterator_1 = obj1.iterator();
    boolean _should_iterate_1 = Should.should_iterate(_iterator_1, obj2);
    Assert.assertTrue(_should_iterate_1);
    Iterator<String> _iterator_2 = obj1.iterator();
    boolean _should_iterate_2 = Should.should_iterate(_iterator_2, obj3);
    Assert.assertTrue(_should_iterate_2);
    Iterator<String> _iterator_3 = obj1.iterator();
    boolean _should_iterate_3 = Should.should_iterate(_iterator_3, obj4);
    Assert.assertFalse(_should_iterate_3);
    Iterator<String> _iterator_4 = obj1.iterator();
    boolean _should_iterate_4 = Should.should_iterate(_iterator_4, obj5);
    Assert.assertFalse(_should_iterate_4);
  }
  
  @Test
  public void should_iterate_IteratorObject_3() {
    Pair<String, Integer> _mappedTo = Pair.<String, Integer>of("a", Integer.valueOf(1));
    Pair<String, Integer> _mappedTo_1 = Pair.<String, Integer>of("b", Integer.valueOf(2));
    Pair<String, Integer> _mappedTo_2 = Pair.<String, Integer>of("c", Integer.valueOf(3));
    final Map<String, Integer> obj1 = Collections.<String, Integer>unmodifiableMap(CollectionLiterals.<String, Integer>newHashMap(_mappedTo, _mappedTo_1, _mappedTo_2));
    Pair<String, Integer> _mappedTo_3 = Pair.<String, Integer>of("a", Integer.valueOf(1));
    Pair<String, Integer> _mappedTo_4 = Pair.<String, Integer>of("b", Integer.valueOf(2));
    Pair<String, Integer> _mappedTo_5 = Pair.<String, Integer>of("c", Integer.valueOf(3));
    final Map<String, Integer> obj2 = Collections.<String, Integer>unmodifiableMap(CollectionLiterals.<String, Integer>newHashMap(_mappedTo_3, _mappedTo_4, _mappedTo_5));
    Pair<String, Integer> _mappedTo_6 = Pair.<String, Integer>of("a", Integer.valueOf(1));
    Pair<String, Integer> _mappedTo_7 = Pair.<String, Integer>of("c", Integer.valueOf(3));
    Pair<String, Integer> _mappedTo_8 = Pair.<String, Integer>of("b", Integer.valueOf(2));
    final Map<String, Integer> obj3 = Collections.<String, Integer>unmodifiableMap(CollectionLiterals.<String, Integer>newHashMap(_mappedTo_6, _mappedTo_7, _mappedTo_8));
    Pair<String, Integer> _mappedTo_9 = Pair.<String, Integer>of("a", Integer.valueOf(1));
    Pair<String, Integer> _mappedTo_10 = Pair.<String, Integer>of("b", Integer.valueOf(2));
    final Map<String, Integer> obj4 = Collections.<String, Integer>unmodifiableMap(CollectionLiterals.<String, Integer>newHashMap(_mappedTo_9, _mappedTo_10));
    Pair<String, Integer> _mappedTo_11 = Pair.<String, Integer>of("a", Integer.valueOf(1));
    Pair<String, Integer> _mappedTo_12 = Pair.<String, Integer>of("b", Integer.valueOf(2));
    Pair<String, Integer> _mappedTo_13 = Pair.<String, Integer>of("c", Integer.valueOf(3));
    Pair<String, Integer> _mappedTo_14 = Pair.<String, Integer>of("d", Integer.valueOf(4));
    final Map<String, Integer> obj5 = Collections.<String, Integer>unmodifiableMap(CollectionLiterals.<String, Integer>newHashMap(_mappedTo_11, _mappedTo_12, _mappedTo_13, _mappedTo_14));
    Set<Map.Entry<String, Integer>> _entrySet = obj1.entrySet();
    Iterator<Map.Entry<String, Integer>> _iterator = _entrySet.iterator();
    boolean _should_iterate = Should.should_iterate(_iterator, obj1);
    Assert.assertTrue(_should_iterate);
    Set<Map.Entry<String, Integer>> _entrySet_1 = obj1.entrySet();
    Iterator<Map.Entry<String, Integer>> _iterator_1 = _entrySet_1.iterator();
    boolean _should_iterate_1 = Should.should_iterate(_iterator_1, obj2);
    Assert.assertTrue(_should_iterate_1);
    Set<Map.Entry<String, Integer>> _entrySet_2 = obj1.entrySet();
    Iterator<Map.Entry<String, Integer>> _iterator_2 = _entrySet_2.iterator();
    boolean _should_iterate_2 = Should.should_iterate(_iterator_2, obj3);
    Assert.assertTrue(_should_iterate_2);
    Set<Map.Entry<String, Integer>> _entrySet_3 = obj1.entrySet();
    Iterator<Map.Entry<String, Integer>> _iterator_3 = _entrySet_3.iterator();
    boolean _should_iterate_3 = Should.should_iterate(_iterator_3, obj4);
    Assert.assertFalse(_should_iterate_3);
    Set<Map.Entry<String, Integer>> _entrySet_4 = obj1.entrySet();
    Iterator<Map.Entry<String, Integer>> _iterator_4 = _entrySet_4.iterator();
    boolean _should_iterate_4 = Should.should_iterate(_iterator_4, obj5);
    Assert.assertFalse(_should_iterate_4);
  }
  
  @Test
  public void should_iterate_IteratorObject_4() {
    final List<String> obj1 = Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("a", "b", "c"));
    Iterator<String> _iterator = obj1.iterator();
    boolean _should_iterate = Should.should_iterate(_iterator, "a");
    Assert.assertFalse(_should_iterate);
  }
  
  @Test
  public void should_iterate_IteratorObject_5() {
    final String[] obj1 = { "a", "b", "c" };
    Iterator<String> _iterator = ((List<String>)Conversions.doWrapArray(obj1)).iterator();
    boolean _should_iterate = Should.should_iterate(_iterator, "a");
    Assert.assertFalse(_should_iterate);
  }
  
  @Test
  public void should_iterate_IteratorObject_6() {
    Pair<String, Integer> _mappedTo = Pair.<String, Integer>of("a", Integer.valueOf(1));
    Pair<String, Integer> _mappedTo_1 = Pair.<String, Integer>of("b", Integer.valueOf(2));
    Pair<String, Integer> _mappedTo_2 = Pair.<String, Integer>of("c", Integer.valueOf(3));
    final Map<String, Integer> obj1 = Collections.<String, Integer>unmodifiableMap(CollectionLiterals.<String, Integer>newHashMap(_mappedTo, _mappedTo_1, _mappedTo_2));
    Set<Map.Entry<String, Integer>> _entrySet = obj1.entrySet();
    Iterator<Map.Entry<String, Integer>> _iterator = _entrySet.iterator();
    Pair<String, Integer> _mappedTo_3 = Pair.<String, Integer>of("a", Integer.valueOf(1));
    boolean _should_iterate = Should.should_iterate(_iterator, _mappedTo_3);
    Assert.assertFalse(_should_iterate);
  }
  
  @Test
  public void should_iterate_IteratorObjectboolean_significantOrder_0() {
    final List<String> obj1 = Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("a", "b", "c"));
    final List<String> obj2 = Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("a", "b", "c"));
    final List<String> obj3 = Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("a", "c", "b"));
    final List<String> obj4 = Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("a", "b"));
    final List<String> obj5 = Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("a", "b", "c", "d"));
    Iterator<String> _iterator = obj1.iterator();
    boolean _should_iterate = Should.should_iterate(_iterator, obj1, true);
    Assert.assertTrue(_should_iterate);
    Iterator<String> _iterator_1 = obj1.iterator();
    boolean _should_iterate_1 = Should.should_iterate(_iterator_1, obj2, true);
    Assert.assertTrue(_should_iterate_1);
    Iterator<String> _iterator_2 = obj1.iterator();
    boolean _should_iterate_2 = Should.should_iterate(_iterator_2, obj3, true);
    Assert.assertFalse(_should_iterate_2);
    Iterator<String> _iterator_3 = obj1.iterator();
    boolean _should_iterate_3 = Should.should_iterate(_iterator_3, obj4, true);
    Assert.assertFalse(_should_iterate_3);
    Iterator<String> _iterator_4 = obj1.iterator();
    boolean _should_iterate_4 = Should.should_iterate(_iterator_4, obj5, true);
    Assert.assertFalse(_should_iterate_4);
  }
  
  @Test
  public void should_iterate_IteratorObjectboolean_significantOrder_1() {
    final String[] obj1 = { "a", "b", "c" };
    final String[] obj2 = { "a", "b", "c" };
    final String[] obj3 = { "a", "c", "b" };
    final String[] obj4 = { "a", "b" };
    final String[] obj5 = { "a", "b", "c", "d" };
    Iterator<String> _iterator = ((List<String>)Conversions.doWrapArray(obj1)).iterator();
    boolean _should_iterate = Should.should_iterate(_iterator, obj1, true);
    Assert.assertTrue(_should_iterate);
    Iterator<String> _iterator_1 = ((List<String>)Conversions.doWrapArray(obj1)).iterator();
    boolean _should_iterate_1 = Should.should_iterate(_iterator_1, obj2, true);
    Assert.assertTrue(_should_iterate_1);
    Iterator<String> _iterator_2 = ((List<String>)Conversions.doWrapArray(obj1)).iterator();
    boolean _should_iterate_2 = Should.should_iterate(_iterator_2, obj3, true);
    Assert.assertFalse(_should_iterate_2);
    Iterator<String> _iterator_3 = ((List<String>)Conversions.doWrapArray(obj1)).iterator();
    boolean _should_iterate_3 = Should.should_iterate(_iterator_3, obj4, true);
    Assert.assertFalse(_should_iterate_3);
    Iterator<String> _iterator_4 = ((List<String>)Conversions.doWrapArray(obj1)).iterator();
    boolean _should_iterate_4 = Should.should_iterate(_iterator_4, obj5, true);
    Assert.assertFalse(_should_iterate_4);
  }
  
  @Test
  public void should_iterate_IteratorObjectboolean_significantOrder_2() {
    final Set<String> obj1 = Collections.<String>unmodifiableSet(CollectionLiterals.<String>newHashSet("a", "b", "c"));
    final Set<String> obj2 = Collections.<String>unmodifiableSet(CollectionLiterals.<String>newHashSet("a", "b", "c"));
    final Set<String> obj3 = Collections.<String>unmodifiableSet(CollectionLiterals.<String>newHashSet("a", "c", "b"));
    final Set<String> obj4 = Collections.<String>unmodifiableSet(CollectionLiterals.<String>newHashSet("a", "b"));
    final Set<String> obj5 = Collections.<String>unmodifiableSet(CollectionLiterals.<String>newHashSet("a", "b", "c", "d"));
    Iterator<String> _iterator = obj1.iterator();
    boolean _should_iterate = Should.should_iterate(_iterator, obj1, true);
    Assert.assertTrue(_should_iterate);
    Iterator<String> _iterator_1 = obj1.iterator();
    boolean _should_iterate_1 = Should.should_iterate(_iterator_1, obj2, true);
    Assert.assertTrue(_should_iterate_1);
    Iterator<String> _iterator_2 = obj1.iterator();
    boolean _should_iterate_2 = Should.should_iterate(_iterator_2, obj3, true);
    Assert.assertTrue(_should_iterate_2);
    Iterator<String> _iterator_3 = obj1.iterator();
    boolean _should_iterate_3 = Should.should_iterate(_iterator_3, obj4, true);
    Assert.assertFalse(_should_iterate_3);
    Iterator<String> _iterator_4 = obj1.iterator();
    boolean _should_iterate_4 = Should.should_iterate(_iterator_4, obj5, true);
    Assert.assertFalse(_should_iterate_4);
  }
  
  @Test
  public void should_iterate_IteratorObjectboolean_significantOrder_3() {
    Pair<String, Integer> _mappedTo = Pair.<String, Integer>of("a", Integer.valueOf(1));
    Pair<String, Integer> _mappedTo_1 = Pair.<String, Integer>of("b", Integer.valueOf(2));
    Pair<String, Integer> _mappedTo_2 = Pair.<String, Integer>of("c", Integer.valueOf(3));
    final Map<String, Integer> obj1 = Collections.<String, Integer>unmodifiableMap(CollectionLiterals.<String, Integer>newHashMap(_mappedTo, _mappedTo_1, _mappedTo_2));
    Pair<String, Integer> _mappedTo_3 = Pair.<String, Integer>of("a", Integer.valueOf(1));
    Pair<String, Integer> _mappedTo_4 = Pair.<String, Integer>of("b", Integer.valueOf(2));
    Pair<String, Integer> _mappedTo_5 = Pair.<String, Integer>of("c", Integer.valueOf(3));
    final Map<String, Integer> obj2 = Collections.<String, Integer>unmodifiableMap(CollectionLiterals.<String, Integer>newHashMap(_mappedTo_3, _mappedTo_4, _mappedTo_5));
    Pair<String, Integer> _mappedTo_6 = Pair.<String, Integer>of("a", Integer.valueOf(1));
    Pair<String, Integer> _mappedTo_7 = Pair.<String, Integer>of("c", Integer.valueOf(3));
    Pair<String, Integer> _mappedTo_8 = Pair.<String, Integer>of("b", Integer.valueOf(2));
    final Map<String, Integer> obj3 = Collections.<String, Integer>unmodifiableMap(CollectionLiterals.<String, Integer>newHashMap(_mappedTo_6, _mappedTo_7, _mappedTo_8));
    Pair<String, Integer> _mappedTo_9 = Pair.<String, Integer>of("a", Integer.valueOf(1));
    Pair<String, Integer> _mappedTo_10 = Pair.<String, Integer>of("b", Integer.valueOf(2));
    final Map<String, Integer> obj4 = Collections.<String, Integer>unmodifiableMap(CollectionLiterals.<String, Integer>newHashMap(_mappedTo_9, _mappedTo_10));
    Pair<String, Integer> _mappedTo_11 = Pair.<String, Integer>of("a", Integer.valueOf(1));
    Pair<String, Integer> _mappedTo_12 = Pair.<String, Integer>of("b", Integer.valueOf(2));
    Pair<String, Integer> _mappedTo_13 = Pair.<String, Integer>of("c", Integer.valueOf(3));
    Pair<String, Integer> _mappedTo_14 = Pair.<String, Integer>of("d", Integer.valueOf(4));
    final Map<String, Integer> obj5 = Collections.<String, Integer>unmodifiableMap(CollectionLiterals.<String, Integer>newHashMap(_mappedTo_11, _mappedTo_12, _mappedTo_13, _mappedTo_14));
    Set<Map.Entry<String, Integer>> _entrySet = obj1.entrySet();
    Iterator<Map.Entry<String, Integer>> _iterator = _entrySet.iterator();
    boolean _should_iterate = Should.should_iterate(_iterator, obj1, true);
    Assert.assertTrue(_should_iterate);
    Set<Map.Entry<String, Integer>> _entrySet_1 = obj1.entrySet();
    Iterator<Map.Entry<String, Integer>> _iterator_1 = _entrySet_1.iterator();
    boolean _should_iterate_1 = Should.should_iterate(_iterator_1, obj2, true);
    Assert.assertTrue(_should_iterate_1);
    Set<Map.Entry<String, Integer>> _entrySet_2 = obj1.entrySet();
    Iterator<Map.Entry<String, Integer>> _iterator_2 = _entrySet_2.iterator();
    boolean _should_iterate_2 = Should.should_iterate(_iterator_2, obj3, true);
    Assert.assertTrue(_should_iterate_2);
    Set<Map.Entry<String, Integer>> _entrySet_3 = obj1.entrySet();
    Iterator<Map.Entry<String, Integer>> _iterator_3 = _entrySet_3.iterator();
    boolean _should_iterate_3 = Should.should_iterate(_iterator_3, obj4, true);
    Assert.assertFalse(_should_iterate_3);
    Set<Map.Entry<String, Integer>> _entrySet_4 = obj1.entrySet();
    Iterator<Map.Entry<String, Integer>> _iterator_4 = _entrySet_4.iterator();
    boolean _should_iterate_4 = Should.should_iterate(_iterator_4, obj5, true);
    Assert.assertFalse(_should_iterate_4);
  }
  
  @Test
  public void should_iterate_IteratorObjectboolean_significantOrder_4() {
    final List<String> obj1 = Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("a", "b", "c"));
    Iterator<String> _iterator = obj1.iterator();
    boolean _should_iterate = Should.should_iterate(_iterator, "a", true);
    Assert.assertFalse(_should_iterate);
  }
  
  @Test
  public void should_iterate_IteratorObjectboolean_significantOrder_5() {
    final String[] obj1 = { "a", "b", "c" };
    Iterator<String> _iterator = ((List<String>)Conversions.doWrapArray(obj1)).iterator();
    boolean _should_iterate = Should.should_iterate(_iterator, "a", true);
    Assert.assertFalse(_should_iterate);
  }
  
  @Test
  public void should_iterate_IteratorObjectboolean_significantOrder_6() {
    Pair<String, Integer> _mappedTo = Pair.<String, Integer>of("a", Integer.valueOf(1));
    Pair<String, Integer> _mappedTo_1 = Pair.<String, Integer>of("b", Integer.valueOf(2));
    Pair<String, Integer> _mappedTo_2 = Pair.<String, Integer>of("c", Integer.valueOf(3));
    final Map<String, Integer> obj1 = Collections.<String, Integer>unmodifiableMap(CollectionLiterals.<String, Integer>newHashMap(_mappedTo, _mappedTo_1, _mappedTo_2));
    Set<Map.Entry<String, Integer>> _entrySet = obj1.entrySet();
    Iterator<Map.Entry<String, Integer>> _iterator = _entrySet.iterator();
    Pair<String, Integer> _mappedTo_3 = Pair.<String, Integer>of("a", Integer.valueOf(1));
    boolean _should_iterate = Should.should_iterate(_iterator, _mappedTo_3, true);
    Assert.assertFalse(_should_iterate);
  }
  
  @Test
  public void should_iterate_IteratorObjectboolean_unsignificantOrder_0() {
    final List<String> obj1 = Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("a", "b", "c"));
    final List<String> obj2 = Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("a", "b", "c"));
    final List<String> obj3 = Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("a", "c", "b"));
    final List<String> obj4 = Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("a", "b"));
    final List<String> obj5 = Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("a", "b", "c", "d"));
    Iterator<String> _iterator = obj1.iterator();
    boolean _should_iterate = Should.should_iterate(_iterator, obj1, false);
    Assert.assertTrue(_should_iterate);
    Iterator<String> _iterator_1 = obj1.iterator();
    boolean _should_iterate_1 = Should.should_iterate(_iterator_1, obj2, false);
    Assert.assertTrue(_should_iterate_1);
    Iterator<String> _iterator_2 = obj1.iterator();
    boolean _should_iterate_2 = Should.should_iterate(_iterator_2, obj3, false);
    Assert.assertTrue(_should_iterate_2);
    Iterator<String> _iterator_3 = obj1.iterator();
    boolean _should_iterate_3 = Should.should_iterate(_iterator_3, obj4, false);
    Assert.assertFalse(_should_iterate_3);
    Iterator<String> _iterator_4 = obj1.iterator();
    boolean _should_iterate_4 = Should.should_iterate(_iterator_4, obj5, false);
    Assert.assertFalse(_should_iterate_4);
  }
  
  @Test
  public void should_iterate_IteratorObjectboolean_unsignificantOrder_1() {
    final String[] obj1 = { "a", "b", "c" };
    final String[] obj2 = { "a", "b", "c" };
    final String[] obj3 = { "a", "c", "b" };
    final String[] obj4 = { "a", "b" };
    final String[] obj5 = { "a", "b", "c", "d" };
    Iterator<String> _iterator = ((List<String>)Conversions.doWrapArray(obj1)).iterator();
    boolean _should_iterate = Should.should_iterate(_iterator, obj1, false);
    Assert.assertTrue(_should_iterate);
    Iterator<String> _iterator_1 = ((List<String>)Conversions.doWrapArray(obj1)).iterator();
    boolean _should_iterate_1 = Should.should_iterate(_iterator_1, obj2, false);
    Assert.assertTrue(_should_iterate_1);
    Iterator<String> _iterator_2 = ((List<String>)Conversions.doWrapArray(obj1)).iterator();
    boolean _should_iterate_2 = Should.should_iterate(_iterator_2, obj3, false);
    Assert.assertTrue(_should_iterate_2);
    Iterator<String> _iterator_3 = ((List<String>)Conversions.doWrapArray(obj1)).iterator();
    boolean _should_iterate_3 = Should.should_iterate(_iterator_3, obj4, false);
    Assert.assertFalse(_should_iterate_3);
    Iterator<String> _iterator_4 = ((List<String>)Conversions.doWrapArray(obj1)).iterator();
    boolean _should_iterate_4 = Should.should_iterate(_iterator_4, obj5, false);
    Assert.assertFalse(_should_iterate_4);
  }
  
  @Test
  public void should_iterate_IteratorObjectboolean_unsignificantOrder_2() {
    final Set<String> obj1 = Collections.<String>unmodifiableSet(CollectionLiterals.<String>newHashSet("a", "b", "c"));
    final Set<String> obj2 = Collections.<String>unmodifiableSet(CollectionLiterals.<String>newHashSet("a", "b", "c"));
    final Set<String> obj3 = Collections.<String>unmodifiableSet(CollectionLiterals.<String>newHashSet("a", "c", "b"));
    final Set<String> obj4 = Collections.<String>unmodifiableSet(CollectionLiterals.<String>newHashSet("a", "b"));
    final Set<String> obj5 = Collections.<String>unmodifiableSet(CollectionLiterals.<String>newHashSet("a", "b", "c", "d"));
    Iterator<String> _iterator = obj1.iterator();
    boolean _should_iterate = Should.should_iterate(_iterator, obj1, false);
    Assert.assertTrue(_should_iterate);
    Iterator<String> _iterator_1 = obj1.iterator();
    boolean _should_iterate_1 = Should.should_iterate(_iterator_1, obj2, false);
    Assert.assertTrue(_should_iterate_1);
    Iterator<String> _iterator_2 = obj1.iterator();
    boolean _should_iterate_2 = Should.should_iterate(_iterator_2, obj3, false);
    Assert.assertTrue(_should_iterate_2);
    Iterator<String> _iterator_3 = obj1.iterator();
    boolean _should_iterate_3 = Should.should_iterate(_iterator_3, obj4, false);
    Assert.assertFalse(_should_iterate_3);
    Iterator<String> _iterator_4 = obj1.iterator();
    boolean _should_iterate_4 = Should.should_iterate(_iterator_4, obj5, false);
    Assert.assertFalse(_should_iterate_4);
  }
  
  @Test
  public void should_iterate_IteratorObjectboolean_unsignificantOrder_3() {
    Pair<String, Integer> _mappedTo = Pair.<String, Integer>of("a", Integer.valueOf(1));
    Pair<String, Integer> _mappedTo_1 = Pair.<String, Integer>of("b", Integer.valueOf(2));
    Pair<String, Integer> _mappedTo_2 = Pair.<String, Integer>of("c", Integer.valueOf(3));
    final Map<String, Integer> obj1 = Collections.<String, Integer>unmodifiableMap(CollectionLiterals.<String, Integer>newHashMap(_mappedTo, _mappedTo_1, _mappedTo_2));
    Pair<String, Integer> _mappedTo_3 = Pair.<String, Integer>of("a", Integer.valueOf(1));
    Pair<String, Integer> _mappedTo_4 = Pair.<String, Integer>of("b", Integer.valueOf(2));
    Pair<String, Integer> _mappedTo_5 = Pair.<String, Integer>of("c", Integer.valueOf(3));
    final Map<String, Integer> obj2 = Collections.<String, Integer>unmodifiableMap(CollectionLiterals.<String, Integer>newHashMap(_mappedTo_3, _mappedTo_4, _mappedTo_5));
    Pair<String, Integer> _mappedTo_6 = Pair.<String, Integer>of("a", Integer.valueOf(1));
    Pair<String, Integer> _mappedTo_7 = Pair.<String, Integer>of("c", Integer.valueOf(3));
    Pair<String, Integer> _mappedTo_8 = Pair.<String, Integer>of("b", Integer.valueOf(2));
    final Map<String, Integer> obj3 = Collections.<String, Integer>unmodifiableMap(CollectionLiterals.<String, Integer>newHashMap(_mappedTo_6, _mappedTo_7, _mappedTo_8));
    Pair<String, Integer> _mappedTo_9 = Pair.<String, Integer>of("a", Integer.valueOf(1));
    Pair<String, Integer> _mappedTo_10 = Pair.<String, Integer>of("b", Integer.valueOf(2));
    final Map<String, Integer> obj4 = Collections.<String, Integer>unmodifiableMap(CollectionLiterals.<String, Integer>newHashMap(_mappedTo_9, _mappedTo_10));
    Pair<String, Integer> _mappedTo_11 = Pair.<String, Integer>of("a", Integer.valueOf(1));
    Pair<String, Integer> _mappedTo_12 = Pair.<String, Integer>of("b", Integer.valueOf(2));
    Pair<String, Integer> _mappedTo_13 = Pair.<String, Integer>of("c", Integer.valueOf(3));
    Pair<String, Integer> _mappedTo_14 = Pair.<String, Integer>of("d", Integer.valueOf(4));
    final Map<String, Integer> obj5 = Collections.<String, Integer>unmodifiableMap(CollectionLiterals.<String, Integer>newHashMap(_mappedTo_11, _mappedTo_12, _mappedTo_13, _mappedTo_14));
    Set<Map.Entry<String, Integer>> _entrySet = obj1.entrySet();
    Iterator<Map.Entry<String, Integer>> _iterator = _entrySet.iterator();
    boolean _should_iterate = Should.should_iterate(_iterator, obj1, false);
    Assert.assertTrue(_should_iterate);
    Set<Map.Entry<String, Integer>> _entrySet_1 = obj1.entrySet();
    Iterator<Map.Entry<String, Integer>> _iterator_1 = _entrySet_1.iterator();
    boolean _should_iterate_1 = Should.should_iterate(_iterator_1, obj2, false);
    Assert.assertTrue(_should_iterate_1);
    Set<Map.Entry<String, Integer>> _entrySet_2 = obj1.entrySet();
    Iterator<Map.Entry<String, Integer>> _iterator_2 = _entrySet_2.iterator();
    boolean _should_iterate_2 = Should.should_iterate(_iterator_2, obj3, false);
    Assert.assertTrue(_should_iterate_2);
    Set<Map.Entry<String, Integer>> _entrySet_3 = obj1.entrySet();
    Iterator<Map.Entry<String, Integer>> _iterator_3 = _entrySet_3.iterator();
    boolean _should_iterate_3 = Should.should_iterate(_iterator_3, obj4, false);
    Assert.assertFalse(_should_iterate_3);
    Set<Map.Entry<String, Integer>> _entrySet_4 = obj1.entrySet();
    Iterator<Map.Entry<String, Integer>> _iterator_4 = _entrySet_4.iterator();
    boolean _should_iterate_4 = Should.should_iterate(_iterator_4, obj5, false);
    Assert.assertFalse(_should_iterate_4);
  }
  
  @Test
  public void should_iterate_IteratorObjectboolean_unsignificantOrder_4() {
    final List<String> obj1 = Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("a", "b", "c"));
    Iterator<String> _iterator = obj1.iterator();
    boolean _should_iterate = Should.should_iterate(_iterator, "a", false);
    Assert.assertFalse(_should_iterate);
  }
  
  @Test
  public void should_iterate_IteratorObjectboolean_unsignificantOrder_5() {
    final String[] obj1 = { "a", "b", "c" };
    Iterator<String> _iterator = ((List<String>)Conversions.doWrapArray(obj1)).iterator();
    boolean _should_iterate = Should.should_iterate(_iterator, "a", false);
    Assert.assertFalse(_should_iterate);
  }
  
  @Test
  public void should_iterate_IteratorObjectboolean_unsignificantOrder_6() {
    Pair<String, Integer> _mappedTo = Pair.<String, Integer>of("a", Integer.valueOf(1));
    Pair<String, Integer> _mappedTo_1 = Pair.<String, Integer>of("b", Integer.valueOf(2));
    Pair<String, Integer> _mappedTo_2 = Pair.<String, Integer>of("c", Integer.valueOf(3));
    final Map<String, Integer> obj1 = Collections.<String, Integer>unmodifiableMap(CollectionLiterals.<String, Integer>newHashMap(_mappedTo, _mappedTo_1, _mappedTo_2));
    Set<Map.Entry<String, Integer>> _entrySet = obj1.entrySet();
    Iterator<Map.Entry<String, Integer>> _iterator = _entrySet.iterator();
    Pair<String, Integer> _mappedTo_3 = Pair.<String, Integer>of("a", Integer.valueOf(1));
    boolean _should_iterate = Should.should_iterate(_iterator, _mappedTo_3, false);
    Assert.assertFalse(_should_iterate);
  }
  
  @Test
  public void should_beNumber_0() {
    boolean _should_beNumber = Should.should_beNumber("abc", "00");
    Assert.assertFalse(_should_beNumber);
    boolean _should_beNumber_1 = Should.should_beNumber("1", "00");
    Assert.assertTrue(_should_beNumber_1);
    boolean _should_beNumber_2 = Should.should_beNumber("10", "00");
    Assert.assertTrue(_should_beNumber_2);
  }
  
  @Test
  public void should_beNumber_1() {
    boolean _should_beNumber = Should.should_beNumber(((String) null), ((String) null));
    Assert.assertFalse(_should_beNumber);
    boolean _should_beNumber_1 = Should.should_beNumber("1", ((String) null));
    Assert.assertTrue(_should_beNumber_1);
    boolean _should_beNumber_2 = Should.should_beNumber(((String) null), "00");
    Assert.assertFalse(_should_beNumber_2);
  }
  
  @Test
  public void should_haveMethod_0() {
    boolean _should_haveMethod = Should.should_haveMethod(Object.class, "equals()");
    Assert.assertFalse(_should_haveMethod);
    boolean _should_haveMethod_1 = Should.should_haveMethod(Object.class, "equals(Object)");
    Assert.assertFalse(_should_haveMethod_1);
    boolean _should_haveMethod_2 = Should.should_haveMethod(Object.class, "equals(java.lang.Object)");
    Assert.assertFalse(_should_haveMethod_2);
    boolean _should_haveMethod_3 = Should.should_haveMethod(Object.class, "equals() : int");
    Assert.assertFalse(_should_haveMethod_3);
    boolean _should_haveMethod_4 = Should.should_haveMethod(Object.class, "equals(Object) : int");
    Assert.assertFalse(_should_haveMethod_4);
    boolean _should_haveMethod_5 = Should.should_haveMethod(Object.class, "equals(java.lang.Object) : int");
    Assert.assertFalse(_should_haveMethod_5);
    boolean _should_haveMethod_6 = Should.should_haveMethod(Object.class, "equals() : boolean");
    Assert.assertFalse(_should_haveMethod_6);
    boolean _should_haveMethod_7 = Should.should_haveMethod(Object.class, "equals(Object) : boolean");
    Assert.assertFalse(_should_haveMethod_7);
    boolean _should_haveMethod_8 = Should.should_haveMethod(Object.class, "equals(java.lang.Object) : boolean");
    Assert.assertTrue(_should_haveMethod_8);
  }
  
  @Test
  public void should_haveMethod_1() {
    boolean _should_haveMethod = Should.should_haveMethod(((Class<?>) null), ((String) null));
    Assert.assertFalse(_should_haveMethod);
    boolean _should_haveMethod_1 = Should.should_haveMethod(Object.class, ((String) null));
    Assert.assertFalse(_should_haveMethod_1);
    boolean _should_haveMethod_2 = Should.should_haveMethod(((Class<?>) null), "equals(java.lang.Object) : boolean");
    Assert.assertFalse(_should_haveMethod_2);
  }
  
  @Test
  public void should_extend_0() {
    boolean _should_extend = Should.should_extend(Integer.class, "java.lang.Number");
    Assert.assertTrue(_should_extend);
    boolean _should_extend_1 = Should.should_extend(Integer.class, "java.lang.Number,java.lang.Comparable");
    Assert.assertFalse(_should_extend_1);
    boolean _should_extend_2 = Should.should_extend(Integer.class, "java.util.Serializable");
    Assert.assertFalse(_should_extend_2);
    boolean _should_extend_3 = Should.should_extend(Integer.class, "java.util.Serializable,java.lang.Comparable");
    Assert.assertFalse(_should_extend_3);
  }
  
  @Test
  public void should_implement_0() {
    boolean _should_implement = Should.should_implement(Integer.class, "java.lang.Number");
    Assert.assertFalse(_should_implement);
    boolean _should_implement_1 = Should.should_implement(Integer.class, "java.lang.Number,java.lang.Comparable");
    Assert.assertFalse(_should_implement_1);
    boolean _should_implement_2 = Should.should_implement(Integer.class, "java.lang.Comparable");
    Assert.assertTrue(_should_implement_2);
    boolean _should_implement_3 = Should.should_implement(Integer.class, "java.io.Serializable,java.lang.Comparable");
    Assert.assertFalse(_should_implement_3);
  }
  
  @Test
  public void should_haveNbMembers_0() {
    boolean _should_haveNbMembers = Should.should_haveNbMembers(TestRule.class, 3);
    Assert.assertFalse(_should_haveNbMembers);
    boolean _should_haveNbMembers_1 = Should.should_haveNbMembers(TestRule.class, 4);
    Assert.assertTrue(_should_haveNbMembers_1);
  }
  
  @Test
  public void should_haveNbMembers_1() {
    boolean _should_haveNbMembers = Should.should_haveNbMembers(((Class<?>) null), 3);
    Assert.assertFalse(_should_haveNbMembers);
    boolean _should_haveNbMembers_1 = Should.should_haveNbMembers(((Class<?>) null), 4);
    Assert.assertFalse(_should_haveNbMembers_1);
  }
  
  @Test
  public void should_beDate_0() {
    final String obj1 = "2014-09-12";
    final String obj2 = "14-09-12";
    final String obj3 = "20140912";
    final String obj4 = "abc";
    boolean _should_beDate = Should.should_beDate(obj1, "YYYY-mm-dd");
    Assert.assertTrue(_should_beDate);
    boolean _should_beDate_1 = Should.should_beDate(obj2, "YYYY-mm-dd");
    Assert.assertTrue(_should_beDate_1);
    boolean _should_beDate_2 = Should.should_beDate(obj3, "YYYY-mm-dd");
    Assert.assertFalse(_should_beDate_2);
    boolean _should_beDate_3 = Should.should_beDate(obj4, "YYYY-mm-dd");
    Assert.assertFalse(_should_beDate_3);
    boolean _should_beDate_4 = Should.should_beDate(obj1, "YY-mm-dd");
    Assert.assertTrue(_should_beDate_4);
    boolean _should_beDate_5 = Should.should_beDate(obj2, "YY-mm-dd");
    Assert.assertTrue(_should_beDate_5);
    boolean _should_beDate_6 = Should.should_beDate(obj3, "YY-mm-dd");
    Assert.assertFalse(_should_beDate_6);
    boolean _should_beDate_7 = Should.should_beDate(obj4, "YY-mm-dd");
    Assert.assertFalse(_should_beDate_7);
  }
  
  @Test
  public void should_beDate_1() {
    final String obj1 = "2014-09-12";
    boolean _should_beDate = Should.should_beDate(((String) null), ((String) null));
    Assert.assertFalse(_should_beDate);
    boolean _should_beDate_1 = Should.should_beDate(obj1, ((String) null));
    Assert.assertFalse(_should_beDate_1);
    boolean _should_beDate_2 = Should.should_beDate(((String) null), "YYYY-mm-dd");
    Assert.assertFalse(_should_beDate_2);
  }
  
  @Test
  public void should_beValidVersionNumber_noMavenSnapshot_0() {
    final String obj1 = "abc";
    final String obj2 = "14.09.12";
    final String obj3 = "2014-09-12";
    final String obj4 = "2014.09-12";
    final String obj5 = "2014.09+12";
    final String obj6 = "2014.09:12";
    final String obj7 = "1..0";
    final String obj8 = "1.0._3";
    final String obj9 = "1.0_.3";
    final String obj10 = "1.0.+3";
    final String obj11 = "1.0+.3";
    final String obj12 = "1.0.:3";
    final String obj13 = "1.0:.3";
    final String obj14 = "1.0-";
    final String obj15 = "-1.0";
    final String obj16 = "1.a";
    boolean _should_beValidVersionNumber = Should.should_beValidVersionNumber(obj1, false);
    Assert.assertFalse(_should_beValidVersionNumber);
    boolean _should_beValidVersionNumber_1 = Should.should_beValidVersionNumber(obj2, false);
    Assert.assertTrue(_should_beValidVersionNumber_1);
    boolean _should_beValidVersionNumber_2 = Should.should_beValidVersionNumber(obj3, false);
    Assert.assertTrue(_should_beValidVersionNumber_2);
    boolean _should_beValidVersionNumber_3 = Should.should_beValidVersionNumber(obj4, false);
    Assert.assertTrue(_should_beValidVersionNumber_3);
    boolean _should_beValidVersionNumber_4 = Should.should_beValidVersionNumber(obj5, false);
    Assert.assertTrue(_should_beValidVersionNumber_4);
    boolean _should_beValidVersionNumber_5 = Should.should_beValidVersionNumber(obj6, false);
    Assert.assertTrue(_should_beValidVersionNumber_5);
    boolean _should_beValidVersionNumber_6 = Should.should_beValidVersionNumber(obj7, false);
    Assert.assertFalse(_should_beValidVersionNumber_6);
    boolean _should_beValidVersionNumber_7 = Should.should_beValidVersionNumber(obj8, false);
    Assert.assertFalse(_should_beValidVersionNumber_7);
    boolean _should_beValidVersionNumber_8 = Should.should_beValidVersionNumber(obj9, false);
    Assert.assertFalse(_should_beValidVersionNumber_8);
    boolean _should_beValidVersionNumber_9 = Should.should_beValidVersionNumber(obj10, false);
    Assert.assertFalse(_should_beValidVersionNumber_9);
    boolean _should_beValidVersionNumber_10 = Should.should_beValidVersionNumber(obj11, false);
    Assert.assertFalse(_should_beValidVersionNumber_10);
    boolean _should_beValidVersionNumber_11 = Should.should_beValidVersionNumber(obj12, false);
    Assert.assertFalse(_should_beValidVersionNumber_11);
    boolean _should_beValidVersionNumber_12 = Should.should_beValidVersionNumber(obj13, false);
    Assert.assertFalse(_should_beValidVersionNumber_12);
    boolean _should_beValidVersionNumber_13 = Should.should_beValidVersionNumber(obj14, false);
    Assert.assertFalse(_should_beValidVersionNumber_13);
    boolean _should_beValidVersionNumber_14 = Should.should_beValidVersionNumber(obj15, false);
    Assert.assertFalse(_should_beValidVersionNumber_14);
    boolean _should_beValidVersionNumber_15 = Should.should_beValidVersionNumber(obj16, false);
    Assert.assertTrue(_should_beValidVersionNumber_15);
  }
  
  @Test
  public void should_beValidVersionNumber_noMavenSnapshot_1() {
    final String obj1 = "abc-SNAPSHOT";
    final String obj2 = "14.09.12-SNAPSHOT";
    final String obj3 = "2014-09-12-SNAPSHOT";
    final String obj4 = "2014.09-12-SNAPSHOT";
    final String obj5 = "2014.09+12-SNAPSHOT";
    final String obj6 = "2014.09:12-SNAPSHOT";
    final String obj7 = "1..0-SNAPSHOT";
    final String obj8 = "1.0._3-SNAPSHOT";
    final String obj9 = "1.0_.3-SNAPSHOT";
    final String obj10 = "1.0.+3-SNAPSHOT";
    final String obj11 = "1.0+.3-SNAPSHOT";
    final String obj12 = "1.0.:3-SNAPSHOT";
    final String obj13 = "1.0:.3-SNAPSHOT";
    final String obj14 = "1.0--SNAPSHOT";
    final String obj15 = "-1.0-SNAPSHOT";
    final String obj16 = "1.a-SNAPSHOT";
    boolean _should_beValidVersionNumber = Should.should_beValidVersionNumber(obj1, false);
    Assert.assertFalse(_should_beValidVersionNumber);
    boolean _should_beValidVersionNumber_1 = Should.should_beValidVersionNumber(obj2, false);
    Assert.assertFalse(_should_beValidVersionNumber_1);
    boolean _should_beValidVersionNumber_2 = Should.should_beValidVersionNumber(obj3, false);
    Assert.assertFalse(_should_beValidVersionNumber_2);
    boolean _should_beValidVersionNumber_3 = Should.should_beValidVersionNumber(obj4, false);
    Assert.assertFalse(_should_beValidVersionNumber_3);
    boolean _should_beValidVersionNumber_4 = Should.should_beValidVersionNumber(obj5, false);
    Assert.assertFalse(_should_beValidVersionNumber_4);
    boolean _should_beValidVersionNumber_5 = Should.should_beValidVersionNumber(obj6, false);
    Assert.assertFalse(_should_beValidVersionNumber_5);
    boolean _should_beValidVersionNumber_6 = Should.should_beValidVersionNumber(obj7, false);
    Assert.assertFalse(_should_beValidVersionNumber_6);
    boolean _should_beValidVersionNumber_7 = Should.should_beValidVersionNumber(obj8, false);
    Assert.assertFalse(_should_beValidVersionNumber_7);
    boolean _should_beValidVersionNumber_8 = Should.should_beValidVersionNumber(obj9, false);
    Assert.assertFalse(_should_beValidVersionNumber_8);
    boolean _should_beValidVersionNumber_9 = Should.should_beValidVersionNumber(obj10, false);
    Assert.assertFalse(_should_beValidVersionNumber_9);
    boolean _should_beValidVersionNumber_10 = Should.should_beValidVersionNumber(obj11, false);
    Assert.assertFalse(_should_beValidVersionNumber_10);
    boolean _should_beValidVersionNumber_11 = Should.should_beValidVersionNumber(obj12, false);
    Assert.assertFalse(_should_beValidVersionNumber_11);
    boolean _should_beValidVersionNumber_12 = Should.should_beValidVersionNumber(obj13, false);
    Assert.assertFalse(_should_beValidVersionNumber_12);
    boolean _should_beValidVersionNumber_13 = Should.should_beValidVersionNumber(obj14, false);
    Assert.assertFalse(_should_beValidVersionNumber_13);
    boolean _should_beValidVersionNumber_14 = Should.should_beValidVersionNumber(obj15, false);
    Assert.assertFalse(_should_beValidVersionNumber_14);
    boolean _should_beValidVersionNumber_15 = Should.should_beValidVersionNumber(obj16, false);
    Assert.assertFalse(_should_beValidVersionNumber_15);
  }
  
  @Test
  public void should_beValidVersionNumber_noMavenSnapshot_2() {
    boolean _should_beValidVersionNumber = Should.should_beValidVersionNumber(((String) null), false);
    Assert.assertFalse(_should_beValidVersionNumber);
  }
  
  @Test
  public void should_beValidVersionNumber_mavenSnapshot_0() {
    final String obj1 = "abc";
    final String obj2 = "14.09.12";
    final String obj3 = "2014-09-12";
    final String obj4 = "2014.09-12";
    final String obj5 = "2014.09+12";
    final String obj6 = "2014.09:12";
    final String obj7 = "1..0";
    final String obj8 = "1.0._3";
    final String obj9 = "1.0_.3";
    final String obj10 = "1.0.+3";
    final String obj11 = "1.0+.3";
    final String obj12 = "1.0.:3";
    final String obj13 = "1.0:.3";
    final String obj14 = "1.0-";
    final String obj15 = "-1.0";
    final String obj16 = "1.a";
    boolean _should_beValidVersionNumber = Should.should_beValidVersionNumber(obj1, true);
    Assert.assertFalse(_should_beValidVersionNumber);
    boolean _should_beValidVersionNumber_1 = Should.should_beValidVersionNumber(obj2, true);
    Assert.assertTrue(_should_beValidVersionNumber_1);
    boolean _should_beValidVersionNumber_2 = Should.should_beValidVersionNumber(obj3, true);
    Assert.assertTrue(_should_beValidVersionNumber_2);
    boolean _should_beValidVersionNumber_3 = Should.should_beValidVersionNumber(obj4, true);
    Assert.assertTrue(_should_beValidVersionNumber_3);
    boolean _should_beValidVersionNumber_4 = Should.should_beValidVersionNumber(obj5, true);
    Assert.assertTrue(_should_beValidVersionNumber_4);
    boolean _should_beValidVersionNumber_5 = Should.should_beValidVersionNumber(obj6, true);
    Assert.assertTrue(_should_beValidVersionNumber_5);
    boolean _should_beValidVersionNumber_6 = Should.should_beValidVersionNumber(obj7, true);
    Assert.assertFalse(_should_beValidVersionNumber_6);
    boolean _should_beValidVersionNumber_7 = Should.should_beValidVersionNumber(obj8, true);
    Assert.assertFalse(_should_beValidVersionNumber_7);
    boolean _should_beValidVersionNumber_8 = Should.should_beValidVersionNumber(obj9, true);
    Assert.assertFalse(_should_beValidVersionNumber_8);
    boolean _should_beValidVersionNumber_9 = Should.should_beValidVersionNumber(obj10, true);
    Assert.assertFalse(_should_beValidVersionNumber_9);
    boolean _should_beValidVersionNumber_10 = Should.should_beValidVersionNumber(obj11, true);
    Assert.assertFalse(_should_beValidVersionNumber_10);
    boolean _should_beValidVersionNumber_11 = Should.should_beValidVersionNumber(obj12, true);
    Assert.assertFalse(_should_beValidVersionNumber_11);
    boolean _should_beValidVersionNumber_12 = Should.should_beValidVersionNumber(obj13, true);
    Assert.assertFalse(_should_beValidVersionNumber_12);
    boolean _should_beValidVersionNumber_13 = Should.should_beValidVersionNumber(obj14, true);
    Assert.assertFalse(_should_beValidVersionNumber_13);
    boolean _should_beValidVersionNumber_14 = Should.should_beValidVersionNumber(obj15, true);
    Assert.assertFalse(_should_beValidVersionNumber_14);
    boolean _should_beValidVersionNumber_15 = Should.should_beValidVersionNumber(obj16, true);
    Assert.assertTrue(_should_beValidVersionNumber_15);
  }
  
  @Test
  public void should_beValidVersionNumber_mavenSnapshot_1() {
    final String obj1 = "abc-SNAPSHOT";
    final String obj2 = "14.09.12-SNAPSHOT";
    final String obj3 = "2014-09-12-SNAPSHOT";
    final String obj4 = "2014.09-12-SNAPSHOT";
    final String obj5 = "2014.09+12-SNAPSHOT";
    final String obj6 = "2014.09:12-SNAPSHOT";
    final String obj7 = "1..0-SNAPSHOT";
    final String obj8 = "1.0._3-SNAPSHOT";
    final String obj9 = "1.0_.3-SNAPSHOT";
    final String obj10 = "1.0.+3-SNAPSHOT";
    final String obj11 = "1.0+.3-SNAPSHOT";
    final String obj12 = "1.0.:3-SNAPSHOT";
    final String obj13 = "1.0:.3-SNAPSHOT";
    final String obj14 = "1.0--SNAPSHOT";
    final String obj15 = "-1.0-SNAPSHOT";
    final String obj16 = "1.a-SNAPSHOT";
    boolean _should_beValidVersionNumber = Should.should_beValidVersionNumber(obj1, true);
    Assert.assertFalse(_should_beValidVersionNumber);
    boolean _should_beValidVersionNumber_1 = Should.should_beValidVersionNumber(obj2, true);
    Assert.assertTrue(_should_beValidVersionNumber_1);
    boolean _should_beValidVersionNumber_2 = Should.should_beValidVersionNumber(obj3, true);
    Assert.assertTrue(_should_beValidVersionNumber_2);
    boolean _should_beValidVersionNumber_3 = Should.should_beValidVersionNumber(obj4, true);
    Assert.assertTrue(_should_beValidVersionNumber_3);
    boolean _should_beValidVersionNumber_4 = Should.should_beValidVersionNumber(obj5, true);
    Assert.assertTrue(_should_beValidVersionNumber_4);
    boolean _should_beValidVersionNumber_5 = Should.should_beValidVersionNumber(obj6, true);
    Assert.assertTrue(_should_beValidVersionNumber_5);
    boolean _should_beValidVersionNumber_6 = Should.should_beValidVersionNumber(obj7, true);
    Assert.assertFalse(_should_beValidVersionNumber_6);
    boolean _should_beValidVersionNumber_7 = Should.should_beValidVersionNumber(obj8, true);
    Assert.assertFalse(_should_beValidVersionNumber_7);
    boolean _should_beValidVersionNumber_8 = Should.should_beValidVersionNumber(obj9, true);
    Assert.assertFalse(_should_beValidVersionNumber_8);
    boolean _should_beValidVersionNumber_9 = Should.should_beValidVersionNumber(obj10, true);
    Assert.assertFalse(_should_beValidVersionNumber_9);
    boolean _should_beValidVersionNumber_10 = Should.should_beValidVersionNumber(obj11, true);
    Assert.assertFalse(_should_beValidVersionNumber_10);
    boolean _should_beValidVersionNumber_11 = Should.should_beValidVersionNumber(obj12, true);
    Assert.assertFalse(_should_beValidVersionNumber_11);
    boolean _should_beValidVersionNumber_12 = Should.should_beValidVersionNumber(obj13, true);
    Assert.assertFalse(_should_beValidVersionNumber_12);
    boolean _should_beValidVersionNumber_13 = Should.should_beValidVersionNumber(obj14, true);
    Assert.assertFalse(_should_beValidVersionNumber_13);
    boolean _should_beValidVersionNumber_14 = Should.should_beValidVersionNumber(obj15, true);
    Assert.assertFalse(_should_beValidVersionNumber_14);
    boolean _should_beValidVersionNumber_15 = Should.should_beValidVersionNumber(obj16, true);
    Assert.assertTrue(_should_beValidVersionNumber_15);
  }
  
  @Test
  public void should_beValidVersionNumber_mavenSnapshot_2() {
    boolean _should_beValidVersionNumber = Should.should_beValidVersionNumber(((String) null), true);
    Assert.assertFalse(_should_beValidVersionNumber);
  }
  
  @Test
  public void should_beURL_0() {
    final String url1 = "http://www.jnario.org";
    final String url2 = "ftp://www.jnario.org";
    boolean _should_beURL = Should.should_beURL(url1, "http");
    Assert.assertTrue(_should_beURL);
    boolean _should_beURL_1 = Should.should_beURL(url2, "http");
    Assert.assertFalse(_should_beURL_1);
    boolean _should_beURL_2 = Should.should_beURL(url1, "ftp");
    Assert.assertFalse(_should_beURL_2);
    boolean _should_beURL_3 = Should.should_beURL(url2, "ftp");
    Assert.assertTrue(_should_beURL_3);
    boolean _should_beURL_4 = Should.should_beURL(url1, "ssh");
    Assert.assertFalse(_should_beURL_4);
    boolean _should_beURL_5 = Should.should_beURL(url2, "ssh");
    Assert.assertFalse(_should_beURL_5);
  }
  
  @Test
  public void should_beURL_1() {
    final String url1 = "http://www.jnario.org";
    final String url2 = "ftp://www.jnario.org";
    boolean _should_beURL = Should.should_beURL(url1, "!http");
    Assert.assertFalse(_should_beURL);
    boolean _should_beURL_1 = Should.should_beURL(url2, "!http");
    Assert.assertTrue(_should_beURL_1);
    boolean _should_beURL_2 = Should.should_beURL(url1, "!ftp");
    Assert.assertTrue(_should_beURL_2);
    boolean _should_beURL_3 = Should.should_beURL(url2, "!ftp");
    Assert.assertFalse(_should_beURL_3);
    boolean _should_beURL_4 = Should.should_beURL(url1, "!ssh");
    Assert.assertTrue(_should_beURL_4);
    boolean _should_beURL_5 = Should.should_beURL(url2, "!ssh");
    Assert.assertTrue(_should_beURL_5);
  }
  
  @Test
  public void should_beURL_2() {
    final String url1 = "http://www.jnario.org";
    final String url2 = "ftp://www.jnario.org";
    boolean _should_beURL = Should.should_beURL(url1, "http,ftp");
    Assert.assertTrue(_should_beURL);
    boolean _should_beURL_1 = Should.should_beURL(url2, "http,ftp");
    Assert.assertTrue(_should_beURL_1);
    boolean _should_beURL_2 = Should.should_beURL(url1, "http,ssh");
    Assert.assertTrue(_should_beURL_2);
    boolean _should_beURL_3 = Should.should_beURL(url2, "http,ssh");
    Assert.assertFalse(_should_beURL_3);
    boolean _should_beURL_4 = Should.should_beURL(url1, "ftp,ssh");
    Assert.assertFalse(_should_beURL_4);
    boolean _should_beURL_5 = Should.should_beURL(url2, "ftp,ssh");
    Assert.assertTrue(_should_beURL_5);
  }
  
  @Test
  public void should_beURL_3() {
    final String url1 = "http://www.jnario.org";
    final String url2 = "ftp://www.jnario.org";
    boolean _should_beURL = Should.should_beURL(url1, "!http,!ftp");
    Assert.assertFalse(_should_beURL);
    boolean _should_beURL_1 = Should.should_beURL(url2, "!http,!ftp");
    Assert.assertFalse(_should_beURL_1);
    boolean _should_beURL_2 = Should.should_beURL(url1, "!http,!ssh");
    Assert.assertFalse(_should_beURL_2);
    boolean _should_beURL_3 = Should.should_beURL(url2, "!http,!ssh");
    Assert.assertTrue(_should_beURL_3);
    boolean _should_beURL_4 = Should.should_beURL(url1, "!ftp,!ssh");
    Assert.assertTrue(_should_beURL_4);
    boolean _should_beURL_5 = Should.should_beURL(url2, "!ftp,!ssh");
    Assert.assertFalse(_should_beURL_5);
  }
  
  @Test
  public void should_beURL_4() {
    final String url1 = "http://www.jnario.org";
    final String url2 = "ftp://www.jnario.org";
    boolean _should_beURL = Should.should_beURL(url1, "http,!ftp");
    Assert.assertTrue(_should_beURL);
    boolean _should_beURL_1 = Should.should_beURL(url2, "http,!ftp");
    Assert.assertFalse(_should_beURL_1);
    boolean _should_beURL_2 = Should.should_beURL(url1, "!http,ftp");
    Assert.assertFalse(_should_beURL_2);
    boolean _should_beURL_3 = Should.should_beURL(url2, "!http,ftp");
    Assert.assertTrue(_should_beURL_3);
    boolean _should_beURL_4 = Should.should_beURL(url1, "http,!ssh");
    Assert.assertTrue(_should_beURL_4);
    boolean _should_beURL_5 = Should.should_beURL(url2, "http,!ssh");
    Assert.assertFalse(_should_beURL_5);
    boolean _should_beURL_6 = Should.should_beURL(url1, "!http,ssh");
    Assert.assertFalse(_should_beURL_6);
    boolean _should_beURL_7 = Should.should_beURL(url2, "!http,ssh");
    Assert.assertFalse(_should_beURL_7);
    boolean _should_beURL_8 = Should.should_beURL(url1, "ftp,!ssh");
    Assert.assertFalse(_should_beURL_8);
    boolean _should_beURL_9 = Should.should_beURL(url2, "ftp,!ssh");
    Assert.assertTrue(_should_beURL_9);
    boolean _should_beURL_10 = Should.should_beURL(url1, "!ftp,ssh");
    Assert.assertFalse(_should_beURL_10);
    boolean _should_beURL_11 = Should.should_beURL(url2, "!ftp,ssh");
    Assert.assertFalse(_should_beURL_11);
  }
  
  @Test
  public void should_beURL_5() {
    final String url1 = "http://www.jnario.org";
    final String url2 = "ftp://www.jnario.org";
    boolean _should_beURL = Should.should_beURL(url1, "http,ftp,ssh");
    Assert.assertTrue(_should_beURL);
    boolean _should_beURL_1 = Should.should_beURL(url1, "http,ftp,!ssh");
    Assert.assertTrue(_should_beURL_1);
    boolean _should_beURL_2 = Should.should_beURL(url1, "http,!ftp,ssh");
    Assert.assertTrue(_should_beURL_2);
    boolean _should_beURL_3 = Should.should_beURL(url1, "http,!ftp,!ssh");
    Assert.assertTrue(_should_beURL_3);
    boolean _should_beURL_4 = Should.should_beURL(url1, "!http,ftp,ssh");
    Assert.assertFalse(_should_beURL_4);
    boolean _should_beURL_5 = Should.should_beURL(url1, "!http,ftp,!ssh");
    Assert.assertFalse(_should_beURL_5);
    boolean _should_beURL_6 = Should.should_beURL(url1, "!http,!ftp,ssh");
    Assert.assertFalse(_should_beURL_6);
    boolean _should_beURL_7 = Should.should_beURL(url1, "!http,!ftp,!ssh");
    Assert.assertFalse(_should_beURL_7);
    boolean _should_beURL_8 = Should.should_beURL(url2, "http,ftp,ssh");
    Assert.assertTrue(_should_beURL_8);
    boolean _should_beURL_9 = Should.should_beURL(url2, "http,ftp,!ssh");
    Assert.assertTrue(_should_beURL_9);
    boolean _should_beURL_10 = Should.should_beURL(url2, "http,!ftp,ssh");
    Assert.assertFalse(_should_beURL_10);
    boolean _should_beURL_11 = Should.should_beURL(url2, "http,!ftp,!ssh");
    Assert.assertFalse(_should_beURL_11);
    boolean _should_beURL_12 = Should.should_beURL(url2, "!http,ftp,ssh");
    Assert.assertTrue(_should_beURL_12);
    boolean _should_beURL_13 = Should.should_beURL(url2, "!http,ftp,!ssh");
    Assert.assertTrue(_should_beURL_13);
    boolean _should_beURL_14 = Should.should_beURL(url2, "!http,!ftp,ssh");
    Assert.assertFalse(_should_beURL_14);
    boolean _should_beURL_15 = Should.should_beURL(url2, "!http,!ftp,!ssh");
    Assert.assertFalse(_should_beURL_15);
  }
  
  @Test
  public void should_beURL_6() {
    final String url1 = "http://www.jnario.org";
    final String url2 = "ftp://www.jnario.org";
    final String url3 = "//www.jnario.org";
    final String url4 = "\\\\mylaptop\\hello.txt";
    final String url5 = "file:C:\\\\mylaptop\\hello.txt";
    boolean _should_beURL = Should.should_beURL(url1, ((String) null));
    Assert.assertTrue(_should_beURL);
    boolean _should_beURL_1 = Should.should_beURL(url2, ((String) null));
    Assert.assertTrue(_should_beURL_1);
    boolean _should_beURL_2 = Should.should_beURL(url3, ((String) null));
    Assert.assertFalse(_should_beURL_2);
    boolean _should_beURL_3 = Should.should_beURL(url4, ((String) null));
    Assert.assertFalse(_should_beURL_3);
    boolean _should_beURL_4 = Should.should_beURL(url5, ((String) null));
    Assert.assertTrue(_should_beURL_4);
  }
  
  @Test
  public void should_beURL_7() {
    final String url1 = "http://www.jnario.org";
    boolean _should_beURL = Should.should_beURL(((String) null), ((String) null));
    Assert.assertFalse(_should_beURL);
    boolean _should_beURL_1 = Should.should_beURL(url1, ((String) null));
    Assert.assertTrue(_should_beURL_1);
    boolean _should_beURL_2 = Should.should_beURL(null, "http");
    Assert.assertFalse(_should_beURL_2);
  }
  
  @Test
  public void should_beAccessibleFrom_0() {
    final String url1 = "./TestSpec.html";
    final String url2 = "./TestSpec2.html";
    boolean _should_beAccessibleFrom = Should.should_beAccessibleFrom(url1, this);
    Assert.assertTrue(_should_beAccessibleFrom);
    boolean _should_beAccessibleFrom_1 = Should.should_beAccessibleFrom(url2, this);
    Assert.assertFalse(_should_beAccessibleFrom_1);
  }
  
  @Test
  public void should_beAccessibleFrom_1() {
    final String url1 = "./TestSpec.html";
    final String url2 = "./TestSpec2.html";
    Object _object = new Object();
    boolean _should_beAccessibleFrom = Should.should_beAccessibleFrom(url1, _object);
    Assert.assertFalse(_should_beAccessibleFrom);
    Object _object_1 = new Object();
    boolean _should_beAccessibleFrom_1 = Should.should_beAccessibleFrom(url2, _object_1);
    Assert.assertFalse(_should_beAccessibleFrom_1);
  }
  
  @Test
  public void should_beAccessibleFrom_2() {
    final String url1 = "./TestSpec.html";
    boolean _should_beAccessibleFrom = Should.should_beAccessibleFrom(((String) null), ((Object) null));
    Assert.assertFalse(_should_beAccessibleFrom);
    boolean _should_beAccessibleFrom_1 = Should.should_beAccessibleFrom(url1, ((Object) null));
    Assert.assertFalse(_should_beAccessibleFrom_1);
    boolean _should_beAccessibleFrom_2 = Should.should_beAccessibleFrom(((String) null), this);
    Assert.assertFalse(_should_beAccessibleFrom_2);
  }
  
  @Test
  public void should_beAccessibleFrom_3() {
    final String url1 = "./TestSpec.html#my_Func";
    final String url2 = "./TestSpec.html#my_Func2";
    boolean _should_beAccessibleFrom = Should.should_beAccessibleFrom(url1, this);
    Assert.assertTrue(_should_beAccessibleFrom);
    boolean _should_beAccessibleFrom_1 = Should.should_beAccessibleFrom(url2, this);
    Assert.assertFalse(_should_beAccessibleFrom_1);
  }
  
  @Test
  public void should_beAccessibleFrom_4() {
    final String url1 = "./TestSpec.html#my_Func";
    final String url2 = "./TestSpec.html#my_Func2";
    Object _object = new Object();
    boolean _should_beAccessibleFrom = Should.should_beAccessibleFrom(url1, _object);
    Assert.assertFalse(_should_beAccessibleFrom);
    Object _object_1 = new Object();
    boolean _should_beAccessibleFrom_1 = Should.should_beAccessibleFrom(url2, _object_1);
    Assert.assertFalse(_should_beAccessibleFrom_1);
  }
  
  @Test
  public void should_beAccessibleFrom_5() {
    final String url1 = "./TestSpec.html#my_Func";
    boolean _should_beAccessibleFrom = Should.should_beAccessibleFrom(((String) null), ((Object) null));
    Assert.assertFalse(_should_beAccessibleFrom);
    boolean _should_beAccessibleFrom_1 = Should.should_beAccessibleFrom(url1, ((Object) null));
    Assert.assertFalse(_should_beAccessibleFrom_1);
    boolean _should_beAccessibleFrom_2 = Should.should_beAccessibleFrom(((String) null), this);
    Assert.assertFalse(_should_beAccessibleFrom_2);
  }
  
  @Test
  public void should_beAccessibleFrom_6() {
    final String url1 = "./TestSpec.class";
    final String url2 = "./TestSpec2.class";
    boolean _should_beAccessibleFrom = Should.should_beAccessibleFrom(url1, this);
    Assert.assertTrue(_should_beAccessibleFrom);
    boolean _should_beAccessibleFrom_1 = Should.should_beAccessibleFrom(url2, this);
    Assert.assertFalse(_should_beAccessibleFrom_1);
  }
}
