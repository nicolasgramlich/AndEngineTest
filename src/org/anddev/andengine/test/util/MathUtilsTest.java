package org.anddev.andengine.test.util;


import junit.framework.TestCase;

import org.anddev.andengine.util.MathUtils;

/**
 * @author Nicolas Gramlich
 * @since 19:03:16 - 03.04.2010
 */
public class MathUtilsTest extends TestCase {
	// ===========================================================
	// Constants
	// ===========================================================
	
	private static final float DELTA = 0.0001f;
	
	private static float[] TEMP_ARRAY = new float[2];

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
	// TestMethods
	// ===========================================================
	
	// ===========================================================
	// RotateAroundCenter
	// ===========================================================

	public void testRotateAroundCenterOriginAngleZero() {
		assertArrayEquals(new float[]{1, 1}, MathUtils.rotateAroundCenter(new float[]{1, 1}, 0, 0, 0), DELTA);
	}
	
	public void testRotateAroundCenterOriginAngle90() {
		assertArrayEquals(new float[]{-1, 1}, MathUtils.rotateAroundCenter(new float[]{1, 1}, 90, 0, 0), DELTA);
	}
	
	public void testRotateAroundCenterNonOriginAngle0() {
		assertArrayEquals(new float[]{1, 1}, MathUtils.rotateAroundCenter(new float[]{1, 1}, 0, 2, 2), DELTA);
	}
	
	public void testRotateAroundCenterNonOriginAngle180() {
		assertArrayEquals(new float[]{3, 3}, MathUtils.rotateAroundCenter(new float[]{1, 1}, 180, 2, 2), DELTA);
	}
	
	// ===========================================================
	// Scale around Center
	// ===========================================================
	
	public void testScaleAroundCenterOriginFactor1() {
		assertArrayEquals(new float[]{1, 1}, MathUtils.scaleAroundCenter(new float[]{1, 1}, 1, 1, 0, 0), DELTA);
	}
	
	public void testScaleAroundCenterOriginFactor2() {
		assertArrayEquals(new float[]{2, 2}, MathUtils.scaleAroundCenter(new float[]{1, 1}, 2, 2, 0, 0), DELTA);
	}
	
	public void testScaleAroundCenterOriginFactor0_5() {
		assertArrayEquals(new float[]{-0.5f, 0.5f}, MathUtils.scaleAroundCenter(new float[]{-1, 1}, 0.5f, 0.5f, 0, 0), DELTA);
	}
	
	public void testScaleAroundCenterNonOriginFactor1() {
		assertArrayEquals(new float[]{1, 1}, MathUtils.scaleAroundCenter(new float[]{1, 1}, 1, 1, 2, 2), DELTA);
	}
	
	public void testScaleAroundCenterNonOriginFactor() {
		assertArrayEquals(new float[]{0, 0}, MathUtils.scaleAroundCenter(new float[]{1, 1}, 2, 2, 2, 2), DELTA);
	}
	
	// ===========================================================
	// Rotate and Scale around Center
	// ===========================================================
	
	public void testRotateAndScaleAroundCenterOriginAngle0Factor1() {
		TEMP_ARRAY = new float[]{1, 1};
		MathUtils.rotateAroundCenter(TEMP_ARRAY, 0, 0, 0);
		MathUtils.scaleAroundCenter(TEMP_ARRAY, 1, 1, 0, 0);
		
		assertArrayEquals(new float[]{1, 1}, TEMP_ARRAY, DELTA);
	}
	
	public void testRotateAndScaleAroundCenterOriginAngle90Factor2() {
		TEMP_ARRAY = new float[]{1, 1};
		MathUtils.rotateAroundCenter(TEMP_ARRAY, 90, 0, 0);
		MathUtils.scaleAroundCenter(TEMP_ARRAY, 2, 2, 0, 0);
		
		assertArrayEquals(new float[]{-2, 2}, TEMP_ARRAY, DELTA);
	}
	
	
	
	private void assertArrayEquals(float[] pArrayA, float[] pArrayB, final float pDelta) {
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
