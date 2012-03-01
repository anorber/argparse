package com.github.anorber.argparse;

public class Option <E extends Enum<?>> {

	final private E id;
	final private String argument;

	/**TODO
	 *
	 * @param id
	 * @param argument
	 */
	public Option(E id, String argument) {
		this.id = id;
		this.argument = argument;
	}

	/**
	 * @return the id
	 */
	public E getId() {
		return id;
	}

	/**
	 * @return the argument
	 */
	public String getArgument() {
		return argument;
	}
}
