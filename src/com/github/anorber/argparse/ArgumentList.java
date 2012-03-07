package com.github.anorber.argparse;

import static com.github.anorber.argparse.HasArg.NO_ARGUMENT;
import static com.github.anorber.argparse.HasArg.REQUIRED_ARGUMENT;

import java.util.ArrayList;
import java.util.List;

class ArgumentList<E> {

	private final List<Argument<? extends E>> list;
	private final FoundOpts<E> foundOpts;

	ArgumentList(FoundOpts<E> foundOpts) {
		list = new ArrayList<Argument<? extends E>>();
		this.foundOpts = foundOpts;
	}

	ArgumentList(final ArgumentList<E> other, FoundOpts<E> foundOpts) {
		list = new ArrayList<Argument<? extends E>>(other.list);
		this.foundOpts = foundOpts;
	}


	void add(final Argument<? extends E> argument) {
		list.add(argument);
	}

	int parseOpts(final String[] args) throws ArgumentParserException {
		int i = 0;
		while (i < args.length && args[i].startsWith("-")) {
			if (args[i].equals("-")) {
				return i;
			}
			if (args[i].equals("--")) {
				return i + 1;
			}
			if (args[i].startsWith("--")) {
				i = longOpt(args, i);
			} else {
				i = shortOpt(args, i);
			}
		}
		return i;
	}


	private int shortOpt(final String[] args, int i) throws ArgumentParserException {
		final String arg = args[i++];
		final int length = arg.length();
		for (int j = 1; j < length; ++j) {
			final char opt = arg.charAt(j);
			final Argument<? extends E> argument = findShortOpt(opt);
			final HasArg hasArg = argument.hasArg();
			if (j + 1 == length && hasArg == REQUIRED_ARGUMENT) {
				if (args.length == i) {
					throw new ArgumentRequiredException(opt);
				}
				foundOpts.addOption(argument.getId(), args[i++]);
				break;
			}
			if (hasArg == NO_ARGUMENT) {
				foundOpts.addOption(argument.getId(), "");
			} else {
				foundOpts.addOption(argument.getId(), arg.substring(j + 1));
				break;
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
		throw new ArgumentNotRecognizedException(shortOpt);
	}

	private int longOpt(final String[] args, final int i) throws ArgumentParserException {
		final String nextArg = args[i];
		final boolean has_arg = nextArg.contains("=");
		final String optstr = has_arg ? nextArg.substring(2, nextArg.indexOf('=')) : nextArg.substring(2);
		final Argument<? extends E> opt = findLongopt(optstr);
		if (has_arg) {
			if (opt.hasArg() == NO_ARGUMENT) {
				throw new ArgumentNeedsArgumentException(optstr);
			}
			foundOpts.addOption(opt.getId(), nextArg.substring(nextArg.indexOf('=') + 1));
			return i + 1;
		}
		if (opt.hasArg() == REQUIRED_ARGUMENT) {
			if (args.length == i + 1) {
				throw new ArgumentRequiredException(opt.getLongName(), optstr);
			}
			foundOpts.addOption(opt.getId(), args[i + 1]);
			return i + 2;
		} else {
			foundOpts.addOption(opt.getId(), "");
			return i + 1;
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
			throw new ArgumentNotRecognizedException(optstr);
		}
		throw new ArgumentNotUniqueException(optstr, opts.toArray(new Argument[opts.size()]));
	}


	/* @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return list.hashCode() ^ foundOpts.hashCode();
	}

	/* @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		return obj instanceof ArgumentList
				&& list.equals(((ArgumentList<?>) obj).list)
				&& foundOpts.equals(((ArgumentList<?>) obj).foundOpts);
	}
}
