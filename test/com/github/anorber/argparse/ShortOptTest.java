package com.github.anorber.argparse;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
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
		assertThat(parser.optionArgumentString(Option.B), nullValue());
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
		assertThat(parser.hasOption(Option.A), is(false));
		assertThat(parser.hasOption(Option.B), is(false));
		assertThat(parser.hasOption(Option.None), is(false));
		assertThat(parser.optionArgumentString(Option.B), nullValue());
	}

	@Test
	public void testParserShortOptNoArgument() {
		//given
		String[] args = new String[] {"-b", "a", "b", "c"};

		//when
		String[] result = parser.parse(args);

		//then
		assertThat(result, is(a_b_c));
		assertThat(parser.hasOption(Option.B), is(true));
		assertThat(parser.optionArgumentString(Option.B), nullValue());
	}

	@Test
	public void testParserShortOptWithArgument() {
		//given
		String[] args = new String[] {"-a", "foo", "a", "b", "c"};

		//when
		String[] result = parser.parse(args);

		//then
		assertThat(result, is(a_b_c));
		assertThat(parser.hasOption(Option.A), is(true));
		assertThat(parser.optionArgumentString(Option.A), is("foo"));
	}

	@Test
	public void testParserShortOptWithEmbeddedArgument() {
		//given
		String[] args = new String[] {"-afoo", "a", "b", "c"};

		//when
		String[] result = parser.parse(args);

		//then
		assertThat(result, is(a_b_c));
		assertThat(parser.hasOption(Option.A), is(true));
		assertThat(parser.optionArgumentString(Option.A), is("foo"));
	}

	@Test
	public void testParserMixedShortOptWithArgument() {
		//given
		String[] args = new String[] {"-ba", "foo", "a", "b", "c"};

		//when
		String[] result = parser.parse(args);

		//then
		assertThat(result, is(a_b_c));
		assertThat(parser.hasOption(Option.B), is(true));
		assertThat(parser.hasOption(Option.A), is(true));
		assertThat(parser.optionArgumentString(Option.B), nullValue());
		assertThat(parser.optionArgumentString(Option.A), is("foo"));
	}
}
