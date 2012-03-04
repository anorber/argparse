package examples;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;

import com.github.anorber.argparse.ArgumentParserException;

public class ExampleTest {

	@Rule
	public StandardOutputStreamLog log = new StandardOutputStreamLog();

	@Test
	public void testExample() throws ArgumentParserException {
		//given
		String[] args = new String[] {
				"-bacon",
				"--a", "one",
				"--bet",
				"--",
				"-a",
				"foo"
		};
		String expected = "" +
				"opts:\n" +
				"   -b\n" +
				"   -a con\n" +
				"   --alpha one\n" +
				"   --beta\n" +
				"args:\n" +
				"   -a\n" +
				"   foo\n";

		//when
		Example.main(args);

		//then
		assertThat(log.getLog(), is(expected));
	}

}