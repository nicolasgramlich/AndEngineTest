package org.anddev.andengine.entity.scene.menu;

import junit.framework.Assert;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.Scene.IOnSceneTouchListener;
import org.anddev.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.anddev.andengine.entity.scene.menu.animator.DirectMenuAnimator;
import org.anddev.andengine.entity.scene.menu.item.IMenuItem;
import org.anddev.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

import android.os.SystemClock;
import android.test.AndroidTestCase;
import android.view.MotionEvent;

/**
 * @author Nicolas Gramlich
 * @since 00:07:21 - 14.05.2010
 */
public class MenuSceneTest  extends AndroidTestCase {
	// ===========================================================
	// Constants
	// ===========================================================

	private static final int MENU_TEST_ID = 1;

	private static final int CAMERA_WIDTH = 100;
	private static final int CAMERA_HEIGHT = 100;

	private static final float DELTA = 0.0001f;

	// ===========================================================
	// Fields
	// ===========================================================

	private Engine mEngine;
	private Camera mCamera;
	private Scene mScene;
	private MenuScene mMenuScene;

	private final Texture mTexture = new Texture(2, 2);

	// ===========================================================
	// Constructors
	// ===========================================================

	@Override
	public void setUp() throws Exception {
		this.mCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
		this.mEngine = new Engine(new EngineOptions(true, ScreenOrientation.PORTRAIT, new FillResolutionPolicy(), this.mCamera));
		this.mEngine.setSurfaceSize(CAMERA_WIDTH, CAMERA_HEIGHT);

		this.mScene = new Scene();

		this.mMenuScene = new MenuScene(this.mCamera);

		this.mScene.setChildSceneModal(this.mMenuScene);

		this.mEngine.setScene(this.mScene);

		this.mEngine.start();
	}

	@Override
	public void tearDown() throws Exception {
		this.mEngine.stop();
	}

	// ===========================================================
	// Test-Methods
	// ===========================================================

	public void testMenuSceneTouchCenter() throws Exception {
		final int surfaceTouchX = 50;
		final int surfaceTouchY = 50;

		final float expectedX = 50;
		final float expectedY = 50;

		this.testSceneTouchWorker(this.mMenuScene, surfaceTouchX, surfaceTouchY, expectedX, expectedY);
	}

	public void testMenuSceneTouchEdge() throws Exception {
		final int surfaceTouchX = 0;
		final int surfaceTouchY = 100;

		final float expectedX = 0;
		final float expectedY = 100;

		this.testSceneTouchWorker(this.mMenuScene, surfaceTouchX, surfaceTouchY, expectedX, expectedY);
	}

	public void testMenuSceneTouchOffsetCamera() throws Exception {
		this.mCamera.setCenter(-1000, -1000);

		final int surfaceTouchX = 50;
		final int surfaceTouchY = 50;

		final float expectedX = 50;
		final float expectedY = 50;

		this.testSceneTouchWorker(this.mMenuScene, surfaceTouchX, surfaceTouchY, expectedX, expectedY);
	}

	public void testSubMenuSceneTouch() throws Exception {
		final int surfaceTouchX = 50;
		final int surfaceTouchY = 50;

		final float expectedX = 50;
		final float expectedY = 50;

		this.testSceneTouchWorker(this.mMenuScene, surfaceTouchX, surfaceTouchY, expectedX, expectedY);

		final MenuScene subMenuScene = new MenuScene(this.mCamera);

		this.mMenuScene.setChildSceneModal(subMenuScene);

		this.testSceneTouchWorker(subMenuScene, surfaceTouchX, surfaceTouchY, expectedX, expectedY);
	}

	public void testSubMenuSceneTouchOffsetCamera() throws Exception {
		final int surfaceTouchX = 50;
		final int surfaceTouchY = 50;

		final float expectedX = 50;
		final float expectedY = 50;

		this.mCamera.setCenter(-1000, -1000);

		this.testSceneTouchWorker(this.mMenuScene, surfaceTouchX, surfaceTouchY, expectedX, expectedY);

		final MenuScene subMenuScene = new MenuScene(this.mCamera);

		this.mMenuScene.setChildSceneModal(subMenuScene);

		this.testSceneTouchWorker(subMenuScene, surfaceTouchX, surfaceTouchY, expectedX, expectedY);
	}

	public void testMenuItemTouch() throws Exception {
		final int surfaceTouchX = 50;
		final int surfaceTouchY = 50;

		this.mMenuScene.addMenuItem(new SpriteMenuItem(MENU_TEST_ID, new TextureRegion(this.mTexture, 0, 0, 50, 50)));
		this.mMenuScene.setMenuAnimator(new DirectMenuAnimator());
		this.mMenuScene.buildAnimations();

		Assert.assertTrue(this.testMenuItemTouchWorker(this.mScene, this.mMenuScene, surfaceTouchX, surfaceTouchY, MENU_TEST_ID));
	}

