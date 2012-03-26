package org.jnario.spec.tests.documentation;

import org.jnario.runner.Contains;
import org.jnario.runner.ExampleGroupRunner;
import org.jnario.runner.Named;
import org.jnario.spec.tests.documentation.JnarioSpecsTutorialHowToWriteASpecificationSpec;
import org.jnario.spec.tests.documentation.JnarioSpecsTutorialWritingAssertionsSpec;
import org.junit.runner.RunWith;

/**
 * This tutorial gives you an introduction on how to write unit specifications with Jnario.
 * Jnario Specs is based on [Xtend](http://www.xtend-lang.org). it might be a good idea
 * to read the Xtend [documentation](http://www.eclipse.org/xtend/documentation/index.html) as well,
 * because expressions, fields, methods and extensions in Jnario work exactly as in Xtend.
 * 
 * ### Installation & Runtime Library
 * 
 * Jnario currently requires [Eclipse](http://www.eclipse.org) 3.5 or higher with [Xtext](http://www.xtext.org) 2.3,  and a Java SDK version 5 or above.
 * You can install the most recent version from this update site: `http://www.jnario.org/updates/snapshot/`. Jnario requires a small runtime library that contains
 * the JUnit integration. You can download the jar [here](http://jnario.org/updates/snapshot/org.jnario.lib-0.1.0-SNAPSHOT.jar).
 */
@RunWith(ExampleGroupRunner.class)
@Named("Jnario Specs - Tutorial")
@Contains({ JnarioSpecsTutorialHowToWriteASpecificationSpec.class, JnarioSpecsTutorialWritingAssertionsSpec.class })
public class JnarioSpecsTutorialSpec {
}