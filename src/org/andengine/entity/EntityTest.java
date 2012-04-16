package org.andengine.entity;

import junit.framework.Assert;

import org.andengine.util.AssertUtils;

import android.test.AndroidTestCase;
import android.util.FloatMath;

/**
 * @author Nicolas Gramlich
 * @since 15:27:27 - 12.05.2010
 */
public class EntityTest extends AndroidTestCase {
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
	public void setUp() throws Exception {

	}

	@Override
	public void tearDown() throws Exception {

	}

	// ===========================================================
	// Test-Methods
	// ===========================================================

	public void testConvertLocalToSceneCoordinatesOrigin() throws Exception {
		final IEntity entity = new Entity(0, 0);

		final float testX = 5;
		final float testY = 5;

		final float expectedX = 5;
		final float expectedY = 5;

		this.testConvertLocalToSceneCoordinates(entity, testX, testY, expectedX, expectedY);
	}

	public void testConvertLocalToSceneCoordinatesOriginWithRotation() throws Exception {
		final IEntity entity = new Entity(0, 0);
		entity.setRotation(180);

		final float testX = 5;
		final float testY = 5;

		final float expectedX = -5;
		final float expectedY = -5;

		this.testConvertLocalToSceneCoordinates(entity, testX, testY, expectedX, expectedY);
	}

	public void testConvertLocalToSceneCoordinatesOriginWithScale() throws Exception {
		final IEntity entity = new Entity(0, 0);
		entity.setScale(2);

		final float testX = 5;
		final float testY = 5;

		final float expectedX = 10;
		final float expectedY = 10;

		this.testConvertLocalToSceneCoordinates(entity, testX, testY, expectedX, expectedY);
	}

	public void testConvertLocalToSceneCoordinatesOriginWithParent() throws Exception {
		final IEntity parent = new Entity(0, 0);
		final IEntity entity = new Entity(0, 0);

		parent.attachChild(entity);

		final float testX = 5;
		final float testY = 5;

		final float expectedX = 5;
		final float expectedY = 5;

		this.testConvertLocalToSceneCoordinates(entity, testX, testY, expectedX, expectedY);
	}

	public void testConvertLocalToSceneCoordinatesOriginWithParentAndRotation() throws Exception {
		final IEntity parent = new Entity(0, 0);
		parent.setRotation(180);
		final IEntity entity = new Entity(0, 0);
		entity.setRotation(180);

		parent.attachChild(entity);

		final float testX = 5;
		final float testY = 5;

		final float expectedX = 5;
		final float expectedY = 5;

		this.testConvertLocalToSceneCoordinates(entity, testX, testY, expectedX, expectedY);
	}

	public void testConvertLocalToSceneCoordinatesWithParent() throws Exception {
		final IEntity parent = new Entity(5, 5);
		final IEntity entity = new Entity(5, 5);

		parent.attachChild(entity);

		final float testX = 5;
		final float testY = 5;

		final float expectedX = 15;
		final float expectedY = 15;

		this.testConvertLocalToSceneCoordinates(entity, testX, testY, expectedX, expectedY);
	}

	public void testConvertLocalToSceneCoordinatesWithParentAndRotation() throws Exception {
		final IEntity parent = new Entity(5, 5);
		final IEntity entity = new Entity(5, 5);
		entity.setRotation(180);

		parent.attachChild(entity);

		final float testX = 5;
		final float testY = 5;

		final float expectedX = 5;
		final float expectedY = 5;

		this.testConvertLocalToSceneCoordinates(entity, testX, testY, expectedX, expectedY);
	}

	public void testConvertLocalToSceneCoordinatesWithParentAndRotation2() throws Exception {
		final IEntity parent = new Entity(5, 5);
		final IEntity entity = new Entity(5, 5);
		entity.setRotation(90);

		parent.attachChild(entity);

		final float testX = 5;
		final float testY = 5;

		final float expectedX = 5;
		final float expectedY = 15;

		this.testConvertLocalToSceneCoordinates(entity, testX, testY, expectedX, expectedY);
	}

	public void testConvertLocalToSceneCoordinatesNotOrigin() throws Exception {
		final IEntity entity = new Entity(5, 5);

		final float testX = 5;
		final float testY = 5;

		final float expectedX = 10;
		final float expectedY = 10;

		this.testConvertLocalToSceneCoordinates(entity, testX, testY, expectedX, expectedY);
	}

