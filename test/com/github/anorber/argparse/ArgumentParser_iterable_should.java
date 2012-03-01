package com.github.anorber.argparse;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

import java.util.Iterator;

import org.junit.*;

public class ArgumentParser_iterable_should extends TestSetup {

	@Test
	public void test1() {
		//given
		Option opt;
		OptionId id;
		String arg;
		parser.parse(args);

		//when
		Iterator<Option> it = parser.iterator();

		//then
		opt = it.next();
		id = (OptionId)opt.id;
		arg = opt.argument;
		assertThat(id, is(OptionId.A));
		assertThat(arg, is("foo"));
	}

	@Test
	public void test2() {
		//given
		Option opt;
		OptionId id;
		parser.parse(args);

		//when
		Iterator<Option> it = parser.iterator();

		//then
		opt = it.next();

		opt = it.next();
		id = (OptionId)opt.id;
		assertThat(id, is(OptionId.B));
	}

	@Test
	public void test3() {
		//given
		Option opt;
		OptionId id;
		String arg;
		parser.parse(args);

		//when
		Iterator<Option> it = parser.iterator();

		//then
		opt = it.next();
		opt = it.next();

		opt = it.next();
		id = (OptionId)opt.id;
		arg = opt.argument;
		assertThat(id, is(OptionId.Alpha));
		assertThat(arg, is("one"));
	}

	@Test
	public void test4() {
		//given
		Option opt;
		OptionId id;
		String arg;
		parser.parse(args);

		//when
		Iterator<Option> it = parser.iterator();

		//then
		opt = it.next();
		opt = it.next();
		opt = it.next();
		opt = it.next();

		opt = it.next();
		id = (OptionId)opt.id;
		arg = opt.argument;
		assertThat(id, is(OptionId.Alpha));
		assertThat(arg, is("two"));
	}

	@Test
	public void test5() {
		//given
		Option opt;
		OptionId id;
		String arg;
		parser.parse(args);

		//when
		Iterator<Option> it = parser.iterator();

		//then
		opt = it.next();
		opt = it.next();
		opt = it.next();
		opt = it.next();

		opt = it.next();
		id = (OptionId)opt.id;
		arg = opt.argument;
		assertThat(id, is(OptionId.Alpha));
		assertThat(arg, is("two"));
	}

	@Test
	public void test6() {
		//given
		Option opt;
		OptionId id;
		String arg;
		parser.parse(args);

		//when
		Iterator<Option> it = parser.iterator();

		//then
		opt = it.next();
		opt = it.next();
		opt = it.next();
		opt = it.next();
		opt = it.next();
		opt = it.next();

		opt = it.next();
		id = (OptionId)opt.id;
		arg = opt.argument;
		assertThat(id, is(OptionId.Alpha));
		assertThat(arg, is("four"));
	}

	@Test
	public void test7() {
		//given
		Option opt;
		OptionId id;
		String arg;
		parser.parse(args);

		//when
		Iterator<Option> it = parser.iterator();

		//then
		opt = it.next();
		opt = it.next();
		opt = it.next();
		opt = it.next();
		opt = it.next();
		opt = it.next();

		opt = it.next();
		id = (OptionId)opt.id;
		arg = opt.argument;
		assertThat(id, is(OptionId.Alpha));
		assertThat(arg, is("four"));
	}

	@Test
	public void test8() {
		//given
		parser.parse(args);
		Iterator<Option> it = parser.iterator();

		//when
		it.next();
		it.next();
		it.next();
		it.next();
		it.next();
		it.next();
		it.next();
		Option opt = it.next();
		OptionId id = (OptionId)opt.id;

		//then
		assertThat(id, is(OptionId.B));
	}

	@Test
	public void test9() {
		//given
		parser.parse(args);
		Iterator<Option> it = parser.iterator();

		//when
		it.next();
		it.next();
		it.next();
		it.next();
		it.next();
		it.next();
		it.next();
		it.next();

		//then
		assertFalse(it.hasNext());
	}
}
