package com.github.anorber.argparse;

import static com.github.anorber.argparse.HasArg.NO_ARGUMENT;
import static com.github.anorber.argparse.HasArg.REQUIRED_ARGUMENT;

import org.junit.Before;

public class TestSetup {

	protected enum OptId {
		A, B, ALPHA, BETA, NONE, BAR, BAS
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
		parser.addArgument(new Argument<OptId>('a', REQUIRED_ARGUMENT, OptId.A));
		parser.addArgument(new Argument<OptId>('b', NO_ARGUMENT, OptId.B));
		parser.addArgument(new Argument<OptId>("alpha", REQUIRED_ARGUMENT, OptId.ALPHA));
		parser.addArgument(new Argument<OptId>("beta", NO_ARGUMENT, OptId.BETA));
		parser.addArgument(new Argument<OptId>("bas", NO_ARGUMENT, OptId.BAS));
		parser.addArgument(new Argument<OptId>("bar", NO_ARGUMENT, OptId.BAR));
	}
}
