package org.anddev.andengine.test.util;


import static junit.framework.Assert.assertEquals;

import org.anddev.andengine.util.StringUtils;

/**
 * @author Nicolas Gramlich
 * @since 19:03:16 - 03.04.2010
 */
public class StringUtilsTest {
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
		assertEquals(2, StringUtils.countOccurences("aabbccdd", "b"));
	}

	public void testCountOccurrenceEmpty() {
		assertEquals(0, StringUtils.countOccurences("", "b"));
	}

	public void testCountOccurrenceNone() {
		assertEquals(0, StringUtils.countOccurences("aaaaaaaa", "b"));
	}

	public void testCountOccurrenceBeginning() {
		assertEquals(1, StringUtils.countOccurences("baaaaa", "b"));
	}

	public void testCountOccurrenceBeginningAndEnd() {
		assertEquals(2, StringUtils.countOccurences("baaaaab", "b"));
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
