package edu.sanekas.cashmachine;

import edu.sanekas.api.Operation;
import edu.sanekas.cashmachine.api.CashMachine;
import edu.sanekas.wrapper.api.InputWrapper;
import edu.sanekas.wrapper.api.OutputWrapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class CashMachineFacade implements ApplicationRunner {
    private Logger LOGGER = Logger.getLogger(CashMachineFacade.class);

    private final CashMachine cashMachine;

    @Autowired
    public CashMachineFacade(CashMachine cashMachine) {
        this.cashMachine = cashMachine;
    }

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        do {
            try {
                InputWrapper inputWrapper = cashMachine.processInput();
                if (inputWrapper.getOperation() == Operation.QUIT) {
                    break;
                }
                OutputWrapper outputWrapper = cashMachine.executeCommand(inputWrapper);
                cashMachine.processOutput(outputWrapper);
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
                LOGGER.error(e);
            }
        } while (true);
    }
}
