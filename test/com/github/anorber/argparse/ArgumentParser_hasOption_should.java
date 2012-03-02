package com.github.anorber.argparse;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ArgumentParser_hasOption_should extends TestSetup {

	@Test
	public void return_false_if_option_was_not_seen() throws ArgumentParserException {
		parser.parse(args);
		assertThat(parser.hasOption(OptionId.None), is(false));
	}

	@Test
	public void return_true_if_options_with_arguments_is_seen() throws ArgumentParserException {
		parser.parse(args);
		assertThat(parser.hasOption(OptionId.A), is(true));
	}

	@Test
	public void return_true_if_options_is_seen() throws ArgumentParserException {
		parser.parse(args);
		assertThat(parser.hasOption(OptionId.B), is(true));
	}

	@Test
	public void return_true_if_longopts_with_arguments_is_seen() throws ArgumentParserException {
		parser.parse(args);
		assertThat(parser.hasOption(OptionId.Alpha), is(true));
	}

	@Test
	public void return_true_if_longopts_is_seen() throws ArgumentParserException {
		parser.parse(args);
		assertThat(parser.hasOption(OptionId.Beta), is(true));
	}

	@Test
	public void recognize_longopts_that_is_substring_of_other_longopt() throws ArgumentParserException {
		parser.addArgument(new Argument<OptionId>("bastu", false, null));
		parser.parse(new String[] {"--bas"});
		assertThat(parser.hasOption(OptionId.Bas), is(true));
	}
}
