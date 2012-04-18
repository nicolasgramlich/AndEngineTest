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
	private static final float SQRT_2 = FloatMath.sqrt(2);

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

	public void testNoSize1() throws Exception {
		final IEntity entity = new Entity(0, 0);

		final float localX = 0;
		final float localY = 0;

		final float sceneX = 0;
		final float sceneY = 0;

		EntityTest.testCoordinateConversion(localX, localY, entity, sceneX, sceneY);
	}

	public void testNoSize2() throws Exception {
		final IEntity entity = new Entity(0, 0);

		final float localX = 1;
		final float localY = 1;

		final float sceneX = 1;
		final float sceneY = 1;

		EntityTest.testCoordinateConversion(localX, localY, entity, sceneX, sceneY);
	}

	public void testSimple() throws Exception {
		final IEntity entity = new Entity(0, 0, 2, 2);

		final float localX = 0;
		final float localY = 0;

		final float sceneX = -1;
		final float sceneY = -1;

		EntityTest.testCoordinateConversion(localX, localY, entity, sceneX, sceneY);

		EntityTest.testContains(0, 0, entity);
		EntityTest.testContains(-1 + EntityTest.DELTA, -1 + EntityTest.DELTA, entity);
		EntityTest.testContains(1 - EntityTest.DELTA, -1 + EntityTest.DELTA, entity);
		EntityTest.testContains(-1 + EntityTest.DELTA, 1 - EntityTest.DELTA, entity);
		EntityTest.testContains(1 - EntityTest.DELTA, 1 - EntityTest.DELTA, entity);
		
		EntityTest.testNotContains(-1 - EntityTest.DELTA, -1 - EntityTest.DELTA, entity);
		EntityTest.testNotContains(1 + EntityTest.DELTA, -1 - EntityTest.DELTA, entity);
		EntityTest.testNotContains(-1 - EntityTest.DELTA, 1 + EntityTest.DELTA, entity);
		EntityTest.testNotContains(1 + EntityTest.DELTA, 1 + EntityTest.DELTA, entity);
	}

	public void testRotation() throws Exception {
		final IEntity entity = new Entity(0, 0, 2, 2);
		entity.setRotation(90);

		final float localX = 0;
		final float localY = 0;

		final float sceneX = 1;
		final float sceneY = -1;

		EntityTest.testCoordinateConversion(localX, localY, entity, sceneX, sceneY);

		EntityTest.testContains(0, 0, entity);
		EntityTest.testContains(-1 + EntityTest.DELTA, -1 + EntityTest.DELTA, entity);
		EntityTest.testContains(1 - EntityTest.DELTA, -1 + EntityTest.DELTA, entity);
		EntityTest.testContains(-1 + EntityTest.DELTA, 1 - EntityTest.DELTA, entity);
		EntityTest.testContains(1 - EntityTest.DELTA, 1 - EntityTest.DELTA, entity);
		
		EntityTest.testNotContains(-1 - EntityTest.DELTA, -1 - EntityTest.DELTA, entity);
		EntityTest.testNotContains(1 + EntityTest.DELTA, -1 - EntityTest.DELTA, entity);
		EntityTest.testNotContains(-1 - EntityTest.DELTA, 1 + EntityTest.DELTA, entity);
		EntityTest.testNotContains(1 + EntityTest.DELTA, 1 + EntityTest.DELTA, entity);
	}

	public void testRotationCenter1() throws Exception {
		final IEntity entity = new Entity(0, 0, 2, 2);
		entity.setRotationCenter(0, 0);
		entity.setRotation(90);

		final float localX = 0;
		final float localY = 0;

		final float sceneX = -1;
		final float sceneY = -1;

		EntityTest.testCoordinateConversion(localX, localY, entity, sceneX, sceneY);
	}

	public void testRotationCenter2() throws Exception {
		final IEntity entity = new Entity(0, 0, 2, 2);
		entity.setRotationCenter(1, 0);
		entity.setRotation(180);

		final float localX = 0;
		final float localY = 0;

		final float sceneX = 3;
		final float sceneY = -1;

		EntityTest.testCoordinateConversion(localX, localY, entity, sceneX, sceneY);
	}

	public void testRotationCenterNonOrigin() throws Exception {
		final IEntity entity = new Entity(10, 10, 2, 2);
		entity.setRotationCenter(1, 0);
		entity.setRotation(180);

		final float localX = 0;
		final float localY = 0;

		final float sceneX = 13;
		final float sceneY = 9;

		EntityTest.testCoordinateConversion(localX, localY, entity, sceneX, sceneY);
	}

	public void testScaleCenter1() throws Exception {
		final IEntity entity = new Entity(0, 0, 2, 2);
		entity.setScaleCenter(1, 1);
		entity.setScale(2);

		final float localX = 0;
		final float localY = 0;

		final float sceneX = -3;
		final float sceneY = -3;

		EntityTest.testCoordinateConversion(localX, localY, entity, sceneX, sceneY);

		final float sceneCenterX = -1;
		final float sceneCenterY = -1;

		EntityTest.testGetSceneCenterCoordinates(sceneCenterX, sceneCenterY, entity);
	}

	public void testOffsetCenter() throws Exception {
		final IEntity entity = new Entity(0, 0, 2, 2);
		entity.setOffsetCenter(0, 0);

		final float localX = 0;
		final float localY = 0;

		final float sceneX = 0;
		final float sceneY = 0;

		EntityTest.testCoordinateConversion(localX, localY, entity, sceneX, sceneY);
	}

	public void testNonOriginScaled() throws Exception {
		final IEntity entity = new Entity(10, 10, 2, 2);
		entity.setScale(2);

		final float localX = 0;
		final float localY = 0;

		final float sceneX = 8;
		final float sceneY = 8;

		EntityTest.testCoordinateConversion(localX, localY, entity, sceneX, sceneY);
	}

	public void testOffsetCenterNonOriginScaled() throws Exception {
		final IEntity entity = new Entity(10, 10, 2, 2);
		entity.setOffsetCenter(0, 0);
		entity.setScale(2);

		final float localX = 0;
		final float localY = 0;

		final float sceneX = 9;
		final float sceneY = 9;

		EntityTest.testCoordinateConversion(localX, localY, entity, sceneX, sceneY);

		final float sceneCenterX = 11;
		final float sceneCenterY = 11;

		EntityTest.testGetSceneCenterCoordinates(sceneCenterX, sceneCenterY, entity);
	}

	public void testCollidesWithIdentical() {
		final IEntity entityA = new Entity(0, 0, 2, 2);
		final IEntity entityB = new Entity(0, 0, 2, 2);

		EntityTest.assertCollidesWith(entityA, entityB);
	}

	public void testCollidesWith() {
		final IEntity entityA = new Entity(0, 0, 2, 2);
		final IEntity entityB = new Entity(2 - DELTA, 0, 2, 2);

		EntityTest.assertCollidesWith(entityA, entityB);
	}

	public void testCollidesRotated() {
		final IEntity entityA = new Entity(0, 0, 2, 2);
		final IEntity entityB = new Entity(0, 0, 2, 2);

		entityA.setRotation(45);
		entityB.setRotation(45);

		entityB.setX(2 * SQRT_2 - DELTA);
		EntityTest.assertCollidesWith(entityA, entityB);

		entityB.setX(2 * SQRT_2 + DELTA);
		EntityTest.assertNotCollidesWith(entityA, entityB);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

	private static void assertCollidesWith(final IEntity pEntityA, final IEntity pEntityB) {
		Assert.assertTrue(pEntityA.collidesWith(pEntityB));
	}

	private static void assertNotCollidesWith(final IEntity pEntityA, final IEntity pEntityB) {
		Assert.assertFalse(pEntityA.collidesWith(pEntityB));
	}

	private static void testContains(final float pSceneX, final float pSceneY, final IEntity pEntity) {
		Assert.assertTrue(pEntity.contains(pSceneX, pSceneY));
	}

	private static void testNotContains(final float pSceneX, final float pSceneY, final IEntity pEntity) {
		Assert.assertFalse(pEntity.contains(pSceneX, pSceneY));
	}

	private static void testGetSceneCenterCoordinates(final float pSceneCenterX, final float pSceneCenterY, final IEntity pEntity) {
		final float[] expected = new float[]{pSceneCenterX, pSceneCenterY};
		final float[] actual = pEntity.getSceneCenterCoordinates();

		AssertUtils.assertArrayEquals(expected, actual, EntityTest.DELTA);
	}

	private static void testCoordinateConversion(final float pLocalX, final float pLocalY, final IEntity pEntity, final float pSceneX, final float pSceneY) {
		EntityTest.testConvertLocalToSceneCoordinates(pSceneX, pSceneY, pEntity, pLocalX, pLocalY);
		EntityTest.testConvertSceneToLocalCoordinates(pLocalX, pLocalY, pEntity, pSceneX, pSceneY);
	}

	private static void testConvertLocalToSceneCoordinates(final float pSceneX, final float pSceneY, final IEntity pEntity, final float pLocalX, final float pLocalY) {
		final float[] expected = new float[]{pSceneX, pSceneY};
		final float[] actual = pEntity.convertLocalToSceneCoordinates(pLocalX, pLocalY);

		AssertUtils.assertArrayEquals(expected, actual, EntityTest.DELTA);
	}

	private static void testConvertSceneToLocalCoordinates(final float pLocalX, final float pLocalY, final IEntity pEntity, final float pSceneX, final float pSceneY) {
		final float[] expected = new float[]{pLocalX, pLocalY};
		final float[] actual = pEntity.convertSceneToLocalCoordinates(pSceneX, pSceneY);

		AssertUtils.assertArrayEquals(expected, actual, EntityTest.DELTA);
	}
}
