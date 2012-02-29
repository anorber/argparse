package com.github.anorber.argparse;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class NoOptTest extends TestSetup {

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
}
