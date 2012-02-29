package com.github.anorber.argparse;

class Argument {

	private char shortName;
	private String longName;
	private final boolean takesArgument;

	Argument(String name, boolean hasArgument, Enum<?> id) {
		this.longName = name;
		this.takesArgument = hasArgument;
	}

	Argument(char name, boolean hasArgument, Enum<?> id) {
		this.shortName = name;
		this.takesArgument = hasArgument;
	}

	char getShortName() {
		return shortName;
	}

	String getLongName() {
		return longName;
	}

	boolean takesArgument() {
		return takesArgument;
	}
}
