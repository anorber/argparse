package com.github.anorber.argparse;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Test;

public class ArgumentParser_parse_should extends TestSetup {

	@Test
	public void parse_shortopt() {
		//given
		String[] args = new String[] {"-b"};

		//when
		parser.parse(args);

		//then
		assertThat(parser.hasOption(OptionId.B), is(true));
		assertThat(parser.optionArgumentString(OptionId.B), nullValue());
	}

	@Test
	public void reject_unknown_shortopts() {
		//given
		String[] args = new String[] {"-c"};

		//when
		try {
			parser.parse(args);

			fail("unknown arguments should throw exception");
		} catch (ArgumentParserException e) {
		}
		assertThat(parser.hasOption(OptionId.A), is(false));
		assertThat(parser.hasOption(OptionId.B), is(false));
		assertThat(parser.hasOption(OptionId.None), is(false));
		assertThat(parser.optionArgumentString(OptionId.B), nullValue());
	}

	@Test
	public void return_unprocessed_args() {
		//given
		String[] args = new String[] {"-b", "a", "b", "c"};

		//when
		String[] result = parser.parse(args);

		//then
		assertThat(result, is(a_b_c));
		assertThat(parser.hasOption(OptionId.B), is(true));
		assertThat(parser.optionArgumentString(OptionId.B), nullValue());
	}

	@Test
	public void parse_shortopt_with_arguments() {
		//given
		String[] args = new String[] {"-a", "foo", "a", "b", "c"};

		//when
		String[] result = parser.parse(args);

		//then
		assertThat(result, is(a_b_c));
		assertThat(parser.hasOption(OptionId.A), is(true));
		assertThat(parser.optionArgumentString(OptionId.A), is("foo"));
	}

	@Test
	public void parse_shortopt_with_embedded_argument() {
		//given
		String[] args = new String[] {"-afoo", "a", "b", "c"};

		//when
		String[] result = parser.parse(args);

		//then
		assertThat(result, is(a_b_c));
		assertThat(parser.hasOption(OptionId.A), is(true));
		assertThat(parser.optionArgumentString(OptionId.A), is("foo"));
	}

	@Test
	public void parse_mixed_shortopt_with_argument() {
		//given
		String[] args = new String[] {"-ba", "foo", "a", "b", "c"};

		//when
		String[] result = parser.parse(args);

		//then
		assertThat(result, is(a_b_c));
		assertThat(parser.hasOption(OptionId.B), is(true));
		assertThat(parser.hasOption(OptionId.A), is(true));
		assertThat(parser.optionArgumentString(OptionId.B), nullValue());
		assertThat(parser.optionArgumentString(OptionId.A), is("foo"));
	}


	@Test
	public void handle_empty_args() {
		//when
		String[] result = parser.parse(noArgs);

		//then
		assertThat(result, is(noArgs));
		assertThat(parser.hasOption(OptionId.None), is(false));
	}

	@Test
	public void not_parse_non_opts() {
		//when
		String[] result = parser.parse(a_b_c);

		//then
		assertThat(result, is(a_b_c));
	}

	@Test
	public void handle_stdin_token() {
		//given
		String[] args = new String[] {"-", "a", "b", "c"};

		//when
		String[] result = parser.parse(args);

		//then
		assertThat(result, is(args));
	}

	@Test
	public void handle_delimiter() {
		//given
		String[] args = new String[] {"--", "a", "b", "c"};

		//when
		String[] result = parser.parse(args);

		//then
		assertThat(result, is(a_b_c));
	}


	@Test
	public void reject_unknown_arguments() {
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
	public void handle_longopt_without_argument() {
		//given
		String[] args = new String[] {"--beta", "a", "b", "c"};

		//when
		String[] result = parser.parse(args);

		//then
		assertThat(result, is(a_b_c));
		assertThat(parser.hasOption(OptionId.Beta), is(true));
	}

	@Test
	public void handle_partial_longopt_without_argument() {
		//given
		String[] args = new String[] {"--be", "a", "b", "c"};

		//when
		String[] result = parser.parse(args);

		//then
		assertThat(result, is(a_b_c));
		assertThat(parser.hasOption(OptionId.Beta), is(true));
	}

	@Test
	public void handle_longopt_with_argument() {
		//given
		String[] args = new String[] {"--alpha", "foo", "a", "b", "c"};

		//when
		String[] result = parser.parse(args);

		//then
		assertThat(result, is(a_b_c));
		assertThat(parser.hasOption(OptionId.Alpha), is(true));
	}

	@Test
	public void handle_longopt_with_embedded_argument() {
		//given
		String[] args = new String[] {"--alpha=foo", "a", "b", "c"};

		//when
		String[] result = parser.parse(args);

		//then
		assertThat(result, is(a_b_c));
		assertThat(parser.hasOption(OptionId.Alpha), is(true));
	}

	@Test
	public void handle_longopt() {
		//given
		String[] args = new String[] {"--beta"};

		//when
		parser.parse(args);

		//then
		assertThat(parser.hasOption(OptionId.Beta), is(true));
	}
}
