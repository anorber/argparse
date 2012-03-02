package com.github.anorber.argparse;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ArgumentParser_getArguments_should extends TestSetup {

	@Test
	public void return_null_if_option_does_not_exist() throws ArgumentParserException {
		parser.parse(args);
		assertThat(parser.getArguments(OptionId.None), nullValue());
	}

	@Test
	public void return_embedded_shortopt_argument() throws ArgumentParserException {
		parser.parse(args);
		assertThat(parser.getArguments(OptionId.A), is(new String[] {"foo"}));
	}

	@Test
	public void return_all_arguments_given_to_longopts() throws ArgumentParserException {
		parser.parse(args);
		assertThat(parser.getArguments(OptionId.Alpha), is(new String[] {"one", "two", "three", "four"}));
	}

	@Test
	public void return_null_for_options_that_does_not_take_arguments() throws ArgumentParserException {
		parser.parse(args);
		assertThat(parser.getArguments(OptionId.B), is(new String[] {null, null}));
	}

	@Test
	public void return_null_value_if_no_argument() throws ArgumentParserException {
		parser.parse(args);
		assertThat(parser.getArguments(OptionId.Beta), is(new String[] {null}));
	}
}
