package org.andengine.util.adt.dictionary;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Arrays;

import org.andengine.test.R;
import org.andengine.util.StreamUtils;
import org.andengine.util.exception.MethodNotYetImplementedException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import android.test.AndroidTestCase;


/**
 * @author Nicolas Gramlich
 * @since Nov 20, 2012
 */
public class DictionaryTest extends AndroidTestCase {
	// ===========================================================
	// Constants
	// ===========================================================

	private static final String[] TEST_ENTRIES_SINGLE = new String[] { "HALLOWEEN" };

	private static final String[] TEST_ENTRIES_SIMPLE = new String[] { "SHELL", "HE", "HELL", "HELLO" };

	private static final String[] TEST_ENTRIES_COMPLEX = new String[] { "HELLO", "WORLD", "DOG", "ASTRONOMER", "MATH", "MATHEMATICIAN", "RUSSIAN", "RUSSIA", "HEART", "HEARTACE", "ACE", "ICE", "ICECREAM", "SENSE", "SENSITIVE" };

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {

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

	@Test
	public void testCreateSingle() {
		final String[] expected = TEST_ENTRIES_SINGLE;

		final Dictionary dictionary = Dictionary.Factory.create(expected);

		final String[] actual = dictionary.getEntries();

		Arrays.sort(expected);
		Arrays.sort(actual);

		Assert.assertArrayEquals(expected, actual);
	}

	@Test
	public void testCreateSimple() {
		final String[] expected = TEST_ENTRIES_SIMPLE;

		final Dictionary dictionary = Dictionary.Factory.create(expected);

		final String[] actual = dictionary.getEntries();

		Arrays.sort(expected);
		Arrays.sort(actual);

		Assert.assertArrayEquals(expected, actual);
	}

	@Test
	public void testCreateFromInputStream() throws URISyntaxException, IOException {
		final InputStream inputStream = this.getContext().getResources().openRawResource(R.raw.dictionary);

		long ms = System.currentTimeMillis(); 
		final String[] expected = StreamUtils.readLines(inputStream);
		System.out.println("Read: " + (System.currentTimeMillis() - ms) + "ms");

		ms = System.currentTimeMillis(); 
		final Dictionary dictionary = Dictionary.Factory.create(expected);
		System.out.println("Created: " + (System.currentTimeMillis() - ms) + "ms");

		ms = System.currentTimeMillis(); 
		final String[] actual = dictionary.getEntries();
		System.out.println("Entries: " + (System.currentTimeMillis() - ms) + "ms");

		ms = System.currentTimeMillis(); 
		for (int i = 0; i < expected.length; i++) {
			if (!dictionary.contains(expected[i])) {
				Assert.fail("Could not find: " + expected[i]);
			}
		}
		System.out.println("Contains: " + (System.currentTimeMillis() - ms) + "ms");

		ms = System.currentTimeMillis();
		final FileOutputStream out = new FileOutputStream("dictionary.bdi");
		dictionary.save(out);
		out.close();
		System.out.println("Saved: " + (System.currentTimeMillis() - ms) + "ms");

		ms = System.currentTimeMillis();
		final FileInputStream in = new FileInputStream("dictionary.bdi");
		Dictionary.load(in);
		in.close();
		System.out.println("Loaded: " + (System.currentTimeMillis() - ms) + "ms");

		Arrays.sort(expected);
		Arrays.sort(actual);

		Assert.assertArrayEquals(expected, actual);
	}

	@Test
	public void testCreateComplex() {
		final String[] expected = TEST_ENTRIES_COMPLEX;

		final Dictionary dictionary = Dictionary.Factory.create(expected);

		final String[] actual = dictionary.getEntries();

		Arrays.sort(expected);
		Arrays.sort(actual);

		Assert.assertArrayEquals(expected, actual);
	}

	@Test
	public void testSave() {
		throw new MethodNotYetImplementedException();
	}

	@Test
	public void testContainsComplex() {
		final Dictionary dictionary = Dictionary.Factory.create(TEST_ENTRIES_COMPLEX);

		for (int i = 0; i < TEST_ENTRIES_COMPLEX.length; i++) {
			if (!dictionary.contains(TEST_ENTRIES_COMPLEX[i])) {
				Assert.fail("Could not find: " + TEST_ENTRIES_COMPLEX[i]);
			}
		}
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
