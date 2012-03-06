package com.github.anorber.argparse;

import static com.github.anorber.argparse.HasArg.NO_ARGUMENT;
import static com.github.anorber.argparse.HasArg.REQUIRED_ARGUMENT;

import java.util.ArrayList;
import java.util.List;

/**
 * @author anorber
 *
 * @param <E>
 */
class ArgumentList<E> {

	private final List<Argument<? extends E>> list;
	private final FoundOpts<E> foundOpts;

	/**
	 */
	ArgumentList(FoundOpts<E> foundOpts) {
		list = new ArrayList<Argument<? extends E>>();
		this.foundOpts = foundOpts;
	}

	/**
	 * @param other AL to clone
	 */
	ArgumentList(final ArgumentList<E> other, FoundOpts<E> foundOpts) {
		list = new ArrayList<Argument<? extends E>>(other.list);
		this.foundOpts = foundOpts;
	}


	void add(final Argument<? extends E> argument) {
		list.add(argument);
	}

	int parseOpts(final String[] args) throws ArgumentParserException {
		int i;
		for (i = 0; i < args.length; ++i) {
			final String arg = args[i];
			if (!arg.startsWith("-") || arg.equals("-")) {
				return i;
			}
			if (arg.equals("--")) {
				return i + 1;
			}
			if (arg.startsWith("--")) {
				i = longOpt(args, i);
			} else {
				i = shortOpt(args, i);
			}
		}
		return i;
	}


	private int shortOpt(final String[] args, final int i) throws ArgumentParserException {
		final String arg = args[i];
		final int length = arg.length();
		for (int j = 1; j < length; ++j) {
			final char opt = arg.charAt(j);
			final Argument<? extends E> argument = findShortOpt(opt);
			final HasArg hasArg = argument.hasArg();
			if (j + 1 == length && hasArg == REQUIRED_ARGUMENT) {
				if (args.length == i + 1) {
					throw new ArgumentParserException("option -" + opt + " requires argument", opt);
				}
				foundOpts.addOption(argument.getId(), args[i + 1]);
				return i + 1;
			}
			if (hasArg == NO_ARGUMENT) {
				foundOpts.addOption(argument.getId(), "");
			} else {
				foundOpts.addOption(argument.getId(), arg.substring(j + 1));
				return i;
			}
		}
		return i;
	}

	private Argument<? extends E> findShortOpt(final char shortOpt) throws ArgumentParserException {
		for (final Argument<? extends E> arg : list) {
			if (arg.getShortName() == shortOpt) {
				return arg;
			}
		}
		throw new ArgumentParserException("option -" + shortOpt + " not recognized", shortOpt);
	}

	private int longOpt(final String[] args, final int i) throws ArgumentParserException {
		final String nextArg = args[i];
		final boolean has_arg = nextArg.contains("=");
		final String optstr = has_arg ? nextArg.substring(2, nextArg.indexOf('=')) : nextArg.substring(2);
		final Argument<? extends E> opt = findLongopt(optstr);
		if (has_arg) {
			if (opt.hasArg() == NO_ARGUMENT) {
				throw new ArgumentParserException("option --" + optstr + " must not have an argument", optstr);
			}
			foundOpts.addOption(opt.getId(), nextArg.substring(nextArg.indexOf('=') + 1));
			return i;
		}
		if (opt.hasArg() == REQUIRED_ARGUMENT) {
			if (args.length == i + 1) {
				throw new ArgumentParserException("option --" + opt.getLongName() + " requires argument", optstr);
			}
			foundOpts.addOption(opt.getId(), args[i + 1]);
			return i + 1;
		} else {
			foundOpts.addOption(opt.getId(), "");
			return i;
		}
	}

	private Argument<? extends E> findLongopt(final String optstr) throws ArgumentParserException {
		final List<Argument<? extends E>> opts = new ArrayList<Argument<? extends E>>();
		for (final Argument<? extends E> arg : list) {
			final String longName = arg.getLongName();
			if (longName.equals(optstr)) {
				return arg;
			}
			if (longName.startsWith(optstr)) {
				opts.add(arg);
			}
		}
		if (opts.size() == 1) {
			return opts.get(0);
		}
		if (opts.isEmpty()) {
			throw new ArgumentParserException("option --" + optstr + " not recognized", optstr);
		}
		// TODO: put possibilities in exception?
		throw new ArgumentParserException("option --" + optstr + " not a unique prefix", optstr);
	}


	/* (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return list.hashCode() ^ foundOpts.hashCode();
	}

	/* (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		return obj instanceof ArgumentList
				&& list.equals(((ArgumentList<?>) obj).list)
				&& foundOpts.equals(((ArgumentList<?>) obj).foundOpts);
	}
}
