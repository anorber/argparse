package com.github.anorber.argparse;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

public class ArgumentParser_iterable_Test2 extends TestSetup {

	private Iterator<Option<OptionId>> it;
	private String[] args;

	@Before
	public void setUp() {
		//given
		args = parser.parse(new String[] {"-baabba", "-bba", "foo", "bar"});
		it = parser.iterator();
	}

	@Test
	public void test0() {
		assertThat(args, is(new String[] {"bar"}));
	}

	@Test
	public void test1() {
		//when
		Option<OptionId> opt = iterations(1);

		//then
		assertThat(opt.getId(), is(OptionId.B));
	}

	@Test
	public void test2() {
		//when
		Option<OptionId> opt = iterations(2);

		//then
		assertThat(opt.getId(), is(OptionId.A));
		assertThat(opt.getArgument(), is("abba"));
	}

	@Test
	public void test3() {
		//when
		Option<OptionId> opt = iterations(3);

		//then
		assertThat(opt.getId(), is(OptionId.B));
	}

	@Test
	public void test4() {
		//when
		Option<OptionId> opt = iterations(4);

		//then
		assertThat(opt.getId(), is(OptionId.B));
	}

	@Test
	public void test5() {
		//when
		Option<OptionId> opt = iterations(5);

		//then
		assertThat(opt.getId(), is(OptionId.A));
		assertThat(opt.getArgument(), is("foo"));
	}

	private Option<OptionId> iterations(int n) {
		while (--n > 0)
			it.next();
		return it.next();
	}
}
