package com.github.anorber.argparse;

public class Option <E> {

	final private String arg;
	final private E id;

	Option(final E id, final String argument) {
		if (id == null)
			throw new IllegalArgumentException("id should not be null");
		this.id = id;
		this.arg = argument;
	}

	/**
	 * @return the id
	 */
	public E getId() {
		return id;
	}

	/**
	 * @return the argument
	 */
	public String getArgument() {
		return arg;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return (arg == null ? 0 : arg.hashCode()) ^ id.hashCode();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Option<?> other = (Option<?>)obj;
		if (arg == null) {
			if (other.arg != null)
				return false;
		} else if (!arg.equals(other.arg))
			return false;
		return id.equals(other.id);
	}
}
