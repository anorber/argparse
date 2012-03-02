Command-line parsing library
============================

Java parser for command line options. It supports similar conventions
as the Unix getopt() function (including the special meanings of arguments
of the form "-" and "--"). Long options similar to those supported by GNU
software may be used as well.

Example usage:
--------------

```Java
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
```

###Run with:
```
java Example -bacon --a one --bet -- -a foo
```

###Prints:
```
opts:
   -b 
   -a con
   --alpha one
   --beta 
args:
   -a
   foo
```
