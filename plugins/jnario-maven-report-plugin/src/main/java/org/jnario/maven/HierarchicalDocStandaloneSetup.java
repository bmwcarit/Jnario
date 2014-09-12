package org.jnario.maven;

import org.eclipse.emf.ecore.EPackage;
import org.jnario.spec.SpecRuntimeModule;
import org.jnario.spec.SpecStandaloneSetupGenerated;
import org.jnario.spec.spec.SpecPackage;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.util.Modules;

/** Class for setting up the injection mechanism in the Maven plugin.
 *
 * @author Stéphane Galland - Initial contribution and API
 */
class HierarchicalDocStandaloneSetup extends SpecStandaloneSetupGenerated {

	private static Injector injector;

	/**
	 */
	public HierarchicalDocStandaloneSetup() {
		//
	}

	/** {@inheritDoc}
	 */
	@Override
	public Injector createInjectorAndDoEMFRegistration() {
		if (injector != null) {
			return injector;
		}
		EPackage.Registry.INSTANCE.put(SpecPackage.eINSTANCE.getNsURI(), SpecPackage.eINSTANCE);
		injector = Guice.createInjector(
				Modules.override(new SpecRuntimeModule()).with(new HierarchicalDocModule()));
		new SpecStandaloneSetupGenerated().register(injector);
		return injector;
	}

}
