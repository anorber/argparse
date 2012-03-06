package com.github.anorber.argparse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author anorber
 *
 * @param <E>
 */
public class ArgumentParser<E> implements Iterable<Option<E>> {

	private final ArgumentList<E> arguments;
	private final FoundOpts<E> foundOpts;

	/**
	 */
	public ArgumentParser() {
		foundOpts = new FoundOpts<E>();
		arguments = new ArgumentList<E>(foundOpts);
	}

	/**
	 * @param other  an ArgumentParser to clone
	 */
	public ArgumentParser(final ArgumentParser<E> other) {
		foundOpts = new FoundOpts<E>(other.foundOpts);
		arguments = new ArgumentList<E>(other.arguments, foundOpts);
	}

	/**
	 * Adds an argument to this parser.
	 *
	 * @param argument  an option argument
	 */
	public void addArgument(final Argument<? extends E> argument) {
		if (argument == null) {
			throw new IllegalArgumentException("argument should not be null");
		}
		arguments.add(argument);
	}

	/**
	 * Parses args for opts.
	 *
	 * @param args  the args to be parsed
	 * @return      the rest of the args after that the opts was parsed
	 *               or null if args was null
	 * @throws ArgumentParserException if somthing goes wrong
	 */
	public String[] parse(final String[] args) throws ArgumentParserException {
		if (args == null) {
			return null;
		}
		final int pos = arguments.parseOpts(args);
		return Arrays.copyOfRange(args, pos, args.length);
	}

	/**
	 * Tells if this parser has seen <code>option</code>.
	 *
	 * @param option  enum representing an option
	 * @return        true if this parser found the option
	 */
	public boolean hasOption(final E option) {
		return foundOpts.containsOption(option);
	}

	/*
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public Iterator<Option<E>> iterator() {
		return foundOpts.getIterator();
	}

	/**
	 * Returns an array with the arguments given to this option.
	 *
	 * @param option  enum representing an option
	 * @return        the arguments in the order they appeared
	 */
	public String[] getArguments(final E option) {
		final List<String> opt = foundOpts.getArgs(option);
		if (opt == null) {
			return null;
		}
		return opt.toArray(new String[opt.size()]);
	}

	/**
	 * Returns all arguments for an option as an array. All strings are split
	 * at the delimiter character.
	 *
	 * @param option     the option who's args we want
	 * @param delimiter  character to use for splitting argument strings
	 * @return           string array of arguments
	 */
	public String[] getArguments(final E option, final char delimiter) {
		final List<String> buf = new ArrayList<String>();
		final List<String> options = foundOpts.getArgs(option);
		if (options == null) {
			return new String[0];
		}
		for (String arg : options) {
			for (String substr : arg.split("\\" + delimiter)) {
				buf.add(substr);
			}
		}
		return buf.toArray(new String[buf.size()]);
	}

	/**
	 * Returns all arguments for an option as a string.
	 *
	 * @param option     the option who's args we want
	 * @param delimiter  character to insert between arguments
	 * @return           string of all arguments
	 */
	public String getArgumentsString(final E option, final char delimiter) {
		final String[] args = getArguments(option);
		if (args == null) {
			return "";
		}
		final StringBuilder buf = new StringBuilder(args[0]);
		for (int i = 1; i < args.length; ++i) {
			buf.append(delimiter).append(args[i]);
		}
		return buf.toString();
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return arguments.hashCode() ^ foundOpts.hashCode();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		return obj instanceof ArgumentParser
				&& arguments.equals(((ArgumentParser<?>) obj).arguments)
				&& foundOpts.equals(((ArgumentParser<?>) obj).foundOpts);
	}
}