	public void testConvertLocalToSceneCoordinatesNotOriginWithRotation() throws Exception {
		final IEntity entity = new Entity(5, 5);
		entity.setRotation(180);

		final float testX = 5;
		final float testY = 5;

		final float expectedX = 0;
		final float expectedY = 0;

		this.testConvertLocalToSceneCoordinates(entity, testX, testY, expectedX, expectedY);
	}

	public void testConvertLocalToSceneCoordinatesNotOriginWithRotation2() throws Exception {
		final IEntity entity = new Entity(5, 5);
		entity.setRotation(180);

		final float testX = 10;
		final float testY = 10;

		final float expectedX = -5;
		final float expectedY = -5;

		this.testConvertLocalToSceneCoordinates(entity, testX, testY, expectedX, expectedY);
	}

	public void testConvertSceneToLocalCoordinatesOrigin() throws Exception {
		final IEntity entity = new Entity(5, 5);

		final float testX = 10;
		final float testY = 10;

		final float expectedX = 5;
		final float expectedY = 5;

		this.testConvertSceneToLocalCoordinates(entity, testX, testY, expectedX, expectedY);
	}

	public void testConvertSceneToLocalCoordinatesOriginWithScale() throws Exception {
		final IEntity entity = new Entity(0, 0);
		entity.setScale(2);

		final float testX = 10;
		final float testY = 10;

		final float expectedX = 5;
		final float expectedY = 5;

		this.testConvertSceneToLocalCoordinates(entity, testX, testY, expectedX, expectedY);
	}

	public void testConvertSceneToLocalCoordinatesNotOriginWithScale() throws Exception {
		final IEntity entity = new Entity(5, 5);
		entity.setScale(2);

		final float testX = 5;
		final float testY = 5;

		final float expectedX = 0;
		final float expectedY = 0;

		this.testConvertSceneToLocalCoordinates(entity, testX, testY, expectedX, expectedY);
	}

	public void testConvertSceneToLocalCoordinatesNotOriginWithScale1() throws Exception {
		final IEntity entity = new Entity(5, 5);
		entity.setScale(2);

		final float testX = 10;
		final float testY = 10;

		final float expectedX = 2.5f;
		final float expectedY = 2.5f;

		this.testConvertSceneToLocalCoordinates(entity, testX, testY, expectedX, expectedY);
	}

	public void testConvertSceneToLocalCoordinatesNotOriginWithScale2() throws Exception {
		final Entity entity = new Entity(5, 5, 5, 5);

		final float testX = 7.5f;
		final float testY = 7.5f;

		final float expectedX = 2.5f;
		final float expectedY = 2.5f;

		this.testConvertSceneToLocalCoordinates(entity, testX, testY, expectedX, expectedY);
	}

	public void testConvertSceneToLocalCoordinatesNotOriginWithScale3() throws Exception {
		final Entity entity = new Entity(5, 5, 5, 5);
		entity.setScale(2);

		final float testX = 7.5f;
		final float testY = 7.5f;

		final float expectedX = 2.5f;
		final float expectedY = 2.5f;

		this.testConvertSceneToLocalCoordinates(entity, testX, testY, expectedX, expectedY);
	}

	public void testRectangleContains() throws Exception {
		final Entity entity = new Entity(0, 0, 10, 10);

		Assert.assertTrue(entity.contains(5, 5));
		Assert.assertTrue(entity.contains(0 + DELTA, 0 + DELTA));
		Assert.assertTrue(entity.contains(0 + DELTA, 10 - DELTA));
		Assert.assertTrue(entity.contains(10 - DELTA, 0 + DELTA));
		Assert.assertTrue(entity.contains(10 - DELTA, 10 - DELTA));

		Assert.assertFalse(entity.contains(0 - DELTA, 0 - DELTA));
		Assert.assertFalse(entity.contains(10 + DELTA, 0 - DELTA));
		Assert.assertFalse(entity.contains(0 - DELTA, 10 + DELTA));
		Assert.assertFalse(entity.contains(10 + DELTA, 10 + DELTA));
	}

	public void testRectangleContainsWithScale() throws Exception {
		final Entity entity = new Entity(0, 0, 10, 10);
		entity.setScale(2);

		Assert.assertTrue(entity.contains(5, 5));
		Assert.assertTrue(entity.contains(-5 + DELTA, -5 + DELTA));
		Assert.assertTrue(entity.contains(15 - DELTA, -5 + DELTA));
		Assert.assertTrue(entity.contains(-5 + DELTA, 15 - DELTA));
		Assert.assertTrue(entity.contains(15 - DELTA, 15 - DELTA));

		Assert.assertFalse(entity.contains(-5 - DELTA, -5 - DELTA));
		Assert.assertFalse(entity.contains(15 + DELTA, -5 - DELTA));
		Assert.assertFalse(entity.contains(-5 - DELTA, 15 + DELTA));
		Assert.assertFalse(entity.contains(15 + DELTA, 15 + DELTA));
	}

