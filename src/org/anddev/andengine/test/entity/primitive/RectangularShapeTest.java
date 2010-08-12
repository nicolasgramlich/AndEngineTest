package org.anddev.andengine.test.entity.primitive;


import junit.framework.TestCase;

import org.anddev.andengine.entity.shape.RectangularShape;
import org.anddev.andengine.opengl.buffer.BufferObjectManager;
import org.anddev.andengine.test.util.AssertUtils;

import android.util.FloatMath;

/**
 * @author Nicolas Gramlich
 * @since 19:03:16 - 03.04.2010
 */
public class RectangularShapeTest extends TestCase {
	// ===========================================================
	// Constants
	// ===========================================================
	
	private static final float DELTA = 0.0001f;

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	@Override
	protected void setUp() throws Exception {
		BufferObjectManager.setActiveInstance(new BufferObjectManager());
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	
	public void testContainsSimple() {
		final TestRectangularShape shape = new TestRectangularShape(0, 0, 2, 2);
		
		/* Center */
		assertTrue(shape.contains(1, 1));

		/* Sides */
		assertTrue(shape.contains(1, 0));
		assertTrue(shape.contains(0, 1));
		assertTrue(shape.contains(2, 1));
		assertTrue(shape.contains(1, 2));
		
		/* Edges */
		assertTrue(shape.contains(0, 0));
		assertTrue(shape.contains(2, 2));
		assertTrue(shape.contains(0, 2));
		assertTrue(shape.contains(2, 0));

		/* Outside */
		assertFalse(shape.contains(-0.01f, -0.01f));
		assertFalse(shape.contains(2.01f, 2.01f));
		assertFalse(shape.contains(2.01f, -0.01f));
		assertFalse(shape.contains(-0.01f, 2.01f));
	}
	
	public void testContainsScaled() {
		final TestRectangularShape shape = new TestRectangularShape(0.5f, 0.5f, 1, 1);
		shape.setScale(2);
		
		/* Center */
		assertTrue(shape.contains(1, 1));

		/* Sides */
		assertTrue(shape.contains(1, 0));
		assertTrue(shape.contains(0, 1));
		assertTrue(shape.contains(2, 1));
		assertTrue(shape.contains(1, 2));
		
		/* Edges */
		assertTrue(shape.contains(0, 0));
		assertTrue(shape.contains(2, 2));
		assertTrue(shape.contains(0, 2));
		assertTrue(shape.contains(2, 0));

		/* Outside */
		assertFalse(shape.contains(-0.01f, -0.01f));
		assertFalse(shape.contains(2.01f, 2.01f));
		assertFalse(shape.contains(2.01f, -0.01f));
		assertFalse(shape.contains(-0.01f, 2.01f));
	}
	
	public void testContainsRotated() {
		final TestRectangularShape shape = new TestRectangularShape(0, 0, 2, 2);
		shape.setRotation(45);
		
		/* Center */
		assertTrue(shape.contains(1, 1));

		/* (Old)Sides */
		assertTrue(shape.contains(1, 0));
		assertTrue(shape.contains(0, 1));
		assertTrue(shape.contains(2, 1));
		assertTrue(shape.contains(1, 2));
		
		/* (Old)Edges */
		assertFalse(shape.contains(0, 0));
		assertFalse(shape.contains(2, 2));
		assertFalse(shape.contains(0, 2));
		assertFalse(shape.contains(2, 0));
	}
	
	public void testContainsRotatedAndScaled() {
		final TestRectangularShape shape = new TestRectangularShape(0, 0, 2, 2);
		shape.setRotation(45);
		shape.setScale(2.01f / FloatMath.sqrt(2f));
		
		/* Center */
		assertTrue(shape.contains(1, 1));

		/* (Old)Sides */
		assertTrue(shape.contains(1, 0));
		assertTrue(shape.contains(0, 1));
		assertTrue(shape.contains(2, 1));
		assertTrue(shape.contains(1, 2));

		/* (Old)Edges */
		assertTrue(shape.contains(0, 0));
		assertTrue(shape.contains(2, 2));
		assertTrue(shape.contains(0, 2));
		assertTrue(shape.contains(2, 0));
		
		/* (New)Edges */
		assertTrue(shape.contains(-1, 1));
		assertTrue(shape.contains(1, -1));
		assertTrue(shape.contains(1, 3));
		assertTrue(shape.contains(3, 1));
	}

	public void testCollidesWithSimple() {
		final TestRectangularShape shapeA = new TestRectangularShape(0, 0, 2, 2);
		final TestRectangularShape shapeB = new TestRectangularShape(1, 1, 2, 2);
		assertTrue(shapeA.collidesWith(shapeB));
	}
	
	public void testCollidesWithSimpleNot() {
		final TestRectangularShape shapeA = new TestRectangularShape(0, 0, 2, 2);
		final TestRectangularShape shapeB = new TestRectangularShape(3, 0, 2, 2);
		assertFalse(shapeA.collidesWith(shapeB));
	}
	
	public void testCollidesWithScaled() {
		final TestRectangularShape shapeA = new TestRectangularShape(0, 0, 2, 2);
		final TestRectangularShape shapeB = new TestRectangularShape(3, 3, 2, 2);
		assertFalse(shapeA.collidesWith(shapeB));
		
		shapeB.setScale(3);
		assertTrue(shapeA.collidesWith(shapeB));
		
		shapeB.setScaleCenter(0, 0);
		assertFalse(shapeA.collidesWith(shapeB));

		shapeB.setScale(2);
		shapeB.setScaleCenter(2, 2);
		assertTrue(shapeA.collidesWith(shapeB));
	}
	
	public void testCollidesWithScaledUneven() {
		final TestRectangularShape shapeA = new TestRectangularShape(0, 0, 2, 2);
		final TestRectangularShape shapeB = new TestRectangularShape(3, 0, 2, 2);
		assertFalse(shapeA.collidesWith(shapeB));

		shapeB.setScaleX(2.1f);
		assertTrue(shapeA.collidesWith(shapeB));

		shapeB.setScaleX(1);
		shapeB.setScaleY(2.1f);
		assertFalse(shapeA.collidesWith(shapeB));
	}
	
	public void testCollidesWithRotated() {
		final TestRectangularShape shapeA = new TestRectangularShape(0, 0, 4, 4);
		final TestRectangularShape shapeB = new TestRectangularShape(5, 0, 4, 4);
		assertFalse(shapeA.collidesWith(shapeB));

		shapeA.setRotation(45f);
		shapeB.setRotation(45f);
		assertTrue(shapeA.collidesWith(shapeB));

		shapeB.setRotation(90f);
		assertFalse(shapeA.collidesWith(shapeB));
	}
	
	public void testCollidesWithRotatedAroundCenter() {
		final TestRectangularShape shapeA = new TestRectangularShape(0, 0, 2, 2);
		final TestRectangularShape shapeB = new TestRectangularShape(3, 0, 2, 2);
		assertFalse(shapeA.collidesWith(shapeB));

		shapeA.setRotationCenter(2, 2);
		shapeA.setRotation(45f);
		assertTrue(shapeA.collidesWith(shapeB));

		shapeA.setRotation(90f);
		assertTrue(shapeA.collidesWith(shapeB));
		
		shapeA.setRotation(179.9f);
		assertTrue(shapeA.collidesWith(shapeB));
		
		shapeA.setRotation(180.1f);
		assertFalse(shapeA.collidesWith(shapeB));
	}
	
	public void testCollidesWithRotatedAndScaled() {
		final TestRectangularShape shapeA = new TestRectangularShape(0, 0, 2, 2);
		final TestRectangularShape shapeB = new TestRectangularShape(3, 0, 2, 2);
		assertFalse(shapeA.collidesWith(shapeB));

		shapeB.setRotation(45f);
		assertFalse(shapeA.collidesWith(shapeB));

		shapeB.setScale(2f / FloatMath.sqrt(2f) + 0.01f);
		assertTrue(shapeA.collidesWith(shapeB));

		shapeB.setScale(2f / FloatMath.sqrt(2f) - 0.01f);
		assertFalse(shapeA.collidesWith(shapeB));
	}
	
	
	public void testGetLocalCoordinatesSimple() {
		final TestRectangularShape shape = new TestRectangularShape(0, 0, 2, 2);
		AssertUtils.assertArrayEquals(new float[]{1, 1}, shape.convertSceneToLocalCoordinates(1, 1), DELTA);
	}
	
	public void testGetLocalCoordinatesNonOrigin() {
		final TestRectangularShape shape = new TestRectangularShape(10, 10, 2, 2);
		AssertUtils.assertArrayEquals(new float[]{1, 1}, shape.convertSceneToLocalCoordinates(11, 11), DELTA);
	}
	
	public void testGetLocalCoordinatesNonOriginRotated() {
		final TestRectangularShape shape = new TestRectangularShape(10, 10, 2, 2);
		shape.setRotation(90);
		AssertUtils.assertArrayEquals(new float[]{0, 2}, shape.convertSceneToLocalCoordinates(10, 10), DELTA);
	}
	
	public void testGetLocalCoordinatesNonOriginScaled() {
		final TestRectangularShape shape = new TestRectangularShape(10, 10, 2, 2);
		shape.setScale(0.5f);
		AssertUtils.assertArrayEquals(new float[]{0, 0}, shape.convertSceneToLocalCoordinates(10.5f, 10.5f), DELTA);
	}
	

	public void testGetSceneCenterCoordinatesSimple() {
		final TestRectangularShape shape = new TestRectangularShape(0, 0, 2, 2);
		AssertUtils.assertArrayEquals(new float[]{1, 1}, shape.getSceneCenterCoordinates(), DELTA);
	}
	
	public void testGetSceneCenterCoordinatesNonOrigin() {
		final TestRectangularShape shape = new TestRectangularShape(10, 10, 2, 2);
		AssertUtils.assertArrayEquals(new float[]{11, 11}, shape.getSceneCenterCoordinates(), DELTA);
	}
	
	public void testGetSceneCenterCoordinatesScaled() {
		final TestRectangularShape shape = new TestRectangularShape(10, 10, 2, 2);
		shape.setScale(2);
		AssertUtils.assertArrayEquals(new float[]{11, 11}, shape.getSceneCenterCoordinates(), DELTA);
	}
	
	public void testGetSceneCenterCoordinatesRotated() {
		final TestRectangularShape shape = new TestRectangularShape(10, 10, 2, 2);
		shape.setRotation(123);
		AssertUtils.assertArrayEquals(new float[]{11, 11}, shape.getSceneCenterCoordinates(), DELTA);
	}
	
	public void testGetSceneCenterCoordinatesScaledUneven() {
		final TestRectangularShape shape = new TestRectangularShape(10, 10, 2, 2);
		shape.setScale(2);
		shape.setScaleCenter(0, 0);
		AssertUtils.assertArrayEquals(new float[]{12, 12}, shape.getSceneCenterCoordinates(), DELTA);
	}
	
	public void testGetSceneCenterCoordinatesRotatedUneven() {
		final TestRectangularShape shape = new TestRectangularShape(10, 10, 2, 2);
		shape.setRotation(90);
		shape.setRotationCenter(0, 0);
		AssertUtils.assertArrayEquals(new float[]{9, 11}, shape.getSceneCenterCoordinates(), DELTA);
	}
	
	public void testGetSceneCenterCoordinatesRotatedAndScaledUneven() {
		final TestRectangularShape shape = new TestRectangularShape(10, 10, 2, 2);
		shape.setRotation(123);
		shape.setScale(2);
		shape.setScaleCenter(0, 0);
		AssertUtils.assertArrayEquals(new float[]{12, 12}, shape.getSceneCenterCoordinates(), DELTA);
	}
	
	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

	private class TestRectangularShape extends RectangularShape {
		public TestRectangularShape(float pX, float pY, float pWidth, float pHeight) {
			super(pX, pY, pWidth, pHeight, null);
		}

		@Override
		protected void onUpdateVertexBuffer() {}
	}
}
