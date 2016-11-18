package edu.sanekas;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CashMachineApplicationIntegrationTest {
	private static final Logger LOGGER = Logger.getLogger(CashMachineApplicationIntegrationTest.class);

	private static final String SYSTEM_IN_FILE = "src/test/resources/quit_test_in.txt";
	private static final String SYSTEM_OUT_FILE = "src/test/resources/quit_test_out.txt";
	private static final String SYSTEM_OUT__ETALON = "src/test/resources/test_out_etalon.txt";


	static {
		try {
			System.setIn(new FileInputStream(SYSTEM_IN_FILE));
			System.setOut(new PrintStream(new FileOutputStream(SYSTEM_OUT_FILE)));
		} catch (FileNotFoundException e) {
			LOGGER.error("Fail to init IO files", e);
			throw new RuntimeException(e);
		}
	}

	@Test
	public void quitTest() throws IOException {
		List<String> out = Files.readAllLines(Paths.get(SYSTEM_OUT_FILE));
		List<String> outEtalon = Files.readAllLines(Paths.get(SYSTEM_OUT__ETALON));
		List<String> valuableOut = out.subList(11, out.size());
 		Assert.assertTrue(valuableOut.equals(outEtalon));
	}

}
