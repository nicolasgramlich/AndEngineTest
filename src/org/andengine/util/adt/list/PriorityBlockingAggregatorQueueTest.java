package org.andengine.util.adt.list;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import org.andengine.util.adt.queue.concurrent.PriorityBlockingAggregatorQueue;
import org.junit.Test;

import android.test.AndroidTestCase;

/**
 * @author Nicolas Gramlich
 * @since 22:31:38 - 16.09.2010
 */
public class PriorityBlockingAggregatorQueueTest extends AndroidTestCase {
	// ===========================================================
	// Constants
	// ===========================================================

	private static final int CAPACITY_HIGHEST = 1000;
	private static final int CAPACITY_HIGH = 100;
	private static final int CAPACITY_MEDIUM = 10;
	private static final int CAPACITY_LOW = 5;
	private static final int CAPACITY_LOWEST = 2;
	private static final int CAPACITY_TWO = 2;
	private static final int CAPACITY_ONE = 1;

	private static final int PRIORITY_HIGHEST = 0;
	private static final int PRIORITY_HIGH = PriorityBlockingAggregatorQueueTest.PRIORITY_HIGHEST + 1;
	private static final int PRIORITY_MEDIUM = PriorityBlockingAggregatorQueueTest.PRIORITY_HIGH + 1;
	private static final int PRIORITY_LOW = PriorityBlockingAggregatorQueueTest.PRIORITY_MEDIUM + 1;
	private static final int PRIORITY_LOWEST = PriorityBlockingAggregatorQueueTest.PRIORITY_LOW + 1;

	// ===========================================================
	// Fields
	// ===========================================================

	private PriorityBlockingAggregatorQueue<String> mPriorityAggregatorQueue;

	// ===========================================================
	// Constructors
	// ===========================================================

