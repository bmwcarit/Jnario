/*******************************************************************************
 * Copyright (c) 2012 BMW Car IT and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
/*
* generated by Xtext
*/
package org.jnario.spec.ui.contentassist;

import static com.google.common.collect.Iterables.filter;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.core.search.IJavaSearchConstants;
import org.eclipse.xtend.core.xtend.XtendClass;
import org.eclipse.xtend.core.xtend.XtendField;
import org.eclipse.xtend.core.xtend.XtendPackage;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.common.types.xtext.ui.TypeMatchFilters;
import org.eclipse.xtext.ui.editor.contentassist.ContentAssistContext;
import org.eclipse.xtext.ui.editor.contentassist.ICompletionProposalAcceptor;
import org.eclipse.xtext.xbase.annotations.xAnnotations.XAnnotationsPackage;
import org.jnario.spec.spec.SpecPackage;
import org.jnario.spec.spec.TestFunction;
/**
 * @author Sebastian Benz - Initial contribution and API
 */
@SuppressWarnings("restriction")
public class SpecProposalProvider extends AbstractSpecProposalProvider {
	
	@Override
	public void completeImport_ImportedType(EObject model, Assignment assignment, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		completeJavaTypes(context, XtendPackage.Literals.XTEND_IMPORT__IMPORTED_TYPE, true,
				getQualifiedNameValueConverter(), new TypeMatchFilters.All(IJavaSearchConstants.TYPE), acceptor);
	}
	
	@Override
	public void completeXAnnotation_AnnotationType(EObject model, Assignment assignment, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		completeJavaTypes(context, XAnnotationsPackage.Literals.XANNOTATION__ANNOTATION_TYPE, 
				TypeMatchFilters.all(IJavaSearchConstants.ANNOTATION_TYPE), acceptor);
	}
	
//	@Override
//	public void completeMockLiteral_Type(EObject model, Assignment assignment,
//			ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
//		completeJavaTypes(context, XbasePackage.Literals.XTYPE_LITERAL__TYPE, 
//				TypeMatchFilters.all(IJavaSearchConstants.CLASS_AND_INTERFACE), acceptor);
//	}
	
	@Override
	public void completeExampleGroup_TargetType(EObject model,
			Assignment assignment, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		completeJavaTypes(context, SpecPackage.Literals.EXAMPLE_GROUP__TARGET_TYPE, true, getQualifiedNameValueConverter(), new TypeMatchFilters.All(IJavaSearchConstants.TYPE), acceptor);
	}
	
	@Override
	public void completeMember_TargetType(EObject model, Assignment assignment,
			ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		completeJavaTypes(context, SpecPackage.Literals.EXAMPLE_GROUP__TARGET_TYPE, true, getQualifiedNameValueConverter(), new TypeMatchFilters.All(IJavaSearchConstants.TYPE), acceptor);
	}
	
	@Override
	public void completeMember_TargetOperation(EObject model,
			Assignment assignment, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		super.completeMember_TargetOperation(model, assignment, context, acceptor);
	}
	
	@Override
	public void completeXFeatureCall_Feature(EObject model,
			Assignment assignment, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		if (model instanceof TestFunction) {
			EObject xtendClass = model.eContainer();
			while(xtendClass != null && (xtendClass instanceof XtendClass)){
				for (XtendField field : filter(((XtendClass)xtendClass).getMembers(), XtendField.class)) {
					acceptor.accept(createCompletionProposal(field.getName(), field.getName(), getImage(field),  context));
				}
				xtendClass = xtendClass.eContainer();
			}
			createLocalVariableAndImplicitProposals(model, context, acceptor);
		}else{
			super.completeXFeatureCall_Feature(model, assignment, context, acceptor);
		}
	}
	
}
