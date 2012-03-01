package com.github.anorber.argparse;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ArgumentParser_should extends TestSetup {

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void not_accept_null_args() throws ArgumentParserException {
		exception.expect(NullPointerException.class);

		parser.parse(null);
	}

	@Test
	public void not_accept_null_arguments() throws ArgumentParserException {
		exception.expect(NullPointerException.class);

		parser.addArgument(null);
	}


	@Test
	public void throw_exception_if_shortopt_lacks_argument() throws ArgumentParserException {
		String[] args = new String[] {"-a"};

		exception.expect(ArgumentParserException.class);
//		exception.expectMessage("option -a requires argument");

		parser.parse(args);
	}

	@Test
	public void throw_exception_on_unexpected_shortopts() throws ArgumentParserException {
		String[] args = new String[] {"-c"};

		exception.expect(ArgumentParserException.class);
//		exception.expectMessage("option -c not recognized");

		parser.parse(args);
	}


	@Test
	public void throw_exeption_when_no_argument() throws ArgumentParserException {
		String[] args = new String[] {"--alpha"};

		exception.expect(ArgumentParserException.class);
//		exception.expectMessage("option --alpha requires argument");

		parser.parse(args);
	}

	@Test
	public void throw_exeption_when_partial_match_and_no_argument() throws ArgumentParserException {
		String[] args = new String[] {"--a"};

		exception.expect(ArgumentParserException.class);
//		exception.expectMessage("option --alpha requires argument");

		parser.parse(args);
	}

	@Test
	public void throw_exeption_when_no_unique_match() throws ArgumentParserException {
		String[] args = new String[] {"--ba"};

		exception.expect(ArgumentParserException.class);
//		exception.expectMessage("option --ba not a unique prefix");

		parser.parse(args);
	}

	@Test
	public void throw_exeption_when_unexpected_argument() throws ArgumentParserException {
		String[] args = new String[] {"--beta=beta"};

		exception.expect(ArgumentParserException.class);
//		exception.expectMessage("option --beta must not have an argument");

		parser.parse(args);
	}

	@Test
	public void throw_exeption_when_unexpected_longopt() throws ArgumentParserException {
		String[] args = new String[] {"--gamma"};

		exception.expect(ArgumentParserException.class);
//		exception.expectMessage("option --gamma not recognized");

		parser.parse(args);
	}

	@Test
	public void throw_exeption_when_unexpected_longopt_with_arguments() throws ArgumentParserException {
		String[] args = new String[] {"--ba=foo"};

		exception.expect(ArgumentParserException.class);
//		exception.expectMessage("option --ba not a unique prefix");

		parser.parse(args);
	}

	@Test
	public void throw_exeption_when_unexpected_partial_longopt_with_arguments() throws ArgumentParserException {
		String[] args = new String[] {"--be=foo"};

		exception.expect(ArgumentParserException.class);
//		exception.expectMessage("option --be must not have an argument");

		parser.parse(args);
	}
}
