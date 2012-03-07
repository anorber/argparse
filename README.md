Command-line parsing library
============================

Java parser for command line options. It supports similar conventions
as the Unix getopt() function (including the special meanings of arguments
of the form "-" and "--"). Long options similar to those supported by GNU
software may be used as well.

Example usage:
--------------

```Java
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
			System.out.println("   " + opt.getId() + " " + opt.getArgument());
		}
		System.out.println("args:");
		for (String arg : args) {
			System.out.println("   " + arg);
		}
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
