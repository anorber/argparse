package com.github.anorber.argparse;

class Argument {

	private char shortName;
	private String longName;
	private final boolean takesArgument;
	private final Enum<?> id;

	Argument(String name, boolean hasArgument, Enum<?> id) {
		this.longName = name;
		this.takesArgument = hasArgument;
		this.id = id;
	}

	Argument(char name, boolean hasArgument, Enum<?> id) {
		this.shortName = name;
		this.takesArgument = hasArgument;
		this.id = id;
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

	Enum<?> getId() {
		return id;
	}
}
