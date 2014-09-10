package org.jnario.maven;

import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtend.core.xtend.XtendClass;
import org.eclipse.xtend.core.xtend.XtendMember;
import org.eclipse.xtend.core.xtend.XtendPackage.Literals;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.jnario.ExampleTable;
import org.jnario.doc.AbstractDocGenerator;
import org.jnario.doc.Filter;
import org.jnario.doc.FilterExtractor;
import org.jnario.doc.FilteringResult;
import org.jnario.doc.HtmlFile;
import org.jnario.spec.naming.ExampleNameProvider;
import org.jnario.spec.spec.Example;
import org.jnario.spec.spec.ExampleGroup;
import org.jnario.util.Strings;

import com.google.common.collect.Lists;
import com.google.inject.Inject;

/** Generator of the HTML files from a Jnario specification
 * based on the depth of the examples in the specification.
 * 
 * <h4>JnarioHierarchicalDocGenerate vs. JnarioDocGenerate</h4>
 * 
 * The HTML generator has a different behavior than the one
 * used by {@link JnarioDocGenerate}. Indeed, it create the HTML
 * title tags according to the depth of the example in the specification
 * file. {@link JnarioDocGenerate} generates the HTML title tags
 * according to the semantic/type of the example (description, fact, etc.)
 * 
 * <h4>Generation of an outline<h4>
 * 
 * This generator generates an outline and put it in the generated
 * HTML files in place of: {@code &lt;!-- OUTPUT OUTLINE --&gt;},
 * {@code @outline}.
 *
 * <h4>HTML Markers<h4>
 * 
 * Several tags are recognized by this generator and replaced in the
 * generated HTML pages: <ul>
 * <li>{@code &lt;note&gt;text&lt;/note&gt;} outputs paragraph marked as a note.</li>
 * <li>{@code &lt;importantnote&gt;text&lt;/importantnote&gt;} outputs paragraph marked as an important note.</li>
 * <li>{@code &lt;cautionnote&gt;text&lt;/cautionnote&gt;} outputs paragraph that the reader should read carefully.</li>
 * <li>{@code &lt;veryimportant&gt;text&lt;/veryimportant&gt;} outputs paragraph marked as critical.</li>
 * </ul>
 *
 * @author Stéphane Galland - Initial contribution and API
 */
class HierarchicalDocGenerator extends AbstractDocGenerator {

	/** Maximal depth that may be referred in the outline.
	 */
	protected static final int MAX_DEPTH_REFERENCING = 9;

	/** List of the markes that should be replaced by the outline.
	 */
	protected static final String[] OUTLINE_MARKERS = new String[] {
		Matcher.quoteReplacement("<!--") //$NON-NLS-1$
			+ "\\s*" //$NON-NLS-1$
			+ Matcher.quoteReplacement("OUTPUT") //$NON-NLS-1$
			+ "\\s+" //$NON-NLS-1$
			+ Matcher.quoteReplacement("OUTLINE") //$NON-NLS-1$
			+ "\\s*" //$NON-NLS-1$
			+ Matcher.quoteReplacement("-->"), //$NON-NLS-1$
		Matcher.quoteReplacement("@outline"), //$NON-NLS-1$
	};

	/** Separator for the IDs.
	 */
	protected static final String ID_SEPARATOR = "_"; //$NON-NLS-1$

	@Inject
	private ExampleNameProvider nameProvider;

	@Inject
	private FilterExtractor filterExtractor;

	/**
	 */
	public HierarchicalDocGenerator() {
		//
	}

