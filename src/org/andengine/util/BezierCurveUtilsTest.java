package org.andengine.util;

import org.andengine.util.debug.Debug;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * @author Nicolas Gramlich
 * @since 08:05:24 - 03.06.2013
 */
public class BezierCurveUtilsTest extends TestCase {
	// ===========================================================
	// Constants
	// ===========================================================

	private static final float CURVATURE_EPSILON = 0.01f;

	private static final float KAPPA = ((((float)Math.sqrt(2) - 1) / 3) * 4);

	private static final int LENGTH_SAMPLES = 20;

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

	public void testGetBezierCurveCurvatureStraightLine() {
		Assert.assertEquals(0, BezierCurveUtils.getBezierCurveCurvature(0.5f, new float[]{ 0f, 1f , 2f }, new float[]{ 0f, 0f, 0f }), CURVATURE_EPSILON);
	}

	public void testGetBezierCurveCurvatureCircleArc() {
		final float r = 1;

		final float[] xs = new float[]{ r, r , r * KAPPA, 0f };
		final float[] ys = new float[]{ 0f, r * KAPPA, r, r };

		for (int k = 1; k <= LENGTH_SAMPLES; k++) {
			final float t = (1f * k) / LENGTH_SAMPLES;
			Debug.d("Curvature at t=" + t + ": " + BezierCurveUtils.getBezierCurveCurvature(t, xs, ys));
		}

		final float curvature = 1 / r;
		Assert.assertEquals(curvature, BezierCurveUtils.getBezierCurveCurvature(0.5f, xs, ys), CURVATURE_EPSILON);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
