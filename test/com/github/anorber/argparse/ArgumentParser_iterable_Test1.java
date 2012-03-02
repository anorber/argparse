package com.github.anorber.argparse;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

public class ArgumentParser_iterable_Test1 extends TestSetup {

	Iterator<Option<OptId>> it;

	@Before
	public void setUp() throws ArgumentParserException {
		//given
		parser.parse(args);
		it = parser.iterator();
	}

	@Test
	public void test1() {
		//when
		Option<OptId> opt = iterations(1);

		//then
		assertThat(opt.getId(), is(OptId.A));
		assertThat(opt.getArgument(), is("foo"));
	}

	@Test
	public void test2() {
		//when
		Option<OptId> opt = iterations(2);

		//then
		assertThat(opt.getId(), is(OptId.B));
	}

	@Test
	public void test3() {
		//when
		Option<OptId> opt = iterations(3);

		//then
		assertThat(opt.getId(), is(OptId.Alpha));
		assertThat(opt.getArgument(), is("one"));
	}

	@Test
	public void test4() {
		//when
		Option<OptId> opt = iterations(4);

		//then
		assertThat(opt.getId(), is(OptId.Beta));
	}

	@Test
	public void test5() {
		//when
		Option<OptId> opt = iterations(5);

		//then
		assertThat(opt.getId(), is(OptId.Alpha));
		assertThat(opt.getArgument(), is("two"));
	}

	@Test
	public void test6() {
		//when
		Option<OptId> opt = iterations(6);

		//then
		assertThat(opt.getId(), is(OptId.Alpha));
		assertThat(opt.getArgument(), is("three"));
	}

	@Test
	public void test7() {
		//when
		Option<OptId> opt = iterations(7);

		//then
		assertThat(opt.getId(), is(OptId.Alpha));
		assertThat(opt.getArgument(), is("four"));
	}

	@Test
	public void test8() {
		//when
		Option<OptId> opt = iterations(8);

		//then
		assertThat(opt.getId(), is(OptId.B));
	}

	@Test
	public void test9() {
		//when
		iterations(8);

		//then
		assertFalse(it.hasNext());
	}

	private Option<OptId> iterations(int n) {
		while (--n > 0)
			it.next();
		return it.next();
	}
}
