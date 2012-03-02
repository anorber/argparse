package examples;

import com.github.anorber.argparse.*;

public class Example {

	static enum Op { A, B, Alpha, Beta }

	public static void main(String[] args) throws ArgumentParserException {
		ArgumentParser<Op> parser = new ArgumentParser<Op>();
		parser.addArgument(new Argument<Op>('a', true, Op.A));
		parser.addArgument(new Argument<Op>('b', false, Op.B));
		parser.addArgument(new Argument<Op>("alpha", true, Op.Alpha));
		parser.addArgument(new Argument<Op>("beta", false, Op.Beta));

		args = parser.parse(args);

		System.out.println("opts:");
		for (Option<Op> opt : parser) {
			switch (opt.getId()) {
			case A:
				System.out.println("   -a " + opt.getArgument());
				break;
			case B:
				System.out.println("   -b ");
				break;
			case Alpha:
				System.out.println("   --alpha " + opt.getArgument());
				break;
			case Beta:
				System.out.println("   --beta ");
				break;
			}
		}

		System.out.println("args:");
		for (String arg : args)
			System.out.println("   " + arg);
	}
}
