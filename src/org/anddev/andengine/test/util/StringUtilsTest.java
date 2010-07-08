package org.anddev.andengine.test.util;


import junit.framework.TestCase;

import org.anddev.andengine.util.StringUtils;

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
		assertEquals(2, StringUtils.countOccurrences("aabbccdd", 'b'));
	}

	public void testCountOccurrenceEmpty() {
		assertEquals(0, StringUtils.countOccurrences("", 'b'));
	}

	public void testCountOccurrenceNone() {
		assertEquals(0, StringUtils.countOccurrences("aaaaaaaa", 'b'));
	}

	public void testCountOccurrenceBeginning() {
		assertEquals(1, StringUtils.countOccurrences("baaaaa", 'b'));
	}

	public void testCountOccurrenceBeginningAndEnd() {
		assertEquals(2, StringUtils.countOccurrences("baaaaab", 'b'));
	}

	public void testSplitSimple() {
		final String[] split = StringUtils.split("a\nb", '\n');
		assertEquals(2, split.length);
		assertArrayEquals(new String[]{"a", "b"}, split);
	}

	public void testSplitSimple2() {
		final String[] split = StringUtils.split("a\nb\nc", '\n');
		assertEquals(3, split.length);
		assertArrayEquals(new String[]{"a", "b", "c"}, split);
	}

	public void testSplitSimple3() {
		final String[] split = StringUtils.split("a\nb\nc\n", '\n');
		assertEquals(4, split.length);
		assertArrayEquals(new String[]{"a", "b", "c", ""}, split);
	}
	
	public void testSplitEmpty() {
		final String[] split = StringUtils.split("", '\n');
		assertEquals(1, split.length);
		assertArrayEquals(new String[]{""}, split);
	}
	
	public void testSplitEmpty2() {
		final String[] split = StringUtils.split("\n", '\n');
		assertEquals(2, split.length);
		assertArrayEquals(new String[]{"", ""}, split);
	}
	
	public void testSplitEmptyLines() {
		final String[] split = StringUtils.split("\n\na", '\n');
		assertEquals(3, split.length);
		assertArrayEquals(new String[]{"", "", "a"}, split);
	}
	
	public void testSplitEmptyLines2() {
		final String[] split = StringUtils.split("\n\n\n", '\n');
		assertEquals(4, split.length);
		assertArrayEquals(new String[]{"", "", "", ""}, split);
	}
	
	private void assertArrayEquals(Object[] pArrayA, Object[] pArrayB) {
		if(pArrayA == null || pArrayB == null) {
			fail("One of the arrays was null.");
		}
		
		if(pArrayA.length != pArrayB.length) {
			fail("Arrays were not the same lenght.");
		}
		
		for(int i = 0; i < pArrayA.length; i++) {
			assertEquals("Index: i", pArrayA[i], pArrayB[i]);
		}
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
