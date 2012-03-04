package com.github.anorber.argparse;

public class Option <E> {

	final private String argument;
	final private E id;

	Option(final E id, final String argument) {
		if (id == null)
			throw new NullPointerException();
		this.id = id;
		this.argument = argument;
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
		return argument;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return (argument != null ? argument.hashCode() : 0) ^ id.hashCode();
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
		Option<?> other = (Option<?>)obj;
		if (argument == null) {
			if (other.argument != null)
				return false;
		} else if (!argument.equals(other.argument))
			return false;
		return id.equals(other.id);
	}
}
