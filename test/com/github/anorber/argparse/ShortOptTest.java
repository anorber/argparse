package com.github.anorber.argparse;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Test;

public class ShortOptTest extends TestSetup {

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
	public void testParserShortOptWithEmbeddedArgument() {
		//given
		String[] args = new String[] {"-afoo", "a", "b", "c"};

		//when
		String[] result = parser.parse(args);

		//then
		assertThat(result, is(a_b_c));
	}
}