	public void testRectangleContainsOriginWithParent() throws Exception {
		final IEntity parent = new Entity(0, 0);
		final Entity entity = new Entity(0, 0, 10, 10);
		entity.setParent(parent);

		Assert.assertTrue(entity.contains(5, 5));
		Assert.assertTrue(entity.contains(0 + DELTA, 0 + DELTA));
		Assert.assertTrue(entity.contains(0 + DELTA, 10 - DELTA));
		Assert.assertTrue(entity.contains(10 - DELTA, 0 + DELTA));
		Assert.assertTrue(entity.contains(10 - DELTA, 10 - DELTA));

		Assert.assertFalse(entity.contains(0 - DELTA, 0 - DELTA));
		Assert.assertFalse(entity.contains(10 + DELTA, 0 - DELTA));
		Assert.assertFalse(entity.contains(0 - DELTA, 10 + DELTA));
		Assert.assertFalse(entity.contains(10 + DELTA, 10 + DELTA));
	}

	public void testRectangleContainsOriginWithParentAndScale() throws Exception {
		final IEntity parent = new Entity(0, 0);
		final Entity entity = new Entity(0, 0, 10, 10);
		entity.setParent(parent);
		entity.setScale(2);

		Assert.assertTrue(entity.contains(5, 5));
		Assert.assertTrue(entity.contains(-5 + DELTA, -5 + DELTA));
		Assert.assertTrue(entity.contains(15 - DELTA, -5 + DELTA));
		Assert.assertTrue(entity.contains(-5 + DELTA, 15 - DELTA));
		Assert.assertTrue(entity.contains(15 - DELTA, 15 - DELTA));

		Assert.assertFalse(entity.contains(-5 - DELTA, -5 - DELTA));
		Assert.assertFalse(entity.contains(15 + DELTA, -5 - DELTA));
		Assert.assertFalse(entity.contains(-5 - DELTA, 15 + DELTA));
		Assert.assertFalse(entity.contains(15 + DELTA, 15 + DELTA));
	}

	public void testRectangleContainsOriginWithParentAndScale2() throws Exception {
		final IEntity parent = new Entity(0, 0);
		parent.setScale(2);
		final Entity entity = new Entity(0, 0, 10, 10);
		entity.setParent(parent);

		Assert.assertTrue(entity.contains(10, 10));
		Assert.assertTrue(entity.contains(0 + DELTA, 0 + DELTA));
		Assert.assertTrue(entity.contains(0 + DELTA, 20 - DELTA));
		Assert.assertTrue(entity.contains(20 - DELTA, 0 + DELTA));
		Assert.assertTrue(entity.contains(20 - DELTA, 20 - DELTA));

		Assert.assertFalse(entity.contains(0 - DELTA, 0 - DELTA));
		Assert.assertFalse(entity.contains(20 + DELTA, 0 - DELTA));
		Assert.assertFalse(entity.contains(0 - DELTA, 20 + DELTA));
		Assert.assertFalse(entity.contains(20 + DELTA, 20 + DELTA));
	}

	public void testRectangleContainsOriginWithParentAndScale3() throws Exception {
		final IEntity parent = new Entity(0, 0);
		parent.setScale(2);
		final Entity entity = new Entity(0, 0, 10, 10);
		entity.setParent(parent);
		entity.setScale(2);

		Assert.assertTrue(entity.contains(10, 10));
		Assert.assertTrue(entity.contains(-10 + DELTA, -10 + DELTA));
		Assert.assertTrue(entity.contains(-10 + DELTA, 30 - DELTA));
		Assert.assertTrue(entity.contains(30 - DELTA, -10 + DELTA));
		Assert.assertTrue(entity.contains(30 - DELTA, 30 - DELTA));

		Assert.assertFalse(entity.contains(-10 - DELTA, -10 - DELTA));
		Assert.assertFalse(entity.contains(30 + DELTA, -10 - DELTA));
		Assert.assertFalse(entity.contains(-10 - DELTA, 30 + DELTA));
		Assert.assertFalse(entity.contains(30 + DELTA, 30 + DELTA));
	}

