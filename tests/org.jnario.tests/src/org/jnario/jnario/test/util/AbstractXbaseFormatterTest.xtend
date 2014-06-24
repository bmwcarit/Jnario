package org.jnario.jnario.test.util;

import com.google.inject.Inject
import org.eclipse.xtext.preferences.MapBasedPreferenceValues
import org.eclipse.xtext.xbase.junit.formatter.FormatterTester

import static org.eclipse.xtext.xbase.formatting.BasicFormatterPreferenceKeys.*

abstract class AbstractXbaseFormatterTest {
	@Inject FormatterTester tester

	def assertFormatted(CharSequence toBeFormatted) {
		assertFormatted(toBeFormatted, toBeFormatted)
	}

	def assertFormattedExpression((MapBasedPreferenceValues)=>void cfg, CharSequence toBeFormatted) {
		assertFormattedExpression(cfg, toBeFormatted, toBeFormatted)
	}

	def assertFormattedExpression(CharSequence toBeFormatted) {
		assertFormattedExpression(null, toBeFormatted, toBeFormatted)
	}

	def assertFormattedExpression(String expectation, CharSequence toBeFormatted) {
		assertFormattedExpression(null, expectation, toBeFormatted)
	}

	def assertFormattedExpression((MapBasedPreferenceValues)=>void cfg, CharSequence expectation,
		CharSequence toBeFormatted) {
		assertFormattedExpression(cfg, expectation, toBeFormatted, false)
	}

	def assertFormattedExpression((MapBasedPreferenceValues)=>void cfg, CharSequence expectation,
		CharSequence toBeFormatted, boolean allowErrors) {
		assertFormatted(
			cfg,
			expectation.toString.trim.indent("\t"),
			toBeFormatted.toString.trim.indent("\t"),
			"{\n\t",
			"\n}",
			allowErrors
		)
	}

	def protected String indent(String string, String indent) {
		string.split("\\r?\\n").map[if(it == "") it else indent + it].join("\n")
	}

	def assertFormatted((MapBasedPreferenceValues)=>void cfg, CharSequence expectation) {
		assertFormatted(cfg, expectation, expectation)
	}

	def assertFormatted(CharSequence expectation, CharSequence toBeFormatted) {
		assertFormatted(null, expectation, toBeFormatted)
	}

	def assertFormatted((MapBasedPreferenceValues)=>void cfg, CharSequence expectation, CharSequence toBeFormatted) {
		assertFormatted(cfg, expectation, toBeFormatted, "", "", true)
	}

	def assertFormatted(
		(MapBasedPreferenceValues)=>void cfg,
		CharSequence expectation,
		CharSequence toBeFormatted,
		String prefix,
		String postfix,
		boolean allowErrors
	) {
		tester.assertFormatted [
			initConfig(it.config, cfg)
			it.expectation = expectation
			it.toBeFormatted = toBeFormatted
			it.prefix = prefix
			it.postfix = postfix
			it.allowErrors = allowErrors
		]
	}

	def initConfig(MapBasedPreferenceValues target, (MapBasedPreferenceValues)=>void cfg) {
		target.put(maxLineWidth.id, 80.toString)
		if (cfg != null)
			cfg.apply(target)
	}

}