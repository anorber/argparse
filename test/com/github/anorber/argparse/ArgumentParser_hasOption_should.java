package com.github.anorber.argparse;

import static com.github.anorber.argparse.HasArg.NO_ARGUMENT;
import static com.github.anorber.argparse.TestSetup.OptId.A;
import static com.github.anorber.argparse.TestSetup.OptId.ALPHA;
import static com.github.anorber.argparse.TestSetup.OptId.B;
import static com.github.anorber.argparse.TestSetup.OptId.BAS;
import static com.github.anorber.argparse.TestSetup.OptId.BETA;
import static com.github.anorber.argparse.TestSetup.OptId.NONE;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ArgumentParser_hasOption_should extends TestSetup {

	@Test
	public void return_false_if_option_was_not_seen() throws ArgumentParserException {
		parser.parse(args);
		assertThat(parser.hasOption(NONE), is(false));
	}

	@Test
	public void return_true_if_options_with_arguments_is_seen() throws ArgumentParserException {
		parser.parse(args);
		assertThat(parser.hasOption(A), is(true));
	}

	@Test
	public void return_true_if_options_is_seen() throws ArgumentParserException {
		parser.parse(args);
		assertThat(parser.hasOption(B), is(true));
	}

	@Test
	public void return_true_if_longopts_with_arguments_is_seen() throws ArgumentParserException {
		parser.parse(args);
		assertThat(parser.hasOption(ALPHA), is(true));
	}

	@Test
	public void return_true_if_longopts_is_seen() throws ArgumentParserException {
		parser.parse(args);
		assertThat(parser.hasOption(BETA), is(true));
	}

	@Test
	public void recognize_longopts_that_is_substring_of_other_longopt() throws ArgumentParserException {
		parser.addArgument(new Argument<OptId>("bastu", NO_ARGUMENT, NONE));
		parser.parse(new String[] { "--bas" });
		assertThat(parser.hasOption(BAS), is(true));
	}
}
