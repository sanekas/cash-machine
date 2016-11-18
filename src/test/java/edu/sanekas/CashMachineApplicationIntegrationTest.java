package edu.sanekas;

import org.apache.log4j.Logger;
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
public class CashMachineApplicationIntegrationTest {
	private static final Logger LOGGER = Logger.getLogger(CashMachineApplicationIntegrationTest.class);

	private static final URL SYSTEM_IN_FILE;
	private static final URL SYSTEM_OUT_FILE;
	private static final URL SYSTEM_OUT_ETALON;


	static {
		SYSTEM_IN_FILE = Thread.currentThread().getContextClassLoader().getResource("smoke_integrartion_test_in.txt");
		SYSTEM_OUT_FILE = Thread.currentThread().getContextClassLoader().getResource("smoke_integration_test_out.txt");
		SYSTEM_OUT_ETALON = Thread.currentThread().getContextClassLoader().getResource("smoke_integration_test_out_etalon.txt");
		try {
			if (SYSTEM_IN_FILE != null && SYSTEM_OUT_FILE != null) {
				System.setIn(new FileInputStream(SYSTEM_IN_FILE.getPath()));
				System.setOut(new PrintStream(new FileOutputStream(SYSTEM_OUT_FILE.getPath())));
			}
		} catch (Exception e) {
			LOGGER.error("Fail to init IO files", e);
			throw new RuntimeException(e);
		}
	}

	@Test
	public void basicIntegrationTest() throws IOException {
		List<String> out = Files.readAllLines(Paths.get(SYSTEM_OUT_FILE.getPath()));
		List<String> outEtalon = Files.readAllLines(Paths.get(SYSTEM_OUT_ETALON.getPath()));
		List<String> valuableOut = out.subList(11, out.size());
 		Assert.assertTrue(valuableOut.equals(outEtalon));
	}

}
