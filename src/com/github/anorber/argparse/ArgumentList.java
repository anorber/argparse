package com.github.anorber.argparse;

import static com.github.anorber.argparse.HasArg.NO_ARGUMENT;
import static com.github.anorber.argparse.HasArg.OPTIONAL_ARGUMENT;
import static com.github.anorber.argparse.HasArg.REQUIRED_ARGUMENT;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

class ArgumentList <E> {

	private final List<Argument<? extends E>> list;
	private final List<Option<E>> optList;
	private final Map<E, List<String>> optMap;

	public ArgumentList() {
		optList = new ArrayList<Option<E>>();
		optMap = new HashMap<E, List<String>>();
		list = new ArrayList<Argument<? extends E>>();
	}

	public ArgumentList(final ArgumentList<E> other) {
		optList = new ArrayList<Option<E>>(other.optList);
		optMap = new HashMap<E, List<String>>(other.optMap);
		list = new ArrayList<Argument<? extends E>>(other.list);
	}


	void add(final Argument<? extends E> argument) {
		list.add(argument);
	}

	private Argument<? extends E> findShortOpt(final char shortOpt) throws ArgumentParserException {
		for (Argument<? extends E> arg : list)
			if (arg.getShortName() == shortOpt)
				return arg;
		throw new ArgumentParserException("option -" + shortOpt + " not recognized", shortOpt);
	}

	private List<Argument<? extends E>> findLongOpts(final String longOpt) throws ArgumentParserException {
		final List<Argument<? extends E>> opts = new ArrayList<Argument<? extends E>>();
		for (final Argument<? extends E> arg : list) {
			final String longName = arg.getLongName();
			if (longName == null)
				continue;
			if (longName.startsWith(longOpt))
				opts.add(arg);
		}
		if (opts.isEmpty())
			throw new ArgumentParserException("option --" + longOpt + " not recognized", longOpt);
		return opts;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return list.hashCode() ^ optList.hashCode();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (obj == null || getClass() != obj.getClass())
			return false;
		final ArgumentList<?> other = (ArgumentList<?>)obj;
		return list.equals(other.list) && optList.equals(other.optList);
	}


	int parseOpts(final String[] args) throws ArgumentParserException {
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
			final Argument<? extends E> arg = findShortOpt(opt);
			if (j + 1 == args[i].length() && arg.hasArg() == REQUIRED_ARGUMENT) {
				addOption(arg.getId(), nextArg(args, i, opt));
				return i + 1;
			} else if (arg.hasArg() == NO_ARGUMENT) {
				addOption(arg.getId(), null);
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
		if (!optMap.containsKey(id))
			optMap.put(id, new ArrayList<String>());
		optMap.get(id).add(argumentString);
	}

	private int longOpt(final String[] args, final int i) throws ArgumentParserException {
		final int j = args[i].indexOf('=');
		if (j > 0)
			return addLongOpt(args, i, args[i].substring(2, j), args[i].substring(j + 1));
		else
			return addLongOpt(args, i, args[i].substring(2), null);
	}

	private int addLongOpt(final String[] args, final int i, final String optstr, final String optarg) throws ArgumentParserException {
		final Argument<? extends E> opt = findLongopt(optstr);
		if (opt.hasArg() == OPTIONAL_ARGUMENT)
			addOption(opt.getId(), optarg == null ? "" : optarg);
		else if (opt.hasArg() == NO_ARGUMENT)
			if (optarg == null)
				addOption(opt.getId(), null);
			else
				throw new ArgumentParserException("option --" + optstr + " must not have an argument", optstr);
		else if (args.length == i + 1)
			throw new ArgumentParserException("option --" + opt.getLongName() + " requires argument", optstr);
		else if (optarg == null) {
			addOption(opt.getId(), args[i + 1]);
			return i + 1;
		} else
			addOption(opt.getId(), optarg);
		return i;
	}

	private Argument<? extends E> findLongopt(final String optstr) throws ArgumentParserException {
		final List<Argument<? extends E>> possibilities = findLongOpts(optstr);
		if (possibilities.size() == 1)
			return possibilities.get(0);
		else
			return searchPossibilities(possibilities, optstr);
	}

	private Argument<? extends E> searchPossibilities(final List<Argument<? extends E>> possibilities, final String optstr) throws ArgumentParserException {
		for (final Argument<? extends E> a : possibilities)
			if (a.getLongName().equals(optstr))
				return a;

		//TODO: put possibilities in exception?
		throw new ArgumentParserException("option --" + optstr + " not a unique prefix", optstr);
	}

	Iterator<Option<E>> getIterator() {
		return optList.iterator();
	}

	boolean containsOption(final E option) {
		return optMap.containsKey(option);
	}

	List<String> getArgs(final E option) {
		return optMap.get(option);
	}
}
