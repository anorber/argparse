package examples;

import static com.github.anorber.argparse.HasArg.*;
import com.github.anorber.argparse.*;

public class Example {

	public static void main(String[] args) throws ArgumentParserException {
		ArgumentParser<String> parser = new ArgumentParser<String>();
		parser.addArgument(new Argument<String>("-a",      'a',     REQUIRED_ARGUMENT));
		parser.addArgument(new Argument<String>("-b",      'b'));
		parser.addArgument(new Argument<String>("--alpha", "alpha", REQUIRED_ARGUMENT));
		parser.addArgument(new Argument<String>("--beta",  "beta"));

		args = parser.parse(args);

		System.out.println("opts:");
		for (Option<String> opt : parser) {
			System.out.print("   " + opt.getId());
			String arg = opt.getArgument();
//			if (arg != null)
				System.out.print(" " + arg);
			System.out.println();
		}
		System.out.println("args:");
		for (String arg : args)
			System.out.println("   " + arg);
	}
}
