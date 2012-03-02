package com.github.anorber.argparse;

public class Argument <E extends Enum<?>> {

	private String longName;
	private char shortName;

	private final boolean takesArgument;
	private final E id;

	/**
	 * An Argument
	 *
	 * @param name         longopt name
	 * @param hasArgument  does this option take an argument
	 * @param id           an enum that identifies this argument
	 */
	public Argument(String name, boolean hasArgument, E id) {
		this.longName = name;
		this.takesArgument = hasArgument;
		this.id = id;
	}

	/**
	 * An argument
	 *
	 * @param name         shortopt name
	 * @param hasArgument  does this option take an argument
	 * @param id           an enum that identifies this argument
	 */
	public Argument(char name, boolean hasArgument, E id) {
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
