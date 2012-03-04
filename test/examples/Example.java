package examples;

import static com.github.anorber.argparse.HasArg.NO_ARGUMENT;
import static com.github.anorber.argparse.HasArg.REQUIRED_ARGUMENT;
import static examples.Example.Op.A;
import static examples.Example.Op.ALPHA;
import static examples.Example.Op.B;
import static examples.Example.Op.BETA;

import com.github.anorber.argparse.*;

public class Example {

	static enum Op { A, B, ALPHA, BETA }

	public static void main(String[] args) throws ArgumentParserException {
		ArgumentParser<Op> parser = new ArgumentParser<Op>();
		parser.addArgument(new Argument<Op>('a', REQUIRED_ARGUMENT, A));
		parser.addArgument(new Argument<Op>('b', NO_ARGUMENT, B));
		parser.addArgument(new Argument<Op>("alpha", REQUIRED_ARGUMENT, ALPHA));
		parser.addArgument(new Argument<Op>("beta", NO_ARGUMENT, BETA));

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
			case ALPHA:
				System.out.println("   --alpha " + opt.getArgument());
				break;
			case BETA:
				System.out.println("   --beta ");
				break;
			}
		}

		System.out.println("args:");
		for (String arg : args)
			System.out.println("   " + arg);
	}
}
