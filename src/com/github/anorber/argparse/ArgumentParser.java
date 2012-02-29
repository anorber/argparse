package com.github.anorber.argparse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class ArgumentParser {

	private List<Argument> arguments = new ArrayList<Argument>();

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
		String argstr = args[i].substring(1);

		int p = 0;
		for (Argument arg : arguments) {
			if (argstr.charAt(p) == arg.getShortName()) {
				if (arg.takesArgument() && argstr.length() - 1 == p) {
					++i;
				}
				return i;
			}
		}
		throw new ArgumentParserException();
	}

	private int longOpt(String[] args, int i) {
		for (Argument arg : arguments) {
			if (args[i].equals("--" + arg.getLongName())) {
				if (arg.takesArgument()) {
					++i;
				}
				return i;
			}
		}
		throw new ArgumentParserException();
	}

	boolean hasOption(Enum<?> option) {
		return !option.toString().equals("None");
	}
}
