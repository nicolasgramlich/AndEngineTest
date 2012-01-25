package org.andengine.util.list;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * @author Nicolas Gramlich
 * @since 22:31:38 - 16.09.2010
 */
public class ConcurrentShiftQueueTest extends TestCase {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private ConcurrentShiftQueue<Integer> mConcurrentShiftQueue;

	// ===========================================================
	// Constructors
	// ===========================================================

	@Override
	protected void setUp() throws Exception {
		this.mConcurrentShiftQueue = new ConcurrentShiftQueue<Integer>(1);
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

	public void testIsEmpty() {
		Assert.assertEquals(true, this.mConcurrentShiftQueue.isEmpty());
	}

	public void testIsNotEmpty() {
		this.mConcurrentShiftQueue.enter(1);
		Assert.assertEquals(false, this.mConcurrentShiftQueue.isEmpty());
	}

	public void testAdd() {
		this.mConcurrentShiftQueue.enter(1);
		Assert.assertEquals(1, this.mConcurrentShiftQueue.size());
	}

	public void testAddMany() {
		this.mConcurrentShiftQueue.enter(1);
		this.mConcurrentShiftQueue.enter(2);
		this.mConcurrentShiftQueue.enter(3);
		this.mConcurrentShiftQueue.enter(4);
		this.mConcurrentShiftQueue.enter(5);

		Assert.assertEquals(5, this.mConcurrentShiftQueue.size());
	}

	public void testAddAndRemove() {
		this.mConcurrentShiftQueue.enter(1);

		Assert.assertEquals(1, this.mConcurrentShiftQueue.size());

		Assert.assertEquals(1, this.mConcurrentShiftQueue.poll().intValue());

		Assert.assertEquals(0, this.mConcurrentShiftQueue.size());
	}

	public void testAddAndRemoveMany() {
		this.mConcurrentShiftQueue.enter(1);
		this.mConcurrentShiftQueue.enter(2);
		this.mConcurrentShiftQueue.enter(3);
		this.mConcurrentShiftQueue.enter(4);
		this.mConcurrentShiftQueue.enter(5);

		Assert.assertEquals(5, this.mConcurrentShiftQueue.size());

		Assert.assertEquals(1, this.mConcurrentShiftQueue.poll().intValue());
		Assert.assertEquals(2, this.mConcurrentShiftQueue.poll().intValue());
		Assert.assertEquals(3, this.mConcurrentShiftQueue.poll().intValue());
		Assert.assertEquals(4, this.mConcurrentShiftQueue.poll().intValue());
		Assert.assertEquals(5, this.mConcurrentShiftQueue.poll().intValue());

		Assert.assertEquals(0, this.mConcurrentShiftQueue.size());
	}

	public void testAddAndRemoveAndAddMany() {
		this.mConcurrentShiftQueue.enter(1);
		this.mConcurrentShiftQueue.enter(2);
		this.mConcurrentShiftQueue.enter(3);
		this.mConcurrentShiftQueue.enter(4);
		this.mConcurrentShiftQueue.enter(5);
		
		Assert.assertEquals(5, this.mConcurrentShiftQueue.size());
		
		Assert.assertEquals(1, this.mConcurrentShiftQueue.poll().intValue());
		Assert.assertEquals(2, this.mConcurrentShiftQueue.poll().intValue());
		Assert.assertEquals(3, this.mConcurrentShiftQueue.poll().intValue());
		Assert.assertEquals(4, this.mConcurrentShiftQueue.poll().intValue());
		Assert.assertEquals(5, this.mConcurrentShiftQueue.poll().intValue());
		
		Assert.assertEquals(0, this.mConcurrentShiftQueue.size());

		this.mConcurrentShiftQueue.enter(1);
		this.mConcurrentShiftQueue.enter(2);
		this.mConcurrentShiftQueue.enter(3);
		this.mConcurrentShiftQueue.enter(4);
		this.mConcurrentShiftQueue.enter(5);
	}

	public void testAutoShift() {
		this.mConcurrentShiftQueue.enter(1);
		this.mConcurrentShiftQueue.enter(2);
		
		Assert.assertEquals(2, this.mConcurrentShiftQueue.size());
		
		Assert.assertEquals(1, this.mConcurrentShiftQueue.poll().intValue());
		Assert.assertEquals(2, this.mConcurrentShiftQueue.poll().intValue());
		
		Assert.assertEquals(0, this.mConcurrentShiftQueue.size());
		
		this.mConcurrentShiftQueue.enter(1);
	}

	public void testShiftNulling1() {
		this.mConcurrentShiftQueue.enter(1);
		this.mConcurrentShiftQueue.enter(2);
		this.mConcurrentShiftQueue.enter(3);
		
		this.mConcurrentShiftQueue.poll();
		this.mConcurrentShiftQueue.poll();
		this.mConcurrentShiftQueue.poll();

		this.mConcurrentShiftQueue.enter(1);
		this.mConcurrentShiftQueue.enter(2);
	}

	public void testShiftNulling2() {
		this.mConcurrentShiftQueue = new ConcurrentShiftQueue<Integer>(3);

		this.mConcurrentShiftQueue.enter(1);
		this.mConcurrentShiftQueue.enter(2);
		this.mConcurrentShiftQueue.enter(3);
		
		this.mConcurrentShiftQueue.poll();
		this.mConcurrentShiftQueue.poll();
		this.mConcurrentShiftQueue.poll();
		
		this.mConcurrentShiftQueue.enter(1);
		this.mConcurrentShiftQueue.enter(2);
	}

	public void testShiftNulling3() {
		this.mConcurrentShiftQueue = new ConcurrentShiftQueue<Integer>(4);
		
		this.mConcurrentShiftQueue.enter(1);
		this.mConcurrentShiftQueue.enter(2);
		this.mConcurrentShiftQueue.enter(3);
		this.mConcurrentShiftQueue.enter(4);
		
		this.mConcurrentShiftQueue.poll();
		
		this.mConcurrentShiftQueue.enter(4);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
