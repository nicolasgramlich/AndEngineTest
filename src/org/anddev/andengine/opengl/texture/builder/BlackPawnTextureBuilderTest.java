package org.anddev.andengine.opengl.texture.builder;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.anddev.andengine.opengl.texture.builder.BlackPawnTextureBuilder.Node;
import org.anddev.andengine.opengl.texture.source.ITextureSource;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;

/**
 * @author Nicolas Gramlich
 * @since 16:08:32 - 12.08.2010
 */
public class BlackPawnTextureBuilderTest extends TestCase {
	// ===========================================================
	// Constants
	// ===========================================================

	private static final int SPACING_NONE = 0;
	private static final int SPACING_ONE = 1;

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

	public void testSingle() {
		final Node node = new Node(0, 0, 10, 10);
		final Node inserted = node.insert(new DummyTextureSource(4, 2), 10, 10, SPACING_NONE);

		this.assertNodeRect(inserted, 0, 0, 4, 2);
	}

	public void testDouble() {
		final Node node = new Node(0, 0, 10, 10);
		node.insert(new DummyTextureSource(4, 2), 10, 10, SPACING_NONE);

		final Node inserted = node.insert(new DummyTextureSource(4, 2), 10, 10, SPACING_NONE);

		this.assertNodeRect(inserted, 4, 0, 4, 2);
	}

	public void testTriple() {
		final Node node = new Node(0, 0, 10, 10);
		node.insert(new DummyTextureSource(4, 2), 10, 10, SPACING_NONE);
		node.insert(new DummyTextureSource(4, 2), 10, 10, SPACING_NONE);

		final Node inserted = node.insert(new DummyTextureSource(4, 2), 10, 10, SPACING_NONE);

		this.assertNodeRect(inserted, 0, 2, 4, 2);
	}

	public void testQuadrupel() {
		final Node node = new Node(0, 0, 10, 10);
		node.insert(new DummyTextureSource(4, 2), 10, 10, SPACING_NONE);
		node.insert(new DummyTextureSource(4, 2), 10, 10, SPACING_NONE);
		node.insert(new DummyTextureSource(4, 2), 10, 10, SPACING_NONE);

		final Node inserted = node.insert(new DummyTextureSource(2, 2), 10, 10, SPACING_NONE);

		this.assertNodeRect(inserted, 8, 0, 2, 2);
	}

	public void testQuadrupel2() {
		final Node node = new Node(0, 0, 10, 10);
		node.insert(new DummyTextureSource(4, 2), 10, 10, SPACING_NONE);
		node.insert(new DummyTextureSource(4, 2), 10, 10, SPACING_NONE);
		node.insert(new DummyTextureSource(4, 2), 10, 10, SPACING_NONE);

		final Node inserted = node.insert(new DummyTextureSource(2, 3), 10, 10, SPACING_NONE);

		this.assertNodeRect(inserted, 0, 4, 2, 3);
	}

	public void testQuintupel() {
		final Node node = new Node(0, 0, 10, 10);
		node.insert(new DummyTextureSource(4, 2), 10, 10, SPACING_NONE);
		node.insert(new DummyTextureSource(4, 2), 10, 10, SPACING_NONE);
		node.insert(new DummyTextureSource(4, 2), 10, 10, SPACING_NONE);
		node.insert(new DummyTextureSource(2, 3), 10, 10, SPACING_NONE);

		final Node inserted = node.insert(new DummyTextureSource(2, 3), 10, 10, SPACING_NONE);

		this.assertNodeRect(inserted, 2, 4, 2, 3);
	}

	public void testQuintupel2() {
		final Node node = new Node(0, 0, 10, 10);
		node.insert(new DummyTextureSource(4, 2), 10, 10, SPACING_NONE);
		node.insert(new DummyTextureSource(4, 2), 10, 10, SPACING_NONE);
		node.insert(new DummyTextureSource(4, 2), 10, 10, SPACING_NONE);
		node.insert(new DummyTextureSource(2, 3), 10, 10, SPACING_NONE);

		final Node inserted = node.insert(new DummyTextureSource(2, 8), 10, 10, SPACING_NONE);

		this.assertNodeRect(inserted, 4, 2, 2, 8);
	}

	public void testQuintupel3() {
		final Node node = new Node(0, 0, 10, 10);
		node.insert(new DummyTextureSource(4, 2), 10, 10, SPACING_NONE);
		node.insert(new DummyTextureSource(4, 2), 10, 10, SPACING_NONE);
		node.insert(new DummyTextureSource(4, 2), 10, 10, SPACING_NONE);
		node.insert(new DummyTextureSource(2, 3), 10, 10, SPACING_NONE);

		final Node inserted = node.insert(new DummyTextureSource(2, 10), 10, 10, SPACING_NONE);

		Assert.assertNull(inserted);
	}

	public void testFullSize() {
		final Node node = new Node(0, 0, 10, 10);

		final Node inserted = node.insert(new DummyTextureSource(10, 10), 10, 10, SPACING_NONE);

		this.assertNodeRect(inserted, 0, 0, 10, 10);
	}

	public void testPerfectFit() {
		final Node node = new Node(0, 0, 10, 10);
		node.insert(new DummyTextureSource(5, 5), 10, 10, SPACING_NONE);
		node.insert(new DummyTextureSource(5, 5), 10, 10, SPACING_NONE);
		node.insert(new DummyTextureSource(5, 5), 10, 10, SPACING_NONE);

		final Node inserted = node.insert(new DummyTextureSource(5, 5), 10, 10, SPACING_NONE);

		this.assertNodeRect(inserted, 5, 5, 5, 5);
	}

	public void testPerfectFit2() {
		final Node node = new Node(0, 0, 10, 10);
		node.insert(new DummyTextureSource(4, 4), 10, 10, SPACING_ONE);
		node.insert(new DummyTextureSource(4, 4), 10, 10, SPACING_ONE);
		node.insert(new DummyTextureSource(4, 4), 10, 10, SPACING_ONE);

		final Node inserted = node.insert(new DummyTextureSource(5, 5), 10, 10, SPACING_ONE);

		this.assertNodeRect(inserted, 5, 5, 5, 5);
	}

	public void testNoFitDueToSpacing() {
		final Node node = new Node(0, 0, 10, 10);
		node.insert(new DummyTextureSource(5, 5), 10, 10, SPACING_ONE);

		final Node inserted = node.insert(new DummyTextureSource(5, 5), 10, 10, SPACING_ONE);

		Assert.assertNull(inserted);
	}

	private void assertNodeRect(final Node pNode, final int pLeft, final int pTop, final int pWidth, final int pHeight) {
		Assert.assertEquals(pLeft, pNode.getRect().getLeft());
		Assert.assertEquals(pTop, pNode.getRect().getTop());
		Assert.assertEquals(pWidth, pNode.getRect().getWidth());
		Assert.assertEquals(pHeight, pNode.getRect().getHeight());
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

	protected static class DummyTextureSource implements ITextureSource {
		private final int mWidth;
		private final int mHeight;

		public DummyTextureSource(final int pWidth, final int pHeight) {
			this.mWidth = pWidth;
			this.mHeight = pHeight;
		}

		@Override
		public int getHeight() {
			return this.mHeight;
		}

		@Override
		public int getWidth() {
			return this.mWidth;
		}

		@Override
		public Bitmap onLoadBitmap(final Config pBitmapConfig) {
			return null;
		}

		@Override
		public DummyTextureSource clone() {
			return new DummyTextureSource(this.mWidth, this.mHeight);
		}
	}
}