	/** {@inheritDoc}
	 */
	@Override
	public HtmlFile createHtmlFile(final XtendClass xtendClass) {
        	if (!(xtendClass instanceof ExampleGroup)) {
            		return HtmlFile.EMPTY_FILE;
	    	}
        	final ExampleGroup exampleGroup = (ExampleGroup) xtendClass;
	   	return HtmlFile.newHtmlFile(new Procedure1<HtmlFile>() {
			@SuppressWarnings("synthetic-access")
			@Override
			public void apply(HtmlFile it) {
	            it.setName(HierarchicalDocGenerator.this.nameProvider.toJavaClassName(exampleGroup));
	            it.setTitle(asTitle(exampleGroup));
	            it.setContent(generateRootContent(exampleGroup));
	            it.setRootFolder(root(exampleGroup));
	            it.setSourceCode(pre(xtendClass.eContainer(), "lang-spec")); //$NON-NLS-1$
	            it.setFileName(fileName(xtendClass));
	            it.setExecutionStatus(executionStateClass(exampleGroup));
			}
		});
	}

	/** Replies the HTML title of the given object.
	 *
	 * @param object - the object.
	 * @return the HTML title.
	 */
	protected String asTitle(EObject object) {
		return toTitle(this.nameProvider.describe(object));
	}

	/** Replies the HTML code for the code part of a fact.
	 *
	 * @param example - the fact.
	 * @param filters - the filters to apply to the code of the fact.
	 * @return the HTML code.
	 */
	protected String toCodeBlock(Example example, List<Filter> filters) {
		String prefix = "<pre class=\"prettyprint lang-spec linenums\">"; //$NON-NLS-1$
        	prefix = apply(filters, prefix);
        	String code = serialize(example.getExpression(), filters);
        	if (code == null || code.isEmpty()) {
        		return ""; //$NON-NLS-1$
        	}
       		return prefix + code + "</pre>\n"; //$NON-NLS-1$
	}

	/** Replies the HTML tag that corresponds to a title at the given level
	 * for the given object.
	 *
	 * @param object - the provider of the title text.
	 * @param sectionNumber - the section numbering from the root to the current object.
	 * @return the HTML tag for the title.
	 */
	protected String makeTitleTag(EObject object, SectionNumber sectionNumber) {
		int depth = sectionNumber.getDepth();
		if (depth < MAX_DEPTH_REFERENCING) {
			String depthLevel = Integer.toString(depth + 1);
			return "<h" + depthLevel //$NON-NLS-1$
					+ id(this.nameProvider.describe(object))
					+ ">" + sectionNumber.toHTML() //$NON-NLS-1$
					+ asTitle(object)
					+ "</h" + depthLevel + ">\n"; //$NON-NLS-1$//$NON-NLS-2$
		}
		return "<p" + id(this.nameProvider.describe(object)) //$NON-NLS-1$
				+ "><strong>" + asTitle(object) //$NON-NLS-1$
				+ "</strong></p>\n"; //$NON-NLS-1$
	}

	/** Protect the value of an ID.
	 * This function is similar to {@link #id(String)},
	 * except that the prefix <code>id=</code> is not appended.
	 *
	 * @param id - the id to protect.
	 * @return the protected ID.
	 */
	protected static String protectID(String id) {
		if (id != null) {
			String p = id.replaceAll("\\W+", ID_SEPARATOR); //$NON-NLS-1$
			if (p != null) {
				return Strings.trim(p, ID_SEPARATOR.charAt(0));
			}
		}
		return ""; //$NON-NLS-1$
	}

	/** Replies the HTML tag that corresponds to a reference to a title.
	 *
	 * @param object - the provider of the title text.
	 * @param sectionNumber - the section numbering from the root to the current object.
	 * @param text - the text of the reference.
	 * @return the HTML tag for the title.
	 */
	protected String makeTitleHref(EObject object, SectionNumber sectionNumber, String text) {
		return "<a href=\"#" //$NON-NLS-1$
				+ protectID(this.nameProvider.describe(object))
				+ "\">" + sectionNumber.toHTML() //$NON-NLS-1$
				+ text + "</a>"; //$NON-NLS-1$
	}

