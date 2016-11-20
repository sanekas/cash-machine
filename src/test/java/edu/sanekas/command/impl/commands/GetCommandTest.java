package edu.sanekas.command.impl.commands;

import edu.sanekas.TestUtils;
import edu.sanekas.api.Operation;
import edu.sanekas.command.api.Command;
import edu.sanekas.command.api.CommandFactory;
import edu.sanekas.wrapper.api.InputWrapper;
import edu.sanekas.wrapper.api.WrapperFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//TODO: Implement unit tests
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class GetCommandTest {

    @SuppressWarnings("SpringJavaAutowiredMembersInspection")
    @Autowired
    private WrapperFactory wrapperFactory;

    @SuppressWarnings("SpringJavaAutowiredMembersInspection")
    @Autowired
    private CommandFactory commandFactory;

    private Command getCommand;
    private InputWrapper inputWrapper;

    @Before
    public void setEnv() {
        getCommand = commandFactory.createCommand(Operation.GET);
        inputWrapper = wrapperFactory.createInputWrapper(Operation.PUT);
    }

    @Test
    public void emptyTest() {

    }

    @After
    public void removeCash() {
        TestUtils.removeCash(commandFactory, inputWrapper);
    }

}