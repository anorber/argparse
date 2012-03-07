package com.github.anorber.argparse;

import static com.github.anorber.argparse.HasArg.REQUIRED_ARGUMENT;
import static com.github.anorber.argparse.TestSetup.OptId.A;
import static com.github.anorber.argparse.TestSetup.OptId.ALPHA;
import static com.github.anorber.argparse.TestSetup.OptId.B;
import static com.github.anorber.argparse.TestSetup.OptId.BAR;
import static com.github.anorber.argparse.TestSetup.OptId.BAS;
import static com.github.anorber.argparse.TestSetup.OptId.BETA;

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
		parser.addArgument(new Argument<OptId>(A, 'a', REQUIRED_ARGUMENT));
		parser.addArgument(new Argument<OptId>(B, 'b'));
		parser.addArgument(new Argument<OptId>(ALPHA, "alpha", REQUIRED_ARGUMENT));
		parser.addArgument(new Argument<OptId>(BETA, "beta"));
		parser.addArgument(new Argument<OptId>(BAS, "bas"));
		parser.addArgument(new Argument<OptId>(BAR, "bar"));
	}
}