	/** Generate the complete documentation for the given group.
	 *
	 * @param group - the object from which the documentation must be generated.
	 * @return the HTML representation of the table.
	 */
	@SuppressWarnings("synthetic-access")
	private String generateRootContent(ExampleGroup group) {
		List<String> outline = Lists.newArrayList();
		StringConcatenation content = generateMembers(group, new SectionNumber(), outline);
		String topDoc = postProcess(generateDoc(group), outline);
		return topDoc + content;
	}

	/** Post process the top documentation.
	 *
	 * @param text - the original value of the top documentation.
	 * @param outline - the outline entries.
	 * @return the top documentation to use.
	 */
	@SuppressWarnings("static-method")
	protected String postProcess(String text, List<String> outline) {
		String outlineText;
		if (outline != null && !outline.isEmpty()) {
			StringBuilder b = new StringBuilder();
			b.append("<ul class=\"page_outline\" id=\"page_outline\">"); //$NON-NLS-1$
			for (String line : outline) {
				if (line != null && !line.isEmpty()) {
					b.append("<li>"); //$NON-NLS-1$
					b.append(line);
					b.append("</li>\n"); //$NON-NLS-1$
				}
			}
			b.append("</ul>"); //$NON-NLS-1$
			outlineText = b.toString();
		} else {
			outlineText = ""; //$NON-NLS-1$
		}
		String refactoredText = text;
		for (String pattern : OUTLINE_MARKERS) {
			refactoredText = refactoredText.replaceAll(pattern, outlineText);
		}
		return refactoredText;
	}

	/** Post process an HTML documentation.
	 *
	 * @param text - the original value of the documentation.
	 * @return the documentation to use.
	 */
	@SuppressWarnings("static-method")
	protected String postProcess(String text) {
		String refactoredText = text;
		for (NoteTag tag : NoteTag.values()) {
			refactoredText = tag.apply(refactoredText);
		}
		refactoredText = refactoredText.replaceAll("(<p>){2,}", "<p>"); //$NON-NLS-1$//$NON-NLS-2$
		refactoredText = refactoredText.replaceAll("(</p>){2,}", "</p>"); //$NON-NLS-1$//$NON-NLS-2$
		return refactoredText;
	}

	/** Generate the HTML code from the Markdown text
	 * of the given object.
	 *
	 * @param object - the object from which the Markdown text should be extract.
	 * @return the HTML representation of the table.
	 */
	protected final String generateDoc(EObject object) {
		String doc = documentation(object);
		if (doc != null && !doc.isEmpty()) {
			return postProcess(markdown2Html(doc));
		}
		return ""; //$NON-NLS-1$
	}

	/** Generate the HTML code for the members of an example group..
	 *
	 * @param group - the example group.
	 * @param sectionNumber - the section numbering from the root to the current object.
	 * @return the HTML representation of the table.
	 */
	protected final StringConcatenation generateMembers(ExampleGroup group, SectionNumber sectionNumber) {
        	return generateMembers(group, sectionNumber, null);
	}

