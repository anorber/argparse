package com.github.anorber.argparse;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ArgumentParser_optionArgumentString_should extends TestSetup {

	@Test
	public void return_argument_if_option_occured_once() {
		parser.parse(args);
		assertThat(parser.optionArgumentString(OptionId.A), is("foo"));
	}

	@Test
	public void return_nothing_for_options_without_arguments() {
		parser.parse(args);
		assertThat(parser.optionArgumentString(OptionId.Beta), nullValue());
	}
}
