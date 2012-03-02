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
	public void addArgument(final Argument<E> argument) {
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
	public String[] parse(final String[] args) throws ArgumentParserException {
		int i = parseOpts(args);
		return Arrays.copyOfRange(args, i, args.length);
	}

	private int parseOpts(final String[] args) throws ArgumentParserException {
		for (int i = 0; ; ++i)
			if (i == args.length || !args[i].startsWith("-") || args[i].equals("-"))
				return i;
			else if (!args[i].startsWith("--"))
				i = shortOpt(args, i);
			else if (!args[i].equals("--"))
				i = longOpt(args, i);
			else
				return i + 1;
	}

	private int shortOpt(final String[] args, final int i) throws ArgumentParserException {
		for (int j = 1; j < args[i].length(); ++j) {
			final char opt = args[i].charAt(j);
			final Argument<E> arg = arguments.findShortOpt(opt);
			if (!arg.takesArgument())
				addOption(arg.getId(), null);
			else if (j + 1 == args[i].length()) {
				addOption(arg.getId(), nextArg(args, i, opt));
				return i + 1;
			} else {
				addOption(arg.getId(), args[i].substring(j + 1));
				break;
			}
		}
		return i;
	}

	private String nextArg(final String[] args, final int i, final char opt) throws ArgumentParserException {
		if (args.length == i + 1)
			throw new ArgumentParserException("option -" + opt + " requires argument", opt);
		return args[i + 1];
	}

	private void addOption(final E id, final String argumentString) {
		optList.add(new Option<E>(id, argumentString));
		if (!opts.containsKey(id))
			opts.put(id, new ArrayList<String>());
		opts.get(id).add(argumentString);
	}

	private int longOpt(final String[] args, final int i) throws ArgumentParserException {
		final int j = args[i].indexOf('=');
		if (j > 0)
			return addLongOpt(args, i, args[i].substring(2, j), args[i].substring(j + 1));
		else
			return addLongOpt(args, i, args[i].substring(2), null);
	}

	private int addLongOpt(final String[] args, final int i, final String optstr, final String optarg) throws ArgumentParserException {
		final Argument<E> opt = findLongopt(optstr);
		if (!opt.takesArgument())
			if (optarg != null)
				throw new ArgumentParserException("option --" + optstr + " must not have an argument", optstr);
			else
				addOption(opt.getId(), null);
		else if (optarg != null)
			addOption(opt.getId(), optarg);
		else if (args.length == i + 1)
			throw new ArgumentParserException("option --" + opt.getLongName() + " requires argument", optstr);
		else {
			addOption(opt.getId(), args[i + 1]);
			return i + 1;
		}
		return i;
	}

	private Argument<E> findLongopt(final String optstr) throws ArgumentParserException {
		final List<Argument<E>> possibilities = arguments.findLongOpts(optstr);
		if (possibilities.size() == 1)
			return possibilities.get(0);
		else
			return searchPossibilities(possibilities, optstr);
	}

	private Argument<E> searchPossibilities(final List<Argument<E>> possibilities, final String optstr) throws ArgumentParserException {
		for (final Argument<E> a : possibilities)
			if (a.getLongName().equals(optstr))
				return a;

		//TODO: put possibilities in exception?
		throw new ArgumentParserException("option --" + optstr + " not a unique prefix", optstr);
	}

	/**
	 * Tells if this parser has seen <code>option</code>
	 *
	 * @param option  enum representing an option
	 * @return        true if this parser found the option
	 */
	public boolean hasOption(final E option) {
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
	public String[] getArguments(final E option) {
		final List<String> opt = opts.get(option);
		if (opt == null)
			return null;
		return opt.toArray(new String[0]);
	}

	String[] getArguments(final E option, final char delimiter) {
		final List<String> buf = new ArrayList<String>();
		for (String arg : opts.get(option))
			for (String substr : arg.split("\\" + delimiter))
				buf.add(substr);
		return buf.toArray(new String[0]);
	}

	String getArgumentsString(final E option, final char delimiter) {
		final String[] args = getArguments(option);
		if (args.length == 0)
			return "";
		final StringBuilder buf = new StringBuilder(args[0]);
		for (int i = 1; i < args.length; ++i)
			buf.append(delimiter).append(args[i]);
		return buf.toString();
	}
}
