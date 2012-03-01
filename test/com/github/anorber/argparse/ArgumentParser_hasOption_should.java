package com.github.anorber.argparse;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ArgumentParser_hasOption_should extends TestSetup {

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
	public void return_false_if_option_was_not_seen() {
		parser.parse(args);
		assertThat(parser.hasOption(Option.None), is(false));
	}

	@Test
	public void return_true_if_options_with_arguments_is_seen() {
		parser.parse(args);
		assertThat(parser.hasOption(Option.A), is(true));
	}

	@Test
	public void return_true_if_options_is_seen() {
		parser.parse(args);
		assertThat(parser.hasOption(Option.B), is(true));
	}

	@Test
	public void return_true_if_longopts_with_arguments_is_seen() {
		parser.parse(args);
		assertThat(parser.hasOption(Option.Alpha), is(true));
	}

	@Test
	public void return_true_if_longopts_is_seen() {
		parser.parse(args);
		assertThat(parser.hasOption(Option.Beta), is(true));
	}
}
