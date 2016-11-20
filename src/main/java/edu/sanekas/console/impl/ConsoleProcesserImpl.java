package edu.sanekas.console.impl;

import edu.sanekas.api.GeneralDefaults;
import edu.sanekas.console.api.ConsoleProcesser;
import edu.sanekas.wrapper.api.OutputWrapper;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class ConsoleProcesserImpl implements ConsoleProcesser{
    private static final Logger LOGGER = Logger.getLogger(ConsoleProcesserImpl.class);

    private BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

    @Nullable
    @Override
    public String readInput() {
        String input;
        printPrefix();
        try {
            input = console.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return input;
    }

    @Nullable
    @Override
    public String printOutput(OutputWrapper outputWrapper) {
        if (outputWrapper != null && !outputWrapper.toString().isEmpty()) {
            printString(outputWrapper.toString());
            return outputWrapper.toString();
        } else {
            return null;
        }
    }

    @Override
    public void closeInputStream() {
        try {
            console.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String printError(Throwable throwable) {
        System.out.println(throwable.getMessage());
        return throwable.toString();
    }

    private void printString(String s) {
        System.out.println(s);
    }

    private void printPrefix() {
        System.out.print(GeneralDefaults.PREFIX);
    }

}
