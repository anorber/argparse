package com.github.anorber.argparse;

import java.util.ArrayList;
import java.util.List;

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

	List<Argument> findLongOpts(String longOpt) {
		List<Argument> opts = new ArrayList<Argument>();

		for (Argument arg : list) {
			String longName = arg.getLongName();
			if (longName == null)
				continue;
			if (longName.startsWith(longOpt)) {
				opts.add(arg);
			}
		}
		if (opts.isEmpty())
			throw new ArgumentParserException();
		return opts;
	}
}
