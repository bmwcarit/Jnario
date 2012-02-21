package de.bmw.carit.jnario.spec.doc

import java.util.regex.Pattern

import static de.bmw.carit.jnario.spec.doc.FilterExtractor.*
import static de.bmw.carit.jnario.spec.doc.FilteringResult.*
import java.util.List

class FilterExtractor {
	
	private static String TAG = "(^|\\W)@filter(\\((.*?)\\))"
	private static Pattern TAG_PATTERN = Pattern::compile(TAG, Pattern::DOTALL)
	
	def FilteringResult apply(String input){
		if(input == null){
			return EMPTY_RESULT
		}
		val resultString = new StringBuilder()
		val List<Filter> filters = newArrayList()
		
		val matcher = TAG_PATTERN.matcher(input);
		var offset = 0
		while (matcher.find()) {
			filters += RegexFilter::create(matcher.group(3))
			
			val nextOffset = matcher.start()
			resultString.append(input.substring(offset, nextOffset))
			offset = matcher.end()
		}

		resultString.append(input.substring(offset))
		return new FilteringResult(resultString.toString, filters)
	}
} 