	/** Generate the HTML code for the members of an example group..
	 *
	 * @param group - the example group.
	 * @param sectionNumber - the section numbering from the root to the current object.
	 * @param outline - the list of the entries for the outline.
	 * @return the HTML representation of the table.
	 */
	private StringConcatenation generateMembers(ExampleGroup group, SectionNumber sectionNumber, List<String> outline) {
		StringConcatenation result = new StringConcatenation();
    		String content;
    		String hrefLabel;
    		SectionNumber childNumber;
    		boolean isRoot = sectionNumber.isRoot();
    		boolean isFlatSections = sectionNumber.isMaxDepthReferencing();
		if (isFlatSections) {
			result.append("<ul>"); //$NON-NLS-1$
		}
		int position = 0;
        	for (XtendMember member : group.getMembers()) {
			if (member instanceof Example) {
				Example example = (Example) member;
				childNumber = sectionNumber.getChild(position);
				hrefLabel = makeTitleHref(example, childNumber, asTitle(example));
				content = generate(example, childNumber);
				++position;
			} else if (member instanceof ExampleGroup) {
				ExampleGroup sgroup = (ExampleGroup) member;
				childNumber = sectionNumber.getChild(position);
				hrefLabel = makeTitleHref(sgroup, childNumber, asTitle(sgroup));
				content = generate(sgroup, childNumber);
				++position;
			} else if (member instanceof ExampleTable) {
				ExampleTable table = (ExampleTable) member;
				childNumber = sectionNumber.getChild(position);
				hrefLabel = makeTitleHref(table, childNumber, asTitle(table));
				content = generate(table, childNumber);
				++position;
			} else {
				content = null;
				hrefLabel = null;
			}
			if (content != null && !content.isEmpty()) {
				if (isFlatSections) {
					result.append("<li>"); //$NON-NLS-1$
				}
	    			result.append(content);
				if (isFlatSections) {
					result.append("</li>"); //$NON-NLS-1$
				} else if (isRoot && outline != null
			    		&& hrefLabel != null && !hrefLabel.isEmpty()) {
			    		outline.add(hrefLabel);
			    	}
        		}
        	}
		if (isFlatSections) {
			result.append("</ul>"); //$NON-NLS-1$
		}
        	return result;
	}

	/** Generate the HTML code for the given example.
	 *
	 * @param example - the example.
	 * @param sectionNumber - the section numbering from the root to the current object.
	 * @return the HTML representation of the table.
	 */
	protected String generate(Example example, SectionNumber sectionNumber) {
		String documentation = documentation(example);
		List<Filter> filters;
		if (documentation != null && !documentation.isEmpty()) {
			FilteringResult result = this.filterExtractor.apply(documentation);
			filters = result.getFilters();
			documentation  = result.getString();
			documentation = postProcess(markdown2Html(documentation));
		} else {
			filters = Collections.emptyList();
		}
		String exampleName = example.getName();
		StringBuilder result = new StringBuilder();
		if (exampleName != null && !exampleName.isEmpty()) {
			result.append(makeTitleTag(example, sectionNumber));
		}
		result.append(documentation);
		if (!example.isPending() && example.eIsSet(Literals.XTEND_EXECUTABLE__EXPRESSION)) {
			result.append(toCodeBlock(example, filters));
		}
		result.append(errorMessage(example));
		return result.toString();
	}

	/** Generate the HTML code for the given example group.
	 *
	 * @param group - the example group.
	 * @param sectionNumber - the section numbering from the root to the current object.
	 * @return the HTML representation of the table.
	 */
	protected String generate(ExampleGroup group, SectionNumber sectionNumber) {
		return makeTitleTag(group, sectionNumber)
				+ generateDoc(group)
				+ generateMembers(group, sectionNumber);
	}

	/** Generate the HTML code for the given example table.
	 *
	 * @param table - the example table.
	 * @param sectionNumber - the section numbering from the root to the current object.
	 * @return the HTML representation of the table.
	 */
	protected String generate(ExampleTable table, SectionNumber sectionNumber) {
		return "<p" //$NON-NLS-1$
				+ id(this.nameProvider.toFieldName(table))
				+ "><strong>" //$NON-NLS-1$
				+ toTitle(this.nameProvider.toFieldName(table))
				+ "</strong></p>" //$NON-NLS-1$
				+ generateDoc(table)
		        + super.generate(table);
	}

	/** A section number.
	 *
	 * @author Stéphane Galland - Initial contribution and API
	 */
	protected final class SectionNumber {

		private final SectionNumber parent;
		private final int position;
		private final int depth;

		/** Create the first root section.
		 */
		private SectionNumber() {
			this(null, 0);
		}

		/** Create the first section in the given parent.
		 *
		 * @param parent - parent number section.
		 * @param position - index in the given parent.
		 */
		private SectionNumber(SectionNumber parent, int position) {
			this.parent = parent;
			this.position = position;
			if (this.parent != null) {
				this.depth = parent.getDepth() + 1;
			} else {
				this.depth = 1;
			}
		}

