package edu.sanekas.console.impl;

import edu.sanekas.api.Nominal;
import edu.sanekas.api.Operation;
import edu.sanekas.console.api.InputProcesser;
import edu.sanekas.wrapper.api.InputWrapper;
import edu.sanekas.wrapper.api.WrapperFactory;
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
public class InputProcesserTest {

    @SuppressWarnings("SpringJavaAutowiredMembersInspection")
    @Autowired
    private InputProcesser inputProcesser;

    @SuppressWarnings("SpringJavaAutowiredMembersInspection")
    @Autowired
    private WrapperFactory wrapperFactory;

    private InputWrapper expectedInputWrapper;

    @Before
    public void setEnv() {
        expectedInputWrapper = wrapperFactory.createInputWrapper(null);
    }


    @Test
    public void testPutOperationProcessing() {
        expectedInputWrapper.setOperation(Operation.PUT);
        expectedInputWrapper.setNominal(Nominal.FIFTY);
        expectedInputWrapper.setCash(10L);
        InputWrapper expectedInputWrapper = wrapperFactory.createInputWrapper(Operation.PUT, Nominal.FIFTY, 10L);
        Assert.assertTrue(inputProcesser.processInput("put 50 10").equals(expectedInputWrapper));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNotEnoughArgsPutOperationProcessing() {
        inputProcesser.processInput("put 50");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNotArgsPutOperationProcessing() {
        inputProcesser.processInput("put 50");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidNominalPutOperationProcessing() {
        inputProcesser.processInput("put 13 10");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeCashPutOperationProcessing() {
        inputProcesser.processInput("put 10 -10");
    }

    @Test
    public void testGetOperationProcessing() {
        expectedInputWrapper.setOperation(Operation.GET);
        expectedInputWrapper.setNominal(Nominal.ANY);
        expectedInputWrapper.setCash(10L);
        Assert.assertTrue(inputProcesser.processInput("get 10").equals(expectedInputWrapper));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNoArgumentsGetOperationProcessing() {
        Assert.assertTrue(inputProcesser.processInput("get").equals(expectedInputWrapper));
    }

    @Test
    public void testStateOperationProcessing() {
        expectedInputWrapper.setOperation(Operation.STATE);
        Assert.assertTrue(inputProcesser.processInput("state").equals(expectedInputWrapper));
    }

    @Test
    public void testDumpOperationProcessing() {
        expectedInputWrapper.setOperation(Operation.DUMP);
        Assert.assertTrue(inputProcesser.processInput("dump").equals(expectedInputWrapper));
    }

    @Test
    public void testQuitOperationProcessing() {
        expectedInputWrapper.setOperation(Operation.QUIT);
        Assert.assertTrue(inputProcesser.processInput("quit").equals(expectedInputWrapper));
    }
}