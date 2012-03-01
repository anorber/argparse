package com.github.anorber.argparse;

public class Option <E extends Enum<?>> {

	final E id;
	final String argument;

	public Option(E id, String argument) {
		this.id = id;
		this.argument = argument;
	}
}
