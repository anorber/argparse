package com.github.anorber.argparse;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Test;

public class LongOptTest extends TestSetup {

	@Test
	public void testParserUnknownLongOpt() {
		//given
		String[] args = new String[] {"--gamma"};

		//when
		try {
			parser.parse(args);

			fail("unknown arguments should throw exception");
		} catch (ArgumentParserException e) { }

		//then
		assertThat(parser.hasOption(OptionId.Alpha), is(false));
		assertThat(parser.hasOption(OptionId.Beta), is(false));
		assertThat(parser.hasOption(OptionId.None), is(false));
	}

	@Test
	public void testParserLongOptNoArgument() {
		//given
		String[] args = new String[] {"--beta", "a", "b", "c"};

		//when
		String[] result = parser.parse(args);

		//then
		assertThat(result, is(a_b_c));
		assertThat(parser.hasOption(OptionId.Beta), is(true));
	}

	@Test
	public void testParserPartialLongOptNoArgument() {
		//given
		String[] args = new String[] {"--be", "a", "b", "c"};

		//when
		String[] result = parser.parse(args);

		//then
		assertThat(result, is(a_b_c));
		assertThat(parser.hasOption(OptionId.Beta), is(true));
	}

	@Test
	public void testParserLongOptWithArgument() {
		//given
		String[] args = new String[] {"--alpha", "foo", "a", "b", "c"};

		//when
		String[] result = parser.parse(args);

		//then
		assertThat(result, is(a_b_c));
		assertThat(parser.hasOption(OptionId.Alpha), is(true));
	}

	@Test
	public void testParserLongOptWithEmbeddedArgument() {
		//given
		String[] args = new String[] {"--alpha=foo", "a", "b", "c"};

		//when
		String[] result = parser.parse(args);

		//then
		assertThat(result, is(a_b_c));
		assertThat(parser.hasOption(OptionId.Alpha), is(true));
	}

	@Test
	public void testLongOpt() {
		//given
		String[] args = new String[] {"--beta"};

		//when
		parser.parse(args);

		//then
		assertThat(parser.hasOption(OptionId.Beta), is(true));
	}
}
