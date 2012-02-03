package org.andengine.util.adt.list;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * @author Nicolas Gramlich
 * @since 22:31:38 - 16.09.2010
 */
public abstract class QueueTest extends TestCase {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private IQueue<Integer> mQueue;

	// ===========================================================
	// Constructors
	// ===========================================================

	@Override
	protected void setUp() throws Exception {
		this.mQueue = this.newQueue(1);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	protected abstract IQueue<Integer> newQueue(final int pInitialCapacity);

	// ===========================================================
	// Methods
	// ===========================================================

	public void testIsEmpty() {
		Assert.assertEquals(true, this.mQueue.isEmpty());
	}

	public void testIsNotEmpty() {
		this.mQueue.enter(1);
		Assert.assertEquals(false, this.mQueue.isEmpty());
	}

	public void testAdd() {
		this.mQueue.enter(1);
		Assert.assertEquals(1, this.mQueue.size());
	}

	public void testAddMany() {
		this.mQueue.enter(1);
		this.mQueue.enter(2);
		this.mQueue.enter(3);
		this.mQueue.enter(4);
		this.mQueue.enter(5);

		Assert.assertEquals(5, this.mQueue.size());
	}

	public void testAddAndRemove() {
		this.mQueue.enter(1);

		Assert.assertEquals(1, this.mQueue.size());

		Assert.assertEquals(1, this.mQueue.poll().intValue());

		Assert.assertEquals(0, this.mQueue.size());
	}

	public void testAddAndRemoveMany() {
		this.mQueue.enter(1);
		this.mQueue.enter(2);
		this.mQueue.enter(3);
		this.mQueue.enter(4);
		this.mQueue.enter(5);

		Assert.assertEquals(5, this.mQueue.size());

		Assert.assertEquals(1, this.mQueue.poll().intValue());
		Assert.assertEquals(2, this.mQueue.poll().intValue());
		Assert.assertEquals(3, this.mQueue.poll().intValue());
		Assert.assertEquals(4, this.mQueue.poll().intValue());
		Assert.assertEquals(5, this.mQueue.poll().intValue());

		Assert.assertEquals(0, this.mQueue.size());
	}

	public void testAddAndRemoveAndAddMany() {
		this.mQueue.enter(1);
		this.mQueue.enter(2);
		this.mQueue.enter(3);
		this.mQueue.enter(4);
		this.mQueue.enter(5);

		Assert.assertEquals(5, this.mQueue.size());

		Assert.assertEquals(1, this.mQueue.poll().intValue());
		Assert.assertEquals(2, this.mQueue.poll().intValue());
		Assert.assertEquals(3, this.mQueue.poll().intValue());
		Assert.assertEquals(4, this.mQueue.poll().intValue());
		Assert.assertEquals(5, this.mQueue.poll().intValue());

		Assert.assertEquals(0, this.mQueue.size());

		this.mQueue.enter(1);
		this.mQueue.enter(2);
		this.mQueue.enter(3);
		this.mQueue.enter(4);
		this.mQueue.enter(5);
	}

	public void testAutoShift() {
		this.mQueue.enter(1);
		this.mQueue.enter(2);

		Assert.assertEquals(2, this.mQueue.size());

		Assert.assertEquals(1, this.mQueue.poll().intValue());
		Assert.assertEquals(2, this.mQueue.poll().intValue());

		Assert.assertEquals(0, this.mQueue.size());

		this.mQueue.enter(1);
	}

	public void testShiftNulling1() {
		this.mQueue.enter(1);
		this.mQueue.enter(2);
		this.mQueue.enter(3);

		this.mQueue.poll();
		this.mQueue.poll();
		this.mQueue.poll();

		this.mQueue.enter(1);
		this.mQueue.enter(2);
	}

	public void testShiftNulling2() {
		this.mQueue = this.newQueue(3);

		this.mQueue.enter(1);
		this.mQueue.enter(2);
		this.mQueue.enter(3);

		this.mQueue.poll();
		this.mQueue.poll();
		this.mQueue.poll();

		this.mQueue.enter(1);
		this.mQueue.enter(2);
	}

	public void testShiftNulling3() {
		this.mQueue = this.newQueue(4);

		this.mQueue.enter(1);
		this.mQueue.enter(2);
		this.mQueue.enter(3);
		this.mQueue.enter(4);

		this.mQueue.poll();

		this.mQueue.enter(4);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
