package com.github.anorber.argparse;

@SuppressWarnings("serial")
public class ArgumentParserException extends RuntimeException {

	ArgumentParserException() {
	}

	/**TODO
	 *
	 * @param message
	 * @param opt
	 */
	public ArgumentParserException(String message, char opt) {
		super(message);
	}

	/**TODO
	 *
	 * @param message
	 * @param opt
	 */
	public ArgumentParserException(String message, String opt) {
		super(message);
	}
}
