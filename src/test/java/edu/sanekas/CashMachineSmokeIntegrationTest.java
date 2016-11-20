package edu.sanekas;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CashMachineSmokeIntegrationTest {
	private static final Logger LOGGER = Logger.getLogger(CashMachineSmokeIntegrationTest.class);

	private static final URL SYSTEM_IN_FILE;
	private static final URL SYSTEM_OUT_FILE;
	private static final URL SYSTEM_OUT_ETALON;

	private static final FileInputStream FILE_INPUT_STREAM;
	private static final FileOutputStream FILE_OUTPUT_STREAM;

	static {

		SYSTEM_IN_FILE = getResource("smoke_integrartion_test_in.txt");
		SYSTEM_OUT_FILE = getResource("smoke_integration_test_out.txt");
		SYSTEM_OUT_ETALON = getResource("smoke_integration_test_out_etalon.txt");

		try {
			FILE_INPUT_STREAM = new FileInputStream(SYSTEM_IN_FILE.getPath());
			FILE_OUTPUT_STREAM = new FileOutputStream(SYSTEM_OUT_FILE.getPath());

			System.setIn(FILE_INPUT_STREAM);
			System.setOut(new PrintStream(FILE_OUTPUT_STREAM, true));
			System.setErr(new PrintStream(FILE_OUTPUT_STREAM));

		} catch (Exception e) {
			LOGGER.error("Fail to init IO files", e);
			throw new RuntimeException(e);
		}
	}

	private static URL getResource(String fileName) {
		return Thread.currentThread().getContextClassLoader().getResource(fileName);
	}

	@Test
	public void smokeIntegrationTest() throws IOException {
		List<String> out = Files.readAllLines(Paths.get(SYSTEM_OUT_FILE.getPath()));
		List<String> outEtalon = Files.readAllLines(Paths.get(SYSTEM_OUT_ETALON.getPath()));
		List<String> valuableOut = out.subList(out.size() - 11, out.size()); // last 11 strings are CashMachine output
 		Assert.assertTrue(valuableOut.equals(outEtalon));
	}

	@After
	public void closeStreams() throws IOException {
		FILE_INPUT_STREAM.close();
		FILE_OUTPUT_STREAM.close();
	}

}
