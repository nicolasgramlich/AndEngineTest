package org.anddev.andengine.entity.layer;

import javax.microedition.khronos.opengles.GL10;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.entity.IEntity;

/**
 * @author Nicolas Gramlich
 * @since 13:09:28 - 09.07.2010
 */
public abstract class ILayerTest extends TestCase {
	// ===========================================================
	// Constants
	// ===========================================================

	protected static final int CAPACITY = 10;

	// ===========================================================
	// Fields
	// ===========================================================

	protected ILayer mLayer;

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

	public void testEmpty() {
		Assert.assertEquals(0, this.mLayer.getEntityCount());
	}

	public void testAdd() {
		this.mLayer.addEntity(new DummyEntity());
		Assert.assertEquals(1, this.mLayer.getEntityCount());
	}

	public void testRemoveSingle() {
		final DummyEntity removeEntity = new DummyEntity();
		this.mLayer.addEntity(removeEntity);

		final boolean success = this.mLayer.removeEntity(removeEntity);

		Assert.assertTrue(success);
		Assert.assertEquals(0, this.mLayer.getEntityCount());
	}

	public void testRemoveLastByIndex() {
		final DummyEntity removeEntity = new DummyEntity();
		this.mLayer.addEntity(new DummyEntity());
		this.mLayer.addEntity(new DummyEntity());
		this.mLayer.addEntity(new DummyEntity());
		this.mLayer.addEntity(removeEntity);

		final IEntity removedEntity = this.mLayer.removeEntity(3);

		Assert.assertSame(removeEntity, removedEntity);
	}

	public void testRemoveFirstByIndex() {
		final DummyEntity removeEntity = new DummyEntity();
		this.mLayer.addEntity(removeEntity);
		this.mLayer.addEntity(new DummyEntity());
		this.mLayer.addEntity(new DummyEntity());
		this.mLayer.addEntity(new DummyEntity());

		final IEntity removedEntity = this.mLayer.removeEntity(0);

		Assert.assertSame(removeEntity, removedEntity);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

	protected static class DummyEntity implements IEntity {
		@Override
		public void onDraw(final GL10 pGL, final Camera pCamera) {}
		@Override
		public void onUpdate(final float pSecondsElapsed) {}
		@Override
		public void reset() {}
		@Override
		public int getZIndex() { return 0; }
		@Override
		public void setZIndex(final int pZIndex) { }
	}
}
