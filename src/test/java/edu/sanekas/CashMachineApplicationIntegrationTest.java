package edu.sanekas;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CashMachineApplicationIntegrationTest {
	private static final Logger LOGGER = Logger.getLogger(CashMachineApplicationIntegrationTest.class);

	private static final String SYSTEM_IN_FILE = "src/test/resources/quit_test_in.txt";
	private static final String SYSTEM_OUT_FILE = "src/test/resources/quit_test_out.txt";

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
	public void quitTest() {

	}

}
