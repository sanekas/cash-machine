package edu.sanekas.command.impl.commands;

import edu.sanekas.api.Nominal;
import edu.sanekas.api.Operation;
import edu.sanekas.command.api.Command;
import edu.sanekas.command.api.CommandFactory;
import edu.sanekas.wrapper.api.InputWrapper;
import edu.sanekas.wrapper.api.WrapperFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class PutCommandTest {

    @SuppressWarnings("SpringJavaAutowiredMembersInspection")
    @Autowired
    private WrapperFactory wrapperFactory;

    @SuppressWarnings("SpringJavaAutowiredMembersInspection")
    @Autowired
    private CommandFactory commandFactory;

    private Command putCommand;
    private InputWrapper inputWrapper;

    @Before
    public void setEnv() {
        putCommand = commandFactory.createCommand(Operation.PUT);
        inputWrapper = wrapperFactory.createInputWrapper(Operation.PUT, Nominal.FIVE_THOUSAND, 10L);
    }

    @Test
    public void putTest() {
        final long expectedState = 50000L;
        Assert.assertEquals(String.valueOf(expectedState), putCommand.execute(inputWrapper).toString());
    }

    @After
    public void removeCash() {
        inputWrapper.setOperation(Operation.GET);
        inputWrapper.setNominal(Nominal.ANY);
        inputWrapper.setCash(50000L);
        commandFactory.createCommand(Operation.GET).execute(inputWrapper);
    }
}