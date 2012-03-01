package com.github.anorber.argparse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class ArgumentList {

	private List<Argument> list = new ArrayList<Argument>();

	void add(Argument argument) {
		list.add(argument);
	}

	Argument findShortOpt(char shortOpt) {
		for (Argument arg : list) {
			if (arg.getShortName() == shortOpt) {
				return arg;
			}
		}
		throw new ArgumentParserException();
	}

	boolean longOptTakesArgument(String longOpt, Map<Enum<?>, String> opts) {
		for (Argument arg : list) {
			if (longOpt.equals("--" + arg.getLongName())) {
				opts.put(arg.getId(), null);
				return arg.takesArgument();
			}
		}
		throw new ArgumentParserException();
	}
}
