package com.github.anorber.argparse;

/**
 *
 * @author anorber
 */
@SuppressWarnings("serial")
public class ArgumentParserException extends Exception {

	private final String opt;

	/**
	 * Indicates that the ArgumentParser could not parse the args.
	 *
	 * @param message  error message
	 * @param opt      the shortopt involved in the error
	 */
	public ArgumentParserException(final String message, final char opt) {
		super(message);
		this.opt = "" + opt;
	}

	/**
	 * Indicates that the ArgumentParser could not parse the args.
	 *
	 * @param message  error message
	 * @param opt      the longopt involved in the error
	 */
	public ArgumentParserException(final String message, final String opt) {
		super(message);
		this.opt = opt;
	}

	/**
	 * @return the opt
	 */
	public String getOpt() {
		return opt;
	}
}
