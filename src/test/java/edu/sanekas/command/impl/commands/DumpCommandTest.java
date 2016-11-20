package edu.sanekas.command.impl.commands;

import edu.sanekas.TestUtils;
import edu.sanekas.api.Operation;
import edu.sanekas.command.api.Command;
import edu.sanekas.command.api.CommandFactory;
import edu.sanekas.wrapper.api.InputWrapper;
import edu.sanekas.wrapper.api.WrapperFactory;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DumpCommandTest {
    @SuppressWarnings("SpringJavaAutowiredMembersInspection")
    @Autowired
    private WrapperFactory wrapperFactory;

    @SuppressWarnings("SpringJavaAutowiredMembersInspection")
    @Autowired
    private CommandFactory commandFactory;

    private Command dumpCommand;
    private InputWrapper inputWrapper;


    @Before
    public void setEnv() {
        dumpCommand = commandFactory.createCommand(Operation.DUMP);
        inputWrapper = wrapperFactory.createInputWrapper(Operation.PUT);
    }

    @Test
    public void testEmptyCashManipulator() {
        Assert.assertTrue(dumpCommand.execute(inputWrapper).toString().isEmpty());
    }

    @Test
    public void testSmokeDump() {
        TestUtils.putCash(commandFactory, inputWrapper);
        final String expectedDump =
                "5000 5000\n" +
                "1000 1000\n" +
                "500 500\n" +
                "100 100\n" +
                "50 50\n" +
                "25 25\n" +
                "10 10\n" +
                "5 5\n" +
                "3 3\n" +
                "1 1";
        inputWrapper.setOperation(Operation.DUMP);
        Assert.assertEquals(dumpCommand.execute(inputWrapper).toString(), expectedDump);
    }

    @After
    public void removeCash() {
        TestUtils.removeCash(commandFactory, inputWrapper);
    }
}