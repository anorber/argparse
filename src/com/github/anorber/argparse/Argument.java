package com.github.anorber.argparse;


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

	/**
	 * An Argument
	 *
	 * @param name    longopt name
	 * @param hasArg  does this option take an argument
	 * @param id      an enum that identifies this argument
	 */
	public Argument(final String name, final HasArg hasArg, final E id) {
		this(-1, name, hasArg, id);
	}

	/**
	 * An argument
	 *
	 * @param name     shortopt name
	 * @param hasArg  does this option take an argument
	 * @param id       an enum that identifies this argument
	 */
	public Argument(final char name, final HasArg hasArg, final E id) {
		this(name, null, hasArg, id);
	}

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


	char getShortName() {
		return shortName;
	}

	String getLongName() {
		return longName;
	}

	HasArg hasArg() {
		return hasArg;
	}

	E getId() {
		return id;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int i = longName == null ? 0 : longName.hashCode();
		return shortName ^ hasArg.hashCode() ^ id.hashCode() ^ i;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
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
