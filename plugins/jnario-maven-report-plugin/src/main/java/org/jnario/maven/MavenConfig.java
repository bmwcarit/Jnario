package org.jnario.maven;


/** Configuration flags from Maven.
 *
 * @author Stéphane Galland - Initial contribution and API
 */
final class MavenConfig {

	private static boolean isSectionNumbering;

	private MavenConfig() {
		//
	}

	/** Replies if the section are automatically numbered.
	 *
	 * @return <code>true</code> if sections are numbered.
	 */
	public static boolean isSectionNumbering() {
		return isSectionNumbering;
	}

	/** Set if the section are automatically numbered.
	 *
	 * @param e - <code>true</code> if sections are numbered.
	 */
	public static void setSectionNumbering(boolean e) {
		isSectionNumbering = e;
	}

}
