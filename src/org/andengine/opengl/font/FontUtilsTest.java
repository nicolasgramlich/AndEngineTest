package org.andengine.opengl.font;


import java.util.ArrayList;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.andengine.opengl.font.exception.LetterNotFoundException;
import org.andengine.opengl.texture.ITexture;
import org.andengine.util.AssertUtils;

/**
 * (c) Zynga 2012
 *
 * @author Nicolas Gramlich <ngramlich@zynga.com>
 * @since 23:16:00 - 25.01.2012
 */
public class FontUtilsTest extends TestCase {
	// ===========================================================
	// Constants
	// ===========================================================

	protected static final int WIDTH = 10;
	protected static final int HEIGHT = 0;
	protected static final float OFFSET_X = 0;
	protected static final float OFFSET_Y = 0;
	protected static final float ADVANCE = 10;

	// ===========================================================
	// Fields
	// ===========================================================

	private final IFont mFont = new IFont() {
		@Override
		public void unload() {
		}

		@Override
		public void load() {
		}

		@Override
		public ITexture getTexture() {
			return null;
		}

		@Override
		public float getLineHeight() {
			return 0;
		}

		@Override
		public Letter getLetter(final char pChar) throws LetterNotFoundException {
			return new Letter(pChar, 0, 0, FontUtilsTest.WIDTH, FontUtilsTest.HEIGHT, FontUtilsTest.OFFSET_X, FontUtilsTest.OFFSET_Y, FontUtilsTest.ADVANCE, 0, 0, 0, 0);
		}
	};

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

	public void testMeasureSingleChar() {
		this.runMeasureTextTest("A", 10);
	}

	public void testMeasureMultipleChars() {
		this.runMeasureTextTest("AAA", 30);
	}

	public void testMeasureMultipleCharsSubstring() {
		this.runMeasureTextTest("AAA", 0, 1, 10);
	}
	
	public void testMeasureMultipleCharsInnerSpace() {
		this.runMeasureTextTest("A A", 0, 3, 30);
	}

	public void testMeasureMultipleCharsMultipleInnerSpaces() {
		this.runMeasureTextTest("A   A", 0, 5, 50);
	}

	
	public void testSplitSingleLineZeroWidth() {
		this.runSplitLinesTest("A", 0, "A");
	}

	public void testSplitSingleLine() {
		this.runSplitLinesTest("AAA", 100, "AAA");
		this.runSplitLinesTest("AAA", 30,  "AAA");
	}

	public void testSplitSingleLineMultiWordTrailingSpaced() {
		this.runSplitLinesTest("AAA AAA   ", 100, "AAA AAA");
	}

	public void testSplitSingleLineExactWidth() {
		this.runSplitLinesTest("AAA", 29,  "AAA");
	}

	public void testSplitSingleLineOverWidth() {
		this.runSplitLinesTest("AAA", 29,  "AAA");
	}

	public void testMultiLine() {
		this.runSplitLinesTest("AAA A", 30, "AAA", "A");
	}

	public void testMultiLineOverWidth() {
		this.runSplitLinesTest("AAA AAAA A AAA", 30, "AAA", "AAAA", "A", "AAA");
	}

	public void testSplitSingleLineWidthLeadingSpaces() {
		this.runSplitLinesTest("         A", 30, "A");
	}

	public void testSplitSingleLineTrailingSpaces() {
		this.runSplitLinesTest("A         ", 30, "A");
	}

	public void testSplitSingleLineLeadingAndTrailingSpaces() {
		this.runSplitLinesTest("         A         ", 30, "A");
	}

	public void testSplitMultiLineMultiWords() {
		this.runSplitLinesTest("AA AA AA AA AA", 50, "AA AA", "AA AA", "AA");
	}
	
	public void testSplitMultipleInnerSpaces() {
		this.runSplitLinesTest("A   A", 100, "A   A");
	}

	public void testSplitMultipleInnerSpacesExactWidth() {
		this.runSplitLinesTest("A   A", 50, "A   A");
	}
	
	public void testSplitMultipleInnerSpacesOverWidth() {
		this.runSplitLinesTest("A    A", 50, "A", "A");
	}
	
	public void testSplitMultipleLinesMultipleInnerSpacesOverWidth() {
		this.runSplitLinesTest("AAAAA  A", 50, "AAAAA", "A");
		this.runSplitLinesTest("AAAAA A", 50, "AAAAA", "A");
		this.runSplitLinesTest("AAAAA   A", 50, "AAAAA", "A");
		this.runSplitLinesTest(" AAAAA   A", 50, "AAAAA", "A");
		this.runSplitLinesTest("AAAAA   A ", 50, "AAAAA", "A");
		this.runSplitLinesTest(" AAAAA   A ", 50, "AAAAA", "A");
		this.runSplitLinesTest(" AAAAA A ", 50, "AAAAA", "A");
	}

	
	private void runSplitLinesTest(final String pText, final int pLineWidthMaximum, final String ... pExpectedLines) {
		final ArrayList<CharSequence> result = new ArrayList<CharSequence>();
		Assert.assertEquals(pExpectedLines.length, FontUtils.splitLines(this.mFont, pText, result, pLineWidthMaximum).size());
		AssertUtils.assertArrayEquals(pExpectedLines, result.toArray(new String[pExpectedLines.length]));
	}

	private void runMeasureTextTest(final String pText, final float pExpectedWidth) {
		Assert.assertEquals(pExpectedWidth, FontUtils.measureText(this.mFont, pText));
	}

	private void runMeasureTextTest(final String pText, final int pStart, final int pEnd, final float pExpectedWidth) {
		Assert.assertEquals(pExpectedWidth, FontUtils.measureText(this.mFont, pText, pStart, pEnd));
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
