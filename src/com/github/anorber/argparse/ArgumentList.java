package com.github.anorber.argparse;

import java.util.ArrayList;
import java.util.List;

class ArgumentList <E> {

	private final List<Argument<? extends E>> list;

	public ArgumentList() {
		list = new ArrayList<Argument<? extends E>>();
	}

	public ArgumentList(ArgumentList<E> other) {
		list = new ArrayList<Argument<? extends E>>(other.list);
	}


	void add(final Argument<? extends E> argument) {
		list.add(argument);
	}

	Argument<? extends E> findShortOpt(final char shortOpt) throws ArgumentParserException {
		for (Argument<? extends E> arg : list)
			if (arg.getShortName() == shortOpt)
				return arg;
		throw new ArgumentParserException("option -" + shortOpt + " not recognized", shortOpt);
	}

	List<Argument<? extends E>> findLongOpts(final String longOpt) throws ArgumentParserException {
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
		return list.hashCode();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArgumentList<?> other = (ArgumentList<?>)obj;
		return list.equals(other.list);
	}
}
