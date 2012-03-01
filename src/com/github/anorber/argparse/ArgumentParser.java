package com.github.anorber.argparse;

import java.util.Arrays;

class ArgumentParser {

	//TODO: make Arguments-object to handle storage and search
	private ArgumentList arguments = new ArgumentList();

	void addArgument(Argument argument) {
		arguments.add(argument);
	}

	String[] parse(String[] args) {
		int i;
		for (i = 0; i < args.length; ++i) {
			if (!args[i].startsWith("-"))
				break;
			if (args[i].equals("-"))
				break;
			if (!args[i].startsWith("--")) {
				i = shortOpt(args, i);
			} else if (args[i].equals("--")) {
				++i;
				break;
			} else {
				i = longOpt(args, i);
			}
		}
		return Arrays.copyOfRange(args, i, args.length);
	}

	private int shortOpt(String[] args, int i) {
		final String argstr = args[i];
		final int length = argstr.length();
		for (int j = 1; j < length; ++j) {
			final char opt = argstr.charAt(j);
			boolean takesArg = arguments.shortOptTakesArgument(opt);
			if (takesArg) {
				if (j + 1 == length) {
					return i + 1;
				} else {
					return i;
				}
			}
		}
		return i;
	}

	private int longOpt(String[] args, int i) {
		boolean takesArguments = arguments.longOptTakesArgument(args[i]);
		if (takesArguments) {
			return i + 1;
		} else {
			return i;
		}
	}

	boolean hasOption(Enum<?> option) {
		return !option.toString().equals("None");
	}
}
