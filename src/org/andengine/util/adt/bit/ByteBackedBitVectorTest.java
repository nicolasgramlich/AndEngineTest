package org.andengine.util.adt.bit;

import junit.framework.Assert;

import org.junit.Test;


/**
 * @author Nicolas Gramlich
 * @since 22:31:38 - 16.09.2010
 */
public class ByteBackedBitVectorTest extends BitVectorTest {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	protected ByteBackedBitVector createBitVector(final byte[] pData) {
		return new ByteBackedBitVector(pData);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	@Test
	public void testConstructorByteMultiple() {
		final ByteBackedBitVector bitVector = new ByteBackedBitVector(8);

		Assert.assertEquals(8, bitVector.getSize());
		Assert.assertEquals(1, bitVector.getByteSize());
	}

	@Test
	public void testConstructorNonByteMultiple() {
		final ByteBackedBitVector bitVector = new ByteBackedBitVector(7);

		Assert.assertEquals(7, bitVector.getSize());
		Assert.assertEquals(1, bitVector.getByteSize());
	}

	@Test
	public void testConstructorByteArray() {
		final byte[] bitData = { BitVectorTest.parseByte("11111111"), BitVectorTest.parseByte("11111111") };

		final ByteBackedBitVector bitVector = new ByteBackedBitVector(bitData);

		Assert.assertEquals(2 * Byte.SIZE, bitVector.getSize());
		Assert.assertEquals(2, bitVector.getByteSize());
	}

	@Test(expected = NullPointerException.class)
	public void testConstructorByteArrayNull() {
		new ByteBackedBitVector((byte[])null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructorByteArrayTooSmall() {
		final byte[] bitData = { BitVectorTest.parseByte("11111111"), BitVectorTest.parseByte("11111111") };

		new ByteBackedBitVector(1 + 2 * Byte.SIZE, bitData);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructorByteArrayTooLarge() {
		final byte[] bitData = { BitVectorTest.parseByte("11111111"), BitVectorTest.parseByte("11111111") };

		new ByteBackedBitVector(1 * Byte.SIZE, bitData);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
