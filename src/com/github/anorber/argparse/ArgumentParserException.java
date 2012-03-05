package com.github.anorber.argparse;

/**
 *
 * @author anorber
 */
public class ArgumentParserException extends Exception {

	private static final long serialVersionUID = -4444608085134488658L;

	/**
	 * Indicates that the ArgumentParser could not parse the args.
	 *
	 * @param message  error message
	 * @param opt      the shortopt involved in the error
	 */
	public ArgumentParserException(final String message, final char opt) {
		super(message);
	}

	/**
	 * Indicates that the ArgumentParser could not parse the args.
	 *
	 * @param message  error message
	 * @param opt      the longopt involved in the error
	 */
	public ArgumentParserException(final String message, final String opt) {
		super(message);
	}
}
