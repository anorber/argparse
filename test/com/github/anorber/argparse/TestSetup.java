package com.github.anorber.argparse;


import org.junit.Before;

public class TestSetup {

	protected enum Option {
		A, B, Alpha, Beta, None
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
	}
}
