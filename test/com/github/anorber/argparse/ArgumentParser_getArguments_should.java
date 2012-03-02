package com.github.anorber.argparse;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ArgumentParser_getArguments_should extends TestSetup {

	@Test
	public void return_null_if_option_does_not_exist() throws ArgumentParserException {
		parser.parse(args);
		assertThat(parser.getArguments(OptId.None), nullValue());
	}

	@Test
	public void return_embedded_shortopt_argument() throws ArgumentParserException {
		parser.parse(args);
		assertThat(parser.getArguments(OptId.A), is(new String[] {"foo"}));
	}

	@Test
	public void return_all_arguments_given_to_longopts() throws ArgumentParserException {
		parser.parse(args);
		assertThat(parser.getArguments(OptId.Alpha), is(new String[] {"one", "two", "three", "four"}));
	}

	@Test
	public void return_null_for_options_that_does_not_take_arguments() throws ArgumentParserException {
		parser.parse(args);
		assertThat(parser.getArguments(OptId.B), is(new String[] {null, null}));
	}

	@Test
	public void return_null_value_if_no_argument() throws ArgumentParserException {
		parser.parse(args);
		assertThat(parser.getArguments(OptId.Beta), is(new String[] {null}));
	}


	@Test
	public void split_arguments_at_delimiter_chars_for_shortopts() throws ArgumentParserException {
		//given
		parser.parse(args2);

		//when
		final String[] arguments = parser.getArguments(OptId.A, ':');

		//then
		assertThat(arguments, is(new String[] {"foo", "bar", "bas"}));
	}

	@Test
	public void split_arguments_at_delimiter_chars_for_longopts() throws ArgumentParserException {
		//given
		parser.parse(args2);

		//when
		final String[] arguments = parser.getArguments(OptId.Alpha, ':');

		//then
		assertThat(arguments, is(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
	}

	private String[] args2 = new String[] {
			"-afoo:bar",
			"--alpha",
			"1:2:3",
			"-a",
			"bas",
			"--a=4:5",
			"--al=6",
			"--alph",
			"7",
			"--alpha",
			"8:9:10"
	};
}
