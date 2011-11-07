package org.anddev.andengine.util;


import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * @author Nicolas Gramlich
 * @since 19:03:16 - 03.04.2010
 */
public class StringUtilsTest extends TestCase {
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

	public void testCountOccurrenceFollowUp() {
		Assert.assertEquals(2, StringUtils.countOccurrences("aabbccdd", 'b'));
	}

	public void testCountOccurrenceEmpty() {
		Assert.assertEquals(0, StringUtils.countOccurrences("", 'b'));
	}

	public void testCountOccurrenceNone() {
		Assert.assertEquals(0, StringUtils.countOccurrences("aaaaaaaa", 'b'));
	}

	public void testCountOccurrenceBeginning() {
		Assert.assertEquals(1, StringUtils.countOccurrences("baaaaa", 'b'));
	}

	public void testCountOccurrenceBeginningAndEnd() {
		Assert.assertEquals(2, StringUtils.countOccurrences("baaaaab", 'b'));
	}

	public void testSplitSimple() {
		final String[] split = StringUtils.split("a\nb", '\n');
		Assert.assertEquals(2, split.length);
		this.assertArrayEquals(new String[]{"a", "b"}, split);
	}

	public void testSplitSimple2() {
		final String[] split = StringUtils.split("a\nb\nc", '\n');
		Assert.assertEquals(3, split.length);
		this.assertArrayEquals(new String[]{"a", "b", "c"}, split);
	}

	public void testSplitSimple3() {
		final String[] split = StringUtils.split("a\nb\nc\n", '\n');
		Assert.assertEquals(4, split.length);
		this.assertArrayEquals(new String[]{"a", "b", "c", ""}, split);
	}

	public void testSplitEmpty() {
		final String[] split = StringUtils.split("", '\n');
		Assert.assertEquals(1, split.length);
		this.assertArrayEquals(new String[]{""}, split);
	}

	public void testSplitEmpty2() {
		final String[] split = StringUtils.split("\n", '\n');
		Assert.assertEquals(2, split.length);
		this.assertArrayEquals(new String[]{"", ""}, split);
	}

	public void testSplitEmptyLines() {
		final String[] split = StringUtils.split("\n\na", '\n');
		Assert.assertEquals(3, split.length);
		this.assertArrayEquals(new String[]{"", "", "a"}, split);
	}

	public void testSplitEmptyLines2() {
		final String[] split = StringUtils.split("\n\n\n", '\n');
		Assert.assertEquals(4, split.length);
		this.assertArrayEquals(new String[]{"", "", "", ""}, split);
	}

	private void assertArrayEquals(final Object[] pArrayA, final Object[] pArrayB) {
		if(pArrayA == null || pArrayB == null) {
			Assert.fail("One of the arrays was null.");
		}

		if(pArrayA.length != pArrayB.length) {
			Assert.fail("Arrays were not the same lenght.");
		}

		for(int i = 0; i < pArrayA.length; i++) {
			Assert.assertEquals("Index: i", pArrayA[i], pArrayB[i]);
		}
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
