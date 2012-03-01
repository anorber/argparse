package com.github.anorber.argparse;

public class Argument <E extends Enum<?>> {

	private char shortName;
	private String longName;

	private final boolean takesArgument;
	private final E id;

	/**TODO
	 *
	 * @param name
	 * @param hasArgument
	 * @param id
	 */
	public Argument(String name, boolean hasArgument, E id) {
		this.longName = name;
		this.takesArgument = hasArgument;
		this.id = id;
	}

	/**TODO
	 *
	 * @param name
	 * @param hasArgument
	 * @param id
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
