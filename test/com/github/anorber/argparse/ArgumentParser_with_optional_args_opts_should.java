package com.github.anorber.argparse;

import static com.github.anorber.argparse.ArgumentParser_with_optional_args_opts_should.Op.A;
import static com.github.anorber.argparse.ArgumentParser_with_optional_args_opts_should.Op.ALPHA;
import static com.github.anorber.argparse.HasArg.OPTIONAL_ARGUMENT;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class ArgumentParser_with_optional_args_opts_should {

	enum Op { A, ALPHA };
	ArgumentParser<Op> parser;

	@Before
	public void setup() {
		//given
		parser = new ArgumentParser<Op>();
		parser.addArgument(new Argument<Op>('a', OPTIONAL_ARGUMENT, A));
		parser.addArgument(new Argument<Op>("alpha", OPTIONAL_ARGUMENT, ALPHA));
	}

	@Test
	public void handle_shortopts_without_arg() throws ArgumentParserException {
		//when
		String[] args = parser.parse(new String[] {"-a", "foo"});

		//then
		assertThat("-a foo", args, is(new String[] {"foo"}));
		assertThat("-a foo", parser.getArgumentsString(A, '\0'), is(""));
	}

	@Test
	public void handle_shortopts_with_arg() throws ArgumentParserException {
		//when
		String[] args = parser.parse(new String[] {"-afoo"});

		//then
		assertThat("-afoo", args, is(new String[0]));
		assertThat("-afoo", parser.getArgumentsString(A, '\0'), is("foo"));
	}

	@Test
	public void handle_longopts_without_arg() throws ArgumentParserException {
		//when
		String[] args = parser.parse(new String[] {"--alpha", "foo"});

		//then
		assertThat("--alpha foo", args, is(new String[] {"foo"}));
		assertThat("--alpha foo", parser.getArgumentsString(ALPHA, '\0'), is(""));
	}

	@Test
	public void handle_longopts_with_arg() throws ArgumentParserException {
		//when
		String[] args = parser.parse(new String[] {"--alpha=foo"});

		//then
		assertThat("--alpha=foo", args, is(new String[0]));
		assertThat("--alpha=foo", parser.getArgumentsString(ALPHA, '\0'), is("foo"));
	}
}
