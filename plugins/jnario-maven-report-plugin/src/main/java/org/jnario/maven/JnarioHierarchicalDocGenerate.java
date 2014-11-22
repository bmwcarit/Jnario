package org.jnario.maven;

import org.apache.maven.plugin.MojoExecutionException;
import org.eclipse.xtext.ISetup;
import org.jnario.feature.FeatureStandaloneSetup;
import org.jnario.suite.SuiteStandaloneSetup;

/** Maven mojo that is generating the documentation from a Jnario specification
 * based on the depth of the examples in the specification.
 * 
 * @author Stéphane Galland - Initial contribution and API
 * @requiresDependencyResolution test
 * @goal generate2
 */
public class JnarioHierarchicalDocGenerate extends JnarioDocGenerate {

	/**
	 * Location of the generated JUnit XML reports.
	 *
	 * @parameter default-value="true"
	 */
	private boolean sectionNumbering;

	@Override
	protected void internalExecute() throws MojoExecutionException {
		getLog().debug("Set section numbering: " + Boolean.toString(this.sectionNumbering)); //$NON-NLS-1$
		MavenConfig.setSectionNumbering(this.sectionNumbering);
		super.internalExecute();
	}

	protected ISetup[] getStandaloneSetups() {
		return new ISetup[] {
				new HierarchicalDocStandaloneSetup(),
				new FeatureStandaloneSetup(),
				new SuiteStandaloneSetup(),
		};
	}

}
