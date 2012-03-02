package com.github.anorber.argparse;

public class ArgumentParserException extends Exception {

	private static final long serialVersionUID = -4444608085134488658L;

	/**
	 * Indicates that the ArgumentParser could not parse the args
	 *
	 * @param message  error message
	 * @param opt      the shortopt involved in the error
	 */
	public ArgumentParserException(String message, char opt) {
		super(message);
	}

	/**
	 * Indicates that the ArgumentParser could not parse the args
	 *
	 * @param message  error message
	 * @param opt      the longopt involved in the error
	 */
	public ArgumentParserException(String message, String opt) {
		super(message);
	}
}
