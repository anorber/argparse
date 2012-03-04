package com.github.anorber.argparse;


public class Argument <E extends Enum<?>> {

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
		this('\0', name, hasArg, id);
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

	private Argument(final char shortName, final String longName, final HasArg takesArgument, final E id) {
		if (takesArgument == null)
			throw new NullPointerException();
		if (id == null)
			throw new NullPointerException();
		this.shortName = shortName;
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
		final int i = longName != null ? longName.hashCode() : 0;
		return shortName ^ hasArg.hashCode() ^ id.hashCode() ^ i;
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
		if (!(obj instanceof Argument))
			return false;
		Argument<?> other = (Argument<?>)obj;
		if (hasArg != other.hasArg)
			return false;
		if (!id.equals(other.id))
			return false;
		if (longName == null) {
			if (other.longName != null)
				return false;
		} else if (!longName.equals(other.longName))
			return false;
		return shortName == other.shortName;
	}
}
