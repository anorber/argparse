package com.github.anorber.argparse;

import static com.github.anorber.argparse.HasArg.NO_ARGUMENT;
import static com.github.anorber.argparse.HasArg.REQUIRED_ARGUMENT;
import static com.github.anorber.argparse.TestSetup.OptId.ALPHA;
import static com.github.anorber.argparse.TestSetup.OptId.NONE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.github.anorber.argparse.TestSetup.OptId;

public class Argument_should {

	@Test
	public void not_accept_null_hasArg() {
		try {
			new Argument<OptId>('\1', null, NONE);
			fail("should not accept null hasArg");
		} catch (IllegalArgumentException e) { }
	}

	@Test
	public void not_accept_null_long_names() {
		try {
			new Argument<OptId>(null, HasArg.values()[0], NONE);
			fail();
		} catch (IllegalArgumentException e) { }
	}

	@Test
	public void not_accept_null_id() {
		try {
			new Argument<TestSetup.OptId>("", NO_ARGUMENT, null);
			fail("should not accept null id");
		} catch (IllegalArgumentException e) { }
	}

	@Test
	public void return_short_name() {
		//given
		final char expected = '_';
		Argument<OptId> arg = new Argument<OptId>(expected, HasArg.valueOf("NO_ARGUMENT"), NONE);

		//when
		char result = arg.getShortName();

		//then
		assertEquals(result, expected);
	}

	@Test
	public void return_long_name() {
		//given
		final String expected = "";
		Argument<OptId> arg = new Argument<OptId>(expected, NO_ARGUMENT, NONE);

		//when
		String result = arg.getLongName();

		//then
		assertEquals(result, expected);
	}

	@Test
	public void return_hasArg() {
		//given
		final HasArg expected = NO_ARGUMENT;
		Argument<OptId> arg = new Argument<OptId>("", expected, NONE);

		//when
		HasArg result = arg.hasArg();

		//then
		assertEquals(result, expected);
	}

	@Test
	public void return_id() {
		//given
		final OptId expected = NONE;
		Argument<OptId> arg = new Argument<OptId>("", NO_ARGUMENT, expected);

		//when
		OptId result = arg.getId();

		//then
		assertEquals(result, expected);
	}

	@Test
	public void not_be_equal_to_null() {
		Argument<OptId> arg = new Argument<OptId>("", NO_ARGUMENT, NONE);

		//when
		boolean result = arg.equals(null);

		//then
		assertFalse(result);
	}

	@Test
	public void not_be_equal_to_other_type() {
		Argument<OptId> arg = new Argument<OptId>("", NO_ARGUMENT, NONE);

		//when
		boolean result = arg.equals("");

		//then
		assertFalse(result);
	}

	@Test
	public void not_be_equal_if_different_hasArg() {
		Argument<OptId> arg1 = new Argument<OptId>("", NO_ARGUMENT, NONE);
		Argument<OptId> arg2 = new Argument<OptId>("", REQUIRED_ARGUMENT, NONE);

		//when
		boolean result = arg1.equals(arg2);

		//then
		assertFalse(result);
	}

	@Test
	public void not_be_equal_if_different_id() {
		Argument<OptId> arg1 = new Argument<OptId>("", NO_ARGUMENT, NONE);
		Argument<OptId> arg2 = new Argument<OptId>("", NO_ARGUMENT, ALPHA);

		//when
		boolean result = arg1.equals(arg2);

		//then
		assertFalse(result);
	}

	@Test
	public void not_be_equal_if_long_name_is_null_and_other_is_not() {
		Argument<OptId> arg1 = new Argument<OptId>(' ', NO_ARGUMENT, NONE);
		Argument<OptId> arg2 = new Argument<OptId>("", NO_ARGUMENT, NONE);

		//when
		boolean result = arg1.equals(arg2);

		//then
		assertFalse(result);
	}

	@Test
	public void not_be_equal_if_different_long_name() {
		Argument<OptId> arg1 = new Argument<OptId>("!", NO_ARGUMENT, NONE);
		Argument<OptId> arg2 = new Argument<OptId>("", NO_ARGUMENT, NONE);

		//when
		boolean result = arg1.equals(arg2);

		//then
		assertFalse(result);
	}

	@Test
	public void not_be_equal_if_different_short_name() {
		Argument<OptId> arg1 = new Argument<OptId>('!', NO_ARGUMENT, NONE);
		Argument<OptId> arg2 = new Argument<OptId>('?', NO_ARGUMENT, NONE);

		//when
		boolean result = arg1.equals(arg2);

		//then
		assertFalse(result);
	}

	@Test
	public void be_equal_if_same_values() {
		Argument<OptId> arg1 = new Argument<OptId>('!', NO_ARGUMENT, NONE);
		Argument<OptId> arg2 = new Argument<OptId>('!', NO_ARGUMENT, NONE);

		//when
		boolean result = arg1.equals(arg2);

		//then
		assertTrue(result);
	}

	@Test
	public void have_same_hash_code_if_equal() {
		Argument<OptId> arg1 = new Argument<OptId>('!', NO_ARGUMENT, NONE);
		Argument<OptId> arg2 = new Argument<OptId>('!', NO_ARGUMENT, NONE);

		//when

		//then
		assertEquals(arg1.hashCode(), arg2.hashCode());
	}

	@Test
	public void have_same_hash_code_if_equal_() {
		Argument<OptId> arg1 = new Argument<OptId>("", NO_ARGUMENT, NONE);
		Argument<OptId> arg2 = new Argument<OptId>("", NO_ARGUMENT, NONE);

		//when

		//then
		assertEquals(arg1.hashCode(), arg2.hashCode());
	}
}
