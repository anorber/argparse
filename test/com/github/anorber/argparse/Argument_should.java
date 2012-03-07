package com.github.anorber.argparse;

import static com.github.anorber.argparse.HasArg.NO_ARGUMENT;
import static com.github.anorber.argparse.HasArg.REQUIRED_ARGUMENT;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

public class Argument_should {

	private Object id = new Object();

	@Test
	public void haveSomeUsefulConstructors() {

		// longopt, no arguments
		new Argument<Object>(id, "alpha");

		// shortopt, no arguments
		new Argument<Object>(id, 'a');

		// both longopt and shortopt, no arguments
		new Argument<Object>(id, 'a', "alpha");

		// longopt
		new Argument<Object>(id, "alpha", NO_ARGUMENT);

		// shortopt
		new Argument<Object>(id, 'a', NO_ARGUMENT);

		// both shortopt and longopt
		new Argument<Object>(id, 'a', "alpha", NO_ARGUMENT);
	}

	@Test
	public void not_accept_null_hasArg() {
		try {
			new Argument<Object>(id, '\0', "", null);
			fail("should not accept null hasArg");
		} catch (IllegalArgumentException e) { }
	}

	@Test
	public void not_accept_null_long_names() {
		try {
			new Argument<Object>(id, null);
			fail();
		} catch (IllegalArgumentException e) { }
	}

	@Test
	public void not_accept_null_id() {
		try {
			new Argument<TestSetup.OptId>(null, "");
			fail("should not accept null id");
		} catch (IllegalArgumentException e) { }
	}

	@Test
	public void return_short_name() {
		//given
		final char expected = '_';
		Argument<Object> arg = new Argument<Object>(id, expected);

		//when
		char result = arg.getShortName();

		//then
		assertEquals(result, expected);
	}

	@Test
	public void return_long_name() {
		//given
		final String expected = "";
		Argument<Object> arg = new Argument<Object>(id, expected);

		//when
		String result = arg.getLongName();

		//then
		assertEquals(result, expected);
	}

	@Test
	public void return_hasArg() {
		//given
		final HasArg expected = NO_ARGUMENT;
		Argument<Object> arg = new Argument<Object>(id, "", expected);

		//when
		HasArg result = arg.hasArg();

		//then
		assertEquals(result, expected);
	}

	@Test
	public void return_id() {
		Argument<Object> arg = new Argument<Object>(id, "");

		//when
		Object result = arg.getId();

		//then
		assertEquals(result, id);
	}

	@Test
	public void not_be_equal_to_null() {
		Argument<Object> arg = new Argument<Object>(id, "");

		//when
		boolean result = arg.equals(null);

		//then
		assertFalse(result);
	}

	@Test
	public void not_be_equal_to_other_type() {
		Argument<Object> arg = new Argument<Object>(id, "");

		//when
		boolean result = arg.equals("");

		//then
		assertFalse(result);
	}

	@Test
	public void not_be_equal_if_different_hasArg() {
		Argument<Object> arg1 = new Argument<Object>(id, "", NO_ARGUMENT);
		Argument<Object> arg2 = new Argument<Object>(id, "", REQUIRED_ARGUMENT);

		//when
		boolean result = arg1.equals(arg2);

		//then
		assertFalse(result);
	}

	@Test
	public void not_be_equal_if_different_id() {
		Object id2 = "";
		Argument<Object> arg1 = new Argument<Object>(id, "", NO_ARGUMENT);
		Argument<Object> arg2 = new Argument<Object>(id2, "", NO_ARGUMENT);

		//when
		boolean result = arg1.equals(arg2);

		//then
		assertFalse(result);
	}

	@Test
	public void not_be_equal_if_long_name_is_null_and_other_is_not() {
		Argument<Object> arg1 = new Argument<Object>(id, ' ');
		Argument<Object> arg2 = new Argument<Object>(id, "");

		//when
		boolean result = arg1.equals(arg2);

		//then
		assertFalse(result);
	}

	@Test
	public void not_be_equal_if_different_long_name() {
		Argument<Object> arg1 = new Argument<Object>(id, "!");
		Argument<Object> arg2 = new Argument<Object>(id, "");

		//when
		boolean result = arg1.equals(arg2);

		//then
		assertFalse(result);
	}

	@Test
	public void not_be_equal_if_different_short_name() {
		Argument<Object> arg1 = new Argument<Object>(id, '!');
		Argument<Object> arg2 = new Argument<Object>(id, '?');

		//when
		boolean result = arg1.equals(arg2);

		//then
		assertFalse(result);
	}

	@Test
	public void be_equal_if_same_values() {
		Argument<Object> arg1 = new Argument<Object>(id, '!');
		Argument<Object> arg2 = new Argument<Object>(id, '!');

		//when
		boolean result = arg1.equals(arg2);

		//then
		assertTrue(result);
	}

	@Test
	public void have_same_hash_code_if_equal() {
		Argument<Object> arg1 = new Argument<Object>(id, '!');
		Argument<Object> arg2 = new Argument<Object>(id, '!');

		//when

		//then
		assertEquals(arg1.hashCode(), arg2.hashCode());
	}

	@Test
	public void have_same_hash_code_if_equal_() {
		Argument<Object> arg1 = new Argument<Object>(id, "");
		Argument<Object> arg2 = new Argument<Object>(id, "");

		//when

		//then
		assertEquals(arg1.hashCode(), arg2.hashCode());
	}
}
