package org.anddev.andengine.opengl.util;

import junit.framework.TestCase;

import org.anddev.andengine.util.AssertUtils;

/**
 * (c) Zynga 2011
 *
 * @author Nicolas Gramlich <ngramlich@zynga.com>
 * @since 10:50:39 - 08.11.2011
 */
public class GLHelperTest extends TestCase {
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

	// ===========================================================
	// Methods
	// ===========================================================

	public void testConvertARGB_8888toRGBA_8888() {
		final int[] actual = GLHelper.convertARGB_8888toRGBA_8888(new int[] { 0xFECB8754 });

		final int[] expected;
		if(GLHelper.IS_LITTLE_ENDIAN) {
			expected = new int[] { 0xFE5487CB };
		} else {
			expected = new int[] { 0xCB8754FE };
		}

		AssertUtils.assertArrayEquals(expected, actual);
	}

	public void testConvertARGB_8888toRGBA_4444() {
		final short[] actual = GLHelper.convertARGB_8888toRGBA_4444(new int[] { 0xFECB8754 });

		final short[] expected;
		if(GLHelper.IS_LITTLE_ENDIAN) {
			expected = new short[] { (short) 0x5FC8 };
		} else {
			expected = new short[] { (short) 0xC85F };
		}

		AssertUtils.assertArrayEquals(expected, actual);
	}

	public void testConvertARGB_8888toRGBA_565() {
		final short[] actual = GLHelper.convertARGB_8888toRGB_565(new int[] { 0xFECB8754 });

		final short[] expected;
		if(GLHelper.IS_LITTLE_ENDIAN) {
			expected = new short[] { (short) 0x2ACC };
		} else {
			expected = new short[] { (short) 0xCC2A };
		}

		AssertUtils.assertArrayEquals(expected, actual);
	}

	public void testConvertARGB_8888toA_8() {
		final byte[] actual = GLHelper.convertARGB_8888toA_8(new int[] { 0xFECB8754 });

		final byte[] expected = new byte[] { (byte) 0xFE };

		AssertUtils.assertArrayEquals(expected, actual);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
