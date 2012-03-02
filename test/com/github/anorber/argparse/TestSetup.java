package com.github.anorber.argparse;

import org.junit.Before;

public class TestSetup {

	protected enum OptId {
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

	protected ArgumentParser<OptId> parser;
	protected String[] noArgs = new String[] {};
	protected String[] a_b_c = new String[] {"a", "b", "c"};

	@Before
	public void setup() {
		parser = new ArgumentParser<OptId>();
		parser.addArgument(new Argument<OptId>('a', true, OptId.A));
		parser.addArgument(new Argument<OptId>('b', false, OptId.B));
		parser.addArgument(new Argument<OptId>("alpha", true, OptId.Alpha));
		parser.addArgument(new Argument<OptId>("beta", false, OptId.Beta));
		parser.addArgument(new Argument<OptId>("bas", false, OptId.Bas));
		parser.addArgument(new Argument<OptId>("bar", false, OptId.Bar));
	}
}
