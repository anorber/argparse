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

	protected ArgumentParser<OptionId> parser;
	protected String[] noArgs = new String[] {};
	protected String[] a_b_c = new String[] {"a", "b", "c"};

	@Before
	public void setup() {
		parser = new ArgumentParser<OptionId>();
		parser.addArgument(new Argument<OptionId>('a', true, OptionId.A));
		parser.addArgument(new Argument<OptionId>('b', false, OptionId.B));
		parser.addArgument(new Argument<OptionId>("alpha", true, OptionId.Alpha));
		parser.addArgument(new Argument<OptionId>("beta", false, OptionId.Beta));
		parser.addArgument(new Argument<OptionId>("bas", false, OptionId.Bas));
		parser.addArgument(new Argument<OptionId>("bar", false, OptionId.Bar));
	}
}
