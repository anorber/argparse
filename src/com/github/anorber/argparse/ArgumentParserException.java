package com.github.anorber.argparse;

/**
 * @author anorber
 */
@SuppressWarnings("serial")
public class ArgumentParserException extends Exception {

	private final String opt;

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


@SuppressWarnings("serial")
class ArgumentRequiredException extends ArgumentParserException {

	ArgumentRequiredException(char opt) {
		super("option -" + opt + " requires argument", "-" + opt);
	}

	ArgumentRequiredException(String longopt, String opt) {
		super("option --" + longopt + " requires argument", "--" + opt);
	}
}

@SuppressWarnings("serial")
class ArgumentNotRecognizedException extends ArgumentParserException {

	ArgumentNotRecognizedException(char opt) {
		super("option -" + opt + " not recognized", "-" + opt);
	}

	ArgumentNotRecognizedException(String opt) {
		super("option --" + opt + " not recognized", "--" + opt);
	}
}

@SuppressWarnings("serial")
class ArgumentNotUniqueException extends ArgumentParserException {

	final Argument<?>[] possibilities;

	ArgumentNotUniqueException(String opt, Argument<?>[] possibilities) {
		super("option --" + opt + " not a unique prefix", "--" + opt);
		this.possibilities = possibilities;
	}
}

@SuppressWarnings("serial")
class ArgumentNeedsArgumentException extends ArgumentParserException {

	ArgumentNeedsArgumentException(String opt) {
		super("option --" + opt + " must not have an argument", "--" + opt);
	}
}
