package org.anddev.andengine.entity.layer;

import junit.framework.Assert;

import org.anddev.andengine.entity.IEntity;

/**
 * @author Nicolas Gramlich
 * @since 13:13:18 - 09.07.2010
 */
public class FixedCapacityLayerTest extends ILayerTest {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	@Override
	protected void setUp() throws Exception {
		this.mLayer = new FixedCapacityLayer(CAPACITY);
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

	public void testRemoveAndCheck() {
		final DummyEntity removeEntity = new DummyEntity();
		final DummyEntity checkEntity = new DummyEntity();
		this.mLayer.addEntity(removeEntity);
		this.mLayer.addEntity(new DummyEntity());
		this.mLayer.addEntity(new DummyEntity());
		this.mLayer.addEntity(checkEntity);

		this.mLayer.removeEntity(removeEntity);

		final IEntity checkedEntity = this.mLayer.getEntity(0);
		Assert.assertSame(checkEntity, checkedEntity);
	}
	
	public void testRemoveLastByIndexAtFullCapacity() {
		final DummyEntity removeEntity = new DummyEntity();
		this.mLayer.addEntity(new DummyEntity()); // 0
		this.mLayer.addEntity(new DummyEntity()); // 1
		this.mLayer.addEntity(new DummyEntity()); // 2
		this.mLayer.addEntity(new DummyEntity()); // 3
		this.mLayer.addEntity(new DummyEntity()); // 4
		this.mLayer.addEntity(new DummyEntity()); // 5
		this.mLayer.addEntity(new DummyEntity()); // 6
		this.mLayer.addEntity(new DummyEntity()); // 7
		this.mLayer.addEntity(new DummyEntity()); // 8
		this.mLayer.addEntity(removeEntity); // 8

		final IEntity removedEntity = this.mLayer.removeEntity(9);

		Assert.assertSame(removeEntity, removedEntity);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
