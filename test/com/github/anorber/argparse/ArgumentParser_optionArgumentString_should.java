package com.github.anorber.argparse;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ArgumentParser_optionArgumentString_should extends TestSetup {

	String[] args = new String[] {
			"-afoo",
			"-b",
			"--alpha", "one",
			"--beta",
			"--alpha=two",
			"--a=three",
			"--alpha", "four",
			"-b"
	};

	@Test
	public void return_argument_if_option_occured_once() {
		parser.parse(args);
		assertThat(parser.optionArgumentString(Option.A), is("foo"));
	}

	@Test
	public void return_nothing_for_options_without_arguments() {
		parser.parse(args);
		assertThat(parser.optionArgumentString(Option.Beta), nullValue());
	}

	@Test
	public void return_null_for_options_that_occured_more_than_once() {
		parser.parse(args);
		assertThat(parser.optionArgumentString(Option.B), nullValue());
	}
}
