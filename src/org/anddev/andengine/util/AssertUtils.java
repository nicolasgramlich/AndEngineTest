package org.anddev.andengine.util;

import junit.framework.Assert;

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
			Assert.fail("One of the arrays was null.");
			return;
		}

		if(pArrayA.length != pArrayB.length) {
			Assert.fail("Arrays were not the same lenght.");
		}

		for(int i = 0; i < pArrayA.length; i++) {
			Assert.assertEquals("Index: [" + i + "] ", pArrayA[i], pArrayB[i], pDelta);
		}
	}

	public static void assertArrayEquals(final byte[] pArrayA, final byte[] pArrayB) {
		if(pArrayA == null || pArrayB == null) {
			Assert.fail("One of the arrays was null.");
			return;
		}

		if(pArrayA.length != pArrayB.length) {
			Assert.fail("Arrays were not the same lenght.");
		}

		for(int i = 0; i < pArrayA.length; i++) {
			Assert.assertEquals("Index: [" + i + "] ", pArrayA[i], pArrayB[i]);
		}
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