	public void testRectangleContainsNonOriginWithParent() throws Exception {
		final IEntity parent = new Entity(5, 5);
		final Entity entity = new Entity(0, 0, 10, 10);
		entity.setParent(parent);

		Assert.assertTrue(entity.contains(10, 10));
		Assert.assertTrue(entity.contains(5 + DELTA, 5 + DELTA));
		Assert.assertTrue(entity.contains(5 + DELTA, 15 - DELTA));
		Assert.assertTrue(entity.contains(15 - DELTA, 5 + DELTA));
		Assert.assertTrue(entity.contains(15 - DELTA, 15 - DELTA));

		Assert.assertFalse(entity.contains(5 - DELTA, 5 - DELTA));
		Assert.assertFalse(entity.contains(15 + DELTA, 5 - DELTA));
		Assert.assertFalse(entity.contains(5 - DELTA, 15 + DELTA));
		Assert.assertFalse(entity.contains(15 + DELTA, 15 + DELTA));
	}

	public void testRectangleContainsNonOriginWithParent2() throws Exception {
		final IEntity parent = new Entity(5, 5);
		final Entity entity = new Entity(5, 5, 10, 10);
		entity.setParent(parent);

		Assert.assertTrue(entity.contains(15, 15));
		Assert.assertTrue(entity.contains(10 + DELTA, 10 + DELTA));
		Assert.assertTrue(entity.contains(10 + DELTA, 20 - DELTA));
		Assert.assertTrue(entity.contains(20 - DELTA, 10 + DELTA));
		Assert.assertTrue(entity.contains(20 - DELTA, 20 - DELTA));

		Assert.assertFalse(entity.contains(10 - DELTA, 10 - DELTA));
		Assert.assertFalse(entity.contains(20 + DELTA, 10 - DELTA));
		Assert.assertFalse(entity.contains(10 - DELTA, 20 + DELTA));
		Assert.assertFalse(entity.contains(20 + DELTA, 20 + DELTA));
	}

	public void testContainsSimple() {
		final Entity entity = new Entity(0, 0, 2, 2);

		/* Center */
		Assert.assertTrue(entity.contains(1, 1));

		/* Sides */
		Assert.assertTrue(entity.contains(1, 0 + DELTA));
		Assert.assertTrue(entity.contains(0 + DELTA, 1));
		Assert.assertTrue(entity.contains(2 - DELTA, 1));
		Assert.assertTrue(entity.contains(1, 2 - DELTA));

		/* Edges */
		Assert.assertTrue(entity.contains(0 + DELTA, 0 + DELTA));
		Assert.assertTrue(entity.contains(2 - DELTA, 2 - DELTA));
		Assert.assertTrue(entity.contains(0 + DELTA, 2 - DELTA));
		Assert.assertTrue(entity.contains(2 - DELTA, 0 + DELTA));

		/* Outside */
		Assert.assertFalse(entity.contains(0 - DELTA, 0 - DELTA));
		Assert.assertFalse(entity.contains(2 + DELTA, 2 + DELTA));
		Assert.assertFalse(entity.contains(2 + DELTA, 0 - DELTA));
		Assert.assertFalse(entity.contains(0 - DELTA, 2 + DELTA));
	}

	public void testContainsScaled() {
		final Entity entity = new Entity(0.5f, 0.5f, 1, 1);
		entity.setScale(2);

		/* Center */
		Assert.assertTrue(entity.contains(1, 1));

		/* Sides */
		Assert.assertTrue(entity.contains(1, 0 + DELTA));
		Assert.assertTrue(entity.contains(0 + DELTA, 1));
		Assert.assertTrue(entity.contains(2 - DELTA, 1));
		Assert.assertTrue(entity.contains(1, 2 - DELTA));

		/* Edges */
		Assert.assertTrue(entity.contains(0 + DELTA, 0 +  DELTA));
		Assert.assertTrue(entity.contains(2 - DELTA, 2 - DELTA));
		Assert.assertTrue(entity.contains(0 + DELTA, 2 - DELTA));
		Assert.assertTrue(entity.contains(2 - DELTA, 0 + DELTA));

		/* Outside */
		Assert.assertFalse(entity.contains(0 - DELTA, 0 - DELTA));
		Assert.assertFalse(entity.contains(2 + DELTA, 2 + DELTA));
		Assert.assertFalse(entity.contains(2 + DELTA, 0 - DELTA));
		Assert.assertFalse(entity.contains(0 - DELTA, 2 + DELTA));
	}