		/** Replies the depth of the section.
		 *
		 * @return the depth of the section.
		 */
		public int getDepth() {
			return this.depth;
		}

		/** Replies the HTML representation of this section number.
		 *
		 * @return the HTML representation of this section number.
		 */
		public String toHTML() {
			if (MavenConfig.isSectionNumbering() && this.position > 0) {
				String txt = toString();
				if (txt != null && !txt.isEmpty()) {
					return txt + "&nbsp;"; //$NON-NLS-1$
				}
				return txt;
			}
			return ""; //$NON-NLS-1$
		}

		@Override
		public String toString() {
			if (MavenConfig.isSectionNumbering() && this.position > 0) {
				StringBuilder b = new StringBuilder();
				if (this.parent != null) {
					b.append(this.parent.toString());
				}
				b.append(Integer.valueOf(this.position));
				b.append("."); //$NON-NLS-1$
				return b.toString();
			}
			return ""; //$NON-NLS-1$
		}

		/** Replies the section number of the child at the given position.
		 *
		 * @param index - position of the child, starting from zero.
		 * @return the section number of the first child.
		 */
		public SectionNumber getChild(int index) {
			return new SectionNumber(this, index + 1);
		}

		/** Replies if the section number is greater to the max depth
		 * for referencing them.
		 *
		 * @return <code>true</code> if greater than max depth.
		 */
		public boolean isMaxDepthReferencing() {
			return this.depth > MAX_DEPTH_REFERENCING;
		}

		/** Replies if the section number is for a root section.
		 *
		 * @return <code>true</code> if the number is for root section.
		 */
		public boolean isRoot() {
			return this.depth == 1;
		}

	}

	/** HTML tags that could be used to put marked sections in the text.
	 *
	 * @author Stéphane Galland - Initial contribution and API
	 */
	protected static enum NoteTag {
		/** A note.
		 */
		NOTE("note", "label-info", "Note"),  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$

		/** An important note.
		 */
		IMPORTANT("importantnote", "label-warning", "Important"),  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$

		/** A caution note.
		 */
		CAUTION("cautionnote", "label-warning", "Caution"),  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$

		/** An very important note.
		 */
		DANGER("veryimportantnote", "label-danger", "Important"); //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$

		private final String htmlTag;
		private final String htmlLabel;
		private final String text;

		private NoteTag(String htmlTag, String htmlLabel, String text) {
			this.htmlTag = htmlTag;
			this.htmlLabel = htmlLabel;
			this.text = text;
		}

		/** Format a text according to the current note tag.
		 *
		 * @param text - the text to format.
		 * @return the formated string.
		 */
		public String apply(String text) {
			Pattern pattern = Pattern.compile(
					"<" + this.htmlTag //$NON-NLS-1$
					+ "(?:\\s+label\\s*=\\s*\"(.*?)\")?" //$NON-NLS-1$
					+ "\\s*>" //$NON-NLS-1$
					+ "(.*?)" //$NON-NLS-1$
					+ "</" + this.htmlTag + "\\s*>", //$NON-NLS-1$//$NON-NLS-2$
					Pattern.DOTALL);
			Matcher m = pattern.matcher(text);
			StringBuffer b = new StringBuffer();
			while (m.find()) {
				String label = m.group(1);
				String htmlText = m.group(2).trim();
				if (label == null) {
					label = this.text;
				} else {
					label = label.trim();
				}
				String replacement =
						"<p><span class=\"label " + this.htmlLabel //$NON-NLS-1$
						+ "\">" + label + "</span> " //$NON-NLS-1$//$NON-NLS-2$
						+ htmlText + "</p>"; //$NON-NLS-1$
				m.appendReplacement(b, replacement);
			}
			m.appendTail(b);
			return b.toString();
		}

	}

}
