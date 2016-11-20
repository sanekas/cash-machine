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
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ActiveProfiles("test")
public class StateCommandTest {

    @SuppressWarnings("SpringJavaAutowiredMembersInspection")
    @Autowired
    private WrapperFactory wrapperFactory;

    @SuppressWarnings("SpringJavaAutowiredMembersInspection")
    @Autowired
    private CommandFactory commandFactory;

    private Command stateCommand;
    private InputWrapper inputWrapper;


    @Before
    public void setEnv() {
        stateCommand = commandFactory.createCommand(Operation.STATE);
        inputWrapper = wrapperFactory.createInputWrapper(Operation.PUT);
    }

    @Test
    public void testEmptyCashManipulator() {
        Assert.assertEquals("0", stateCommand.execute(inputWrapper).toString());
    }

    @Test
    public void testSmokeState() {
        TestUtils.putCash(commandFactory, inputWrapper);
        final long expectedState = 26263260L;
        inputWrapper.setOperation(Operation.STATE);
        Assert.assertEquals(String.valueOf(expectedState), stateCommand.execute(inputWrapper).toString());
    }

    @After
    public void removeCash() {
        TestUtils.removeCash(commandFactory, inputWrapper);
    }
}