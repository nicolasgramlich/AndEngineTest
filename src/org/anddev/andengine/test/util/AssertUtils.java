package org.anddev.andengine.test.util;

import static junit.framework.TestCase.*;

/**
 * @author Nicolas Gramlich
 * @since 18:35:24 - 17.07.2010
 */
public class AssertUtils {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	public static void assertArrayEquals(final float[] pArrayA, final float[] pArrayB, final float pDelta) {
		if(pArrayA == null || pArrayB == null) {
			fail("One of the arrays was null.");
		}

		if(pArrayA.length != pArrayB.length) {
			fail("Arrays were not the same lenght.");
		}

		for(int i = 0; i < pArrayA.length; i++) {
			assertEquals("Index: i", pArrayA[i], pArrayB[i], pDelta);
		}
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
