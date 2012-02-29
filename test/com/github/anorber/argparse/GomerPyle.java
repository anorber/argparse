package com.github.anorber.argparse;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class GomerPyle {

	enum Option {
		HELP
	}

	@Test
	public void testLongOpt() {
		//given
		ArgumentParser parser = new ArgumentParser();
		Argument argument = new Argument("help", false, Option.HELP);

		//when
		parser.addArgument(argument);
		parser.parse(new String[] {"--help"});

		//then
		assertThat(parser.hasOption(Option.HELP), is(true));
	}

	@Test
	public void testShortOpt() {
		//given
		ArgumentParser parser = new ArgumentParser();
		Argument argument = new Argument('h', false, Option.HELP);

		//when
		parser.addArgument(argument);
		parser.parse(new String[] {"-h"});

		//then
		assertThat(parser.hasOption(Option.HELP), is(true));
	}
}