	@Override
	protected void setUp() throws Exception {
		this.mPriorityAggregatorQueue = new PriorityBlockingAggregatorQueue<String>(true);
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	public void testPut() throws InterruptedException {
		this.mPriorityAggregatorQueue.addQueue(PriorityBlockingAggregatorQueueTest.PRIORITY_HIGHEST, CAPACITY_ONE);

		this.mPriorityAggregatorQueue.put(PriorityBlockingAggregatorQueueTest.PRIORITY_HIGHEST, "A");

		Assert.assertEquals(1, this.mPriorityAggregatorQueue.size());
	}

	public void testPutIsBlocking() throws InterruptedException {
		final PriorityBlockingAggregatorQueue<String> paq = PriorityBlockingAggregatorQueueTest.this.mPriorityAggregatorQueue;
		this.mPriorityAggregatorQueue.addQueue(PriorityBlockingAggregatorQueueTest.PRIORITY_HIGHEST, CAPACITY_ONE);

		final CountDownLatch timeoutCountDownLatch = new CountDownLatch(1);
		final CountDownLatch putCountDownLatch = new CountDownLatch(1);

		new Thread(new FallibleRunnable() {
			@Override
			public void runFallible() throws InterruptedException {
				putCountDownLatch.await();

				paq.put(PriorityBlockingAggregatorQueueTest.PRIORITY_HIGHEST, "A");

				paq.put(PriorityBlockingAggregatorQueueTest.PRIORITY_HIGHEST, "B");

				timeoutCountDownLatch.countDown();
			}
		}).start();

		putCountDownLatch.countDown();

		Assert.assertFalse(timeoutCountDownLatch.await(100, TimeUnit.MILLISECONDS));
	}

	public void testPutAggregated() throws InterruptedException {
		final PriorityBlockingAggregatorQueue<String> paq = PriorityBlockingAggregatorQueueTest.this.mPriorityAggregatorQueue;

		final CountDownLatch startCountDownLatch = new CountDownLatch(CAPACITY_HIGHEST);
		final CountDownLatch putCountDownLatch = new CountDownLatch(1);
		final CountDownLatch completeCountDownLatch = new CountDownLatch(CAPACITY_HIGHEST);

		for (int i = 0; i < CAPACITY_HIGHEST; i++) {
			final int priority = i;
			this.mPriorityAggregatorQueue.addQueue(priority, CAPACITY_TWO);
			final String item = String.valueOf('A' + i);

			new Thread(new FallibleRunnable() {
				@Override
				public void runFallible() throws InterruptedException {
					startCountDownLatch.countDown();

					putCountDownLatch.await();

					paq.put(priority, item);
					paq.put(priority, item);

					completeCountDownLatch.countDown();
				}
			}).start();
		}

		startCountDownLatch.await();

		putCountDownLatch.countDown();

		completeCountDownLatch.await();

		for (int i = 0; i < CAPACITY_HIGHEST; i++) {
			final String expected = String.valueOf('A' + i);
			Assert.assertEquals(expected, paq.take());
			Assert.assertEquals(expected, paq.take());
		}
	}

	public void testOffer() throws InterruptedException {
		this.mPriorityAggregatorQueue.addQueue(PriorityBlockingAggregatorQueueTest.PRIORITY_HIGHEST, CAPACITY_ONE);

		final boolean success = this.mPriorityAggregatorQueue.offer(PriorityBlockingAggregatorQueueTest.PRIORITY_HIGHEST, "A");
		Assert.assertTrue(success);

		Assert.assertEquals(1, this.mPriorityAggregatorQueue.size());
	}

	public void testOfferOnFull() throws InterruptedException {
		this.mPriorityAggregatorQueue.addQueue(PriorityBlockingAggregatorQueueTest.PRIORITY_HIGHEST, CAPACITY_ONE);

		this.mPriorityAggregatorQueue.offer(PriorityBlockingAggregatorQueueTest.PRIORITY_HIGHEST, "A");
		final boolean success = this.mPriorityAggregatorQueue.offer(PriorityBlockingAggregatorQueueTest.PRIORITY_HIGHEST, "B");

		Assert.assertFalse(success);
	}

	public void testPeek() throws InterruptedException {
		this.mPriorityAggregatorQueue.addQueue(PriorityBlockingAggregatorQueueTest.PRIORITY_HIGHEST, CAPACITY_ONE);

		this.mPriorityAggregatorQueue.put(PriorityBlockingAggregatorQueueTest.PRIORITY_HIGHEST, "A");
		Assert.assertEquals("A", this.mPriorityAggregatorQueue.peek());

		Assert.assertEquals(1, this.mPriorityAggregatorQueue.size());
	}

	public void testPeekOnEmpty() throws InterruptedException {
		this.mPriorityAggregatorQueue.addQueue(PriorityBlockingAggregatorQueueTest.PRIORITY_HIGHEST, CAPACITY_ONE);

		Assert.assertNull(this.mPriorityAggregatorQueue.peek());

		Assert.assertEquals(0, this.mPriorityAggregatorQueue.size());
	}

	public void testPoll() throws InterruptedException {
		this.mPriorityAggregatorQueue.addQueue(PriorityBlockingAggregatorQueueTest.PRIORITY_HIGHEST, CAPACITY_ONE);

		this.mPriorityAggregatorQueue.put(PriorityBlockingAggregatorQueueTest.PRIORITY_HIGHEST, "A");
		Assert.assertEquals("A", this.mPriorityAggregatorQueue.poll());

		Assert.assertEquals(0, this.mPriorityAggregatorQueue.size());
	}

	public void testPollOnEmpty() throws InterruptedException {
		this.mPriorityAggregatorQueue.addQueue(PriorityBlockingAggregatorQueueTest.PRIORITY_HIGHEST, CAPACITY_ONE);

		Assert.assertNull(this.mPriorityAggregatorQueue.poll());

		Assert.assertEquals(0, this.mPriorityAggregatorQueue.size());
	}

	public void testTake() throws InterruptedException {
		this.mPriorityAggregatorQueue.addQueue(PriorityBlockingAggregatorQueueTest.PRIORITY_HIGHEST, CAPACITY_ONE);

		this.mPriorityAggregatorQueue.put(PriorityBlockingAggregatorQueueTest.PRIORITY_HIGHEST, "A");
		Assert.assertEquals("A", this.mPriorityAggregatorQueue.take());

		Assert.assertEquals(0, this.mPriorityAggregatorQueue.size());
	}

	public void testTakeIsBlockingOnEmpty() throws InterruptedException {
		final PriorityBlockingAggregatorQueue<String> paq = PriorityBlockingAggregatorQueueTest.this.mPriorityAggregatorQueue;
		this.mPriorityAggregatorQueue.addQueue(PriorityBlockingAggregatorQueueTest.PRIORITY_HIGHEST, CAPACITY_ONE);

		final CountDownLatch timeoutCountDownLatch = new CountDownLatch(1);
		final CountDownLatch pollCountDownLatch = new CountDownLatch(1);

		new Thread(new FallibleRunnable() {
			@Override
			public void runFallible() throws InterruptedException {
				pollCountDownLatch.await();

				paq.take();

				timeoutCountDownLatch.countDown();
			}
		}).start();

		pollCountDownLatch.countDown();

		Assert.assertFalse(timeoutCountDownLatch.await(100, TimeUnit.MILLISECONDS));
	}

	public void testPutWithTake() throws InterruptedException {
		final PriorityBlockingAggregatorQueue<String> paq = PriorityBlockingAggregatorQueueTest.this.mPriorityAggregatorQueue;
		this.mPriorityAggregatorQueue.addQueue(PriorityBlockingAggregatorQueueTest.PRIORITY_HIGHEST, CAPACITY_TWO);

		final CountDownLatch takeCountDownLatch = new CountDownLatch(1);
		final CountDownLatch putCountDownLatch = new CountDownLatch(1);
		final CountDownLatch completeCountDownLatch = new CountDownLatch(1);

		new Thread(new FallibleRunnable() {
			@Override
			public void runFallible() throws InterruptedException {
				putCountDownLatch.await();

				paq.put(PriorityBlockingAggregatorQueueTest.PRIORITY_HIGHEST, "A");

				takeCountDownLatch.countDown();

				paq.put(PriorityBlockingAggregatorQueueTest.PRIORITY_HIGHEST, "B");

				completeCountDownLatch.countDown();
			}
		}).start();

		putCountDownLatch.countDown();

		takeCountDownLatch.await();
		Assert.assertEquals("A", paq.take());

		completeCountDownLatch.await();

		Assert.assertEquals("B", paq.take());
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

	public abstract class FallibleRunnable implements Runnable {
		protected abstract void runFallible() throws Throwable;

		@Override
		public final void run() {
			try {
				this.runFallible();
			} catch (final Throwable t) {
				Assert.fail(t.toString());
			}
		}
	}
}
