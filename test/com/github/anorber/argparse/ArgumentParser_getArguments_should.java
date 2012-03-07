package com.github.anorber.argparse;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ArgumentParser_getArguments_should extends TestSetup {

	@Test
	public void return_null_if_option_does_not_exist() throws ArgumentParserException {
		//given
		parser.parse(args);

		//when
		final String[] result = parser.getArguments(OptId.NONE);

		//then
		assertThat(result, is(nullValue()));
	}

	@Test
	public void return_embedded_shortopt_argument() throws ArgumentParserException {
		//given
		final String[] expected = new String[] {"foo"};
		parser.parse(args);

		//when
		final String[] result = parser.getArguments(OptId.A);

		//then
		assertThat(result, is(expected));
	}

	@Test
	public void return_all_arguments_given_to_longopts() throws ArgumentParserException {
		//given
		final String[] expected = new String[] {"one", "two", "three", "four"};
		parser.parse(args);

		//when
		final String[] result = parser.getArguments(OptId.ALPHA);

		//then
		assertThat(result, is(expected));
	}


	@Test
	public void split_arguments_at_delimiter_chars_for_shortopts() throws ArgumentParserException {
		//given
		final String[] expected = new String[] {"foo", "bar", "bas"};
		parser.parse(args2);

		//when
		final String[] result = parser.getArguments(OptId.A, ':');

		//then
		assertThat(result, is(expected));
	}

	@Test
	public void split_arguments_at_delimiter_chars_for_longopts() throws ArgumentParserException {
		//given
		final String[] expected = new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
		parser.parse(args2);

		//when
		final String[] result = parser.getArguments(OptId.ALPHA, ':');

		//then
		assertThat(result, is(expected));
	}

	@Test
	public void return_empty_array_for_unseen_opts() throws ArgumentParserException {
		//given
		final String[] expected = new String[0];
		parser.parse(args2);

		//when
		final String[] result = parser.getArguments(OptId.NONE, ':');

		//then
		assertThat(result, is(expected));
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
