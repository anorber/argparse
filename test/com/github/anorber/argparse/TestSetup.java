package com.github.anorber.argparse;

import org.junit.Before;

public class TestSetup {

	protected enum OptionId {
		A, B, Alpha, Beta, None, Bar, Bas
	}

	protected String[] args = new String[] {
			"-afoo",
			"-b",
			"--alpha", "one",
			"--beta",
			"--alpha=two",
			"--a=three",
			"--alpha", "four",
			"-b"
	};

	protected ArgumentParser parser;
	protected String[] noArgs = new String[] {};
	protected String[] a_b_c = new String[] {"a", "b", "c"};

	@Before
	public void setup() {
		parser = new ArgumentParser();
		parser.addArgument(new Argument('a', true, OptionId.A));
		parser.addArgument(new Argument('b', false, OptionId.B));
		parser.addArgument(new Argument("alpha", true, OptionId.Alpha));
		parser.addArgument(new Argument("beta", false, OptionId.Beta));
		parser.addArgument(new Argument("bas", false, OptionId.Bas));
		parser.addArgument(new Argument("bar", false, OptionId.Bar));
	}
}
