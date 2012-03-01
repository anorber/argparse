package com.github.anorber.argparse;

import java.util.*;

class ArgumentParser {

	private ArgumentList arguments = new ArgumentList();
	private Map<Enum<?>, String> opts = new HashMap<Enum<?>, String>();

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
			Argument argument = arguments.findShortOpt(opt);
			boolean takesArg = argument.takesArgument();
			if (takesArg) {
				if (j + 1 == length) {
					opts.put(argument.getId(), args[i + 1]);
					return i + 1;
				} else {
					opts.put(argument.getId(), argstr.substring(j + 1));
					return i;
				}
			} else {
				opts.put(argument.getId(), null);
			}
		}
		return i;
	}

	private int longOpt(String[] args, int i) {
		int j = args[i].indexOf('=');
		final String optstr;
		final String optarg;
		if (j > 0) {
			optstr = args[i].substring(2, j);
			optarg = args[i].substring(j + 1);
		} else {
			optstr = args[i].substring(2);
			optarg = null;
		}

		List<Argument> possibilities = arguments.findLongOpts(optstr);

		if (possibilities.size() > 1)
			throw new ArgumentParserException();

		Argument opt = possibilities.get(0);
		boolean takesArguments = opt.takesArgument();
		if (takesArguments) {
			if (optarg == null) {
				opts.put(opt.getId(), args[i + 1]);
				return i + 1;
			}
			opts.put(opt.getId(), optarg);
			return i;
		} else {
			opts.put(opt.getId(), null);
			return i;
		}
	}

	boolean hasOption(Enum<?> option) {
		return opts.containsKey(option);
	}

	String optionArgumentString(Enum<?> option) {
		return opts.get(option);
	}
}
