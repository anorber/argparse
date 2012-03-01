package com.github.anorber.argparse;

import java.util.*;

class ArgumentParser implements Iterable<Option> {

	private ArgumentList arguments = new ArgumentList();
	private List<Option> optList = new ArrayList<Option>();
	private Map<Enum<?>, List<String>> opts = new HashMap<Enum<?>, List<String>>();

	void addArgument(Argument argument) {
		if (argument == null)
			throw new NullPointerException();
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
			final Argument argument = arguments.findShortOpt(opt);
			final boolean takesArg = argument.takesArgument();
			final Enum<?> id = argument.getId();
			if (takesArg) {
				if (j + 1 == length) {
					if (args.length == i + 1)
						throw new ArgumentParserException();
					final String argumentString = args[i + 1];
					addOption(id, argumentString);
					return i + 1;
				} else {
					final String argumentString = argstr.substring(j + 1);
					addOption(id, argumentString);
					return i;
				}
			} else {
				addOption(id, null);
			}
		}
		return i;
	}

	private void addOption(final Enum<?> id, final String argumentString) {
		optList.add(new Option(id, argumentString));
		if (!opts.containsKey(id))
			opts.put(id, new ArrayList<String>());
		opts.get(id).add(argumentString);
	}

	private int longOpt(String[] args, int i) {
		final int j = args[i].indexOf('=');

		final String optstr;
		final String optarg;

		if (j > 0) {
			optstr = args[i].substring(2, j);
			optarg = args[i].substring(j + 1);
		} else {
			optstr = args[i].substring(2);
			optarg = null;
		}

		final List<Argument> possibilities = arguments.findLongOpts(optstr);

		Argument opt = null;
		if (possibilities.size() > 1) {
			for (Argument a : possibilities) {
				if (a.getLongName().equals(args[i])) {
					opt = a;
				}
			}
			if (opt == null) {
				throw new ArgumentParserException();
			}
		}
		opt = possibilities.get(0);

		final boolean takesArguments = opt.takesArgument();
		final Enum<?> id = opt.getId();
		if (takesArguments) {
			if (optarg == null) {
				if (args.length == i + 1)
					throw new ArgumentParserException();
				final String argumentString = args[i + 1];
				addOption(id, argumentString);
				return i + 1;
			}
			addOption(id, optarg);
			return i;
		} else {
			if (optarg != null)
				throw new ArgumentParserException();
			addOption(id, null);
			return i;
		}
	}

	boolean hasOption(Enum<?> option) {
		return opts.containsKey(option);
	}

	String optionArgumentString(Enum<?> option) {
		List<String> opt = opts.get(option);
		if (opt == null)
			return null;
		if (opt.size() != 1)
			throw new ArgumentParserException();
		return opt.get(0);
	}

	@Override
	public Iterator<Option> iterator() {
		return optList.iterator();
	}

	String[] getArguments(Enum<?> id) {
		List<String> opt = opts.get(id);
		if (opt != null)
			return opt.toArray(new String[0]);
		return null;
	}
}