	public void testContainsRotated() {
		final Entity entity = new Entity(0, 0, 2, 2);
		entity.setRotation(45);

		/* Center */
		Assert.assertTrue(entity.contains(1, 1));

		/* (Old)Sides */
		Assert.assertTrue(entity.contains(1, 0));
		Assert.assertTrue(entity.contains(0, 1));
		Assert.assertTrue(entity.contains(2, 1));
		Assert.assertTrue(entity.contains(1, 2));

		/* (Old)Edges */
		Assert.assertFalse(entity.contains(0, 0));
		Assert.assertFalse(entity.contains(2, 2));
		Assert.assertFalse(entity.contains(0, 2));
		Assert.assertFalse(entity.contains(2, 0));
	}

	public void testContainsRotatedAndScaled() {
		final Entity entity = new Entity(0, 0, 2, 2);
		entity.setRotation(45);
		entity.setScale(2 + DELTA / FloatMath.sqrt(2f));

		/* Center */
		Assert.assertTrue(entity.contains(1, 1));

		/* (Old)Sides */
		Assert.assertTrue(entity.contains(1, 0));
		Assert.assertTrue(entity.contains(0, 1));
		Assert.assertTrue(entity.contains(2, 1));
		Assert.assertTrue(entity.contains(1, 2));

		/* (Old)Edges */
		Assert.assertTrue(entity.contains(0, 0));
		Assert.assertTrue(entity.contains(2, 2));
		Assert.assertTrue(entity.contains(0, 2));
		Assert.assertTrue(entity.contains(2, 0));

		/* (New)Edges */
		Assert.assertTrue(entity.contains(-1, 1));
		Assert.assertTrue(entity.contains(1, -1));
		Assert.assertTrue(entity.contains(1, 3));
		Assert.assertTrue(entity.contains(3, 1));
	}

	public void testCollidesWithSimple() {
		final Entity entityA = new Entity(0, 0, 2, 2);
		final Entity entityB = new Entity(1, 1, 2, 2);
		Assert.assertTrue(entityA.collidesWith(entityB));
	}

	public void testCollidesWithSimpleNot() {
		final Entity entityA = new Entity(0, 0, 2, 2);
		final Entity entityB = new Entity(3, 0, 2, 2);
		Assert.assertFalse(entityA.collidesWith(entityB));
	}

	public void testCollidesWithScaled() {
		final Entity entityA = new Entity(0, 0, 2, 2);
		final Entity entityB = new Entity(3, 3, 2, 2);
		Assert.assertFalse(entityA.collidesWith(entityB));

		entityB.setScale(3);
		Assert.assertTrue(entityA.collidesWith(entityB));

		entityB.setScaleCenter(0, 0);
		Assert.assertFalse(entityA.collidesWith(entityB));

		entityB.setScale(2);
		entityB.setScaleCenter(2, 2);
		Assert.assertTrue(entityA.collidesWith(entityB));
	}

	public void testCollidesWithScaledUneven() {
		final Entity entityA = new Entity(0, 0, 2, 2);
		final Entity entityB = new Entity(3, 0, 2, 2);
		Assert.assertFalse(entityA.collidesWith(entityB));

		entityB.setScaleX(2.1f);
		Assert.assertTrue(entityA.collidesWith(entityB));

		entityB.setScaleX(1);
		entityB.setScaleY(2.1f);
		Assert.assertFalse(entityA.collidesWith(entityB));
	}

	public void testCollidesWithRotated() {
		final Entity entityA = new Entity(0, 0, 4, 4);
		final Entity entityB = new Entity(5, 0, 4, 4);
		Assert.assertFalse(entityA.collidesWith(entityB));

		entityA.setRotation(45f);
		entityB.setRotation(45f);
		Assert.assertTrue(entityA.collidesWith(entityB));

		entityB.setRotation(90f);
		Assert.assertFalse(entityA.collidesWith(entityB));
	}

	public void testCollidesWithRotatedAroundCenter() {
		final Entity entityA = new Entity(0, 0, 2, 2);
		final Entity entityB = new Entity(3, 0, 2, 2);
		Assert.assertFalse(entityA.collidesWith(entityB));

		entityA.setRotationCenter(2, 2);
		entityA.setRotation(45f);
		Assert.assertTrue(entityA.collidesWith(entityB));

		entityA.setRotation(90f);
		Assert.assertTrue(entityA.collidesWith(entityB));

		entityA.setRotation(179.9f);
		Assert.assertTrue(entityA.collidesWith(entityB));

		entityA.setRotation(180.1f);
		Assert.assertFalse(entityA.collidesWith(entityB));
	}

