package com.github.anorber.argparse;

import static com.github.anorber.argparse.HasArg.NO_ARGUMENT;

/**
 * @author anorber
 *
 * @param <E>
 */
public class Argument <E> {

	private final String longName;
	private final char shortName;

	private final HasArg hasArg;
	private final E id;

	private Argument(final int shortName, final String longName, final HasArg takesArgument, final E id) {
		if (longName == null && shortName == -1) {
			throw new IllegalArgumentException("name should not be null");
		}
		if (takesArgument == null) {
			throw new IllegalArgumentException("takesArgument should not be null");
		}
		if (id == null) {
			throw new IllegalArgumentException("id should not be null");
		}
		this.shortName = (char) shortName;
		this.longName = longName;
		this.hasArg = takesArgument;
		this.id = id;
	}

	/**
	 */
	public Argument(final E id, String longopt) {
		this(-1, longopt, NO_ARGUMENT, id);
	}

	/**
	 */
	public Argument(final E id, char shortopt) {
		this(shortopt, null, NO_ARGUMENT, id);
	}

	/**
	 */
	public Argument(final E id, char shortopt, String longopt) {
		this(shortopt, longopt, NO_ARGUMENT, id);
	}

	/**
	 */
	public Argument(final E id, String longopt, HasArg hasArg) {
		this(-1, longopt, hasArg, id);
	}

	/**
	 */
	public Argument(final E id, char shortopt, HasArg hasArg) {
		this(shortopt, null, hasArg, id);
	}

	/**
	 */
	public Argument(final E id, char shortopt, String longopt, HasArg hasArg) {
		this(shortopt, longopt, hasArg, id);
	}


	char getShortName() {
		return shortName;
	}

	String getLongName() {
		if (longName == null)
			return "";
		return longName;
	}

	HasArg hasArg() {
		return hasArg;
	}

	E getId() {
		return id;
	}


	/* @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int i = longName == null ? 0 : longName.hashCode();
		return shortName ^ hasArg.hashCode() ^ id.hashCode() ^ i;
	}

	/* @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (obj == null || !(obj instanceof Argument)) {
			return false;
		}
		final Argument<?> other = (Argument<?>)obj;
		if (longName == null) {
			if (other.longName != null) {
				return false;
			}
		} else if (!longName.equals(other.longName)) {
			return false;
		}
		return shortName == other.shortName && hasArg == other.hasArg && id.equals(other.id);
	}
}
