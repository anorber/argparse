package com.github.anorber.argparse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ArgumentParser<E extends Enum<?>> implements Iterable<Option<E>> {

	private List<Option<E>> optList = new ArrayList<Option<E>>();
	private Map<E, List<String>> opts = new HashMap<E, List<String>>();
	private ArgumentList<E> arguments = new ArgumentList<E>();

	/**
	 * Adds an argument to this parser
	 *
	 * @param argument  an option argument
	 */
	public void addArgument(Argument<E> argument) {
		if (argument == null)
			throw new NullPointerException();
		arguments.add(argument);
	}

	/**
	 * Parses args for opts
	 *
	 * @param args  the args to be parsed
	 * @return      the rest of the args after that the opts was parsed
	 * @throws ArgumentParserException
	 */
	public String[] parse(String[] args) throws ArgumentParserException {
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

	private int shortOpt(String[] args, int i) throws ArgumentParserException {
		final String argstr = args[i];
		final int length = argstr.length();
		for (int j = 1; j < length; ++j) {
			final char opt = argstr.charAt(j);
			final Argument<E> argument = arguments.findShortOpt(opt);
			final boolean takesArg = argument.takesArgument();
			final E id = argument.getId();
			if (takesArg) {
				if (j + 1 == length) {
					if (args.length == i + 1)
						throw new ArgumentParserException("option -" + opt + " requires argument", opt);
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

	private void addOption(final E id, final String argumentString) {
		optList.add(new Option<E>(id, argumentString));
		if (!opts.containsKey(id))
			opts.put(id, new ArrayList<String>());
		opts.get(id).add(argumentString);
	}

	private int longOpt(String[] args, int i) throws ArgumentParserException {
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

		final List<Argument<E>> possibilities = arguments.findLongOpts(optstr);

		Argument<E> opt = null;
		if (possibilities.size() > 1) {
			for (Argument<E> a : possibilities) {
				if (a.getLongName().equals(optstr)) {
					opt = a;
				}
			}
			if (opt == null) {
				throw new ArgumentParserException("option --" + optstr + " not a unique prefix", optstr);
			}
		} else {
			opt = possibilities.get(0);
		}

		final boolean takesArguments = opt.takesArgument();
		final E id = opt.getId();
		if (takesArguments) {
			if (optarg == null) {
				if (args.length == i + 1)
					throw new ArgumentParserException("option --" + opt.getLongName() + " requires argument", optstr);
				final String argumentString = args[i + 1];
				addOption(id, argumentString);
				return i + 1;
			}
			addOption(id, optarg);
			return i;
		} else {
			if (optarg != null)
				throw new ArgumentParserException("option --" + optstr + " must not have an argument", optstr);
			addOption(id, null);
			return i;
		}
	}

	/**
	 * Tells if this parser has seen <code>option</code>
	 *
	 * @param option  enum representing an option
	 * @return        true if this parser found the option
	 */
	public boolean hasOption(E option) {
		return opts.containsKey(option);
	}

	/*
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public Iterator<Option<E>> iterator() {
		return optList.iterator();
	}

	/**
	 * Returns an array with the arguments given to this option
	 *
	 * @param option  enum representing an option
	 * @return        the arguments in the order they appeared
	 */
	public String[] getArguments(E option) {
		List<String> opt = opts.get(option);
		if (opt != null)
			return opt.toArray(new String[0]);
		return null;
	}
}
