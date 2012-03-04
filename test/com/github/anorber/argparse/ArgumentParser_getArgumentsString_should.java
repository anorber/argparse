package com.github.anorber.argparse;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ArgumentParser_getArgumentsString_should extends TestSetup {

	private String[] args = new String[] {
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

	@Test
	public void return_one_string_with_all_arguments_for_shortopts() throws ArgumentParserException {
		//given
		parser.parse(args);

		//when
		final String argumentString = parser.getArgumentsString(OptId.A, ':');

		//then
		assertThat(argumentString, is("foo:bar:bas"));
	}

	@Test
	public void return_one_string_with_all_arguments_for_longopts() throws ArgumentParserException {
		//given
		parser.parse(args);

		//when
		final String argumentString = parser.getArgumentsString(OptId.ALPHA, ':');

		//then
		assertThat(argumentString, is("1:2:3:4:5:6:7:8:9:10"));
	}

	@Test
	public void return_empty_string_for_unseen_options() throws ArgumentParserException {
		//given
		parser.parse(args);

		//when
		final String argumentString = parser.getArgumentsString(OptId.NONE, ':');

		//then
		assertThat(argumentString, is(""));
	}
}
