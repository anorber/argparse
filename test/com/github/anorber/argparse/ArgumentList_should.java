package com.github.anorber.argparse;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ArgumentList_should {

	enum T {}

	@Test
	public void be_equal_to_itself() {
		//given
		ArgumentList<T> arglist = new ArgumentList<T>();

		//when
		boolean result = arglist.equals(arglist);

		//then
		assertTrue(result);
	}

	@Test
	public void not_be_equal_to_null() {
		//given
		ArgumentList<T> arglist = new ArgumentList<T>();

		//when
		boolean result = arglist.equals(null);

		//then
		assertFalse(result);
	}

	@Test
	public void not_be_equal_to_other_type() {
		//given
		ArgumentList<T> arglist = new ArgumentList<T>();

		//when
		boolean result = arglist.equals("");

		//then
		assertFalse(result);
	}

	@Test
	public void not_be_equal_if_different_lists() {
		//given
		ArgumentList<T> arglist1 = new ArgumentList<T>();
		ArgumentList<T> arglist2 = new ArgumentList<T>();
		arglist1.add(null);

		//when
		boolean result = arglist1.equals(arglist2);

		//then
		assertFalse(result);
	}

	@Test
	public void be_equal_if_same_state() {
		//given
		ArgumentList<T> arglist1 = new ArgumentList<T>();
		ArgumentList<T> arglist2 = new ArgumentList<T>();

		//when
		boolean result = arglist1.equals(arglist2);

		//then
		assertTrue(result);
	}

	@Test
	public void have_the_same_hask_code_if_equal() {
		//given
		ArgumentList<T> arglist1 = new ArgumentList<T>();
		ArgumentList<T> arglist2 = new ArgumentList<T>();

		//when

		//then
		assertEquals(arglist1.hashCode(), arglist2.hashCode());
	}
}
