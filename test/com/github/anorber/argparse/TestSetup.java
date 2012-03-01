package com.github.anorber.argparse;

import org.junit.Before;

public class TestSetup {

	protected enum Option {
		A, B, Alpha, Beta, None, Bar, Bas
	}

	protected ArgumentParser parser;
	protected String[] noArgs = new String[] {};
	protected String[] a_b_c = new String[] {"a", "b", "c"};

	@Before
	public void setup() {
		parser = new ArgumentParser();
		parser.addArgument(new Argument('a', true, Option.A));
		parser.addArgument(new Argument('b', false, Option.B));
		parser.addArgument(new Argument("alpha", true, Option.Alpha));
		parser.addArgument(new Argument("beta", false, Option.Beta));
		parser.addArgument(new Argument("bas", false, Option.Bas));
		parser.addArgument(new Argument("bar", false, Option.Bar));
	}
}
