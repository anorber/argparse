package com.github.anorber.argparse;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

import java.util.Iterator;

import org.junit.Test;

public class ArgumentParser_iterable_should extends TestSetup {

	//TODO: clean up

	@Test
	public void test1() {
		//given
		Option<OptionId> opt;
		OptionId id;
		String arg;
		parser.parse(args);

		//when
		Iterator<Option<OptionId>> it = parser.iterator();

		//then
		opt = it.next();
		id = opt.getId();
		arg = opt.getArgument();
		assertThat(id, is(OptionId.A));
		assertThat(arg, is("foo"));
	}

	@Test
	public void test2() {
		//given
		Option<OptionId> opt;
		OptionId id;
		parser.parse(args);

		//when
		Iterator<Option<OptionId>> it = parser.iterator();

		//then
		opt = it.next();

		opt = it.next();
		id = opt.getId();
		assertThat(id, is(OptionId.B));
	}

	@Test
	public void test3() {
		//given
		Option<OptionId> opt;
		OptionId id;
		String arg;
		parser.parse(args);

		//when
		Iterator<Option<OptionId>> it = parser.iterator();

		//then
		opt = it.next();
		opt = it.next();

		opt = it.next();
		id = opt.getId();
		arg = opt.getArgument();
		assertThat(id, is(OptionId.Alpha));
		assertThat(arg, is("one"));
	}

	@Test
	public void test4() {
		//given
		Option<OptionId> opt;
		OptionId id;
		String arg;
		parser.parse(args);

		//when
		Iterator<Option<OptionId>> it = parser.iterator();

		//then
		opt = it.next();
		opt = it.next();
		opt = it.next();
		opt = it.next();

		opt = it.next();
		id = opt.getId();
		arg = opt.getArgument();
		assertThat(id, is(OptionId.Alpha));
		assertThat(arg, is("two"));
	}

	@Test
	public void test5() {
		//given
		Option<OptionId> opt;
		OptionId id;
		String arg;
		parser.parse(args);

		//when
		Iterator<Option<OptionId>> it = parser.iterator();

		//then
		opt = it.next();
		opt = it.next();
		opt = it.next();
		opt = it.next();

		opt = it.next();
		id = opt.getId();
		arg = opt.getArgument();
		assertThat(id, is(OptionId.Alpha));
		assertThat(arg, is("two"));
	}

	@Test
	public void test6() {
		//given
		Option<OptionId> opt;
		OptionId id;
		String arg;
		parser.parse(args);

		//when
		Iterator<Option<OptionId>> it = parser.iterator();

		//then
		opt = it.next();
		opt = it.next();
		opt = it.next();
		opt = it.next();
		opt = it.next();
		opt = it.next();

		opt = it.next();
		id = opt.getId();
		arg = opt.getArgument();
		assertThat(id, is(OptionId.Alpha));
		assertThat(arg, is("four"));
	}

	@Test
	public void test7() {
		//given
		Option<OptionId> opt;
		OptionId id;
		String arg;
		parser.parse(args);

		//when
		Iterator<Option<OptionId>> it = parser.iterator();

		//then
		opt = it.next();
		opt = it.next();
		opt = it.next();
		opt = it.next();
		opt = it.next();
		opt = it.next();

		opt = it.next();
		id = opt.getId();
		arg = opt.getArgument();
		assertThat(id, is(OptionId.Alpha));
		assertThat(arg, is("four"));
	}

	@Test
	public void test8() {
		//given
		parser.parse(args);
		Iterator<Option<OptionId>> it = parser.iterator();

		//when
		it.next();
		it.next();
		it.next();
		it.next();
		it.next();
		it.next();
		it.next();
		Option<OptionId> opt = it.next();
		OptionId id = opt.getId();

		//then
		assertThat(id, is(OptionId.B));
	}

	@Test
	public void test9() {
		//given
		parser.parse(args);
		Iterator<Option<OptionId>> it = parser.iterator();

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


	@Test
	public void test10() {
		//given
		Option<OptionId> opt;
		OptionId id;
		String arg;
		parser.parse(new String[] {"-baabba", "-bba", "foo", "bar"});

		//when
		Iterator<Option<OptionId>> it = parser.iterator();

		//then
		opt = it.next();
		id = opt.getId();
		assertThat(id, is(OptionId.B));

		opt = it.next();
		id = opt.getId();
		assertThat(id, is(OptionId.A));

		opt = it.next();
		id = opt.getId();
		assertThat(id, is(OptionId.B));

		opt = it.next();
		id = opt.getId();
		assertThat(id, is(OptionId.B));

		opt = it.next();
		id = opt.getId();
		arg = opt.getArgument();
		assertThat(id, is(OptionId.A));
		assertThat(arg, is("foo"));
	}
}
