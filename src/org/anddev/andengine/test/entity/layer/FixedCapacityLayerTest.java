package org.anddev.andengine.test.entity.layer;

import org.anddev.andengine.entity.IEntity;
import org.anddev.andengine.entity.layer.FixedCapacityLayer;

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
		assertSame(checkEntity, checkedEntity);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
