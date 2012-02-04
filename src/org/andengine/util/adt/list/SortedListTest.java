package org.andengine.util.adt.list;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.andengine.util.adt.list.ShiftList;
import org.andengine.util.adt.list.SortedList;

/**
 * @author Nicolas Gramlich
 * @since 22:31:38 - 16.09.2010
 */
public class SortedListTest extends TestCase {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private SortedList<Integer> mIntegerSortedQueue;
	private SortedList<UniqueInteger> mUniqueIntegerSortedQueue;
	// ===========================================================
	// Constructors
	// ===========================================================

	@Override
	protected void setUp() throws Exception {
		this.mIntegerSortedQueue = new SortedList<Integer>(new ShiftList<Integer>(1));
		this.mUniqueIntegerSortedQueue = new SortedList<UniqueInteger>(new ShiftList<UniqueInteger>(1));
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

	public void testSingleItem() {
		this.mIntegerSortedQueue.add(1);

		Assert.assertEquals(1, this.mIntegerSortedQueue.removeFirst().intValue());
	}

	public void testTwoItemsCorrectOrder() {
		this.mIntegerSortedQueue.add(1);
		this.mIntegerSortedQueue.add(2);

		Assert.assertEquals(1, this.mIntegerSortedQueue.removeFirst().intValue());
		Assert.assertEquals(2, this.mIntegerSortedQueue.removeFirst().intValue());
	}

	public void testTwoItemsReverseOrder() {
		this.mIntegerSortedQueue.add(2);
		this.mIntegerSortedQueue.add(1);

		Assert.assertEquals(1, this.mIntegerSortedQueue.removeFirst().intValue());
		Assert.assertEquals(2, this.mIntegerSortedQueue.removeFirst().intValue());
	}

	public void testDuplicateItems() {
		this.mIntegerSortedQueue.add(2);
		this.mIntegerSortedQueue.add(2);

		Assert.assertEquals(2, this.mIntegerSortedQueue.removeFirst().intValue());
		Assert.assertEquals(2, this.mIntegerSortedQueue.removeFirst().intValue());
	}

	public void testManyItemsWithDuplicates() {
		this.mIntegerSortedQueue.add(1);
		this.mIntegerSortedQueue.add(2);
		this.mIntegerSortedQueue.add(3);
		this.mIntegerSortedQueue.add(2);

		Assert.assertEquals(1, this.mIntegerSortedQueue.removeFirst().intValue());
		Assert.assertEquals(2, this.mIntegerSortedQueue.removeFirst().intValue());
		Assert.assertEquals(2, this.mIntegerSortedQueue.removeFirst().intValue());
		Assert.assertEquals(3, this.mIntegerSortedQueue.removeFirst().intValue());
	}

	public void testRemoveSingle() {
		this.mIntegerSortedQueue.add(1);

		Assert.assertTrue(this.mIntegerSortedQueue.remove(new Integer(1)));
	}

	public void testRemoveMultiple() {
		this.mIntegerSortedQueue.add(1);
		this.mIntegerSortedQueue.add(2);
		this.mIntegerSortedQueue.add(3);

		Assert.assertTrue(this.mIntegerSortedQueue.remove(new Integer(1)));
		Assert.assertTrue(this.mIntegerSortedQueue.remove(new Integer(2)));
		Assert.assertTrue(this.mIntegerSortedQueue.remove(new Integer(3)));
	}

	public void testRemoveNonExistent() {
		this.mIntegerSortedQueue.add(1);
		this.mIntegerSortedQueue.add(2);
		this.mIntegerSortedQueue.add(3);

		Assert.assertFalse(this.mIntegerSortedQueue.remove(new Integer(4)));
	}

	public void testUniqueRemoval_1() {
		UniqueInteger zero = new UniqueInteger(0, "ZERO");
		UniqueInteger one_1 = new UniqueInteger(1, "ONE(1)");
		UniqueInteger one_2 = new UniqueInteger(1, "ONE(2)");
		
		this.mUniqueIntegerSortedQueue.add(zero);
		this.mUniqueIntegerSortedQueue.add(one_1);
		this.mUniqueIntegerSortedQueue.add(one_2);

		Assert.assertTrue(this.mUniqueIntegerSortedQueue.remove(one_1));
	}

	public void testUniqueRemoval_2() {
		UniqueInteger zero = new UniqueInteger(0, "ZERO");
		UniqueInteger one_1 = new UniqueInteger(1, "ONE(1)");
		UniqueInteger one_2 = new UniqueInteger(1, "ONE(2)");
		
		this.mUniqueIntegerSortedQueue.add(zero);
		this.mUniqueIntegerSortedQueue.add(one_1);
		this.mUniqueIntegerSortedQueue.add(one_2);

		Assert.assertTrue(this.mUniqueIntegerSortedQueue.remove(one_2));
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

	public class UniqueInteger implements Comparable<UniqueInteger> {
		// ===========================================================
		// Constants
		// ===========================================================

		// ===========================================================
		// Fields
		// ===========================================================

		private final int mValue;
		private final String pName;

		// ===========================================================
		// Constructors
		// ===========================================================

		public UniqueInteger(final int mValue, final String pName) {
			this.mValue = mValue;
			this.pName = pName;
		}

		// ===========================================================
		// Getter & Setter
		// ===========================================================

		// ===========================================================
		// Methods for/from SuperClass/Interfaces
		// ===========================================================

		@Override
		public int compareTo(final UniqueInteger pUniqueInteger) {
			return this.mValue - pUniqueInteger.mValue;
		}

		@Override
		public boolean equals(final Object pObject) {
			return this == pObject;
		}

		@Override
		public String toString() {
			return pName;
		}

		// ===========================================================
		// Methods
		// ===========================================================

		// ===========================================================
		// Inner and Anonymous Classes
		// ===========================================================
	}
}
