/*******************************************************************************
 * Copyright (c) 2012 BMW Car IT and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package de.bmw.carit.jnario.ui.highlighting;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.syntaxcoloring.IHighlightedPositionAcceptor;
import org.eclipse.xtext.xtend2.ui.highlighting.XtendHighlightingCalculator;
import org.eclipse.xtext.xtend2.xtend2.XtendField;

import de.bmw.carit.jnario.common.ExampleTable;
import de.bmw.carit.jnario.jnario.AndReference;
import de.bmw.carit.jnario.jnario.GivenReference;
import de.bmw.carit.jnario.jnario.JnarioPackage;
import de.bmw.carit.jnario.jnario.Scenario;
import de.bmw.carit.jnario.jnario.Step;
import de.bmw.carit.jnario.jnario.ThenReference;
import de.bmw.carit.jnario.jnario.WhenReference;
import de.bmw.carit.jnario.jnario.util.JnarioSwitch;

/**
 * @author Birgit Engelmann - Initial contribution and API
 * @author Sebastian Benz
 */
public class JnarioSemanticHighlightingCalculator extends XtendHighlightingCalculator {

	private static final Pattern IDENTIFIER = Pattern.compile("\"([a-zA-Z0-9_]+)\"");

	private class Implementation extends JnarioSwitch<Boolean> {

		private final IHighlightedPositionAcceptor acceptor;

		public Implementation(IHighlightedPositionAcceptor acceptor) {
			this.acceptor = acceptor;
		}

		@Override
		public Boolean caseScenario(Scenario scenario) {
			if(scenario.getExamples().size() > 0){
				for(ExampleTable table: scenario.getExamples())
					highlightExampleHeader(table);
			}			
			return Boolean.TRUE;
		}

		@Override
		public Boolean caseStep(Step step) {
			String description;
			if(step.getName() != null){
				description = getFirstWord(step.getName());
				highlightStep(description, step, JnarioPackage.Literals.STEP__NAME);
			}
			else if(step instanceof GivenReference){
				GivenReference ref = (GivenReference) step;
				highlightFirstWordOfReference(ref, ref.getReference());
			}else if(step instanceof WhenReference){
				WhenReference ref = (WhenReference) step;
				highlightFirstWordOfReference(ref, ref.getReference());
			}else if(step instanceof ThenReference){
				ThenReference ref = (ThenReference) step;
				highlightFirstWordOfReference(ref, ref.getReference());
			}else if(step instanceof AndReference){
				AndReference ref = (AndReference) step;
				highlightFirstWordOfReference(ref, ref.getReference());
			}

			highlightIdentifiers(step);

			return Boolean.TRUE;
		}

		private String getFirstWord(String desc) {
			if(desc != null){
				int begin = 0;
				for (; isWhiteSpace(desc, begin); begin++) {
				}
				int end = desc.indexOf(' ', begin);
				if (end > 0) {
					return desc.substring(begin, end);
				} else{
					if(desc.length() > 0){
						return desc;
					}
				}
			}
			return "";
		}

		private boolean isWhiteSpace(String desc, int begin) {
			return desc.charAt(begin) == ' ' || desc.charAt(begin) == '\t';
		}

		private void highlightStep(String string, EObject object, EAttribute attribute) {
			acceptor.addPosition(offset(object, attribute), string.length(),
					JnarioHighlightingConfiguration.STEP_ID);
		}

		private void highlightReference(String string, EObject object, EReference reference, String highlightConfig){
			acceptor.addPosition(offset(object, reference), string.length(), highlightConfig);
		}
		
		private void highlightFirstWordOfReference(Step reference, Step referencedStep){
			String description = getFirstWord(referencedStep.getName());
			if(description != ""){
				highlightReference(description, reference, JnarioPackage.Literals.STEP_REFERENCE__REFERENCE, JnarioHighlightingConfiguration.STEP_REFERNCE_ID);
				
			}
			else{
				// unresolved reference -> take first word until whitespace
				description = getFirstWord(stepReferenceName(reference, JnarioPackage.Literals.STEP_REFERENCE__REFERENCE));
				highlightReference(description, reference, JnarioPackage.Literals.STEP_REFERENCE__REFERENCE, JnarioHighlightingConfiguration.STEP_ID);
			}
		}
		
		private String stepReferenceName(Step step, EReference ref){
			List<INode> nodes = NodeModelUtils.findNodesForFeature(step, ref);
			// works only if keyword exists only once in Step
			return nodes.iterator().next().getText();
		}

		private int offset(EObject content, EAttribute attribute) {
			List<INode> nodes = NodeModelUtils.findNodesForFeature(content,
					attribute);
			// works only if keyword exists only once in Step
			return nodes.iterator().next().getOffset();
		}

		private int offset(EObject content, EReference reference) {
			List<INode> nodes = NodeModelUtils.findNodesForFeature(content,
					reference);
			// works only if keyword exists only once in Step
			return nodes.iterator().next().getOffset();
		}

		private void highlightIdentifiers(Step step){
			if(step.getName() != null){
				Matcher m = IDENTIFIER.matcher(step.getName());
				while (m.find()) {
					int offset = offset(step, JnarioPackage.Literals.STEP__NAME);
					acceptor.addPosition(offset + m.start(1), m.end(1) - m.start(1), JnarioHighlightingConfiguration.IDENTIFIERS_ID);
				} 
			}
		}

		private void highlightExampleHeader(ExampleTable table){
			
			if(table == null){
				return;
			}
			for (XtendField element : table.getColumns()) {
				INode node = NodeModelUtils.getNode(element);
				highlightNode(node ,  JnarioHighlightingConfiguration.IDENTIFIERS_ID, acceptor);
			}
		}
	}

	public void provideHighlightingFor(XtextResource resource,
			IHighlightedPositionAcceptor acceptor) {
		super.provideHighlightingFor(resource, acceptor);
		if (noNodeModel(resource)) {
			return;
		}

		Implementation highlighter = new Implementation(acceptor);
		Iterator<EObject> contents = resource.getAllContents();
		while (contents.hasNext()) {
			highlighter.doSwitch((EObject) contents.next());
		}
	}

	protected EObject root(XtextResource resource) {
		return resource.getParseResult().getRootASTElement();
	}

	protected boolean noNodeModel(XtextResource resource) {
		return resource == null || resource.getParseResult() == null
				|| root(resource) == null;
	}

}
