package com.github.anorber.argparse;

public class Option {

	final Enum<?> id;
	final String argument;

	public Option(Enum<?> id, String argument) {
		this.id = id;
		this.argument = argument;
	}
}
