package com.github.anorber.argparse;

import java.util.ArrayList;
import java.util.List;

class ArgumentList {

	private List<Argument> list = new ArrayList<Argument>();

	void add(Argument argument) {
		list.add(argument);
	}

	boolean shortOptTakesArgument(char shortOpt) {
		for (Argument arg : list) {
			if (arg.getShortName() == shortOpt) {
				return arg.takesArgument();
			}
		}
		throw new ArgumentParserException();
	}

	boolean longOptTakesArgument(String longOpt) {
		for (Argument arg : list) {
			if (longOpt.equals("--" + arg.getLongName())) {
				return arg.takesArgument();
			}
		}
		throw new ArgumentParserException();
	}
}
