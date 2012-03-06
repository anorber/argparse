package com.github.anorber.argparse;

import static com.github.anorber.argparse.TestSetup.OptId.ALPHA;
import static com.github.anorber.argparse.TestSetup.OptId.NONE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.github.anorber.argparse.TestSetup.OptId;

public class Option_should {

	@Test
	public void not_accept_null_id() {
		try {
			new Option<OptId>(null, "");
			fail();
		} catch (IllegalArgumentException e) { }
	}

	@Test
	public void return_id() {
		//given
		final OptId expected = NONE;
		Option<OptId> op = new Option<OptId>(expected, null);

		//when
		OptId result = op.getId();

		//then
		assertEquals(result, expected);
	}

	@Test
	public void return_argument() {
		//given
		final String expected = "";
		Option<OptId> op = new Option<OptId>(NONE, expected);

		//when
		String result = op.getArgument();

		//then
		assertEquals(result, expected);
	}

	@Test
	public void not_be_equal_to_null() {
		//given
		Option<OptId> op = new Option<OptId>(NONE, "");

		//when
		boolean result = op.equals(null);

		//then
		assertFalse(result);
	}

	@Test
	public void not_be_equal_to_other_type() {
		//given
		Option<OptId> op = new Option<OptId>(NONE, "");

		//when
		boolean result = op.equals("");

		//then
		assertFalse(result);
	}

	@Test
	public void not_be_equal_if_argument_is_null_and_other_is_not() {
		//given
		Option<OptId> op1 = new Option<OptId>(NONE, null);
		Option<OptId> op2 = new Option<OptId>(NONE, "");

		//when
		boolean result = op1.equals(op2);

		//then
		assertFalse(result);
	}

	@Test
	public void not_be_equal_if_argument_is_different() {
		//given
		Option<OptId> op1 = new Option<OptId>(NONE, "");
		Option<OptId> op2 = new Option<OptId>(NONE, "!");

		//when
		boolean result = op1.equals(op2);

		//then
		assertFalse(result);
	}

	@Test
	public void not_be_equal_if_id_is_different() {
		//given
		Option<OptId> op1 = new Option<OptId>(NONE, "");
		Option<OptId> op2 = new Option<OptId>(ALPHA, "");

		//when
		boolean result = op1.equals(op2);

		//then
		assertFalse(result);
	}

	@Test
	public void be_equal_if_same() {
		//given
		Option<OptId> op1 = new Option<OptId>(NONE, "");
		Option<OptId> op2 = new Option<OptId>(NONE, "");

		//when
		boolean result = op1.equals(op2);

		//then
		assertTrue(result);
	}

	@Test
	public void have_same_hash_code_if_equal() {
		//given
		Option<OptId> op1 = new Option<OptId>(NONE, "");
		Option<OptId> op2 = new Option<OptId>(NONE, "");

		//then
		assertEquals(op1.hashCode(), op2.hashCode());
	}

	@Test
	public void have_same_hash_code_if_equal_and_null() {
		//given
		Option<OptId> op1 = new Option<OptId>(NONE, null);
		Option<OptId> op2 = new Option<OptId>(NONE, null);

		//then
		assertEquals(op1.hashCode(), op2.hashCode());
	}
}
