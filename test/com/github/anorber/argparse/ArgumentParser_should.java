package com.github.anorber.argparse;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ArgumentParser_should extends TestSetup {

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void not_accept_null_args() {
		try {
			parser.parse(null);
			fail("should throw exception when parsing null");
		} catch (NullPointerException e) { }
	}

	@Test
	public void not_accept_null_arguments() {
		try {
			parser.addArgument(null);
			fail("should throw exception when adding null argument");
		} catch (NullPointerException e) { }
	}


	@Test
	public void throw_exception_if_shortopt_lacks_argument() {
		String[] args = new String[] {"-a"};
		try {
			parser.parse(args);
			fail("should throw exception if shortopt lacks argument");
		} catch (ArgumentParserException e) {
			assertThat(e.getMessage(), is("option -a requires argument"));
		}
	}

	@Test
	public void throw_exception_on_unexpected_shortopts() throws ArgumentParserException {
		String[] args = new String[] {"-c"};
		try {
			parser.parse(args);
			fail("should throw exception on unexpected shortopts");
		} catch (ArgumentParserException e) {
			assertThat(e.getMessage(), is("option -c not recognized"));
		}
	}


	@Test
	public void throw_exeption_when_no_argument() throws ArgumentParserException {
		String[] args = new String[] {"--alpha"};
		try {
			parser.parse(args);
			fail("should throw exception if longopt lacks argument");
		} catch (ArgumentParserException e) {
			assertThat(e.getMessage(), is("option --alpha requires argument"));
		}
	}

	@Test
	public void throw_exeption_when_partial_match_and_no_argument() throws ArgumentParserException {
		String[] args = new String[] {"--a"};
		try {
			parser.parse(args);
			fail("should throw exception if partial longopt lacks argument");
		} catch (ArgumentParserException e) {
			assertThat(e.getMessage(), is("option --alpha requires argument"));
		}
	}

	@Test
	public void throw_exeption_when_no_unique_match() throws ArgumentParserException {
		String[] args = new String[] {"--ba"};
		try {
			parser.parse(args);
			fail("should throw exception when no unique prefix");
		} catch (ArgumentParserException e) {
			assertThat(e.getMessage(), is("option --ba not a unique prefix"));
		}
	}

	@Test
	public void throw_exeption_when_unexpected_argument() throws ArgumentParserException {
		String[] args = new String[] {"--beta=beta"};
		try {
			parser.parse(args);
			fail("should throw exception when no unique prefix");
		} catch (ArgumentParserException e) {
			assertThat(e.getMessage(), is("option --beta must not have an argument"));
		}
	}

	@Test
	public void throw_exeption_when_unexpected_longopt() throws ArgumentParserException {
		String[] args = new String[] {"--gamma"};
		try {
			parser.parse(args);
			fail("should throw exception when unexpected longopt");
		} catch (ArgumentParserException e) {
			assertThat(e.getMessage(), is("option --gamma not recognized"));
		}
	}

	@Test
	public void throw_exeption_when_unexpected_longopt_with_arguments() throws ArgumentParserException {
		String[] args = new String[] {"--ba=foo"};
		try {
			parser.parse(args);
			fail("should throw exception unexpected_longopt_with_arguments");
		} catch (ArgumentParserException e) {
			assertThat(e.getMessage(), is("option --ba not a unique prefix"));
		}
	}

	@Test
	public void throw_exeption_when_unexpected_partial_longopt_with_arguments() throws ArgumentParserException {
		String[] args = new String[] {"--be=foo"};
		try {
			parser.parse(args);
			fail("should throw exception when partial longopt has unexpected argument");
		} catch (ArgumentParserException e) {
			assertThat(e.getMessage(), is("option --be must not have an argument"));
		}
	}

	@Test
	public void throw_exception_for_options_that_occured_more_than_once() {
		parser.parse(args);
		try {
			assertThat(parser.optionArgumentString(OptionId.B), nullValue());
			fail("should only return value if there is only one string");
		} catch (ArgumentParserException e) {
			//FIXME!
			assertThat(e.getMessage(), is(nullValue()));
		}
	}
}
