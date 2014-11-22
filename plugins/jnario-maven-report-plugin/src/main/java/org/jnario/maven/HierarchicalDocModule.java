package org.jnario.maven;

import org.jnario.doc.AbstractDocGenerator;

import com.google.inject.Binder;
import com.google.inject.Module;

/** Injection module that permits to inject the documentation generator
 * based on the depth of the examples in the specification.
 *
 * @author Stéphane Galland - Initial contribution and API
 */
class HierarchicalDocModule implements Module {

	/**
	 */
	public HierarchicalDocModule() {
		//
	}

	/** {@inheritDoc}
	 */
	public void configure(Binder binder) {
		binder.bind(AbstractDocGenerator.class).to(HierarchicalDocGenerator.class);
	}

}
