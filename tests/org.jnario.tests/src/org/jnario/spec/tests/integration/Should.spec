/*******************************************************************************
 * Copyright (c) 2014 BMW Car IT and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.jnario.spec.tests.integration

import static org.hamcrest.Matchers.*
import static org.jnario.lib.JnarioCollectionLiterals.*

import static extension org.jnario.jnario.test.util.Helpers.*
import static extension org.jnario.lib.Should.*
import java.util.Stack
import java.util.EmptyStackException

/*
 * You can use the `should` statement to express the expected behavior of objects.
 */
describe "Using Should"{
 
  /*
   * `should` passes if the result of the left expression is 
   * equal to the result of the right expression. You can use `not` to  
   * assert that the expressions have different results. There is also 
   * a short cut available: `=>` which has the same effect as `should be`.
   */
  fact "To pass.."{
    // equality
    true should be true
    1 + 1 should not be 1 
    "something" should not be null 
    1 + 1 => 2
     
    // types
    "a string" => typeof(String)
    "a string".getClass => typeof(String)

    // strings
    // -- does a string contains another string?
    "something" should contain "thing"
    "something" should not contain "any"
    // -- does a string may be parsed as a number with the given format?
    "123" should beNumber "000"
    "something" should not beNumber "000"
    // -- does a string may be parsed as a date with the given format?
    "2014-09-12" should beDate "Y-m-d"
    "something" should not beDate "Y-m-d"
    // -- does a string is an URL for all procotols?
	"http://www.jnario.org" should beURL _
	// -- does a string is an URL only for FTP protocol?
	"http://www.jnario.org" should not beURL "ftp"
	// -- does a string is an URL for every protocol except the the HTTP protocol? 
	"http://www.jnario.org" should not beURL "!http"
	// -- does a string is an URL for HTTP or FTP protocols? 
	"http://www.jnario.org" should beURL "http,ftp"
	// -- does a string is an URL for every protocol except HTTP and FTP protocols?
	"http://www.jnario.org" should not beURL "!ftp,!http"
	// -- does a string may be parsed as a valid version number?
	// -- the boolean value indicates if the "-SNAPSHOT" postfix string is valid or not.
	"1.0.0" should beValidVersionNumber true
	"abc" should not beValidVersionNumber true

    // iterables
    list("something") should contain "something"
    list("something") should not contain "something else"
    
    // iterators
    list("something").iterator should iterate "something"
    list("something").iterator should not iterate "something else"
    list("a", "b", "c").iterator should iterate #[ "a", "b", "c" ]
    list("a", "b", "c").iterator should not iterate #[ "a", "c", "b" ]

    // using xtend's "with" operator
    
    val greeting = "hello world" => [
      length => 11
      it should startWith("hello")
      it should endWith("world")
    ]
    greeting => typeof(String)
    
    // expecting exceptions
    new Stack<String>().pop should throw EmptyStackException
    new Stack<String>().pop throws EmptyStackException
    
    // type reflection
    // --- check if a type extends another type
    typeof(Integer) should extend typeof(Number)
    // --- check if a type implements an interface
	typeof(Integer) should implement typeof(Comparable)
    // --- check the number of declared members in a type
    typeof(Integer) should not haveNbElements 5
    // --- check if a method is declared in a type
    typeof(Object) should haveMethod "equals(java.lang.Object) : boolean"
  }

  /*     
   * `should` throws an AssertionError if the result of the left 
   * expression does not equal the result of the right expression.
   * Here is a helper method we are going to use:
   * 
   * <pre class="prettyprint lang-spec">
   * def void method(){
   *   throw new IllegalArgumentException
   * }
   * </pre>
   * 
   * ...and here are the examples:
   */
  fact "...or not to pass"{
    1 + 1 should be 1 throws AssertionError
    1 + 1 should not be 2 throws AssertionError
    1 + 1 => 1 throws AssertionError
    new Object => null throws AssertionError
    new Object should be null throws AssertionError
    method() throws IllegalArgumentException
  }
  
  def void method(){
    throw new IllegalArgumentException
  }
 
  /*
   * When failing, `should` and `=>` try to give you as much context information as possible. 
   * The error message will print the values of all expressions and their subexpressions.
   *  
   */
  fact "Why did it fail?"{
    errorMessage[1 + 1 => 1].is('''
     Expected 1 + 1 => 1 but
          1 + 1 is <2>''')  
    
    val x = "hello"        
    errorMessage[x.toUpperCase should not be "HELLO"].is('''
      Expected x.toUpperCase should not be "HELLO" but
           x.toUpperCase is "HELLO"
           x is "hello"''')    
           
    val y = "world"        
    errorMessage[x => y].is('''
      Expected x => y but
           x is "hello"
           y is "world"''')    
  }  
  
  /*
   * This useful helper automatically waits until a condition turns true.
   * Expects a lambda expression returning a boolean value. The error message,
   * polling interval and duration are configurable.
   */
  fact "Wait for something"{
    waitUntil[1 > 0]
    waitUntil[
      message = "Custom error message"
      duration = 100
      pollingInterval = 10
      1 > 0
    ]
  }
  
  /*
   * You can also define your own *should* matchers by 
   * defining a method with the prefix *should_XXXX* in 
   * the current scope. The method needs to return a boolean
   * value. For example, when we define 
   * a method *should_eat*: 
   * 
   *     def should_eat(String animal, String food){
   *       animal == "Monkey" && food == "Banana"
   *     }
   * 
   * We can use "eat" in our should expressions:
   */ 
  fact "Define your own matchers"{
    "Monkey" should eat "Banana"
    "Monkey" should eat "Rocks" throws AssertionError
  }
  
  def should_eat(String animal, String food){
    animal == "Monkey" && food == "Banana"
  }
  
  /*
   * You can also the `should` and `=>` together with [hamcrest](http://code.google.com/p/hamcrest/) 
   * matchers. The following static import statements are needed to run the examples:
   *  
   *     import static org.hamcrest.CoreMatchers.startsWith
   *     import static org.hamcrest.CoreMatchers.equalTo
   * 
   * If the right-hand side of a should expression is a matcher, the matcher will be evaluated instead
   * of testing for equality:
   */ 
  fact "Combining hamcrest and should"{
    "hello" => startsWith("h")
    9 should be greaterThan(5) 
  }
  
  /*
   * In order to be able to print the value of all subexpressions when an assertion failed, 
   * we disabled the short circuit semantics of '&&' and '||' within assertions. 
   */
  fact "Short Circuit Invocation"{
    val String aString = null
    (assert aString != null && aString.length == 0) throws NullPointerException 
  }
  
  /*
   * Several time, you may want to write a hyperlink reference from one of
   * specifications to another specification. In this case, you put an
   * hyperlink in the description of the fact.
   * But, how to be sure that the link is really pointed to the HTML
   * page of a Jnario specification?
   *
   * This following statement permits to check that an URL is corresponding
   * to a Jnario specification.
   *
   * The value given after <code>beAccessibleFrom</code> is the object from
   * which the page must be found, usually this. The package of this object
   * will be used as the default location for the URLs. 
   *
   * Below
   */
  fact "Short Circuit Invocation"{
    // Check a fact in this specification
    "#Wait_for_something" should beAccessibleFrom this
    // Check that another specification is accessible.
    "./ExtensionLibrarySpec.html" should beAccessibleFrom this
    // Check a specification page URL with target.
    "./ExtensionLibrarySpec.html#Converting_Strings" should beAccessibleFrom this
  }

}