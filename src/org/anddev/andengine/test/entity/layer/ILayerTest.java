package org.anddev.andengine.test.entity.layer;

import javax.microedition.khronos.opengles.GL10;

import junit.framework.TestCase;

import org.anddev.andengine.entity.IEntity;
import org.anddev.andengine.entity.layer.ILayer;

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
		assertEquals(0, this.mLayer.getEntityCount());
	}
	
	public void testAdd() {
		this.mLayer.addEntity(new DummyEntity());
		assertEquals(1, this.mLayer.getEntityCount());
	}
	
	public void testRemoveSingle() {
		final DummyEntity removeEntity = new DummyEntity();
		this.mLayer.addEntity(removeEntity);
		
		final boolean success = this.mLayer.removeEntity(removeEntity);
		
		assertTrue(success);
		assertEquals(0, this.mLayer.getEntityCount());
	}
	
	public void testRemoveLastByIndex() {
		final DummyEntity removeEntity = new DummyEntity();
		this.mLayer.addEntity(new DummyEntity());
		this.mLayer.addEntity(new DummyEntity());
		this.mLayer.addEntity(new DummyEntity());
		this.mLayer.addEntity(removeEntity);
		
		final IEntity removedEntity = this.mLayer.removeEntity(3);
		
		assertSame(removeEntity, removedEntity);
	}
	
	public void testRemoveFirstByIndex() {
		final DummyEntity removeEntity = new DummyEntity();
		this.mLayer.addEntity(removeEntity);
		this.mLayer.addEntity(new DummyEntity());
		this.mLayer.addEntity(new DummyEntity());
		this.mLayer.addEntity(new DummyEntity());
		
		final IEntity removedEntity = this.mLayer.removeEntity(0);
		
		assertSame(removeEntity, removedEntity);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
	
	protected static class DummyEntity implements IEntity {
		@Override
		public void onDraw(GL10 pGL) {}
		@Override
		public void onUpdate(float pSecondsElapsed) {}
		@Override
		public void reset() {}
	}
}