	public void testMenuItemTouchEdge() throws Exception {
		final int surfaceTouchX = 75;
		final int surfaceTouchY = 75;

		this.mMenuScene.addMenuItem(new SpriteMenuItem(MENU_TEST_ID, new TextureRegion(this.mTexture, 0, 0, 50, 50)));
		this.mMenuScene.setMenuAnimator(new DirectMenuAnimator());
		this.mMenuScene.buildAnimations();

		Assert.assertTrue(this.testMenuItemTouchWorker(this.mScene, this.mMenuScene, surfaceTouchX, surfaceTouchY, MENU_TEST_ID));
	}

	public void testMenuItemTouchOutside() throws Exception {
		final int surfaceTouchX = 76;
		final int surfaceTouchY = 76;

		this.mMenuScene.addMenuItem(new SpriteMenuItem(MENU_TEST_ID, new TextureRegion(this.mTexture, 0, 0, 50, 50)));
		this.mMenuScene.setMenuAnimator(new DirectMenuAnimator());
		this.mMenuScene.buildAnimations();

		Assert.assertFalse(this.testMenuItemTouchWorker(this.mScene, this.mMenuScene, surfaceTouchX, surfaceTouchY, MENU_TEST_ID));
	}

	public void testSubMenuItemTouch() throws Exception {
		final int surfaceTouchX = 50;
		final int surfaceTouchY = 50;

		final MenuScene subMenuScene = new MenuScene(this.mCamera);

		subMenuScene.addMenuItem(new SpriteMenuItem(MENU_TEST_ID, new TextureRegion(this.mTexture, 0, 0, 50, 50)));
		subMenuScene.setMenuAnimator(new DirectMenuAnimator());
		subMenuScene.buildAnimations();

		this.mMenuScene.setChildSceneModal(subMenuScene);

		Assert.assertTrue(this.testMenuItemTouchWorker(this.mScene, subMenuScene, surfaceTouchX, surfaceTouchY, MENU_TEST_ID));
	}

	public void testSubMenuItemTouchOffsetCamera() throws Exception {
		final int surfaceTouchX = 50;
		final int surfaceTouchY = 50;

		this.mCamera.setCenter(-1000, -1000);

		final MenuScene subMenuScene = new MenuScene(this.mCamera);

		subMenuScene.addMenuItem(new SpriteMenuItem(MENU_TEST_ID, new TextureRegion(this.mTexture, 0, 0, 50, 50)));
		subMenuScene.setMenuAnimator(new DirectMenuAnimator());
		subMenuScene.buildAnimations();

		this.mMenuScene.setChildSceneModal(subMenuScene);

		Assert.assertTrue(this.testMenuItemTouchWorker(this.mScene, subMenuScene, surfaceTouchX, surfaceTouchY, MENU_TEST_ID));
	}

	public void testSubMenuItemTouchOutsideOffsetCamera() throws Exception {
		final int surfaceTouchX = 76;
		final int surfaceTouchY = 76;

		this.mCamera.setCenter(-1000, -1000);

		final MenuScene subMenuScene = new MenuScene(this.mCamera);

		subMenuScene.addMenuItem(new SpriteMenuItem(MENU_TEST_ID, new TextureRegion(this.mTexture, 0, 0, 50, 50)));
		subMenuScene.setMenuAnimator(new DirectMenuAnimator());
		subMenuScene.buildAnimations();

		this.mMenuScene.setChildSceneModal(subMenuScene);

		Assert.assertFalse(this.testMenuItemTouchWorker(this.mScene, subMenuScene, surfaceTouchX, surfaceTouchY, MENU_TEST_ID));
	}

	// ===========================================================
	// Methods
	// ===========================================================

	private void testSceneTouchWorker(final Scene pScene, final int pSurfaceTouchX, final int pSurfaceTouchY, final float pExpectedX, final float pExpectedY) {
		pScene.setOnSceneTouchListener(new IOnSceneTouchListener() {
			@Override
			public boolean onSceneTouchEvent(final Scene pScene, final TouchEvent pSceneTouchEvent) {
				final float actualX = pSceneTouchEvent.getX();
				final float actualY = pSceneTouchEvent.getY();
				Assert.assertEquals(pExpectedX, actualX, DELTA);
				Assert.assertEquals(pExpectedY, actualY, DELTA);
				return true;
			}
		});

		final long uptimeMillis = SystemClock.uptimeMillis();

		final boolean result = this.mEngine.onTouch(null, MotionEvent.obtain(uptimeMillis, uptimeMillis, MotionEvent.ACTION_DOWN, pSurfaceTouchX, pSurfaceTouchY, 0));

		Assert.assertTrue(result);
	}

	private boolean testMenuItemTouchWorker(final Scene pScene, final MenuScene pMenuScene, final int pSurfaceTouchX, final int pSurfaceTouchY, final int pMenuItemID) {
		pMenuScene.setOnMenuItemClickListener(new IOnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClicked(final MenuScene pMenuScene, final IMenuItem pMenuItem, final float pMenuItemLocalX, final float pMenuItemLocalY) {
				Assert.assertSame(pMenuItemID, pMenuItem.getID());
				return true;
			}
		});

		final long uptimeMillis = SystemClock.uptimeMillis();

		return this.mEngine.onTouch(null, MotionEvent.obtain(uptimeMillis, uptimeMillis, MotionEvent.ACTION_DOWN, pSurfaceTouchX, pSurfaceTouchY, 0));
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
