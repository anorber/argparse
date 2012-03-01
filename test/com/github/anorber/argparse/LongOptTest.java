package com.github.anorber.argparse;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Test;

public class LongOptTest extends TestSetup {

	//TODO:
	// - Abbreviated names (--a)
	// - Argument in option (--alpha=foo)

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
		assertThat(parser.hasOption(Option.Alpha), is(false));
		assertThat(parser.hasOption(Option.Beta), is(false));
		assertThat(parser.hasOption(Option.None), is(false));
	}

	@Test
	public void testParserLongOptNoArgument() {
		//given
		String[] args = new String[] {"--beta", "a", "b", "c"};

		//when
		String[] result = parser.parse(args);

		//then
		assertThat(result, is(a_b_c));
		assertThat(parser.hasOption(Option.Beta), is(true));
	}

	@Test
	public void testParserLongOptWithArgument() {
		//given
		String[] args = new String[] {"--alpha", "foo", "a", "b", "c"};

		//when
		String[] result = parser.parse(args);

		//then
		assertThat(result, is(a_b_c));
		assertThat(parser.hasOption(Option.Alpha), is(true));
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
}
