package com.github.anorber.argparse;

import java.util.ArrayList;
import java.util.List;

class ArgumentList <E extends Enum<?>> {

	private List<Argument<E>> list = new ArrayList<Argument<E>>();

	void add(final Argument<E> argument) {
		list.add(argument);
	}

	Argument<E> findShortOpt(final char shortOpt) throws ArgumentParserException {
		for (Argument<E> arg : list)
			if (arg.getShortName() == shortOpt)
				return arg;
		throw new ArgumentParserException("option -" + shortOpt + " not recognized", shortOpt);
	}

	List<Argument<E>> findLongOpts(final String longOpt) throws ArgumentParserException {
		final List<Argument<E>> opts = new ArrayList<Argument<E>>();
		for (final Argument<E> arg : list) {
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
}
