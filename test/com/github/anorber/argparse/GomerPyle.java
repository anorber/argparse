package com.github.anorber.argparse;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class GomerPyle {

	enum Option {
		A, B, Alpha, Beta, None
	}

	ArgumentParser parser;
	String[] noArgs = new String[] {};
	String[] a_b_c = new String[] {"a", "b", "c"};

	@Before
	public void setup() {
		parser = new ArgumentParser();
		parser.addArgument(new Argument('a', true, Option.A));
		parser.addArgument(new Argument('b', false, Option.B));
		parser.addArgument(new Argument("alpha", true, Option.Alpha));
		parser.addArgument(new Argument("beta", false, Option.Beta));
	}

	@Test
	public void testLongOpt() {
		//given
		String[] args = new String[] {"--beta"};

		//when
		parser.parse(args);

		//then
		assertThat(parser.hasOption(Option.Beta), is(true));
	}

	@Test
	public void testShortOpt() {
		//given
		String[] args = new String[] {"-b"};

		//when
		parser.parse(args);

		//then
		assertThat(parser.hasOption(Option.B), is(true));
	}

	@Test
	public void testNoOpt() {
		//when
		parser.parse(noArgs);

		//then
		assertThat(parser.hasOption(Option.None), is(false));
	}

	@Test
	public void testParserNoArgs() {
		//when
		String[] result = parser.parse(noArgs);

		//then
		assertThat(result, is(noArgs));
	}

	@Test
	public void testParserNoOptArgs() {
		//when
		String[] result = parser.parse(a_b_c);

		//then
		assertThat(result, is(a_b_c));
	}

	@Test
	public void testParserUnknownShortOpt() {
		//given
		String[] args = new String[] {"-c"};

		//when
		try {
			parser.parse(args);

			fail("unknown arguments should throw exception");
		} catch (ArgumentParserException e) {
		}
	}

	@Test
	public void testParserShortOptNoArgument() {
		//given
		String[] args = new String[] {"-b", "a", "b", "c"};

		//when
		String[] result = parser.parse(args);

		//then
		assertThat(result, is(a_b_c));
	}

	@Test
	public void testParserShortOptWithArgument() {
		//given
		String[] args = new String[] {"-a", "foo", "a", "b", "c"};

		//when
		String[] result = parser.parse(args);

		//then
		assertThat(result, is(a_b_c));
	}

	@Test
	public void testParserStdInToken() {
		//given
		String[] args = new String[] {"-", "a", "b", "c"};

		//when
		String[] result = parser.parse(args);

		//then
		assertThat(result, is(args));
	}

	@Test
	public void testParserDelimiterToken() {
		//given
		String[] args = new String[] {"--", "a", "b", "c"};

		//when
		String[] result = parser.parse(args);

		//then
		assertThat(result, is(a_b_c));
	}

	@Test
	public void testParserUnknownLongOpt() {
		//given
		String[] args = new String[] {"--gamma"};

		//when
		try {
			parser.parse(args);

			fail("unknown arguments should throw exception");
		} catch (ArgumentParserException e) {
		}
	}

	@Test
	public void testParserLongOptNoArgument() {
		//given
		String[] args = new String[] {"--beta", "a", "b", "c"};

		//when
		String[] result = parser.parse(args);

		//then
		assertThat(result, is(a_b_c));
	}

	@Test
	public void testParserLongOptWithArgument() {
		//given
		String[] args = new String[] {"--alpha", "foo", "a", "b", "c"};

		//when
		String[] result = parser.parse(args);

		//then
		assertThat(result, is(a_b_c));
	}
}