	public void testCollidesWithRotatedAndScaled() {
		final Entity entityA = new Entity(0, 0, 2, 2);
		final Entity entityB = new Entity(3, 0, 2, 2);
		Assert.assertFalse(entityA.collidesWith(entityB));

		entityB.setRotation(45f);
		Assert.assertFalse(entityA.collidesWith(entityB));

		entityB.setScale(2f / FloatMath.sqrt(2f) + DELTA);
		Assert.assertTrue(entityA.collidesWith(entityB));

		entityB.setScale(2f / FloatMath.sqrt(2f) - DELTA);
		Assert.assertFalse(entityA.collidesWith(entityB));
	}


	public void testGetLocalCoordinatesSimple() {
		final Entity entity = new Entity(0, 0, 2, 2);
		AssertUtils.assertArrayEquals(new float[]{1, 1}, entity.convertSceneToLocalCoordinates(1, 1), DELTA);
	}

	public void testGetLocalCoordinatesNonOrigin() {
		final Entity entity = new Entity(10, 10, 2, 2);
		AssertUtils.assertArrayEquals(new float[]{1, 1}, entity.convertSceneToLocalCoordinates(11, 11), DELTA);
	}

	public void testGetLocalCoordinatesNonOriginRotated() {
		final Entity entity = new Entity(10, 10, 2, 2);
		entity.setRotation(90);
		AssertUtils.assertArrayEquals(new float[]{0, 2}, entity.convertSceneToLocalCoordinates(10, 10), DELTA);
	}

	public void testGetLocalCoordinatesNonOriginScaled() {
		final Entity entity = new Entity(10, 10, 2, 2);
		entity.setScale(0.5f);
		AssertUtils.assertArrayEquals(new float[]{0, 0}, entity.convertSceneToLocalCoordinates(10.5f, 10.5f), DELTA);
	}


	public void testGetSceneCenterCoordinatesSimple() {
		final Entity entity = new Entity(0, 0, 2, 2);
		AssertUtils.assertArrayEquals(new float[]{1, 1}, entity.getSceneCenterCoordinates(), DELTA);
	}

	public void testGetSceneCenterCoordinatesNonOrigin() {
		final Entity entity = new Entity(10, 10, 2, 2);
		AssertUtils.assertArrayEquals(new float[]{11, 11}, entity.getSceneCenterCoordinates(), DELTA);
	}

	public void testGetSceneCenterCoordinatesScaled() {
		final Entity entity = new Entity(10, 10, 2, 2);
		entity.setScale(2);
		AssertUtils.assertArrayEquals(new float[]{11, 11}, entity.getSceneCenterCoordinates(), DELTA);
	}

	public void testGetSceneCenterCoordinatesRotated() {
		final Entity entity = new Entity(10, 10, 2, 2);
		entity.setRotation(123);
		AssertUtils.assertArrayEquals(new float[]{11, 11}, entity.getSceneCenterCoordinates(), DELTA);
	}

	public void testGetSceneCenterCoordinatesScaledUneven() {
		final Entity entity = new Entity(10, 10, 2, 2);
		entity.setScale(2);
		entity.setScaleCenter(0, 0);
		AssertUtils.assertArrayEquals(new float[]{12, 12}, entity.getSceneCenterCoordinates(), DELTA);
	}

	public void testGetSceneCenterCoordinatesRotatedUneven() {
		final Entity entity = new Entity(10, 10, 2, 2);
		entity.setRotation(90);
		entity.setRotationCenter(0, 0);
		AssertUtils.assertArrayEquals(new float[]{9, 11}, entity.getSceneCenterCoordinates(), DELTA);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

	private void testConvertLocalToSceneCoordinates(final IEntity pEntity, final float pTestX, final float pTestY, final float pExpectedX, final float pExpectedY) {
		final float[] actual = pEntity.convertLocalToSceneCoordinates(pTestX, pTestY);

		AssertUtils.assertArrayEquals(new float[]{pExpectedX, pExpectedY}, actual, EntityTest.DELTA);
	}

	private void testConvertSceneToLocalCoordinates(final IEntity pEntity, final float pTestX, final float pTestY, final float pExpectedX, final float pExpectedY) {
		final float[] actual = pEntity.convertSceneToLocalCoordinates(pTestX, pTestY);

		AssertUtils.assertArrayEquals(new float[]{pExpectedX, pExpectedY}, actual, EntityTest.DELTA);
	}
}
