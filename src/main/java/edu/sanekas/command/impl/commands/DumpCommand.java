package edu.sanekas.command.impl.commands;

import edu.sanekas.api.Nominal;
import edu.sanekas.command.api.Command;
import edu.sanekas.wrapper.api.OutputWrapper;

import java.util.Map;

public class DumpCommand implements Command {
    @Override
    public OutputWrapper execute(Map<Nominal, Integer> commandOptions) {
        return null;
    }
}
