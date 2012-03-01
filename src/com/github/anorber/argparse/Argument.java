package com.github.anorber.argparse;

class Argument <E extends Enum<?>> {

	private char shortName;
	private String longName;

	private final boolean takesArgument;
	private final E id;

	Argument(String name, boolean hasArgument, E id) {
		this.longName = name;
		this.takesArgument = hasArgument;
		this.id = id;
	}

	Argument(char name, boolean hasArgument, E id) {
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

	E getId() {
		return id;
	}
}
